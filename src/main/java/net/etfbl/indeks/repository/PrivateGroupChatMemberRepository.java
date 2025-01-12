package net.etfbl.indeks.repository;

import net.etfbl.indeks.model.PrivateGroupChatMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivateGroupChatMemberRepository extends JpaRepository<PrivateGroupChatMember, Long> {
    List<PrivateGroupChatMember> findByPrivateGroupChat_Id(Long privateGroupChatId);
}
