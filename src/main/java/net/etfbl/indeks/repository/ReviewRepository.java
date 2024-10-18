package net.etfbl.indeks.repository;

import net.etfbl.indeks.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository  extends JpaRepository<Review, Long> {
}
