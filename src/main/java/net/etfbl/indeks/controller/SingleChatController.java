package net.etfbl.indeks.controller;

import net.etfbl.indeks.model.SingleChat;
import net.etfbl.indeks.service.SingleChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/singleChat")
public class SingleChatController {

    private final SingleChatService singleChatService;

    @Autowired
    public SingleChatController(SingleChatService singleChatService) {
        this.singleChatService = singleChatService;
    }

    @GetMapping
    public ResponseEntity<List<SingleChat>> getSingleChats() {
        return ResponseEntity.ok(singleChatService.getSingleChats());
    }

    @GetMapping(path = "{singleChatId}")
    public ResponseEntity<SingleChat> getSingleChatById(@PathVariable("singleChatId") Long id) {
        Optional<SingleChat> singleChat = singleChatService.getSingleChatById(id);
        return singleChat.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SingleChat> addNewMessage(@RequestBody SingleChat singleChat) {
        singleChatService.addNewSingleChat(singleChat);
        return new ResponseEntity<>(singleChat, HttpStatus.OK);
    }

    @DeleteMapping(path = "{singleChatId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("singleChatId") Long id) {
        boolean deleted = singleChatService.deleteSingleChat(id);
        if(deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
