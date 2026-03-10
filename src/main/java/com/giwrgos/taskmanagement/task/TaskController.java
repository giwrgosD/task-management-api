package com.giwrgos.taskmanagement.task;

import com.giwrgos.taskmanagement.task.dto.CreateTaskRequest;
import com.giwrgos.taskmanagement.task.dto.TaskResponse;
import com.giwrgos.taskmanagement.task.dto.UpdateTaskRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public Page<TaskResponse> getTasks(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPriority priority,
            Pageable pageable) {
        return taskService.getTasks(status, priority, pageable);
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public TaskResponse createTask(@Valid @RequestBody CreateTaskRequest request) {
        return taskService.createTask(request);
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id,
                                   @Valid @RequestBody UpdateTaskRequest request) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}