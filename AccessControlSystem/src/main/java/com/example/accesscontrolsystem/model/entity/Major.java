package com.example.accesscontrolsystem.model.entity;

import com.example.accesscontrolsystem.model.entity.user.SchoolManager;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "major")
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @ManyToOne
    @JoinColumn(name = "campus-id", nullable = false)
    private Campus campus;
    @OneToOne(mappedBy = "major")
    private SchoolManager schoolManager;
    @OneToMany(mappedBy = "major")
    private List<Class> classList;
}
