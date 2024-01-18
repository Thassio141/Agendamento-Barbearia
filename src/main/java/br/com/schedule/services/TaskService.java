package br.com.schedule.services;

import br.com.schedule.dtos.TaskDTO;
import br.com.schedule.models.Task;
import br.com.schedule.repositories.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDTO> findAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

//    public TaskDTO findByIdTask(Long id){
//        Optional<Task> task = taskRepository.findById(id);
//        return modelMapper.map(task,TaskDTO.class);
//    }

    public TaskDTO createTask(TaskDTO taskDTO){
        Task saveTask = modelMapper.map(taskDTO,Task.class);
        return modelMapper.map(taskRepository.save(saveTask),TaskDTO.class);
    }

    public TaskDTO updateTask(Long id,TaskDTO taskDTO){
        Optional<Task> task = taskRepository.findById(id);

        return modelMapper.map(task,TaskDTO.class);
    }
}
