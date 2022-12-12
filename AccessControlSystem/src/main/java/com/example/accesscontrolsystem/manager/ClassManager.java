package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.ClassMapper;
import com.example.accesscontrolsystem.model.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ClassManager")
public class ClassManager {
    private final ClassMapper classMapper;
    @Autowired
    public ClassManager(ClassMapper classMapper) {
        this.classMapper = classMapper;
    }
    public Class findClassById(Integer id) {
        return classMapper.findClassById(id);
    }

    public Class getClassById(Integer classId) {
        return classMapper.getClassById(classId);
    }

    public List<Class> findClassesByMajorId(Integer id) {
        return classMapper.findClassesByMajorId(id);
    }
}
