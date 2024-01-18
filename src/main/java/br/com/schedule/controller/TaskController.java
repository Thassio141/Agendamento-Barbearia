package br.com.schedule.controller;

import br.com.schedule.dtos.TaskDTO;
import br.com.schedule.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public List<TaskDTO> findAllTasks(){
        return taskService.findAllTasks();
    }

//    @GetMapping( "/{id}")
//    public ResponseEntity<TaskDTO> findByIdTask(@PathVariable Long id){
//        return new ResponseEntity<>(taskService.findByIdTask(id), HttpStatus.OK);
//    }

    @PostMapping("/create")
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO){
        return taskService.createTask(taskDTO);
    }

    @PutMapping("/update/{id}")
    public TaskDTO updateTask(@PathVariable Long id,@RequestBody TaskDTO taskDTO){
        return taskService.updateTask(id,taskDTO);
    }
}
