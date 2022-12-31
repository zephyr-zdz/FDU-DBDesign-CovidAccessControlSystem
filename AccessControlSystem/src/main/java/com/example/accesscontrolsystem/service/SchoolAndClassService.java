package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.manager.SchoolAndClassManager;
import com.example.accesscontrolsystem.model.entity.Class;
import com.example.accesscontrolsystem.model.entity.Major;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SchoolAndClassService")
public class SchoolAndClassService {
    private final SchoolAndClassManager schoolAndClassManager;
    public SchoolAndClassService(SchoolAndClassManager schoolAndClassManager) {
        this.schoolAndClassManager = schoolAndClassManager;
    }

    public Response<List<Major>> findAllMajors() {
        return new Response<>(Response.SUCCESS, "查询成功", schoolAndClassManager.findAllMajors());
    }

    public Response<List<Class>> findAllClasses() {
        return new Response<>(Response.SUCCESS, "查询成功", schoolAndClassManager.findAllClasses());
    }

    public Response<List<Class>> findClassesByMajorId(Integer majorId) {
        return new Response<>(Response.SUCCESS, "查询成功", schoolAndClassManager.findClassesByMajorId(majorId));
    }
}
