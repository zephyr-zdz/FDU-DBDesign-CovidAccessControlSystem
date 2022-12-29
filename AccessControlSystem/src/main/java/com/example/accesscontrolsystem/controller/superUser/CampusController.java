package com.example.accesscontrolsystem.controller.superUser;

import com.example.accesscontrolsystem.model.entity.Campus;
import com.example.accesscontrolsystem.service.CampusService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("CampusController")
@RequestMapping("/campus")
public class CampusController {
    private final CampusService campusService;

    @Autowired
    public CampusController(CampusService campusService) {
        this.campusService = campusService;
    }
    @GetMapping("/most-logged-campus")
    public Response<Campus> majorNDaysMostLoggedCampus(@RequestParam("major-id") Integer majorId, @RequestParam("n") Integer n) {
        return campusService.majorNDaysMostLoggedCampus(majorId, n);
    }
}
