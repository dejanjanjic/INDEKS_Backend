package net.etfbl.indeks.service;


import net.etfbl.indeks.dto.AddElementaryGroupChatDTO;
import net.etfbl.indeks.dto.AddPrivateGroupChatDTO;
import net.etfbl.indeks.model.ElementaryGroupChat;
import net.etfbl.indeks.model.GroupChat;
import net.etfbl.indeks.model.PrivateGroupChat;
import net.etfbl.indeks.repository.GroupRepository;
import net.etfbl.indeks.repository.PrivateGroupChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PrivateGroupChatService {
    private final PrivateGroupChatRepository privateGroupChatRepository;
    private final GroupRepository groupRepository;

    @Autowired
    PrivateGroupChatService(PrivateGroupChatRepository privateGroupChatRepository,GroupRepository groupRepository) {
        this.privateGroupChatRepository = privateGroupChatRepository;
        this.groupRepository=groupRepository;
    }

    public List<PrivateGroupChat> getPrivateGroupChats() {
        return privateGroupChatRepository.findAll();
    }

    public Optional<PrivateGroupChat> getGroup(Long groupId) {
        return privateGroupChatRepository.findById(groupId);
    }

    @Transactional
    public PrivateGroupChat addNewPrivateGroupChat(AddPrivateGroupChatDTO group) {
        return privateGroupChatRepository.save(new PrivateGroupChat(groupRepository.save(new GroupChat(group.getName()))));
    }


    public boolean deleteGroup(Long groupId) {
        boolean exists = privateGroupChatRepository.existsById(groupId);
        if (!exists) {
           return false;
        }
        privateGroupChatRepository.deleteById(groupId);
        groupRepository.deleteById(groupId);
        return exists;
    }

    @Transactional
    public boolean updatePrivateChatGroup(Long groupId, String groupName) {
        Optional<GroupChat> group = groupRepository.findById(groupId);
        if(group.isEmpty())
        {
            return false;
        }
        group.get().setName(groupName);
        return true;
    }
}
