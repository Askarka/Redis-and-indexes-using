package ru.itis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import ru.itis.dtos.TaskDto;
import ru.itis.dtos.TaskFilter;

@SpringBootApplication
public class MySiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySiteApplication.class, args);
    }


    @Bean
    public RedisTemplate<String, Page<TaskDto>> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        final RedisTemplate<String, Page<TaskDto>> template = new RedisTemplate<String, Page<TaskDto>>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }

    @Bean
    public HashMapper<Object, byte[], byte[]> hashMapper(){
        return new ObjectHashMapper();
    }

}
