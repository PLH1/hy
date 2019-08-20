package com.example.demo.redis.serivce;

import com.example.demo.redis.mode.Depa;
import com.example.demo.redis.repository.DepsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author panlihuan
 * @date 2019/8/20
 */
@Service
public class DepaService {


    @Autowired
    DepsRepository depsRepository;
    @Autowired
    RedisTemplate redisTemplate;

    public Depa getDepa(String title){
        long startTime=System.currentTimeMillis();
        String key= "/depa/"+title;
        boolean hasKey = redisTemplate.hasKey(key);
        Depa depa=null;
        if (hasKey) {
            // 从缓存中取
             depa= (Depa)redisTemplate.opsForValue().get(key);
        }else {
            depa=depsRepository.findByTitle(title);
            redisTemplate.opsForValue().set(key, depa, 600, TimeUnit.SECONDS);
        }
        long endTime=System.currentTimeMillis();
        depa.setTime(endTime-startTime);
        return  depa;
    }
}
