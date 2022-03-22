package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itis.dtos.TaskDto;
import ru.itis.dtos.TaskFilter;
import ru.itis.dtos.TaskForm;
import ru.itis.models.Task;
import ru.itis.repositories.PageCacheRepository;
import ru.itis.repositories.TaskRepository;
import ru.itis.utils.TaskDatabaseFiller;

import javax.persistence.criteria.Predicate;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskDatabaseFiller filler;

    @Autowired
    private PageCacheRepository pageCacheRepository;

    @Override
    public Page<TaskDto> getTasks(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return taskRepository.findAll(pageable).map(TaskDto::from);
    }

    @Override
    public Page<TaskDto> getFilteredTasks(int page, int pageSize, TaskFilter taskFilter) {

        if(pageCacheRepository.hasKey(taskFilter)){
            return pageCacheRepository.getPage(taskFilter);
        }

        Specification<Task> spec = (r, cq, cb) -> {
            Predicate nameLike = null;
            if (!taskFilter.getNameLike().equals("")) {
                nameLike = cb.like(r.get(Task.NAME_FIELD), "%" + taskFilter.getNameLike() + "%");
            } else {
                nameLike = cb.and();
            }


            Predicate betweenStartAndFinish = null;
            if (taskFilter.getFinishDate() != null && taskFilter.getStartDate() != null) {
                betweenStartAndFinish =
                        cb.and(
                                cb.greaterThanOrEqualTo(r.get(Task.START_DATE_FIELD),
                                        Date.valueOf(taskFilter.getStartDate())),
                                cb.lessThanOrEqualTo(r.get(Task.FINISH_DATE_FIELD),
                                        Date.valueOf(taskFilter.getFinishDate())));
            } else {
                betweenStartAndFinish = cb.and();
            }


            Predicate done = null;
            if (taskFilter.getIsDone() != null) {
                done = cb.equal(r.get(Task.DONE_FIELD), taskFilter.getIsDone());
            } else {
                done = cb.and();
            }
            return cb.and(nameLike, betweenStartAndFinish, done);
        };
        Pageable pageable;
        if (!taskFilter.getSortBy().equals("")) {
            Sort sort = Sort.by(taskFilter.getSortBy());
            if (taskFilter.isDesc()) {
                sort = sort.descending();
            }
            pageable = PageRequest.of(page, pageSize, sort);
        } else {
            pageable = PageRequest.of(page, pageSize);
        }
        Page<TaskDto> taskDtos = taskRepository.findAll(spec, pageable).map(TaskDto::from);
        pageCacheRepository.addPage(taskFilter, taskDtos);
        return taskDtos;
    }

    @Override
    public void addTask(TaskForm taskForm) {
        pageCacheRepository.clearCache();
        Task newTask = Task.builder()
                .name(taskForm.getName())
                .description(taskForm.getDescription())
                .startDate(Date.valueOf(taskForm.getStartDate()))
                .finishDate(Date.valueOf(taskForm.getFinishDate()))
                .done(false)
                .build();
        taskRepository.save(newTask);
    }

    @Override
    public void updateTask(TaskForm taskForm, Long id) {
        Task task = taskRepository.getById(id);

        task.setName(taskForm.getName());


        task.setDescription(taskForm.getDescription());


        task.setStartDate(Date.valueOf(taskForm.getStartDate()));


        task.setFinishDate(Date.valueOf(taskForm.getFinishDate()));

        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void markTaskComplete(Long id) {
        Task task = taskRepository.getById(id);
        task.setDone(true);
        taskRepository.save(task);
    }

    @Override
    public void unmarkTaskComplete(Long id) {
        Task task = taskRepository.getById(id);
        task.setDone(false);
        taskRepository.save(task);
    }

    @Override
    public void fillDBWithRandomData(int count) {
        filler.fillWithRandomData(taskRepository, count);
    }

    @Override
    public Optional<TaskDto> getTaskById(Long id) {
        return taskRepository.findById(id).map(TaskDto::from);
    }
}
