package com.example.accesscontrolsystem.model.entity.reportNlog;

import com.example.accesscontrolsystem.model.entity.user.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
    @Column(name = "create-time", nullable = false)
    private Long createTime; // utf-8编码的时间戳，例如：1607766400000
    @Column(name = "location", nullable = false, length = 45)
    private String location;
    @Column(name = "temperature", nullable = false)
    public BigDecimal temperature; // 36.5
    @Column(name = "other", nullable = false, length = 45)
    private String other;
    @ManyToOne
    @JoinColumn(name = "student-id", nullable = false)
    private Student student;
    @Column(name = "time", nullable = false)
    private Long time; // utf-8编码的时间戳，例如：1607766400000
    @Column(name = "date", nullable = false)
    private String date; // utf-8编码的时间戳，例如：1607766400000
}
