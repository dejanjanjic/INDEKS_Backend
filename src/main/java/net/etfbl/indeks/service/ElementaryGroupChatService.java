package net.etfbl.indeks.service;



import net.etfbl.indeks.model.ElementaryGroupChat;
import net.etfbl.indeks.model.GroupChat;

import net.etfbl.indeks.repository.ElementaryGroupChatRepository;
import net.etfbl.indeks.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ElementaryGroupChatService {
    private final ElementaryGroupChatRepository elementaryGroupChatRepository;
    private final GroupRepository groupRepository;

    @Autowired
    ElementaryGroupChatService(ElementaryGroupChatRepository elementaryGroupChatRepository,GroupRepository groupRepository) {
        this.elementaryGroupChatRepository = elementaryGroupChatRepository;
        this.groupRepository=groupRepository;
    }

    public List<ElementaryGroupChat> getElementaryGroupChats() {
        return elementaryGroupChatRepository.findAll();
    }

    public Optional<ElementaryGroupChat> getGroup(Long groupId) {
        return elementaryGroupChatRepository.findById(groupId);
    }

    public void addNewElementaryGroupChat(ElementaryGroupChat group) {
        groupRepository.save(group.getGroupChat());
        elementaryGroupChatRepository.save(group);
    }

    public void deleteGroup(Long groupId) {
        boolean exists = elementaryGroupChatRepository.existsById(groupId);
        if (!exists) {
            throw new IllegalStateException("group doesn't exist");
        }
        elementaryGroupChatRepository.deleteById(groupId);
        groupRepository.deleteById(groupId);
    }

    @Transactional
    public void updateElementaryChatGroup(Long groupId, String groupName) {
        GroupChat group = groupRepository.findById(groupId).orElseThrow(() -> new IllegalStateException("group doesn't exist"));
        if(groupName!=null &&
                groupName.length()>0 &&
                !Objects.equals(group.getName(), groupName)){
            group.setName(groupName);
        }

    }
}
