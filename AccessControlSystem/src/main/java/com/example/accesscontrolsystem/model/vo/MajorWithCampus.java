package com.example.accesscontrolsystem.model.vo;

import com.example.accesscontrolsystem.model.entity.Campus;
import com.example.accesscontrolsystem.model.entity.Major;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MajorWithCampus {
    private Major major;
    private Campus campus;
}
