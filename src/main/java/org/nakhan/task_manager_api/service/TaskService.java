package org.nakhan.task_manager_api.service;

import org.nakhan.task_manager_api.model.Task;
import org.nakhan.task_manager_api.model.User;
import org.nakhan.task_manager_api.model.TaskStatus;
import org.nakhan.task_manager_api.model.TaskPriority;
import org.nakhan.task_manager_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setPriority(taskDetails.getPriority());
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getAllTasksByUser(User user) {
        return taskRepository.findByUser(user);
    }

    public List<Task> getTasksByStatus(User user, TaskStatus status) {
        return taskRepository.findByUserAndStatus(user, status);
    }

    public List<Task> getTasksByPriority(User user, TaskPriority priority) {
        return taskRepository.findByUserAndPriority(user, priority);
    }

    public List<Task> getTasksByStatusAndPriority(User user, TaskStatus status, TaskPriority priority) {
        return taskRepository.findByUserAndStatusAndPriority(user, status, priority);
    }
}
