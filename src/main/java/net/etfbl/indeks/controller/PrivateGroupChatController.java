package net.etfbl.indeks.controller;

import net.etfbl.indeks.model.GroupChat;
import net.etfbl.indeks.model.PrivateGroupChat;
import net.etfbl.indeks.service.PrivateGroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/privateGroup")
public class PrivateGroupChatController {
    private final PrivateGroupChatService privateGroupChatService;

    @Autowired
    public PrivateGroupChatController(PrivateGroupChatService privateGroupChatService) {
        this.privateGroupChatService = privateGroupChatService;
    }


    @GetMapping
    public List<PrivateGroupChat> getPrivateGroupChats() {
        return privateGroupChatService.getPrivateGroupChats();
    }
    @GetMapping(path = "{groupId}")
    public Optional<PrivateGroupChat> getPrivateGroupChat(@PathVariable("groupId")Long groupId) {
        return privateGroupChatService.getGroup(groupId);
    }
    @PostMapping
    public void registerNewPrivateGroupChat(@RequestBody PrivateGroupChat group){
        privateGroupChatService.addNewPrvateGroupChat(group);
    }

    @DeleteMapping(path = "{groupId}")
    public void deleteGroup(@PathVariable("groupId")Long groupId){
        privateGroupChatService.deleteGroup(groupId);
    }

    @PutMapping(path="{groupId}")
    public void updateGroup(
            @PathVariable("groupId") Long groupId,
            @RequestParam(required = false) String groupName){
        privateGroupChatService.updatePrivateChatGroup(groupId, groupName);
    }
}
