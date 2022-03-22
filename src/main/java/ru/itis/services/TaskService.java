package ru.itis.services;

import org.springframework.data.domain.Page;
import ru.itis.dtos.TaskDto;
import ru.itis.dtos.TaskFilter;
import ru.itis.dtos.TaskForm;
import ru.itis.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Page<TaskDto> getTasks(int page, int pageSize);

    Page<TaskDto> getFilteredTasks(int page, int pageSize, TaskFilter taskFilter);

    void addTask(TaskForm taskForm);

    void updateTask(TaskForm taskForm, Long id);

    void deleteTask(Long id);

    void markTaskComplete(Long id);

    void unmarkTaskComplete(Long id);

    void fillDBWithRandomData(int count);

    Optional<TaskDto> getTaskById(Long id);
}
