package br.com.haircutappoitment.models.dtos.user;

import br.com.haircutappoitment.models.enums.ActivityStatus;
import br.com.haircutappoitment.models.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private ActivityStatus activityStatus;
    
    @NotNull
    private UserRole userRole;
}
