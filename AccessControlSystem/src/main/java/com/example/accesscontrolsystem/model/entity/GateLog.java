package com.example.accesscontrolsystem.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gate_log")
public class GateLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "time", nullable = false)
    private String  time; // utf-8编码的时间字符串，例如：2020-12-12 12:12:12
    @Column(name = "direction", nullable = false)
    private String  direction; // in/out
    @Column(name = "student-id", nullable = false)
    private Integer studentId;
    @Column(name = "campus-id", nullable = false)
    private Integer campusId;
}
