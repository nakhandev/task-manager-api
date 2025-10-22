package org.nakhan.task_manager_api.service;

import org.nakhan.task_manager_api.model.Task;
import org.nakhan.task_manager_api.model.User;
import org.nakhan.task_manager_api.model.TaskStatus;
import org.nakhan.task_manager_api.model.TaskPriority;
import org.nakhan.task_manager_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.ArrayList;

@SpringJUnitConfig
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void testCreateTask() {
        User user = new User("testuser", "test@example.com", "password");
        Task task = new Task("Test Task", "Description", TaskStatus.PENDING, TaskPriority.HIGH, user);
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.createTask(task);

        assertNotNull(result);
        verify(taskRepository).save(task);
    }

    @Test
    public void testGetAllTasksByUser() {
        User user = new User("testuser", "test@example.com", "password");
        List<Task> tasks = new ArrayList<>();
        when(taskRepository.findByUser(user)).thenReturn(tasks);

        List<Task> result = taskService.getAllTasksByUser(user);

        assertEquals(tasks, result);
    }
}
