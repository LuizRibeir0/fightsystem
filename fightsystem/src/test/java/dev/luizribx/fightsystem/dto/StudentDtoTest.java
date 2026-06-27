package dev.luizribx.fightsystem.dto;

import dev.luizribx.fightsystem.domain.StudentsDomain;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class StudentDtoTest {

    @Test
    void toEntityCopiesAllRequestFields() {
        StudentRequestDto request = fullRequest();

        StudentsDomain student = request.toEntity();

        assertThat(student.getName()).isEqualTo("Ana Silva");
        assertThat(student.getBirthDay()).isEqualTo(LocalDate.of(2000, 1, 15));
        assertThat(student.getGender()).isEqualTo("F");
        assertThat(student.getPhone()).isEqualTo("1133334444");
        assertThat(student.getMobilePhone()).isEqualTo("11999998888");
        assertThat(student.getEmail()).isEqualTo("ana@email.com");
        assertThat(student.getNotes()).isEqualTo("Observacao");
        assertThat(student.getAddress()).isEqualTo("Rua A");
        assertThat(student.getNumber()).isEqualTo("123");
        assertThat(student.getComplement()).isEqualTo("Apto 4");
        assertThat(student.getNeighborhood()).isEqualTo("Centro");
        assertThat(student.getCity()).isEqualTo("Sao Paulo");
        assertThat(student.getState()).isEqualTo("SP");
        assertThat(student.getZipcode()).isEqualTo("01001000");
    }

    @Test
    void fillInUpdatesExistingEntity() {
        StudentsDomain student = new StudentsDomain();
        student.setId(10L);
        student.setName("Nome antigo");

        fullRequest().fillIn(student);

        assertThat(student.getId()).isEqualTo(10L);
        assertThat(student.getName()).isEqualTo("Ana Silva");
        assertThat(student.getEmail()).isEqualTo("ana@email.com");
        assertThat(student.getCity()).isEqualTo("Sao Paulo");
    }

    @Test
    void fromEntityCopiesResponseFields() {
        LocalDateTime createdAt = LocalDateTime.of(2026, 6, 20, 10, 30);
        StudentsDomain student = new StudentsDomain();
        student.setId(7L);
        student.setName("Ana Silva");
        student.setBirthDay(LocalDate.of(2000, 1, 15));
        student.setGender("F");
        student.setPhone("1133334444");
        student.setMobilePhone("11999998888");
        student.setEmail("ana@email.com");
        student.setCity("Sao Paulo");
        student.setCreatedAt(createdAt);

        StudentResponseDto response = StudentResponseDto.fromEntity(student);

        assertThat(response.id()).isEqualTo(7L);
        assertThat(response.name()).isEqualTo("Ana Silva");
        assertThat(response.birthDay()).isEqualTo(LocalDate.of(2000, 1, 15));
        assertThat(response.gender()).isEqualTo("F");
        assertThat(response.phone()).isEqualTo("1133334444");
        assertThat(response.mobilePhone()).isEqualTo("11999998888");
        assertThat(response.email()).isEqualTo("ana@email.com");
        assertThat(response.city()).isEqualTo("Sao Paulo");
        assertThat(response.createdAt()).isEqualTo(createdAt);
    }

    private StudentRequestDto fullRequest() {
        return new StudentRequestDto(
                "Ana Silva",
                LocalDate.of(2000, 1, 15),
                "F",
                "1133334444",
                "11999998888",
                "ana@email.com",
                "Observacao",
                "Rua A",
                "123",
                "Apto 4",
                "Centro",
                "Sao Paulo",
                "SP",
                "01001000"
        );
    }
}
