package com.example.accesscontrolsystem.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawGateLog {
    private long time; // utf-8编码的时间字符串，例如：2020-12-12 12:12:12
    private String direction; // in/out

    private Integer studentId;
    private Integer campusId;
}
