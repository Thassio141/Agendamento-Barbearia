package br.com.haircutappoitment.models.dtos.task;

import java.time.LocalTime;

import br.com.haircutappoitment.models.enums.ActivityStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    
    @NotNull
    private Long taskId;

    @NotBlank
    private String name;

    @NotNull
    private Double price;

    private LocalTime duration;

    @NotNull
    private ActivityStatus statusTask;

}
