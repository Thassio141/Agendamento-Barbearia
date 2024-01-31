package br.com.haircutappoitment.models.dtos.appointment;

import br.com.haircutappoitment.models.dtos.task.TaskDto;
import br.com.haircutappoitment.models.entities.TaskEntity;
import br.com.haircutappoitment.models.entities.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentCreateDto {

    @NotNull
    private UserEntity user;

    @NotNull
    private TaskEntity task;
}
