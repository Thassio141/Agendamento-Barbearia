package com.br.scheduling.services;

import com.br.scheduling.dtos.TaskDTO;
import com.br.scheduling.dtos.TaskDTOMapper;
import com.br.scheduling.models.Task;
import com.br.scheduling.models.enums.StatusTask;
import com.br.scheduling.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskDTOMapper taskDTOMapper;

    public Optional<TaskDTO> findTaskById(Long id){
        if (taskRepository.findById(id).isPresent()){
            return taskRepository.findById(id)
                    .map(taskDTOMapper);
        }
        else{
            return null; //TODO better exception error message
        }

    }

    public List<TaskDTO> getAllTasks(){
        return taskRepository.findAll()
                .stream()
                .map(taskDTOMapper)
                .collect(Collectors.toList());
    }

    public List<TaskDTO> pageSearchTask(){
        return null;
    }
    public TaskDTO createTask(TaskDTO taskDTO){
        Task task = new Task();

        task.setName(taskDTO.name());
        task.setPrice(taskDTO.price());
        task.setStatusTask(StatusTask.AVAILABLE);

        Task saveTask = taskRepository.save(task);

        return taskDTOMapper.apply(saveTask);
    }

    public TaskDTO updateTask(TaskDTO taskDTO){
        findTaskById(taskDTO.id());

        Task task = new Task();

        task.setName(taskDTO.name());
        task.setPrice(taskDTO.price());
        task.setStatusTask(taskDTO.statusTask());

        Task updateTask = taskRepository.save(task);

        return taskDTOMapper.apply(updateTask);
    }

    public TaskDTO softDeleteTask(TaskDTO taskDTO){
        findTaskById(taskDTO.id());

        Task task = new Task();

        task.setStatusTask(StatusTask.UNAVAILABLE);

        Task updateTask = taskRepository.save(task);

        return taskDTOMapper.apply(updateTask);
    }

    public void deleteTask(Long id){
        if (findTaskById(id).isPresent()){
            taskRepository.deleteById(id);
        }
        // TODO return if the id didn't exist
    }
}
