package dev.luizribx.fightsystem.rest;

import dev.luizribx.fightsystem.dto.StudentFilterRequest;
import dev.luizribx.fightsystem.dto.StudentRequestDto;
import dev.luizribx.fightsystem.dto.StudentResponseDto;
import dev.luizribx.fightsystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDto registerStudent(@RequestBody @Valid StudentRequestDto studentRequestDto) {
        return studentService.registerStudent(studentRequestDto);
    }

    @GetMapping
    public Page<StudentResponseDto> findList(StudentFilterRequest request, Pageable pageable) {
        return studentService.listed(request, pageable);
    }

    @GetMapping("/{id}")
    public StudentResponseDto findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PutMapping("/{id}")
    public StudentResponseDto updateStudent(@PathVariable Long id, @RequestBody @Valid StudentRequestDto studentRequestDto) {
        return studentService.updateStudent(id, studentRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
