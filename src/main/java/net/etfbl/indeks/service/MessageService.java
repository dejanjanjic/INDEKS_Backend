package net.etfbl.indeks.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import net.etfbl.indeks.dto.AddMessageDTO;
import net.etfbl.indeks.model.*;
import net.etfbl.indeks.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private EntityManager entityManager;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    @Transactional
    public Message addNewMessage(AddMessageDTO addMessageDTO)
    {

        UserAccount sender = entityManager.find(UserAccount.class, addMessageDTO.getUserAccountId());
        if (sender == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender not found");
        }

        SingleChat singleChat = null;
        GroupChat groupChat = null;

        if (addMessageDTO.getSingleChatId() != null) {
            singleChat = entityManager.find(SingleChat.class, addMessageDTO.getSingleChatId());

        }
        if (addMessageDTO.getGroupChatId()!=null) {
            groupChat = entityManager.find(GroupChat.class, addMessageDTO.getGroupChatId());
        }

        Message message = new Message(
                addMessageDTO.getText(),
                singleChat,
                groupChat,
                addMessageDTO.getStatus(),
                sender
        );

        entityManager.persist(message);

        return message;
    }



    public boolean deleteMessage(Long id) {
        boolean exists = messageRepository.existsById(id);
        if (exists) {
            messageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateMessage(Long id, String text) {
        Optional<Message> message = messageRepository.findById(id);
        if(message.isPresent() && !text.isEmpty() && !message.get().getText().equals(text)) {
            message.get().setText(text);
            return true;
        }
        return false;
    }
}
