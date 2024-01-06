package com.br.scheduling.dtos;

import com.br.scheduling.models.Task;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class TaskDTOMapper implements Function<Task,TaskDTO>{

    @Override
    public TaskDTO apply(Task task) {
            return new TaskDTO(
                    task.getId(),
                    task.getName(),
                    task.getPrice(),
                    task.getStatusTask()
            );
    }
}
