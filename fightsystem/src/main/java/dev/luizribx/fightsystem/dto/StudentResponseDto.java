package dev.luizribx.fightsystem.dto;

import dev.luizribx.fightsystem.domain.StudentsDomain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StudentResponseDto(

        Long id,
        String name,
        LocalDate birthDay,
        String gender,
        String phone,
        String mobilePhone,
        String email,
        String city,
        LocalDateTime createdAt
) {

    public static StudentResponseDto fromEntity(StudentsDomain studentsDomain) {
        return new StudentResponseDto(
                studentsDomain.getId(),
                studentsDomain.getName(),
                studentsDomain.getBirthDay(),
                studentsDomain.getGender(),
                studentsDomain.getPhone(),
                studentsDomain.getEmail(),
                studentsDomain.getCity(),
                studentsDomain.getState(),
                studentsDomain.getCreatedAt()
        );
    }
}
