package br.com.haircutappoitment.domain.entities;

import java.time.LocalTime;

import br.com.haircutappoitment.domain.enums.ActivityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class TaskEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "duration")
    private LocalTime duration;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_task")
    private ActivityStatus statusTask;

    @OneToOne(mappedBy = "task")
    private AppointmentEntity appointment;
}
