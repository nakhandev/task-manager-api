package org.nakhan.task_manager_api.repository;

import org.nakhan.task_manager_api.model.Task;
import org.nakhan.task_manager_api.model.User;
import org.nakhan.task_manager_api.model.TaskStatus;
import org.nakhan.task_manager_api.model.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    List<Task> findByUserAndStatus(User user, TaskStatus status);
    List<Task> findByUserAndPriority(User user, TaskPriority priority);
    List<Task> findByUserAndStatusAndPriority(User user, TaskStatus status, TaskPriority priority);
}
