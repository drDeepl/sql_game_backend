package ru.sqlinvestigation.RestAPI.controllers.userDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sqlinvestigation.RestAPI.security.PersonDetails;
import ru.sqlinvestigation.RestAPI.services.userDB.AdminService;

/**
 * @author Neil Alishev
 */
@Controller
public class HelloController {
    private final AdminService adminService;

    @Autowired
    public HelloController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/showUserInfo")
    @ResponseBody
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        return personDetails.getUsername();
    }

    @GetMapping("/admin")
    public String adminPage() {
        adminService.doAdminStuff();
        return "admin";
    }
}
