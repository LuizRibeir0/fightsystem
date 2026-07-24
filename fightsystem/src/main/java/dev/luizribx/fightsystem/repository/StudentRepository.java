package dev.luizribx.fightsystem.repository;

import dev.luizribx.fightsystem.domain.StudentsDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<StudentsDomain,Long>, JpaSpecificationExecutor<StudentsDomain> {

    boolean existsByEmail(String email);
}
