package com.example.accesscontrolsystem.service;

import com.example.accesscontrolsystem.manager.CampusManager;
import com.example.accesscontrolsystem.model.vo.MajorWithCampus;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CampusService")
public class CampusService {
    private final CampusManager campusManager;

    public CampusService(CampusManager campusManager) {
        this.campusManager = campusManager;
    }

    public Response<List<MajorWithCampus>> allMajorWithMostLoggedCampusNDays(Integer n) {
        return new Response<>(Response.SUCCESS, "ok", campusManager.allMajorWithMostLoggedCampusNDays(n));
    }
}
