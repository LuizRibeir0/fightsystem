package dev.luizribx.fightsystem.repository;

import dev.luizribx.fightsystem.domain.RegistrationsModalitiesDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationsRepository extends JpaRepository<RegistrationsModalitiesDomain, Long> {
}
