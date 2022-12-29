package com.example.accesscontrolsystem.model.entity.reportNlog;

import com.example.accesscontrolsystem.model.entity.user.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enter_application")
public class EnterApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "passing-areas", nullable = false, length = 45)
    private String passingAreas;// 虹口区, 杨浦区, 闵行区 注意是英文逗号分隔
    @Column(name = "create-time", nullable = false)
    private Long createTime; // utf-8编码的时间戳，例如：1607760000
    @Column(name = "accept-time")
    private Long acceptTime; // utf-8编码的时间戳，例如：1607760000
    @Column(name = "enter-time", nullable = false)
    private Long enterTime; // utf-8编码的时间戳，例如：1607760000
    @Column(name = "status", nullable = false, length = 10)
    private String status; // pending/counsellor/manager/accepted/rejected
    @ManyToOne
    @JoinColumn(name = "student-id", nullable = false)
    private Student student;
}
