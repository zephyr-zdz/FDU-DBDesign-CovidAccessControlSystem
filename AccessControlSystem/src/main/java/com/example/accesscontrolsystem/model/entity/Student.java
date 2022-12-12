package com.example.accesscontrolsystem.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone-number", nullable = false)
    private String phoneNumber;
    @Column(name = "dorm-address", nullable = false)
    private String dormAddress; // 东南北本江张枫-xx-x0xx
    @Column(name = "home-address", nullable = false)
    private String homeAddress; // xx省市xx县xx街道xx号
    @Column(name = "id-type", nullable = false)
    private String idType; // 身份证件类型
    @Column(name = "id-number", nullable = false)
    private String idNumber; // 身份证件号码
    @Column(name = "status", nullable = false)
    private String status; // in / out
    @Column(name = "auth", nullable = false)
    private String auth; // Y/N
    @Column(name = "class-id", nullable = false)
    private Integer classId;
}
