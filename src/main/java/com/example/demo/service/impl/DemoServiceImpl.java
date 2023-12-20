package com.example.demo.service.impl;

import com.example.demo.dto.DemoDto;
import com.example.demo.exception.DemoAlreadyExistsException;
import com.example.demo.exception.DemoNotFoundException;
import com.example.demo.model.Demo;
import com.example.demo.repository.DemoRepository;
import com.example.demo.service.DemoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DemoServiceImpl implements DemoService {
    private final DemoRepository demoRepository;

    @Override
    public Demo saveDemo(DemoDto demoDto) {
        log.info("saveDemo method is called");
        Optional<Demo> demoByName = demoRepository.findByName(demoDto.getName());
        if (demoByName.isPresent()) {
            throw new DemoAlreadyExistsException("Demo already exits with this name!");
        }
        Demo demo = Demo.builder().name(demoDto.getName()).age(demoDto.getAge()).build();
        demoRepository.save(demo);
        log.info("Demo object is saved successfully");
        return demo;
    }

    @Override
    public List<Demo> getAllDemos() {
        log.info("getAllDemos method is called");
        return demoRepository.findAll();
    }

    @Override
    public Demo getDemoById(Long id) {
        log.info("saveDemo method is called");
        return demoRepository.findById(id).orElseThrow(() -> new DemoNotFoundException("Demo entity not found with this id!"));
    }

    @Override
    public void deleteDemoById(Long id) {
        log.info("deleteDemoById method is called");
        Optional<Demo> demo = demoRepository.findById(id);
        if (!demo.isPresent()) {
            log.error("Entity not found with id: " + id);
            throw new DemoNotFoundException("Demo entity not found!");
        }
        demoRepository.deleteById(id);
        log.info("Demo object is deleted successfully");
    }

    @Override
    public Demo updateDemo(Long id, DemoDto demoDto) {
        log.info("updateDemo method is called");

        Demo existingDemo = demoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
        existingDemo.setName(demoDto.getName());
        existingDemo.setAge(demoDto.getAge());

        log.info("Demo object is updated successfully");

        return demoRepository.save(existingDemo);
    }

    @Override
    public List<Demo> getDemoListPagination(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Demo> demoPage = demoRepository.findAll(pageRequest);
        return demoPage.toList();
    }
}
