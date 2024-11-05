package net.etfbl.indeks.service;

import jakarta.persistence.EntityManager;
import net.etfbl.indeks.dto.AddTutoringOfferDTO;
import net.etfbl.indeks.dto.UpdateTutoringOfferDTO;
import net.etfbl.indeks.model.Review;
import net.etfbl.indeks.model.Subject;
import net.etfbl.indeks.model.TutorAccount;
import net.etfbl.indeks.model.TutoringOffer;
import net.etfbl.indeks.repository.TutoringOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TutoringOfferService
{
    @Autowired
    private EntityManager entityManager;
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


    @Transactional
    public TutoringOffer addNewTutoringOffer(AddTutoringOfferDTO addTutoringOfferDTO) {
        Subject subject = entityManager.find(Subject.class, addTutoringOfferDTO.getSubjectId());
        if (subject == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject not found");
        }

        TutorAccount tutorAccount = entityManager.find(TutorAccount.class, addTutoringOfferDTO.getTutorAccountId());
        if (tutorAccount == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutor Account not found");
        }

        TutoringOffer newTutoringOffer = new TutoringOffer(
                addTutoringOfferDTO.getDescription(),
                subject,
                tutorAccount
        );

        entityManager.persist(newTutoringOffer);

        return newTutoringOffer;
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
    public boolean updateTutoringOffer(UpdateTutoringOfferDTO updateTutoringOfferDTO) {
        Optional<TutoringOffer> temp = tutoringOfferRepository.findById(updateTutoringOfferDTO.getId());

        if (temp.isEmpty()) {
            return false;
        }

        TutoringOffer existingTutoringOffer = temp.get();
        existingTutoringOffer.setDescription(updateTutoringOfferDTO.getDescription());
        tutoringOfferRepository.save(existingTutoringOffer);


        return true;
    }

    public Double getAverageRatingForTutoringOffer(Long tutoringOfferId) {
        TutoringOffer tutoringOffer = tutoringOfferRepository.findById(tutoringOfferId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutoring Offer not found"));

        List<Review> reviews = tutoringOffer.getReviews();
        if (reviews.isEmpty()) {
            return 0.0;
        }

        double averageRating = reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);

        return averageRating;
    }


}
