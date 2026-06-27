package dev.luizribx.fightsystem.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.luizribx.fightsystem.dto.StudentRequestDto;
import dev.luizribx.fightsystem.dto.StudentResponseDto;
import dev.luizribx.fightsystem.exception.BusinessRuleException;
import dev.luizribx.fightsystem.exception.GlobalExceptionHundler;
import dev.luizribx.fightsystem.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static dev.luizribx.fightsystem.constants.MessageThrowConstant.EMAIL_ALREADY_EXISTS;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
@Import(GlobalExceptionHundler.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private StudentService studentService;

    @Test
    void registerStudentReturnsCreatedStudent() throws Exception {
        StudentRequestDto request = request("Ana Silva", "ana@email.com");
        when(studentService.registerStudent(any(StudentRequestDto.class))).thenReturn(response(1L, "Ana Silva", "ana@email.com"));

        mockMvc.perform(post("/students")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Ana Silva"))
                .andExpect(jsonPath("$.email").value("ana@email.com"));
    }

    @Test
    void registerStudentReturnsBadRequestWhenRequestIsInvalid() throws Exception {
        StudentRequestDto request = request("", "email-invalido");

        mockMvc.perform(post("/students")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.messages", hasItem(containsString("name"))));
    }

    @Test
    void findListReturnsStudentsPage() throws Exception {
        when(studentService.listed(any())).thenReturn(new PageImpl<>(List.of(response(1L, "Ana Silva", "ana@email.com"))));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Ana Silva"));
    }

    @Test
    void findByIdReturnsStudent() throws Exception {
        when(studentService.findById(1L)).thenReturn(response(1L, "Ana Silva", "ana@email.com"));

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Ana Silva"));
    }

    @Test
    void findByIdReturnsBadRequestWhenBusinessRuleFails() throws Exception {
        when(studentService.findById(1L)).thenThrow(new BusinessRuleException(EMAIL_ALREADY_EXISTS));

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.messages[0]").value(EMAIL_ALREADY_EXISTS));
    }

    @Test
    void updateStudentReturnsUpdatedStudent() throws Exception {
        StudentRequestDto request = request("Novo nome", "novo@email.com");
        when(studentService.updateStudent(eq(1L), any(StudentRequestDto.class)))
                .thenReturn(response(1L, "Novo nome", "novo@email.com"));

        mockMvc.perform(put("/students/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Novo nome"))
                .andExpect(jsonPath("$.email").value("novo@email.com"));
    }

    @Test
    void deleteStudentReturnsNoContent() throws Exception {
        doNothing().when(studentService).deleteStudent(1L);

        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isNoContent());

        verify(studentService).deleteStudent(1L);
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

    private StudentResponseDto response(Long id, String name, String email) {
        return new StudentResponseDto(
                id,
                name,
                LocalDate.of(2000, 1, 15),
                "F",
                "1133334444",
                "11999998888",
                email,
                "Sao Paulo",
                LocalDateTime.of(2026, 6, 20, 10, 30)
        );
    }
}
