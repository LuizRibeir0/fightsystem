package dev.luizribx.fightsystem.repository;

import dev.luizribx.fightsystem.domain.GraduationDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraduationRepository extends JpaRepository<GraduationDomain, Long> {
}
