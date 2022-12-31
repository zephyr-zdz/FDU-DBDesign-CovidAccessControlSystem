package com.example.accesscontrolsystem.controller.common;

import com.example.accesscontrolsystem.model.entity.Class;
import com.example.accesscontrolsystem.model.entity.Major;
import com.example.accesscontrolsystem.service.SchoolAndClassService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("SchoolAndClassController")
public class SchoolAndMajorController {
    private final SchoolAndClassService schoolAndClassService;
    @Autowired
    public SchoolAndMajorController(SchoolAndClassService schoolAndClassService) {
        this.schoolAndClassService = schoolAndClassService;
    }
    @GetMapping("/majors")
    public Response<List<Major>> findAllMajors() {
        return schoolAndClassService.findAllMajors();
    }
    @GetMapping("/classes")
    public Response<List<Class>> findAllClasses() {
        return schoolAndClassService.findAllClasses();
    }
    @GetMapping("/classes/{majorId}")
    public Response<List<Class>> findClassesByMajorId(@PathVariable Integer majorId) {
        return schoolAndClassService.findClassesByMajorId(majorId);
    }
}
