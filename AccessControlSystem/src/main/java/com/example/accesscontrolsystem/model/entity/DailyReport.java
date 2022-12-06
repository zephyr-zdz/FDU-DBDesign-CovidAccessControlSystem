package com.example.accesscontrolsystem.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
