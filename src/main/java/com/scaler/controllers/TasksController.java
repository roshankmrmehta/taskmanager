package com.scaler.controllers;

import com.scaler.dto.CreateTaskDTO;
import com.scaler.dto.ErrorResponseDTO;
import com.scaler.dto.UpdateTaskDTO;
import com.scaler.entities.TaskEntity;
import com.scaler.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TaskService taskService;

    public TasksController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks(){
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTasksById(@PathVariable int id){
        var task = taskService.getTaskById(id);
        if(task == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        var task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable int id, @RequestBody UpdateTaskDTO updateTaskDTO) throws ParseException {
        var task = taskService.updateTask(id, updateTaskDTO.getDescription(), updateTaskDTO.getDeadline(), updateTaskDTO.getCompleted());
        if(task == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid date format"));
        }
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error"));
    }
}
