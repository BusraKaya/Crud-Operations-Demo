package com.example.demo.controller;

import com.example.demo.aspect.Log;
import com.example.demo.dto.DemoDto;
import com.example.demo.model.Demo;
import com.example.demo.repository.DemoRepository;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/demo")
@RequiredArgsConstructor
public class DemoController {
    private final DemoService demoService;
    private final DemoRepository demoRepository;

    @PostMapping("/save")
    public ResponseEntity saveDemo(@RequestBody DemoDto demoDto) {
        Demo demo = demoService.saveDemo(demoDto);
        return new ResponseEntity(ResponseHandler.generateResponse(HttpStatus.CREATED, demo), HttpStatus.CREATED);
    }

    @GetMapping("/demos")
    public ResponseEntity getAllDemos() {
        return new ResponseEntity<>(ResponseHandler.generateResponse(HttpStatus.OK, demoService.getAllDemos()), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getDemoById(@RequestParam(name = "param1", required = true) Long id) {
        return new ResponseEntity<>(ResponseHandler.generateResponse(demoService.getDemoById(id)), HttpStatus.OK);
    }

    @Log
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDemoById(@PathVariable Long id) {
        demoService.deleteDemoById(id);
        return new ResponseEntity<>(ResponseHandler.generateResponse(HttpStatus.OK), HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateEntity(@PathVariable Long id, @RequestBody DemoDto demoDto) {
        Demo demo = demoService.updateDemo(id, demoDto);
        return new ResponseEntity(ResponseHandler.generateResponse("Updated successfully", HttpStatus.OK, demo), HttpStatus.OK);
    }

    @Log
    @GetMapping("/pagination")
    public ResponseEntity getItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return new ResponseEntity(ResponseHandler.generateResponse(demoService.getDemoListPagination(page, size)), HttpStatus.OK);
    }
}
