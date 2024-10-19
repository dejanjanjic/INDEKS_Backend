package net.etfbl.indeks.repository;

import net.etfbl.indeks.model.PrivateGroupChatMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivateGroupChatMemberRepository extends JpaRepository<PrivateGroupChatMember, Long> {
}
