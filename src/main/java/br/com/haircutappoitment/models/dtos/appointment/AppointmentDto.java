package br.com.haircutappoitment.models.dtos.appointment;

import br.com.haircutappoitment.models.entities.TaskEntity;
import br.com.haircutappoitment.models.entities.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    
    @NotNull
    private Long appointmentId;

    @NotNull
    private UserEntity user;

    @NotNull
    private TaskEntity task;
}
