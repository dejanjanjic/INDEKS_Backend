package net.etfbl.indeks.service;

import net.etfbl.indeks.model.TutoringOffer;
import net.etfbl.indeks.repository.TutoringOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TutoringOfferService
{
    private final TutoringOfferRepository tutoringOfferRepository;

    @Autowired
    public TutoringOfferService(TutoringOfferRepository tutoringOfferRepository){
        this.tutoringOfferRepository=tutoringOfferRepository;
    }

    public List<TutoringOffer> getTutoringOffers() {
        return tutoringOfferRepository.findAll();
    }
    public Optional<TutoringOffer> getTutoringOffer(Long id) {
        return tutoringOfferRepository.findById(id);
    }


    public TutoringOffer addNewTutoringOffer(TutoringOffer tutoringOffer) {
        tutoringOfferRepository.save(tutoringOffer);
        return tutoringOffer;
    }


    public boolean deleteTutoringOffer(Long id) {
        boolean exists = tutoringOfferRepository.existsById(id);
        if(!exists){
            return false;
        }
        tutoringOfferRepository.deleteById(id);
        return true;
    }


    @Transactional
    public boolean updateTutoringOffer(TutoringOffer tutoringOffer) {
        Optional<TutoringOffer> temp = tutoringOfferRepository.findById(tutoringOffer.getId());
        if(temp.isEmpty()){
            return false;
        }
        temp.get().setDescription(tutoringOffer.getDescription());
        return true;
    }
}
