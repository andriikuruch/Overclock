package com.overclock.overclock.controller;

import com.overclock.overclock.service.AdminService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminController {
    private AdminService adminService;
    @Autowired
    public void setAdminService(AdminService adminService){this.adminService = adminService;}

    @GetMapping
    public void unblockUser(BigInteger id){}

    @GetMapping
    public String blockUser(BigInteger id, Authentication authentication){return null;}
}
