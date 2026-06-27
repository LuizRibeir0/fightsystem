package dev.luizribx.fightsystem.service;

import dev.luizribx.fightsystem.domain.StudentsDomain;
import dev.luizribx.fightsystem.dto.StudentRequestDto;
import dev.luizribx.fightsystem.dto.StudentResponseDto;
import dev.luizribx.fightsystem.exception.BusinessRuleException;
import dev.luizribx.fightsystem.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static dev.luizribx.fightsystem.constants.MessageThrowConstant.EMAIL_ALREADY_EXISTS;
import static dev.luizribx.fightsystem.constants.MessageThrowConstant.STUDENT_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void registerStudentSavesAndReturnsCreatedStudent() {
        StudentRequestDto request = request("Ana Silva", "ana@email.com");
        when(studentRepository.existsByEmail("ana@email.com")).thenReturn(false);
        when(studentRepository.save(any(StudentsDomain.class))).thenAnswer(invocation -> {
            StudentsDomain student = invocation.getArgument(0);
            student.setId(1L);
            return student;
        });

        StudentResponseDto response = studentService.registerStudent(request);

        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("Ana Silva");
        assertThat(response.email()).isEqualTo("ana@email.com");

        ArgumentCaptor<StudentsDomain> studentCaptor = ArgumentCaptor.forClass(StudentsDomain.class);
        verify(studentRepository).save(studentCaptor.capture());
        assertThat(studentCaptor.getValue().getName()).isEqualTo("Ana Silva");
    }

    @Test
    void registerStudentThrowsWhenEmailAlreadyExists() {
        StudentRequestDto request = request("Ana Silva", "ana@email.com");
        when(studentRepository.existsByEmail("ana@email.com")).thenReturn(true);

        assertThatThrownBy(() -> studentService.registerStudent(request))
                .isInstanceOf(BusinessRuleException.class)
                .hasMessage(EMAIL_ALREADY_EXISTS);

        verify(studentRepository, never()).save(any());
    }

    @Test
    void registerStudentDoesNotCheckDuplicatedEmailWhenEmailIsNull() {
        StudentRequestDto request = request("Ana Silva", null);
        when(studentRepository.save(any(StudentsDomain.class))).thenAnswer(invocation -> invocation.getArgument(0));

        StudentResponseDto response = studentService.registerStudent(request);

        assertThat(response.name()).isEqualTo("Ana Silva");
        verify(studentRepository, never()).existsByEmail(any());
    }

    @Test
    void listedReturnsMappedPage() {
        StudentsDomain student = student(1L, "Ana Silva", "ana@email.com");
        PageRequest pageable = PageRequest.of(0, 10);
        when(studentRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(student), pageable, 1));

        Page<StudentResponseDto> page = studentService.listed(pageable);

        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getContent()).extracting(StudentResponseDto::name).containsExactly("Ana Silva");
    }

    @Test
    void findByIdReturnsStudentWhenFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student(1L, "Ana Silva", "ana@email.com")));

        StudentResponseDto response = studentService.findById(1L);

        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("Ana Silva");
    }

    @Test
    void findByIdThrowsWhenStudentDoesNotExist() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.findById(99L))
                .isInstanceOf(BusinessRuleException.class)
                .hasMessage(STUDENT_NOT_FOUND);
    }

    @Test
    void updateStudentFillsExistingEntityAndSavesIt() {
        StudentsDomain existingStudent = student(1L, "Nome antigo", "antigo@email.com");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(existingStudent)).thenReturn(existingStudent);

        StudentResponseDto response = studentService.updateStudent(1L, request("Novo nome", "novo@email.com"));

        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("Novo nome");
        assertThat(response.email()).isEqualTo("novo@email.com");
        verify(studentRepository).save(existingStudent);
    }

    @Test
    void deleteStudentDeletesExistingEntity() {
        StudentsDomain existingStudent = student(1L, "Ana Silva", "ana@email.com");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));

        studentService.deleteStudent(1L);

        verify(studentRepository).delete(existingStudent);
    }

    private StudentRequestDto request(String name, String email) {
        return new StudentRequestDto(
                name,
                LocalDate.of(2000, 1, 15),
                "F",
                "1133334444",
                "11999998888",
                email,
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

    private StudentsDomain student(Long id, String name, String email) {
        StudentsDomain student = new StudentsDomain();
        student.setId(id);
        student.setName(name);
        student.setBirthDay(LocalDate.of(2000, 1, 15));
        student.setGender("F");
        student.setPhone("1133334444");
        student.setMobilePhone("11999998888");
        student.setEmail(email);
        student.setCity("Sao Paulo");
        return student;
    }
}
