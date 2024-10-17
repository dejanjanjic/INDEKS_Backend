package net.etfbl.indeks.controller;


import net.etfbl.indeks.model.ElementaryGroupChat;
import net.etfbl.indeks.service.ElementaryGroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/elementaryGroup")
public class ElementaryGroupChatController {
    private final ElementaryGroupChatService elementaryGroupChatService;

    @Autowired
    public ElementaryGroupChatController(ElementaryGroupChatService elementaryGroupChatService) {
        this.elementaryGroupChatService = elementaryGroupChatService;
    }


    @GetMapping
    public List<ElementaryGroupChat> getPrivateGroupChats() {
        return elementaryGroupChatService.getElementaryGroupChats();
    }
    @GetMapping(path = "{groupId}")
    public Optional<ElementaryGroupChat> getPrivateGroupChat(@PathVariable("groupId")Long groupId) {
        return elementaryGroupChatService.getGroup(groupId);
    }
    @PostMapping
    public void registerNewPrivateGroupChat(@RequestBody ElementaryGroupChat group){
        elementaryGroupChatService.addNewElementaryGroupChat(group);
    }

    @DeleteMapping(path = "{groupId}")
    public void deleteGroup(@PathVariable("groupId")Long groupId){
        elementaryGroupChatService.deleteGroup(groupId);
    }

    @PutMapping(path="{groupId}")
    public void updateGroup(
            @PathVariable("groupId") Long groupId,
            @RequestParam(required = false) String groupName){
        elementaryGroupChatService.updateElementaryChatGroup(groupId, groupName);
    }
}



