package org.nakhan.task_manager_api.controller;

import org.nakhan.task_manager_api.model.Task;
import org.nakhan.task_manager_api.model.User;
import org.nakhan.task_manager_api.model.TaskStatus;
import org.nakhan.task_manager_api.model.TaskPriority;
import org.nakhan.task_manager_api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        task.setUser(user);
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(@RequestParam(required = false) TaskStatus status,
                                               @RequestParam(required = false) TaskPriority priority) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Task> tasks;
        if (status != null && priority != null) {
            tasks = taskService.getTasksByStatusAndPriority(user, status, priority);
        } else if (status != null) {
            tasks = taskService.getTasksByStatus(user, status);
        } else if (priority != null) {
            tasks = taskService.getTasksByPriority(user, priority);
        } else {
            tasks = taskService.getAllTasksByUser(user);
        }
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return ResponseEntity.ok(taskService.updateTask(id, taskDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
