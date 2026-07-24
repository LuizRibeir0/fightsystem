package dev.luizribx.fightsystem.dto;

public record StudentFilterRequest (
        String name,
        String email,
        String phone,
        String city,
        String state

) {
}
