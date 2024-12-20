package net.etfbl.indeks.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import net.etfbl.indeks.dto.AddSingleChatDTO;
import net.etfbl.indeks.dto.GetMessageDTO;
import net.etfbl.indeks.dto.LastMessageInfo;
import net.etfbl.indeks.dto.SingleChatSummaryDTO;
import net.etfbl.indeks.model.*;
import net.etfbl.indeks.repository.SingleChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SingleChatService {

    @Autowired
    private EntityManager entityManager;
    private final SingleChatRepository singleChatRepository;

    @Autowired
    public SingleChatService(SingleChatRepository singleChatRepository) {
        this.singleChatRepository = singleChatRepository;
    }

    public List<SingleChat> getSingleChats() {
        return singleChatRepository.findAll();
    }

    public Optional<SingleChat> getSingleChatById(Long id) {
        return singleChatRepository.findById(id);
    }

    @Transactional
    public SingleChat addNewSingleChat(AddSingleChatDTO singleChatDTO) {


        UserAccount firstParticipant = Optional.ofNullable(entityManager.find(UserAccount.class, singleChatDTO.getFirstParticipantId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "First participant not found"));


        UserAccount secondParticipant = Optional.ofNullable(entityManager.find(UserAccount.class, singleChatDTO.getSecondParticipantId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Second participant not found"));


        SingleChat newSingleChat = new SingleChat(firstParticipant, secondParticipant);
        entityManager.persist(newSingleChat);

        return newSingleChat;
    }


    @Transactional
    public List<GetMessageDTO> getMessagesFromChat(Long chatId, Long userId) {


        SingleChat chat = entityManager.find(SingleChat.class, chatId);
        if (chat == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found");
        }


        UserAccount user = entityManager.find(UserAccount.class, userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }


        String queryStr = "SELECT m FROM Message m WHERE m.singleChat.id = :chatId ORDER BY m.time ASC";
        TypedQuery<Message> query = entityManager.createQuery(queryStr, Message.class);
        query.setParameter("chatId", chatId);
        List<Message> messages = query.getResultList();


        return messages.stream().map(message -> {
            boolean isSentByUser = message.getUserAccount().getId().equals(user.getId());
            return new GetMessageDTO(
                    String.valueOf(message.getId()),
                    message.getText(),
                    message.getTime(),
                    isSentByUser
            );
        }).collect(Collectors.toList());
    }

    @Transactional
    public List<SingleChatSummaryDTO> getAllChatsWithLastMessage(Long userId) {

        UserAccount user = entityManager.find(UserAccount.class, userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }


        String singleChatQueryStr = "SELECT c FROM SingleChat c WHERE c.firstParticipant = :user OR c.secondParticipant = :user";
        TypedQuery<SingleChat> singleChatQuery = entityManager.createQuery(singleChatQueryStr, SingleChat.class);
        singleChatQuery.setParameter("user", user);
        List<SingleChat> singleChats = singleChatQuery.getResultList();


        String elementaryGroupChatQueryStr = "SELECT egc FROM ElementaryGroupChat egc WHERE EXISTS (SELECT 1 FROM ElementaryGroupChatMember egcm WHERE egcm.elementaryGroupChat.id = egc.id AND egcm.studentAccount.id = :userId)";
        TypedQuery<ElementaryGroupChat> elementaryGroupChatQuery = entityManager.createQuery(elementaryGroupChatQueryStr, ElementaryGroupChat.class);
        elementaryGroupChatQuery.setParameter("userId", userId);
        List<ElementaryGroupChat> elementaryGroupChats = elementaryGroupChatQuery.getResultList();


        String privateGroupChatQueryStr = "SELECT pgc FROM PrivateGroupChat pgc WHERE EXISTS (SELECT 1 FROM PrivateGroupChatMember pgcm WHERE pgcm.privateGroupChat.id = pgc.id AND pgcm.userAccount.id = :userId)";
        TypedQuery<PrivateGroupChat> privateGroupChatQuery = entityManager.createQuery(privateGroupChatQueryStr, PrivateGroupChat.class);
        privateGroupChatQuery.setParameter("userId", userId);
        List<PrivateGroupChat> privateGroupChats = privateGroupChatQuery.getResultList();

        List<SingleChatSummaryDTO> chatSummaries = new ArrayList<>();


        for (SingleChat chat : singleChats) {
            LastMessageInfo lastMessageInfo = getLastMessageFromChat(chat.getId());
            UserAccount otherParticipant = chat.getFirstParticipant().equals(user) ? chat.getSecondParticipant() : chat.getFirstParticipant();
            chatSummaries.add(new SingleChatSummaryDTO(
                    String.valueOf(chat.getId()),
                    otherParticipant.getFirstName() + " " + otherParticipant.getLastName(),
                    lastMessageInfo.getSender(),
                    lastMessageInfo.getMessage(),
                    false
            ));
        }


        for (ElementaryGroupChat chat : elementaryGroupChats) {
            LastMessageInfo lastMessageInfo = getLastMessageFromChat(chat.getId());
            String groupName = chat.getGroupChat().getName();
            chatSummaries.add(new SingleChatSummaryDTO(
                    String.valueOf(chat.getId()),
                    groupName,
                    lastMessageInfo.getSender(),
                    lastMessageInfo.getMessage(),
                    true
            ));
        }


        for (PrivateGroupChat chat : privateGroupChats) {
            LastMessageInfo lastMessageInfo = getLastMessageFromChat(chat.getId());
            String groupName = "Private Group";
            chatSummaries.add(new SingleChatSummaryDTO(
                    String.valueOf(chat.getId()),
                    groupName,
                    lastMessageInfo.getSender(),
                    lastMessageInfo.getMessage(),
                    true
            ));
        }

        return chatSummaries;
    }


    private LastMessageInfo getLastMessageFromChat(Long chatId) {

        String messageQueryStr = "SELECT m FROM Message m WHERE m.singleChat.id = :chatId OR m.groupChat.id=:chatId ORDER BY m.time DESC";
        TypedQuery<Message> messageQuery = entityManager.createQuery(messageQueryStr, Message.class);
        messageQuery.setParameter("chatId", chatId);
        messageQuery.setMaxResults(1);
        List<Message> messages = messageQuery.getResultList();

        if (messages.isEmpty()) {
            return new LastMessageInfo("", "");
        }

        Message lastMessage = messages.get(0);
        String messageText = lastMessage.getText();
        UserAccount sender = lastMessage.getUserAccount();

        return new LastMessageInfo(messageText, sender.getFirstName() + " " + sender.getLastName());
    }



    public boolean deleteSingleChat(Long id) {
        boolean exists = singleChatRepository.existsById(id);
        if (exists) {
            singleChatRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
