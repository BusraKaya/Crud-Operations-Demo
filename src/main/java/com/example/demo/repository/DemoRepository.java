package com.example.demo.repository;

import com.example.demo.model.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DemoRepository extends JpaRepository<Demo,Long> {
    public Optional<Demo> findByName(String name);
}
