package com.example.accesscontrolsystem.service.system;

import com.example.accesscontrolsystem.manager.CampusManager;
import com.example.accesscontrolsystem.manager.GateLogManager;
import com.example.accesscontrolsystem.manager.StudentManager;
import com.example.accesscontrolsystem.model.entity.Campus;
import com.example.accesscontrolsystem.model.entity.reportNlog.GateLog;
import com.example.accesscontrolsystem.model.entity.user.Student;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("SystemGateService")
public class GateService {
    private final GateLogManager gateLogManager;
    private final StudentManager studentManager;
    private final CampusManager campusManager;

    @Autowired
    public GateService(GateLogManager gateLogManager, StudentManager studentManager, CampusManager campusManager) {
        this.gateLogManager = gateLogManager;
        this.studentManager = studentManager;
        this.campusManager = campusManager;
    }

    public Response<String> gate(GateLog gateLog) {
        Student student = studentManager.findStudentById(gateLog.getStudentId());
        if (student == null) {
            return Response.error("学生不存在");
        }
        String direction = gateLog.getDirection();
        if (!direction.equals("in") && !direction.equals("out")) {
            return Response.error("direction is not in or out");
        }
        Campus campus = campusManager.findCampusById(gateLog.getCampusId());
        if (campus == null) {
            return Response.error("campus is null");
        }
        // 判断学生所在区域和门禁记录是否相符合
        if (Objects.equals(student.getStatus(), "outside") && direction.equals("in")) {
            student.setStatus(campusManager.findCampusById(gateLog.getCampusId()).getName());
            studentManager.update(student);
            gateLogManager.create(gateLog);
            return new Response<>(Response.SUCCESS, "进入校园", null);
        } else if (Objects.equals(student.getStatus(), "inside") && direction.equals("out")) {
            if (Objects.equals(student.getStatus(), campusManager.findCampusById(gateLog.getCampusId()).getName())) {
                student.setStatus("outside");
                studentManager.update(student);
                gateLogManager.create(gateLog);
                return new Response<>(Response.SUCCESS, "离开校区:" + campusManager.findCampusById(gateLog.getCampusId()).getName(), null);
            } else {
                return Response.error("学生不在该校区");
            }
        } else {
            return Response.error("学生状态不正确");
        }
    }
}
