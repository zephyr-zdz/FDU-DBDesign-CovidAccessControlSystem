
package com.example.accesscontrolsystem.mapper;

import com.example.accesscontrolsystem.model.entity.Campus;
import com.example.accesscontrolsystem.model.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampusMapper extends JpaRepository<Campus, Integer> {
    @Query("select c from Campus c where c.id = ?1")
    Campus findCampusById(Integer id);
    Campus findCampusByName(String name);
}
