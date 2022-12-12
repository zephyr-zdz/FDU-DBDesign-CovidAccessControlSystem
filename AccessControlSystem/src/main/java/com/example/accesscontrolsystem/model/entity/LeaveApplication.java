package com.example.accesscontrolsystem.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leave_application")
public class LeaveApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "reason", nullable = false)
    private String reason;
    @Column(name = "destination", nullable = false)
    private String destination; // 上海市杨浦区邯郸路221号
    @Column(name = "leave-time", nullable = false)
    private String leaveTime; // utf-8编码的时间字符串，例如：2020-12-12 12:12:12
    @Column(name = "return-time", nullable = false)
    private String returnTime; // utf-8编码的时间字符串，例如：2020-12-12 12:12:12
    @Column(name = "status", nullable = false)
    private String status; // 申请状态：pending/accepted/rejected
    @Column(name = "student-id", nullable = false)
    private Integer studentId;
    @Column(name = "date", nullable = false)
    private String date;
}
