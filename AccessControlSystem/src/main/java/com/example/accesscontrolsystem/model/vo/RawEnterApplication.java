package com.example.accesscontrolsystem.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawEnterApplication {
    private Integer id;
    private String passingAreas;// 虹口区, 杨浦区, 闵行区 注意是英文逗号分隔
    // TODO: 时间格式
    private Long createTime; // utf-8编码的时间戳，例如：1607760000
    private Long acceptTime; // utf-8编码的时间戳，例如：1607760000
    private Long enterTime; // utf-8编码的时间戳，例如：1607760000
    private String status; // pending/accepted/rejected
    private Integer studentId;
}
