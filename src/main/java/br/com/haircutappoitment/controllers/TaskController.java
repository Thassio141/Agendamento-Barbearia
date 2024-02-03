package br.com.haircutappoitment.controllers;

import br.com.haircutappoitment.domain.dtos.task.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.haircutappoitment.domain.dtos.task.TaskCreateDto;
import br.com.haircutappoitment.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

	@Autowired
	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}


	@GetMapping
	public ResponseEntity<List<TaskDto>> findAllTasks(){
		return new ResponseEntity<>(taskService.findAllTask(),HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskDto> findTaskById(@PathVariable Long id){
		return new ResponseEntity<>(taskService.findTaskById(id),HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<TaskCreateDto> createTask(@RequestBody TaskCreateDto taskCreateDto) {
		taskService.createTask(taskCreateDto);
		return new ResponseEntity<>(taskCreateDto, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id){
		taskService.deleteTask(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
