package br.com.haircutappoitment.controllers;

import br.com.haircutappoitment.domain.dtos.task.TaskDto;
import br.com.haircutappoitment.exceptions.BadRequestException;
import br.com.haircutappoitment.exceptions.InternalServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.haircutappoitment.domain.dtos.task.TaskCreateDto;
import br.com.haircutappoitment.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

	private final TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}


	@GetMapping
	public ResponseEntity<List<TaskDto>> findAllTasks()
			throws BadRequestException, InternalServerErrorException {
		return new ResponseEntity<>(taskService.findAllTask(),HttpStatus.OK);
	}

	@GetMapping("/pageable")
	public ResponseEntity<Page<TaskDto>> findAllPageable(@RequestParam Integer page, @RequestParam Integer size)
			throws BadRequestException, InternalServerErrorException{
		return new ResponseEntity<>(taskService.findAllPageable(page,size),HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskDto> findTaskById(@PathVariable Long id)
			throws BadRequestException, InternalServerErrorException{
		return new ResponseEntity<>(taskService.findTaskById(id),HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<TaskCreateDto> createTask(@RequestBody TaskCreateDto taskCreateDto)
			throws BadRequestException, InternalServerErrorException{
		return new ResponseEntity<>(taskService.createTask(taskCreateDto), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<TaskCreateDto> updateTask(@PathVariable Long id, @RequestBody TaskCreateDto taskCreateDto)
			throws BadRequestException, InternalServerErrorException{
		return new ResponseEntity<>(taskService.updateTask(id,taskCreateDto),HttpStatus.OK);
	}

	@PutMapping("/softdelete/{id}")
	public ResponseEntity<TaskDto> softDeleteTask(@PathVariable Long id)
			throws BadRequestException, InternalServerErrorException{
		return new ResponseEntity<>(taskService.softDeleteTask(id),HttpStatus.OK);
	}

	@PutMapping("/activatetask/{id}")
	public ResponseEntity<TaskDto> activateTask(@PathVariable Long id)
			throws BadRequestException, InternalServerErrorException{
		return new ResponseEntity<>(taskService.activateTask(id),HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id)
			throws BadRequestException, InternalServerErrorException{
		taskService.deleteTask(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
