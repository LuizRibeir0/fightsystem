package dev.luizribx.fightsystem.dto;

import dev.luizribx.fightsystem.domain.StudentsDomain;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record StudentRequestDto(

        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 150, message = "O nome deve ter no máximo 150 caractéres")
        String name,

        @Past(message = "A data de nascimento deve estar no passado")
        LocalDate birthDay,

        @Size(max = 1, message = "O sexo deve ter no máximo 1(um) caractérer")
        String gender,

        @Size(max = 30, message = "O telefone deve ter no máximo 30 caractéreres")
        String phone,

        @Size(max = 30, message = "O celular deve ter no máximo 30 caractéreres")
        String mobilePhone,

        @Email(message = "E=mail inválido")
        @Size(max = 150, message = "O e-mail deve ter no máximo 150 caractéreres")
        String email,


        String notes,

        @Size(max = 150, message = "O endereço deve ter no máximo 150 caractéreres")
        String address,

        @Size(max = 20, message = "O número deve ter no máximo 20 caractéreres")
        String number,

        @Size(max = 100, message = "O complemento deve ter no máximo 100 caractéreres")
        String complement,

        @Size(max = 100, message = "O bairro deve ter no máximo 100 caractéreres")
        String neighborhood,

        @Size(max = 100, message = "A cidade deve ter no máximo 100 caractéreres")
        String city,

        @Size(max = 2, message = "O estado deve ter no máximo 2 caractéreres")
        String state,

        @Size(max = 20, message = "O CEP deve ter no máximo 20 caractéreres")
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
