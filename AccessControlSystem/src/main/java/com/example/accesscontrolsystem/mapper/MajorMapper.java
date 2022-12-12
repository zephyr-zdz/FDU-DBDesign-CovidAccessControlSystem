package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorMapper extends JpaRepository<Major, Integer> {

    Major findMajorById(Integer id);
}
