package dev.luizribx.fightsystem.repository;

import dev.luizribx.fightsystem.domain.ModalitiesDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModalitiesRepository extends JpaRepository<ModalitiesDomain,Long> {
}
