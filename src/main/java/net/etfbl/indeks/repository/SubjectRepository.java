package net.etfbl.indeks.repository;


import net.etfbl.indeks.model.Account;
import net.etfbl.indeks.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>
{

}
