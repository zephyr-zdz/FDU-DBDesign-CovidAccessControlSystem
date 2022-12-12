package com.example.accesscontrolsystem.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "daily_report")
public class DailyReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "location", nullable = false, length = 45)
    private String location;
    @Column(name = "temperature", nullable = false)
    public Integer temperature; // 是真实温度*10的结果，例如：36.5度，就是365
    @Column(name = "other", nullable = false, length = 45)
    private String other;
    @Column(name = "student-id", nullable = false)
    private Integer studentId;
}
