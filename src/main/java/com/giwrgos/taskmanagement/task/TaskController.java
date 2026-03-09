package com.giwrgos.taskmanagement.task;

import com.giwrgos.taskmanagement.task.dto.CreateTaskRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAllTasks();
    }

    @PostMapping
    public Task createTask(@jakarta.validation.Valid @RequestBody CreateTaskRequest request) {
        return taskService.createTask(request);
    }
}
