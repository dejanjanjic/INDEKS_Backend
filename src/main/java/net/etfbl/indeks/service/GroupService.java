package net.etfbl.indeks.service;

import net.etfbl.indeks.model.Group;
import net.etfbl.indeks.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GroupService
{
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }
    public Optional<Group> getGroup(Long groupId) {
        return groupRepository.findById(groupId);
    }
    public void addNewGrupa(Group group) {
        groupRepository.save(group);
    }

    public void deleteGroup(Long groupId) {
        boolean exists = groupRepository.existsById(groupId);
        if(!exists){
            throw new IllegalStateException("group doesn't exist");
        }
        groupRepository.deleteById(groupId);
    }
    @Transactional
    public void updateStudent(Long groupId, String groupName) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new IllegalStateException("group doesn't exist"));
        if(groupName!=null &&
                groupName.length()>0 &&
                !Objects.equals(group.getGroupName(), groupName)){
            group.setGroupName(groupName);
        }

    }

}
