package br.com.haircutappoitment.domain.dtos.appointment;

import br.com.haircutappoitment.domain.entities.TaskEntity;
import br.com.haircutappoitment.domain.entities.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    
    @NotNull
    private Long appointmentId;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private UserEntity user;

    @NotNull
    private TaskEntity task;
}
