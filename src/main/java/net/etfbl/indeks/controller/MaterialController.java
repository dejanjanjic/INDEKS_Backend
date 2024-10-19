package net.etfbl.indeks.controller;

import net.etfbl.indeks.model.Account;
import net.etfbl.indeks.model.Material;
import net.etfbl.indeks.service.AccountService;
import net.etfbl.indeks.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/material")
public class MaterialController {
    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService){
        this.materialService = materialService;
    }

    @GetMapping
    public ResponseEntity<List<Material>> getMaterials(){
        return ResponseEntity.ok(materialService.getMaterials());
    }

    @GetMapping(path = "{materialId}")
    public ResponseEntity<Material> getMaterialById(@PathVariable(name = "materialId")Long materialId){
        Optional<Material> material = materialService.getMaterialById(materialId);
        if(material.isPresent()){
            return ResponseEntity.ok(material.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Material> postMaterial(@RequestBody Material material){
        Material temp = materialService.addNewMaterial(material);
        if(temp != null){
            return ResponseEntity.ok(temp);
        }else{
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        }
    }

    @DeleteMapping(path = "{materialId}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable("materialId")Long materialId){
        boolean deleted = materialService.deleteMaterial(materialId);
        if(deleted){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateMaterial(@RequestBody Material material){
        boolean updated = materialService.updateMaterial(material);
        if(updated){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
