package com.mit.ticket_mgt_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mit.ticket_mgt_api.conn_class.db_settings;
import com.mit.ticket_mgt_api.models.auth_service_model;
import com.mit.ticket_mgt_api.models.sms.cls_sms;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import io.swagger.v3.oas.annotations.tags.Tag;

//@PostMapping("/sms_service")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/sms_service")
@Tag(name = "SMS Service", description = "Authentication Service for App")

public class sms_service {
	cls_sms cls_sms_cl = new cls_sms();

	@Autowired
	private db_settings cls_db_config;

	// @POST
	@PostMapping("/select_log_sms_alert")
	// //@Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public String outgoing_sms_alert(@RequestBody String json_data) throws Exception {
		cls_sms_cl.con = cls_db_config.getCon();

		String result = cls_sms_cl.lc_outgoing_sms_log(json_data);

		if (result.equals("SMS Log Successfully")) {
			JSONObject obj = new JSONObject(json_data);
			String reciplent = obj.getString("recipient");
			String msg = obj.getString("msg");
			cls_sms_cl.send_register_message1(msg, reciplent);
		}

		return result;
	}

	// @POST
	@PostMapping("/select_log_sms_alert_bulk")
	// //@Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public String outgoing_sms_alert_bulk(@RequestBody String json_data) throws Exception {
		cls_sms_cl.con = cls_db_config.getCon();

		// String result = cls_sms_cl.lc_outgoing_sms_log(data);

		// if (result.equals("SMS Log Successfully")) {
		JSONObject obj = new JSONObject(json_data);
		String reciplent = obj.getString("recipient");
		String msg = obj.getString("msg");
		cls_sms_cl.send_register_message1(msg, reciplent);
		// }

		return "Sent";
	}

	@PostMapping("/send_single_sms")
	// //@Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public String send_single_sms(@RequestBody String json_data) throws Exception {
		cls_sms_cl.con = cls_db_config.getCon();
		JSONObject obj = new JSONObject(json_data);
		String reciplent = obj.getString("recipient");
		String msg = obj.getString("msg");
		String result = cls_sms_cl.select_send_sms_new(msg, reciplent);
		return result;
	}

}
