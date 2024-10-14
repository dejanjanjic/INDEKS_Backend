package net.etfbl.indeks.repository;


import net.etfbl.indeks.model.GRUPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrupaRepository extends JpaRepository<GRUPA, Long>
{
    Optional<GRUPA> findByName(String GroupName);
}
