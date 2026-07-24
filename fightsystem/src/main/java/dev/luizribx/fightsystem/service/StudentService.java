package dev.luizribx.fightsystem.service;

import dev.luizribx.fightsystem.domain.StudentsDomain;
import dev.luizribx.fightsystem.dto.StudentFilterRequest;
import dev.luizribx.fightsystem.dto.StudentRequestDto;
import dev.luizribx.fightsystem.dto.StudentResponseDto;
import dev.luizribx.fightsystem.exception.BusinessRuleException;
import dev.luizribx.fightsystem.repository.StudentRepository;
import dev.luizribx.fightsystem.specification.StudentSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static dev.luizribx.fightsystem.constants.MessageThrowConstant.EMAIL_ALREADY_EXISTS;
import static dev.luizribx.fightsystem.constants.MessageThrowConstant.STUDENT_NOT_FOUND;

@Service
public class StudentService {

    public final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponseDto registerStudent(StudentRequestDto student) {
        if (student.email() != null && studentRepository.existsByEmail(student.email())) {

            throw new BusinessRuleException(EMAIL_ALREADY_EXISTS);
        }

        StudentsDomain studentsDomain = student.toEntity();
        StudentsDomain savedStudent = studentRepository.save(studentsDomain);
        return StudentResponseDto.fromEntity(savedStudent);
    }

    public Page<StudentResponseDto> listed(StudentFilterRequest request, Pageable pageable) {
        return studentRepository.findAll(StudentSpecification.comFiltros(request),
                pageable).map(StudentResponseDto::fromEntity);
    }

    public StudentResponseDto findById(Long id) {
        StudentsDomain student = findEntityById(id);
        return StudentResponseDto.fromEntity(student);
    }

    public StudentResponseDto updateStudent(Long id, StudentRequestDto student) {
        StudentsDomain studentsDomain = findEntityById(id);
        student.fillIn(studentsDomain);
        StudentsDomain updatedStudent = studentRepository.save(studentsDomain);
        return StudentResponseDto.fromEntity(updatedStudent);
    }

    public void deleteStudent(Long id) {
        StudentsDomain student = findEntityById(id);
        studentRepository.delete(student);
    }

    private StudentsDomain findEntityById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new BusinessRuleException(STUDENT_NOT_FOUND));
    }
}
