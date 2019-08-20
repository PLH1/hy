package com.example.demo.redis.repository;

import com.example.demo.redis.mode.Depa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepsRepository extends JpaRepository<Depa, Integer> {
    public Depa findByTitle(String title);
}
