package com.giwrgos.taskmanagement.task;

import com.giwrgos.taskmanagement.common.exception.ResourceNotFoundException;
import com.giwrgos.taskmanagement.task.dto.CreateTaskRequest;
import com.giwrgos.taskmanagement.task.dto.TaskResponse;
import com.giwrgos.taskmanagement.task.dto.UpdateTaskRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Page<TaskResponse> getTasks(TaskStatus status, TaskPriority priority, Pageable pageable) {
        Page<Task> tasks;

        if (status != null && priority != null) {
            tasks = taskRepository.findByStatusAndPriority(status, priority, pageable);
        } else if (status != null) {
            tasks = taskRepository.findByStatus(status, pageable);
        } else if (priority != null) {
            tasks = taskRepository.findByPriority(priority, pageable);
        } else {
            tasks = taskRepository.findAll(pageable);
        }

        return tasks.map(this::mapToResponse);
    }

    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        return mapToResponse(task);
    }

    public TaskResponse createTask(CreateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }

    public TaskResponse updateTask(Long id, UpdateTaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        task.setUpdatedAt(LocalDateTime.now());

        Task updatedTask = taskRepository.save(task);
        return mapToResponse(updatedTask);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        taskRepository.delete(task);
    }

    private TaskResponse mapToResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate()
        );
    }
}