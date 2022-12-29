package com.example.accesscontrolsystem.model.entity.reportNlog;

import com.example.accesscontrolsystem.model.entity.Campus;
import com.example.accesscontrolsystem.model.entity.Major;
import com.example.accesscontrolsystem.model.entity.user.Student;
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
    @ManyToOne
    @JoinColumn(name = "student-id", nullable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(name = "student-major-id", nullable = false)
    private Major major;
    @ManyToOne
    @JoinColumn(name = "campus-id", nullable = false)
    private Campus campus;
    @Column(name = "leave-duration")
    private Double leaveDuration; // 离开时长，单位:hour
}
