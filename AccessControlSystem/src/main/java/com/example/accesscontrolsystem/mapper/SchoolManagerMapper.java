package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.user.SchoolManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolManagerMapper extends JpaRepository<SchoolManager, Integer> {

    @Query("select s from SchoolManager s where s.id = ?1")
    SchoolManager findSchoolManagerById(Integer managerId);

    @Query("select s from SchoolManager s where s.major.id = ?1")
    SchoolManager findByMajorId(Integer id);
}
