package ru.itis.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.itis.dtos.TaskDto;
import ru.itis.dtos.TaskFilter;
import ru.itis.models.RedisTaskFilter;
import ru.itis.models.Task;

public interface PageCacheRepository {
    void addPage(TaskFilter filter, Page<TaskDto> page);

    Page<TaskDto> getPage(TaskFilter filter);

    void clearCache();

    boolean hasKey(TaskFilter filter);
}
