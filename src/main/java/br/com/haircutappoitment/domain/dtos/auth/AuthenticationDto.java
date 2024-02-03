package br.com.haircutappoitment.domain.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
