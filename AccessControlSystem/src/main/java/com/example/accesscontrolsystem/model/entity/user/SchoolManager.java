package com.example.accesscontrolsystem.model.entity.user;

import com.example.accesscontrolsystem.model.entity.Major;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "school_manager")
public class SchoolManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @OneToOne
    @JoinColumn(name = "major-id", nullable = false)
    private Major major;
}
