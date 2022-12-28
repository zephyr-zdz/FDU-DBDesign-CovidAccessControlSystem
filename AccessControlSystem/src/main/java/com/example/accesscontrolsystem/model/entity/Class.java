package com.example.accesscontrolsystem.model.entity;

import com.example.accesscontrolsystem.model.entity.user.Counsellor;
import com.example.accesscontrolsystem.model.entity.user.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "class")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @ManyToOne
    @JoinColumn(name = "major-id", nullable = false)
    private Major major;
    @OneToOne(mappedBy = "myClass")
    private Counsellor counsellor;
    @OneToMany(mappedBy = "myClass")
    private List<Student> studentList;
}
