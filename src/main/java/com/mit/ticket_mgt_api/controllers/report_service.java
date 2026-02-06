package com.mit.ticket_mgt_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mit.ticket_mgt_api.conn_class.db_settings;
import com.mit.ticket_mgt_api.models.auth_service_model;
import com.mit.ticket_mgt_api.models.report_class.Cls_reports;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import io.swagger.v3.oas.annotations.tags.Tag;

//Functions of this web service

//@PostMapping("/report_service")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/report_service")
@Tag(name = "Report Service", description = "Authentication Service for App")

public class report_service {

	Cls_reports reports_cl = new Cls_reports();

	@Autowired
	private db_settings cls_db_config;

	// @POST
	@PostMapping("/report_on_the_cases")
	public String report_on_the_cases(@RequestBody String job_number) throws Exception {
		reports_cl.con = cls_db_config.getCon();
		// System.out.println(job_number);
		return reports_cl.report_on_the_cases(job_number);
	}

	@PostMapping("/report_on_the_cases_stamping")
	public String report_on_the_cases_stamping(@RequestBody String job_number) throws Exception {
		reports_cl.con = cls_db_config.getCon();
		// System.out.println(job_number);
		return reports_cl.report_on_the_cases_stamping(job_number);
	}

	// @POST
	@PostMapping("/select_business_processes_list")
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public String select_business_processes_list(@RequestBody String job_number)
			throws Exception {
		reports_cl.con = cls_db_config.getCon();
		// System.out.println(job_number);
		return reports_cl.select_business_processes_list();
	}

	// @POST
	@PostMapping("/select_business_processes_sub_list")
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public String select_business_processes_sub_list(@RequestBody String job_number)
			throws Exception {
		reports_cl.con = cls_db_config.getCon();
		// System.out.println(job_number);
		return reports_cl.select_business_processes_sub_list(job_number);
	}

	// @POST
	@PostMapping("/select_business_processes_sub_list_all")
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public String select_business_processes_sub_list_all(@RequestBody String job_number)
			throws Exception {
		reports_cl.con = cls_db_config.getCon();
		// System.out.println(job_number);
		return reports_cl.select_business_processes_sub_list_all();
	}

	// @GET
	@GetMapping("/get_gender")
	// @Produces(MediaType.APPLICATION_JSON)
	public String get_all_gender() throws Exception {
		reports_cl.con = cls_db_config.getCon();
		return reports_cl.report_on_the_cases("");
	}

	@PostMapping("/report_landed_cases_gra_revised")
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public String report_landed_cases_gra_revised(@RequestBody String job_number) throws Exception {
		reports_cl.con = cls_db_config.getCon();
		// System.out.println(job_number);
		return reports_cl.report_landed_cases_gra_revised(job_number);
	}

}
