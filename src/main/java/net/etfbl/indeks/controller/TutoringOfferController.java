package net.etfbl.indeks.controller;

import net.etfbl.indeks.dto.AddTutoringOfferDTO;
import net.etfbl.indeks.dto.UpdateTutoringOfferDTO;
import net.etfbl.indeks.model.TutoringOffer;
import net.etfbl.indeks.service.TutoringOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/tutoringOffer")
public class TutoringOfferController
{
    private final TutoringOfferService tutoringOfferService;

    @Autowired
    public TutoringOfferController(TutoringOfferService tutoringOfferService) {
        this.tutoringOfferService = tutoringOfferService;
    }

    @GetMapping
    public ResponseEntity<List<TutoringOffer>> getTutoringOffers(){
        return ResponseEntity.ok(tutoringOfferService.getTutoringOffers());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<TutoringOffer> getTutoringOffer(@PathVariable(name = "id")Long id){
        Optional<TutoringOffer> tutoringOffer = tutoringOfferService.getTutoringOffer(id);
        if(tutoringOffer.isPresent()){
            return ResponseEntity.ok(tutoringOffer.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TutoringOffer> registerNewTutoringOffer(@RequestBody AddTutoringOfferDTO addTutoringOfferDTO){
        TutoringOffer temp = tutoringOfferService.addNewTutoringOffer(addTutoringOfferDTO);
        if(temp != null){
            return ResponseEntity.ok(temp);
        }else{
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteTutoringOffer(@PathVariable("id")Long id){
        boolean deleted = tutoringOfferService.deleteTutoringOffer(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateTutoringOffer(@RequestBody UpdateTutoringOfferDTO updateTutoringOfferDTO){
        boolean updated = tutoringOfferService.updateTutoringOffer(updateTutoringOfferDTO);
        if(updated){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
