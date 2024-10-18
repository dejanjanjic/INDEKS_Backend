package net.etfbl.indeks.service;

import net.etfbl.indeks.model.SingleChat;
import net.etfbl.indeks.repository.SingleChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SingleChatService {

    private final SingleChatRepository singleChatRepository;

    @Autowired
    public SingleChatService(SingleChatRepository singleChatRepository) {
        this.singleChatRepository = singleChatRepository;
    }

    public List<SingleChat> getSingleChats() {
        return singleChatRepository.findAll();
    }

    public Optional<SingleChat> getSingleChatById(Long id) {
        return singleChatRepository.findById(id);
    }

    public void addNewSingleChat(SingleChat singleChat) {
        singleChatRepository.save(singleChat);
    }

    public boolean deleteSingleChat(Long id) {
        boolean exists = singleChatRepository.existsById(id);
        if (exists) {
            singleChatRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
