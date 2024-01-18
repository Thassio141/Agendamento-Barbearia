package br.com.schedule.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedules")
public class Schedule {
    @Id
    private Long id;

    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
