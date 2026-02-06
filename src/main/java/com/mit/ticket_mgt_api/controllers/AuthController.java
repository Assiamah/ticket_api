package com.mit.ticket_mgt_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mit.ticket_mgt_api.conn_class.db_settings;
import com.mit.ticket_mgt_api.models.auth.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/auth_service")
@Tag(name = "Authentication Service", description = "Authentication Service for TerraFinder Application")
public class AuthController {

    AuthService authService = new AuthService();

    @Autowired
     private db_settings cls_db_config;

    @PostMapping("/user_login")
    public ResponseEntity<?> userLogin(@RequestBody String jsonReq)  throws Exception {
        authService.con = cls_db_config.getCon();
        String result = authService.userLogin(jsonReq);
        authService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/verify_otp")
    public ResponseEntity<?> verifyOtp(@RequestBody String jsonReq)  throws Exception {
        authService.con = cls_db_config.getCon();
        String result = authService.verifyOtp(jsonReq);
        authService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/check_registration_email")
    public ResponseEntity<?> checkRegistrationEmail(@RequestBody String jsonReq)  throws Exception {
        authService.con = cls_db_config.getCon();
        String result = authService.checkRegistrationEmail(jsonReq);
        authService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/verify_registration_email")
    public ResponseEntity<?> verifyRegistrationEmail(@RequestBody String jsonReq)  throws Exception {
        authService.con = cls_db_config.getCon();
        String result = authService.verifyRegistrationEmail(jsonReq);
        authService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/create_portal_user_password")
    public ResponseEntity<?> createPortalUserPassword(@RequestBody String jsonReq)  throws Exception {
        authService.con = cls_db_config.getCon();
        String result = authService.createPortalUserPassword(jsonReq);
        authService.con.close();
        return ResponseEntity.ok(result);
    }

}
