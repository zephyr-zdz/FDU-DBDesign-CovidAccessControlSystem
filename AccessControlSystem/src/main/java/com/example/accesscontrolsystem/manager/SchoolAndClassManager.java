package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.ClassMapper;
import com.example.accesscontrolsystem.mapper.MajorMapper;
import com.example.accesscontrolsystem.model.entity.Major;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("SchoolAndClassManager")
public class SchoolAndClassManager {
    private final MajorMapper majorMapper;
    private final ClassMapper classMapper;
    @Autowired
    public SchoolAndClassManager(MajorMapper majorMapper, ClassMapper classMapper) {
        this.majorMapper = majorMapper;
        this.classMapper = classMapper;
    }
    public List<Major> findAllMajors() {
        return majorMapper.findAll();
    }
    public List<com.example.accesscontrolsystem.model.entity.Class> findAllClasses() {
        return classMapper.findAll();
    }

    public List<com.example.accesscontrolsystem.model.entity.Class> findClassesByMajorId(Integer majorId) {
        return classMapper.findClassesByMajorId(majorId);
    }
}
