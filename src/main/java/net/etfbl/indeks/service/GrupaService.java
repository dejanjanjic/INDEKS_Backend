package net.etfbl.indeks.service;

import net.etfbl.indeks.model.GRUPA;
import net.etfbl.indeks.repository.GrupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GrupaService
{
    private final GrupaRepository grupaRepository;

    @Autowired
    public GrupaService(GrupaRepository grupaRepository) {
        this.grupaRepository = grupaRepository;
    }

    public List<GRUPA> getGroups() {
        return grupaRepository.findAll();
    }

    public void addNewGrupa(GRUPA grupa) {
        grupaRepository.save(grupa);
    }

    public void deleteGroup(Long groupId) {
        boolean exists = grupaRepository.existsById(groupId);
        if(!exists){
            throw new IllegalStateException("group doesn't exist");
        }
        grupaRepository.deleteById(groupId);
    }
    @Transactional
    public void updateStudent(Long groupId, String groupName) {
        GRUPA grupa = grupaRepository.findById(groupId).orElseThrow(() -> new IllegalStateException("group doesn't exist"));
        if(groupName!=null &&
                groupName.length()>0 &&
                !Objects.equals(grupa.getGroupName(), groupName)){
            grupa.setGroupName(groupName);
        }

    }

}
