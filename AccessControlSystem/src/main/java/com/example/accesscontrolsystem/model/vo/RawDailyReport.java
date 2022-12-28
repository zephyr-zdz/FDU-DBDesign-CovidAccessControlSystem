package com.example.accesscontrolsystem.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawDailyReport {

    private Integer id;
    private String location;
    public BigDecimal temperature;
    private String other;
    private Integer studentId;
}
