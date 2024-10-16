package net.etfbl.indeks.controller;

import net.etfbl.indeks.model.Group;
import net.etfbl.indeks.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/group")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @GetMapping
    public List<Group> getGroups() {
        return groupService.getGroups();
    }
    @GetMapping(path = "{groupId}")
    public Optional<Group> getGroup(@PathVariable("groupId")Long groupId) {
        return groupService.getGroup(groupId);
    }
    @PostMapping
    public void registerNewGroup(@RequestBody Group group){
        groupService.addNewGrupa(group);
    }

    @DeleteMapping(path = "{groupId}")
    public void deleteGroup(@PathVariable("groupId")Long groupId){
        groupService.deleteGroup(groupId);
    }

    @PutMapping(path="{groupId}")
    public void updateGroup(
            @PathVariable("groupId") Long groupId,
            @RequestParam(required = false) String groupName){
        groupService.updateStudent(groupId, groupName);
    }
}
