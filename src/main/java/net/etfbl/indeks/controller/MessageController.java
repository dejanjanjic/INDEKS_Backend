package net.etfbl.indeks.controller;

import net.etfbl.indeks.model.Message;
import net.etfbl.indeks.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/message")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<Message>> getMessages() {
        return ResponseEntity.ok(messageService.getMessages());
    }

    @GetMapping(path = "{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable(name = "messageId") Long id) {
        Optional<Message> message = messageService.getMessageById(id);
        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Message> addNewMessage(@RequestBody Message message) {
        messageService.addNewMessage(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping(path = "{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("messageId") Long id) {
        boolean deleted = messageService.deleteMessage(id);
        if(deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping()
    public ResponseEntity<Void> updateMessage(@RequestBody Message message) {
        boolean updated = messageService.updateMessage(message.getId(), message.getText());
        if(updated) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
