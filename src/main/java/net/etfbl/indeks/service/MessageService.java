package net.etfbl.indeks.service;

import jakarta.transaction.Transactional;
import net.etfbl.indeks.model.Message;
import net.etfbl.indeks.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    public void addNewMessage(Message message) {
        messageRepository.save(message);
    }

    public boolean deleteMessage(Long id) {
        boolean exists = messageRepository.existsById(id);
        if (exists) {
            messageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateMessage(Long id, String text) {
        Optional<Message> message = messageRepository.findById(id);
        if(message.isPresent() && !text.isEmpty() && !message.get().getText().equals(text)) {
            message.get().setText(text);
            return true;
        }
        return false;
    }
}
