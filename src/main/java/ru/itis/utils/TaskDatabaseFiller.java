package ru.itis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.models.Task;
import ru.itis.repositories.TaskRepository;

import java.sql.Date;
import java.util.Random;

@Component
public class TaskDatabaseFiller implements DatabaseFiller<TaskRepository> {

    private Random random;

    public TaskDatabaseFiller(){
        random = new Random();
    }

    @Override
    public void fillWithRandomData(TaskRepository repository, int count) {
        Task newTask;
        for(int i = 0; i < count; i++){
            long startMillis = random.nextInt(1000000000);
            long period = random.nextInt(1000000000);
            newTask = Task.builder()
                    .name("Task #" + i)
                    .description("This is " + i + " task")
                    .startDate(new Date(startMillis))
                    .finishDate(new Date(startMillis + period))
                    .done(random.nextBoolean())
                    .build();
            repository.save(newTask);
        }
    }
}
