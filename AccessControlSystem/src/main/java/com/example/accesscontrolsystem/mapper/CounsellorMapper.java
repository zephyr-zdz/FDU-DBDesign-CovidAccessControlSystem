package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.Counsellor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CounsellorMapper extends JpaRepository<Counsellor, Integer> {
    Optional<Counsellor> findCounsellorByName(String name);
    Optional<Counsellor> findCounsellorById(Integer id);
    List<Counsellor> findAll();
}
