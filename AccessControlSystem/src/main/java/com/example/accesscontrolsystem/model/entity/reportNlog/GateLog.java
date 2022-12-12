package com.example.accesscontrolsystem.model.entity.reportNlog;

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
    private long time; // utf-8编码的时间字符串，例如：2020-12-12 12:12:12
    @Column(name = "direction", nullable = false)
    private String  direction; // in/out
    @Column(name = "student-id", nullable = false)
    private Integer studentId;
    @Column(name = "class-id", nullable = false) // 辅助索引
    private Integer classId;
    @Column(name = "major-id", nullable = false) // 辅助索引
    private Integer majorId;
    @Column(name = "campus-id", nullable = false)
    private Integer campusId;
}
