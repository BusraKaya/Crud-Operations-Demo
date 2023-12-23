package com.example.demo.service;

import com.example.demo.dto.DemoDto;
import com.example.demo.model.Demo;

import java.util.List;

public interface DemoService {
    public Demo saveDemo(DemoDto demoDto);
    public Demo getDemoById(Long id);
    public void deleteDemoById(Long id);
    public List<Demo> getAllDemos();
    public Demo updateDemo(Long id, DemoDto demoDto);
    public List<Demo> getDemoListPagination(int page , int size);
}
