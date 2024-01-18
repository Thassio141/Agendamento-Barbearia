package br.com.schedule.dtos;

import br.com.schedule.models.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDTO {
    @NotNull
    private Long id;

    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String phoneNumber;

    @NotNull
    private UserRole userRole;
}
