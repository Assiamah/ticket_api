package com.mit.ticket_mgt_api.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mit.ticket_mgt_api.conn_class.db_settings;
import com.mit.ticket_mgt_api.models.users.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/user_service")
@Tag(name = "User Service", description = "User Service for TerraFinder Application")
public class UserController {
    UserService userService = new UserService();

    @Autowired
     private db_settings cls_db_config;

    @PostMapping("/load_users")
    public ResponseEntity<?> loadUsers(@RequestBody Map<String, Object> params) throws Exception {
        int page = params.get("page") != null ? (Integer) params.get("page") : 1;
        int limit = params.get("limit") != null ? (Integer) params.get("limit") : 10;
        String search = params.get("search") != null ? (String) params.get("search") : "";
        String currentUserUniqueId = params.get("current_user_unique_id") != null ? (String) params.get("current_user_unique_id") : "";

        userService.con = cls_db_config.getCon();
        String result = userService.loadUsers(page, limit, search, currentUserUniqueId);
        userService.con.close();

        return ResponseEntity.ok(result);
    }

    @PostMapping("/add_user")
    public ResponseEntity<?> addUser(@RequestBody String jsonReq)  throws Exception {
        userService.con = cls_db_config.getCon();
        String result = userService.addUser(jsonReq);
        userService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/get_user_by_id")
    public ResponseEntity<?> getUserById(@RequestBody String jsonReq)  throws Exception {
        userService.con = cls_db_config.getCon();
        String result = userService.getUserById(jsonReq);
        userService.con.close();
        //System.out.println(result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/get_user_profile")
    public ResponseEntity<?> getUserProfile(@RequestBody String jsonReq)  throws Exception {
        userService.con = cls_db_config.getCon();
        String result = userService.getUserProfile(jsonReq);
        userService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/update_user")
    public ResponseEntity<?> updateUser(@RequestBody String jsonReq)  throws Exception {
        userService.con = cls_db_config.getCon();
        String result = userService.updateUser(jsonReq);
        userService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/update_portal_user")
    public ResponseEntity<?> updatePortalUser(@RequestBody String jsonReq)  throws Exception {
        userService.con = cls_db_config.getCon();
        String result = userService.updatePortalUser(jsonReq);
        userService.con.close();
        return ResponseEntity.ok(result);
    }    @PostMapping("/set_force_password_change")
    public ResponseEntity<?> setForcePasswordChange(@RequestBody String jsonReq)  throws Exception {
        userService.con = cls_db_config.getCon();
        String result = userService.setForcePasswordChange(jsonReq);
        userService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/set_default_password")
    public ResponseEntity<?> setDefaultPassword(@RequestBody String jsonReq)  throws Exception {
        userService.con = cls_db_config.getCon();
        String result = userService.setDefaultPassword(jsonReq);
        userService.con.close();
        return ResponseEntity.ok(result);
    }

      @GetMapping("/get_user_select")
    public ResponseEntity<?> getUserSelect() throws Exception {
        userService.con = cls_db_config.getCon();
        String result = userService.getUserSelect();
        userService.con.close();
        return ResponseEntity.ok(result);
    }


}
