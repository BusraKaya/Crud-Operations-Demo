package com.example.demo.controller;

import com.example.demo.dto.DemoDto;
import com.example.demo.model.Demo;
import com.example.demo.repository.DemoRepository;
import com.example.demo.response.GenericResponse;
import com.example.demo.response.ResponseHandler;
import com.example.demo.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public GenericResponse<Object> saveDemo(@RequestBody DemoDto demoDto){
        Demo demo = demoService.saveDemo(demoDto);
        return ResponseHandler.generateResponse(HttpStatus.CREATED, demo);
       // ResponseHandler responseHandler = new ResponseHandler(); Utility sınıfların objesi olmaz
    }

    @GetMapping("/demos")
    public GenericResponse<Object> getAllDemos(){
        return ResponseHandler.generateResponse(HttpStatus.OK, demoService.getAllDemos());
    }

    @GetMapping()
    public GenericResponse<Object> getDemoById(@RequestParam(name = "param1", required = true) Long id){
        return ResponseHandler.generateResponse(demoService.getDemoById(id));
    }

    @DeleteMapping("/delete/{id}")
    public GenericResponse<Object> deleteDemoById(@PathVariable Long id){
        demoService.deleteDemoById(id);
        return ResponseHandler.generateResponse(HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public GenericResponse<Object> updateEntity(@PathVariable Long id, @RequestBody DemoDto demoDto){
        Demo demo = demoService.updateDemo(id, demoDto);
        return ResponseHandler.generateResponse(demo);
    }

    @GetMapping("/pagination")
    public GenericResponse<Object> getItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseHandler.generateResponse(demoService.getDemoListPagination(page, size));
    }
}
