package dev.luizribx.fightsystem.repository;

import dev.luizribx.fightsystem.domain.AttendanceDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<AttendanceDomain,Long> {
}
