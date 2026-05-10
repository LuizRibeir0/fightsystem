package dev.luizribx.fightsystem.repository;

import dev.luizribx.fightsystem.domain.InvoicesRegistrationsDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoicesRegistrationsRepository extends JpaRepository<InvoicesRegistrationsDomain,Long> {
}
