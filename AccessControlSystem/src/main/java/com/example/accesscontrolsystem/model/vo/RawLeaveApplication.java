package com.example.accesscontrolsystem.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawLeaveApplication {

    private Integer id;
    private String reason;
    private String destination; // 上海市杨浦区邯郸路221号
    // TODO: 时间格式
    private Long createTime; // utf-8编码的时间戳
    private Long leaveTime; // utf-8编码的时间戳
    private Long returnTime; // utf-8编码的时间字符串，例如：2020-12-12 12:12:12
    private String status; // 申请状态：pending/accepted/rejected
    private Integer studentId;
}
