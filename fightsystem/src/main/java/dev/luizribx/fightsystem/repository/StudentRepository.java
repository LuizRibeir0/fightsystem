package dev.luizribx.fightsystem.repository;

import dev.luizribx.fightsystem.domain.StudentsDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentsDomain,Long> {

    boolean existsByEmail(String email);
}
