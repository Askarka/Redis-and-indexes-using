package ru.itis.dtos;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskFilter implements Serializable {
    private String nameLike;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Boolean isDone;
    private String sortBy;
    private boolean desc;
}
