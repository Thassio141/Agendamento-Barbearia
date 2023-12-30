package com.br.scheduling.models;

import com.br.scheduling.models.enums.ScheduleStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "schedules")
public class Schedule   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

//    @NotNull
//    private LocalDate date;

//    @NotNull
//    private LocalTime localTime;

    @NotNull
    private ScheduleStatus scheduleStatus;

    private Long userId;

    private Long taskId;
}
