package com.mit.ticket_mgt_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mit.ticket_mgt_api.conn_class.db_settings;
import com.mit.ticket_mgt_api.models.auth_service_model;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v1/auth_service")
@Tag(name = "Authentication Service", description = "Authentication Service for Blood Bank and Donation Application")

public class auth_service_controller {

    auth_service_model maps_service = new auth_service_model();

    @Autowired
    private db_settings cls_db_config;

    @PostMapping("/auth_user")
    @Operation(summary = "Verify User Account", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Verify User", required = true, content = @Content(mediaType = "application/json", schema = @Schema(type = "string", description = "the Sub", accessMode = Schema.AccessMode.READ_ONLY, example = "Schema example"), examples = {
            @ExampleObject(name = "Sample Request", value = "{\"user\" : \"test.user\",\"pass\" : \"0001\"}", summary = "Verify User"),

    })))
    public String select_user_for_login(@RequestBody String json_data) throws Exception {
        maps_service.con = cls_db_config.getCon();
        System.out.println(json_data);
        String result = maps_service.select_user_for_login(json_data);
        return result;
    }

}
