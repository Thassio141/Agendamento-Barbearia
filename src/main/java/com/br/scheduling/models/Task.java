package com.br.scheduling.models;

import com.br.scheduling.models.enums.StatusTask;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private StatusTask statusTask;
}
