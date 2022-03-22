package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.stereotype.Component;
import ru.itis.dtos.TaskDto;
import ru.itis.dtos.TaskFilter;
import ru.itis.models.Task;

@Component
public class PageCacheRedisRepository implements PageCacheRepository{

    public static final String KEY = "pages";

    @Autowired
    private RedisTemplate<String, Page<TaskDto>> redisTemplate;



    @Override
    public void addPage(TaskFilter filter, Page<TaskDto> page) {
        redisTemplate.opsForHash().put(KEY, filter, page);
    }

    @Override
    public Page<TaskDto> getPage(TaskFilter filter) {
        Page<TaskDto> page =  (Page<TaskDto>) redisTemplate.opsForHash().get(KEY, filter);
        System.out.println(page);
        return page;
    }

    @Override
    public void clearCache() {
        for(Object o : redisTemplate.opsForHash().keys(KEY)){
            redisTemplate.opsForHash().delete(KEY, o);
        }
    }

    @Override
    public boolean hasKey(TaskFilter filter) {
        return redisTemplate.opsForHash().hasKey(KEY, filter);
    }
}
