package com.br.scheduling.services;

import com.br.scheduling.dtos.TaskDTO;
import com.br.scheduling.dtos.TaskDTOMapper;
import com.br.scheduling.models.Task;
import com.br.scheduling.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskDTOMapper taskDTOMapper;

    public Optional<Task> findTaskById(Long id){
        //TODO
        return taskRepository.findById(id);
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
    public TaskDTO createTask(){
        //TODO
        return null;
    }

    public TaskDTO updateTask(){
        //TODO
        return null;
    }

    public TaskDTO softDeleteTask(){
        //TODO
        return null;
    }

    public void deleteTask(Long id){
        //TODO verify if the task exist
        taskRepository.deleteById(id);
    }

    public Optional<Task> verifyTaskExistById(Long id){
        //TODO
        return taskRepository.findById(id);
    }

}
