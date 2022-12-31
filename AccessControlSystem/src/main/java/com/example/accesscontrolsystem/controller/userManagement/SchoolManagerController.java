package com.example.accesscontrolsystem.controller.userManagement;

import com.example.accesscontrolsystem.model.entity.user.SchoolManager;
import com.example.accesscontrolsystem.service.UserService;
import com.example.accesscontrolsystem.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("SchoolManagerController")
@RequestMapping("/school-manager")
public class SchoolManagerController {
    private final UserService userservice;
    @Autowired
    public SchoolManagerController(UserService userservice) {
        this.userservice = userservice;
    }
    @GetMapping("/{school-manager-id}")
    public Response<SchoolManager> getSchoolManagerById(@PathVariable("school-manager-id") Integer schoolManagerId) {
        return userservice.getSchoolManagerById(schoolManagerId);
    }
}
