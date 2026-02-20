package com.mit.ticket_mgt_api.controllers.security_controller;

import com.mit.ticket_mgt_api.models.auth_service_model;
import com.mit.ticket_mgt_api.conn_class.db_settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @Autowired
    private db_settings cls_db_config;

    @PostMapping("/security/set_force_password_change")
    public String setForcePasswordChange(@RequestBody String json_data) throws Exception {
        auth_service_model auth_service = new auth_service_model();
        auth_service.con = cls_db_config.getCon();
        String result = auth_service.setForcePasswordChange(json_data);
        auth_service.con.close();
        return result;
    }

    @PostMapping("/security/set_default_password")
    public String setDefaultPassword(@RequestBody String json_data) throws Exception {
        auth_service_model auth_service = new auth_service_model();
        auth_service.con = cls_db_config.getCon();
        String result = auth_service.setDefaultPassword(json_data);
        auth_service.con.close();
        return result;
    }

    @PostMapping("/reset_password_with_default")
    public String resetPasswordWithDefault(@RequestBody String json_data) throws Exception {
        auth_service_model auth_service = new auth_service_model();
        auth_service.con = cls_db_config.getCon();
        String result = auth_service.resetPasswordWithDefault(json_data);
        auth_service.con.close();
        return result;
    }

    @PostMapping("/change_password")
    public String changePassword(@RequestBody String json_data) throws Exception {
        auth_service_model auth_service = new auth_service_model();
        auth_service.con = cls_db_config.getCon();
        String result = auth_service.changePassword(json_data);
        auth_service.con.close();
        return result;
    }
}