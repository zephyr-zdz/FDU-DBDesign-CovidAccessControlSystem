package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.manager.CampusManager;
import com.example.accesscontrolsystem.manager.MajorManager;
import com.example.accesscontrolsystem.model.entity.Campus;
import com.example.accesscontrolsystem.model.entity.Major;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.stereotype.Service;

@Service("CampusService")
public class CampusService {
    private final CampusManager campusManager;
    private final MajorManager majorManager;

    public CampusService(CampusManager campusManager, MajorManager majorManager) {
        this.campusManager = campusManager;
        this.majorManager = majorManager;
    }

    public Response<Campus> majorNDaysMostLoggedCampus(Integer majorId, Integer n) {
        Major major = majorManager.findMajorById(majorId);
        if (major == null) {
            return new Response<>(Response.FAIL, "专业不存在", null);
        }
        return new Response<>(Response.SUCCESS, "查询成功", campusManager.majorNDaysMostLoggedCampus(majorId, n));
    }
}
