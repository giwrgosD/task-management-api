package com.giwrgos.taskmanagement.task;

import com.giwrgos.taskmanagement.task.dto.CreateTaskRequest;
import com.giwrgos.taskmanagement.task.dto.TaskResponse;
import com.giwrgos.taskmanagement.task.dto.UpdateTaskRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springdoc.core.annotations.ParameterObject;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Parameters({
            @Parameter(name = "page", description = "Page number (0-based)"),
            @Parameter(name = "size", description = "Number of items per page"),
            @Parameter(name = "sort", description = "Sorting field, e.g. title or dueDate")
    })
    public Page<TaskResponse> getTasks(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPriority priority,
            @ParameterObject Pageable pageable) {

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