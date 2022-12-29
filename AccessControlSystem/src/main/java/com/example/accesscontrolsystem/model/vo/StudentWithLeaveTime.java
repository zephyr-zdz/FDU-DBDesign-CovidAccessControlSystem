package com.example.accesscontrolsystem.model.vo;

import com.example.accesscontrolsystem.model.entity.user.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentWithLeaveTime {
    private Student student;
    private Long leaveTime;
    private String leaveTimeStr;
}
