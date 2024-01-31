package br.com.haircutappoitment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.haircutappoitment.models.dtos.task.TaskCreateDto;
import br.com.haircutappoitment.services.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("task")
public class TaskController {

	@Autowired
	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@PostMapping("/create")
	public ResponseEntity<TaskCreateDto> createTask(@RequestBody TaskCreateDto taskCreateDto) {
		taskService.createTask(taskCreateDto);
		return new ResponseEntity<>(taskCreateDto, HttpStatus.CREATED);
	}

}
