package net.etfbl.indeks.repository;

import net.etfbl.indeks.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
