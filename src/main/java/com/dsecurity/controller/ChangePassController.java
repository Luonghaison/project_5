package com.dsecurity.controller;

import com.dsecurity.dto.request.ChangePassRequest;
import com.dsecurity.dto.response.ResponseMessage;
import com.dsecurity.model.User;
import com.dsecurity.security.userPrincipal.UserDetailService;
import com.dsecurity.security.userPrincipal.UserPrincipal;
import com.dsecurity.service.serviceIMPL.UserServiceIMPL;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/password")
@RequiredArgsConstructor
public class ChangePassController {

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    private UserServiceIMPL userServiceIMPL;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;


    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassRequest changePasswordRequest) {
        try {
            User user = userDetailService.getUserPrincipal();
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            changePasswordRequest.getCurrentPassword())
                    );
            // Lấy thông tin người dùng hiện tại
            user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            userServiceIMPL.save(user);

            return ResponseEntity.ok("Cập nhật mật khẩu thành công ");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.ok("Cập nhật mật khẩu thất bại ");
        }
    }
}

