package net.etfbl.indeks.service;

import net.etfbl.indeks.model.Review;
import net.etfbl.indeks.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService
{
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository=reviewRepository;
    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }
    public Optional<Review> getReview(Long id) {
        return reviewRepository.findById(id);
    }

    public Review addNewReview(Review review) {
       reviewRepository.save(review);
       return  review;
    }

    public boolean deleteReview(Long id) {
        boolean exists = reviewRepository.existsById(id);
        if(!exists){
            return false;
        }
        reviewRepository.deleteById(id);
        return true;
    }


    @Transactional
    public boolean updateReview(Review review) {
        Optional<Review> temp = reviewRepository.findById(review.getId());
        if(temp.isEmpty()){
            return false;
        }
        temp.get().setComment(review.getComment());
        temp.get().setDateTime(review.getDateTime());
        temp.get().setTutoringOffer(review.getTutoringOffer());
        return true;
    }
}
