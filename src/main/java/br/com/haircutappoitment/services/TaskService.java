package br.com.haircutappoitment.services;

import java.util.List;
import java.util.stream.Collectors;

import br.com.haircutappoitment.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.haircutappoitment.domain.dtos.task.TaskCreateDto;
import br.com.haircutappoitment.domain.dtos.task.TaskDto;
import br.com.haircutappoitment.domain.entities.TaskEntity;
import br.com.haircutappoitment.domain.enums.ActivityStatus;
import br.com.haircutappoitment.repositories.TaskRepository;

@Service
public class TaskService {
    
    private final TaskRepository taskRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    public List<TaskDto> findAllTask(){
        List<TaskEntity> taskEntity = taskRepository.findAll();
        return taskEntity.stream()
            .map(this::convertEntityToDto)
            .collect(Collectors.toList());
    }

    public TaskDto findTaskById(Long id){
        return convertEntityToDto(findByIdEntity(id));
    }

    public Page<TaskDto> findAllPageable(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskEntity> taskEntities = taskRepository.findAllBy(pageable);

        return taskEntities.map(this::convertEntityToDto);
    }

    public TaskCreateDto createTask(TaskCreateDto taskCreateDto){
        TaskEntity taskEntity = convertCreateDtoToEntity(taskCreateDto);
        taskEntity.setStatusTask(ActivityStatus.ACTIVE);
        TaskEntity task = taskRepository.save(taskEntity);
        return convertEntityToCreateDto(task);
    }

    public TaskCreateDto updateTask(Long id, TaskCreateDto taskCreateDto){
        TaskEntity taskEntity = findByIdEntity(id);

        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        modelMapper.map(taskCreateDto, taskEntity);

        taskRepository.save(taskEntity);

        return taskCreateDto;
    }

    public TaskDto softDeleteTask(Long id){
        TaskEntity taskEntity = findByIdEntity(id);
        taskEntity.setStatusTask(ActivityStatus.INACTIVE);
        return convertEntityToDto(taskRepository.save(taskEntity));
    }

    public TaskDto activateTask(Long id){
        TaskEntity taskEntity = findByIdEntity(id);
        taskEntity.setStatusTask(ActivityStatus.ACTIVE);
        return convertEntityToDto(taskRepository.save(taskEntity));
    }

    public void deleteTask(Long id){
        TaskEntity taskEntity = findByIdEntity(id);
        taskRepository.delete(taskEntity);
    }

    private TaskEntity findByIdEntity(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not found task with id:" + id));
    }

    private TaskDto convertEntityToDto(TaskEntity taskEntity){
        return modelMapper.map(taskEntity, TaskDto.class);
    }

    private TaskEntity convertCreateDtoToEntity(TaskCreateDto taskCreateDto){
        return modelMapper.map(taskCreateDto, TaskEntity.class);
    }

    private TaskCreateDto convertEntityToCreateDto(TaskEntity taskEntity){
        return modelMapper.map(taskEntity, TaskCreateDto.class);
    }
}
