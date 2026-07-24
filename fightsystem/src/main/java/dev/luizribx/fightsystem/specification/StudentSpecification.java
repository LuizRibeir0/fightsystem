package dev.luizribx.fightsystem.specification;

import dev.luizribx.fightsystem.domain.StudentsDomain;
import dev.luizribx.fightsystem.dto.StudentFilterRequest;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {

    public static Specification<StudentsDomain> comFiltros(StudentFilterRequest request) {
        return Specification
                .where(nomeContem(request.name()))
                .and(emailContem(request.email()))
                .and(celularContem(request.phone()))
                .and(cidadeContem(request.city()))
                .and(estadoIgual(request.state()));
    }

    private static Specification<StudentsDomain> nomeContem(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) {
                return null;
            }

            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }
    private static Specification<StudentsDomain> emailContem(String email) {
        return (root, query, cb) -> {
            if (email == null || email.isBlank()) {
                return null;
            }

            return cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
        };
    }

    private static Specification<StudentsDomain> celularContem(String phone) {
        return (root, query, cb) -> {
            if (phone == null || phone.isBlank()) {
                return null;
            }

            return cb.like(cb.lower(root.get("phone")), "%" + phone.toLowerCase() + "%");
        };
    }

    private static Specification<StudentsDomain> cidadeContem(String city) {
        return (root, query, cb) -> {
            if (city == null || city.isBlank()) {
                return null;
            }

            return cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%");
        };
    }

    private static Specification<StudentsDomain> estadoIgual(String state) {
        return (root, query, cb) -> {
            if (state == null || state.isBlank()) {
                return null;
            }

            return cb.equal(cb.upper(root.get("state")), state.toUpperCase());
        };
    }
}
