package com.example.accesscontrolsystem.manager;

import com.example.accesscontrolsystem.mapper.ClassMapper;
import com.example.accesscontrolsystem.model.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("ClassManager")
public class ClassManager {
    private final ClassMapper classMapper;
    @Autowired
    public ClassManager(ClassMapper classMapper) {
        this.classMapper = classMapper;
    }
    public Optional<Class> findClassById(Integer id) {
        return classMapper.findClassById(id);
    }
}
