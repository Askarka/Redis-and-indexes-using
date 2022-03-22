package ru.itis.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskForm {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate finishDate;
    private boolean done;
}
