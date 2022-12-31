package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.user.Counsellor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounsellorMapper extends JpaRepository<Counsellor, Integer> {
    Counsellor findCounsellorById(Integer id);
    List<Counsellor> findAll();

    Counsellor findCounsellorByMyClassId(Integer id);
}
