package net.etfbl.indeks.controller;

import net.etfbl.indeks.dto.AddElementaryGroupChatDTO;
import net.etfbl.indeks.dto.AddPrivateGroupChatDTO;
import net.etfbl.indeks.dto.GetMessageDTO;
import net.etfbl.indeks.model.ElementaryGroupChat;
import net.etfbl.indeks.model.PrivateGroupChat;
import net.etfbl.indeks.service.PrivateGroupChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/privateGroup")
public class PrivateGroupChatController {

    private final PrivateGroupChatService privateGroupChatService;

    @Autowired
    public PrivateGroupChatController(PrivateGroupChatService privateGroupChatService) {
        this.privateGroupChatService = privateGroupChatService;
    }

    @GetMapping
    public ResponseEntity<List<PrivateGroupChat>> getPrivateGroupChats() {
        List<PrivateGroupChat> chats = privateGroupChatService.getPrivateGroupChats();
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @GetMapping(path = "{groupId}")
    public ResponseEntity<Optional<PrivateGroupChat>> getPrivateGroupChat(@PathVariable("groupId") Long groupId) {
        Optional<PrivateGroupChat> chat = privateGroupChatService.getGroup(groupId);
        if (chat.isPresent()) {
            return new ResponseEntity<>(chat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PrivateGroupChat> registerNewPrivateGroupChat(@RequestBody AddPrivateGroupChatDTO group) {
        PrivateGroupChat PrGroup =privateGroupChatService.addNewPrivateGroupChat(group);
        return new ResponseEntity<>(PrGroup, HttpStatus.OK);
    }


    @DeleteMapping(path = "{groupId}")
    public ResponseEntity deleteGroup(@PathVariable("groupId") Long groupId) {
        boolean isDeleted = privateGroupChatService.deleteGroup(groupId);
        if (isDeleted) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity (HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "{groupId}")
    public ResponseEntity updateGroup(
            @PathVariable("groupId") Long groupId,
            @RequestParam(required = false) String groupName) {
        boolean isUpdated = privateGroupChatService.updatePrivateChatGroup(groupId, groupName);
        if (isUpdated) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<List<GetMessageDTO>> getMessagesFromChat(@PathVariable Long chatId, @RequestParam Long userId) {
        List<GetMessageDTO> messages = privateGroupChatService.getMessagesFromChat(chatId, userId);
        return ResponseEntity.ok(messages);
    }
}
