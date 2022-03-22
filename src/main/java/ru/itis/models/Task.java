package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(indexes = {@Index(name = "name_index",columnList = "name"),
        @Index(name = "start_date_index", columnList = "startDate"),
        @Index(name = "finish_date_index", columnList = "finishDate")})
public class Task {

    public static final String NAME_FIELD = "name";
    public static final String DESCRIPTION_FIELD = "description";
    public static final String START_DATE_FIELD = "startDate";
    public static final String FINISH_DATE_FIELD = "finishDate";
    public static final String DONE_FIELD = "done";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Date startDate;
    private Date finishDate;
    private Boolean done;
}
