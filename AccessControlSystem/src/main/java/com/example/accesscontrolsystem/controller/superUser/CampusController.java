package com.example.accesscontrolsystem.controller.superUser;

import com.example.accesscontrolsystem.model.vo.MajorWithCampus;
import com.example.accesscontrolsystem.service.CampusService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("CampusController")
@RequestMapping("/campus")
public class CampusController {
    private final CampusService campusService;

    @Autowired
    public CampusController(CampusService campusService) {
        this.campusService = campusService;
    }
    @GetMapping("/most-logged-campus")
    public Response<List<MajorWithCampus>> majorNDaysMostLoggedCampus(Integer n) {
        return campusService.allMajorWithMostLoggedCampusNDays(n);
    }
}
