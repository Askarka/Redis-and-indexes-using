package ru.itis.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Task;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto implements Serializable {
    private Long id;

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Boolean done;

    public static TaskDto from(Task task){
        return TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .finishDate(task.getFinishDate().toLocalDate())
                .startDate(task.getStartDate().toLocalDate())
                .done(task.getDone())
                .build();

    }

    public static List<TaskDto> from(List<Task> taskList){
        return taskList.stream().map(TaskDto::from).collect(Collectors.toList());
    }

}
