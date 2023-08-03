package com.dsecurity.controller;

import com.dsecurity.model.User;
import com.dsecurity.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v7/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> findAllUser() {
        return userService.findAllUser();
    }
}
