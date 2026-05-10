package dev.luizribx.fightsystem.repository;

import dev.luizribx.fightsystem.domain.SubscriptionDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriptionDomain,Long> {
}
