package net.etfbl.indeks.service;


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

    public void addNewPrvateGroupChat(PrivateGroupChat group) {
        groupRepository.save(group.getGroupChat());
        privateGroupChatRepository.save(group);
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
        GroupChat group = groupRepository.findById(groupId).orElseThrow(() -> new IllegalStateException("group doesn't exist"));
        if(groupName!=null &&
                groupName.length()>0 &&
                !Objects.equals(group.getName(), groupName)){
            group.setName(groupName);
            return true;
        }

        return false;
    }
}
