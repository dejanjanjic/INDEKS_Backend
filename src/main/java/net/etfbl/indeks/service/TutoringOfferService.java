package net.etfbl.indeks.service;

import jakarta.persistence.EntityManager;
import net.etfbl.indeks.dto.AddTutoringOfferDTO;
import net.etfbl.indeks.dto.TutoringOfferDetailsDTO;
import net.etfbl.indeks.dto.TutoringOfferWithReviewsDTO;
import net.etfbl.indeks.dto.UpdateTutoringOfferDTO;
import net.etfbl.indeks.model.*;
import net.etfbl.indeks.repository.ReviewRepository;
import net.etfbl.indeks.repository.SubjectRepository;
import net.etfbl.indeks.repository.TutoringOfferRepository;
import net.etfbl.indeks.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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

        StudentAccount studentAccount = entityManager.find(StudentAccount.class, addTutoringOfferDTO.getStudentAccountId());
        if (studentAccount == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutor Account not found");
        }

        TutoringOffer newTutoringOffer = new TutoringOffer(
                addTutoringOfferDTO.getDescription(),
                subject,
                studentAccount
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

    public List<TutoringOffer> getTutoringOffersByStudentAccountId(Long studentAccountId) {
        return tutoringOfferRepository.findByStudentAccountId(studentAccountId);
    }

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ReviewRepository reviewRepository;  // Add the Review repository to fetch reviews

    public List<TutoringOfferDetailsDTO> getTutoringOfferDetails() {
        List<TutoringOffer> tutoringOffers = tutoringOfferRepository.findAll();  // Retrieve all tutoring offers

        List<TutoringOfferDetailsDTO> offerDetailsDTOList = new ArrayList<>();

        for (TutoringOffer offer : tutoringOffers) {
            // Fetch related user (acting as instructor)
            UserAccount instructor = userAccountRepository.findById(offer.getTutorAccount().getId()).orElse(null);
            // Fetch related subject
            Subject subject = subjectRepository.findById(offer.getSubject().getId()).orElse(null);

            // Calculate the average rating from reviews for the tutoring offer
            Double averageRating = calculateAverageRating(offer.getId());

            if (instructor != null && subject != null) {
                String instructorName = instructor.getFirstName() + " " + instructor.getLastName();
                offerDetailsDTOList.add(new TutoringOfferDetailsDTO(instructorName, subject.getName(), offer.getId(), averageRating));
            }
        }

        return offerDetailsDTOList;
    }

    private Double calculateAverageRating(long tutoringOfferId) {
        List<Review> reviews = reviewRepository.findByTutoringOfferId(tutoringOfferId);  // Retrieve reviews for this tutoring offer

        if (reviews.isEmpty()) {
            return 0.0;  // If no reviews, return 0.0
        }

        double totalRating = 0.0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }

        return totalRating / reviews.size();  // Return the average rating as a Double
    }

    // Method to fetch details for a specific tutoring offer by ID
    public TutoringOfferWithReviewsDTO getTutoringOfferWithReviewsById(long tutoringOfferId) {
        // Fetch the tutoring offer
        TutoringOffer offer = tutoringOfferRepository.findById(tutoringOfferId).orElse(null);


        if (offer == null) {
            return null;  // Return null if tutoring offer not found
        }

        // Fetch related subject
        Subject subject = subjectRepository.findById(offer.getSubject().getId()).orElse(null);
        // Fetch related instructor (student account acting as the instructor)
        UserAccount instructor = userAccountRepository.findById(offer.getTutorAccount().getId()).orElse(null);
        // Fetch reviews for the specific tutoring offer
        List<Review> reviews = reviewRepository.findByTutoringOfferId(offer.getId());

        // Map reviews to ReviewDTO
        List<TutoringOfferWithReviewsDTO.ReviewDTO> reviewDTOs = new ArrayList<>();
        for (Review review : reviews) {
            UserAccount reviewer = userAccountRepository.findById(review.getStudentAccount().getId()).orElse(null);
            if (reviewer != null) {
                String reviewerName = reviewer.getFirstName() + " " + reviewer.getLastName();
                reviewDTOs.add(new TutoringOfferWithReviewsDTO.ReviewDTO(reviewerName, review.getComment(), review.getRating()));
            }
        }
        // If subject or instructor is not found, return null
        if (instructor != null && subject != null) {
            String instructorName = instructor.getFirstName() + " " + instructor.getLastName();
            return new TutoringOfferWithReviewsDTO(
                    subject.getName(),
                    instructorName,
                    offer.getDescription(),
                    offer.getId(),
                    reviewDTOs
            );
        }

        return null;  // Return null if no valid data is found
    }

}
