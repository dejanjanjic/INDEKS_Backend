package net.etfbl.indeks.service;

import net.etfbl.indeks.model.Account;
import net.etfbl.indeks.model.Material;
import net.etfbl.indeks.repository.AccountRepository;
import net.etfbl.indeks.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository){
        this.materialRepository = materialRepository;
    }

    public List<Material> getMaterials() {
        return materialRepository.findAll();
    }
    public Optional<Material> getMaterialById(Long materialId) {
        return materialRepository.findById(materialId);
    }

    public Material addNewMaterial(Material material) {
        return materialRepository.save(material);
    }

    public boolean deleteMaterial(Long materialId) {
        boolean exists = materialRepository.existsById(materialId);
        if(!exists){
            return false;
        }
        materialRepository.deleteById(materialId);
        return true;
    }


    @Transactional
    public boolean updateMaterial(Material material) {
        Optional<Material> temp = materialRepository.findById(material.getId());
        if(temp.isEmpty()){
            return false;
        }
        Material updatedMaterial = temp.get();
        if(material.getContent() != null){
            updatedMaterial.setContent(material.getContent());
        }
        if(material.getName() != null){
            updatedMaterial.setName(material.getName());
        }
        materialRepository.save(updatedMaterial);
        return true;
    }
}
