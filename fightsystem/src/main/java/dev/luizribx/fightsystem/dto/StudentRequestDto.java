package dev.luizribx.fightsystem.dto;

import dev.luizribx.fightsystem.domain.StudentsDomain;

import java.time.LocalDate;

public record StudentRequestDto(

        String name,
        LocalDate birthDay,
        String gender,
        String phone,
        String mobilePhone,
        String email,
        String notes,
        String address,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String zipcode
) {

    public StudentsDomain toEntity() {
        StudentsDomain studentsDomain = new StudentsDomain();
        fillIn(studentsDomain);
        return studentsDomain;
    }

    public void fillIn(StudentsDomain studentsDomain) {
        studentsDomain.setName(name);
        studentsDomain.setBirthDay(birthDay);
        studentsDomain.setGender(gender);
        studentsDomain.setPhone(phone);
        studentsDomain.setMobilePhone(mobilePhone);
        studentsDomain.setEmail(email);
        studentsDomain.setNotes(notes);
        studentsDomain.setAddress(address);
        studentsDomain.setNumber(number);
        studentsDomain.setComplement(complement);
        studentsDomain.setNeighborhood(neighborhood);
        studentsDomain.setCity(city);
        studentsDomain.setState(state);
        studentsDomain.setZipcode(zipcode);
    }
}
