package com.mit.ticket_mgt_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mit.ticket_mgt_api.conn_class.db_settings;
import com.mit.ticket_mgt_api.models.auth_service_model;
import com.mit.ticket_mgt_api.models.useraccount.useraccount;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import io.swagger.v3.oas.annotations.tags.Tag;

//@PostMapping("/user_management_service")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user_management_service")
@Tag(name = "User Account Management Service", description = "Authentication Service for App")

public class user_management_service {

	useraccount user_account = new useraccount();

	@Autowired
	private db_settings cls_db_config;

	// Get All Users In A Table
	// @GET
	@GetMapping("/get_all_users")
	// @Produces(MediaType.APPLICATION_JSON)
	public String get_all_users() throws Exception {
		user_account.con = cls_db_config.getCon();
		// user_account.con = cls_db_config.getCon();
		return user_account.get_all_users();
	}

	// @POST
	@PostMapping("/get_all_users_per_region")
	// @Consumes(MediaType.APPLICATION_JSON)
	public String get_all_users_per_region(@RequestBody String json_data) throws Exception {
		user_account.con = cls_db_config.getCon();
		String result;
		user_account.con = cls_db_config.getCon();
		result = user_account.get_all_users_per_region(json_data);
		return result;
	}

	// @GET
	@GetMapping("/get_all_users_short")
	// @Produces(MediaType.APPLICATION_JSON)
	public String user_to_batch_to_list_short() throws Exception {
		user_account.con = cls_db_config.getCon();
		return user_account.user_to_batch_to_list_short();
	}

	// @POST
	@PostMapping("/get_all_users_division")
	// //@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public String get_all_users(@RequestBody String json_data) throws Exception {
		user_account.con = cls_db_config.getCon();
		String result;
		result = user_account.get_all_users_division(json_data);
		System.out.println(result);
		return result;
	}

	// @POST
	@PostMapping("/add_new_user")
	// //@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public String add_new_user(@RequestBody String json_data) throws Exception {
		user_account.con = cls_db_config.getCon();
		// Response
		// System.out.println(json_data);
		String result;
		result = user_account.add_new_user(json_data);
		return result;
	}

	// @POST
	@PostMapping("/update_user")
	// //@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public String update_user(@RequestBody String json_data) throws Exception {
		user_account.con = cls_db_config.getCon();
		String result;
		result = user_account.update_user(json_data);
		return result;
	}

	// @POST
	@PostMapping("/delete_user")
	// //@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public String delete_user(@RequestBody String json_data) throws Exception {
		user_account.con = cls_db_config.getCon();
		String result;
		int id_delete = Integer.parseInt(json_data);
		result = user_account.delete_user(id_delete);
		return result;
	}

	// @POST
	@PostMapping("/update_user_profile")
	// //@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public String update_user_profile(@RequestBody String json_data) throws Exception {
		user_account.con = cls_db_config.getCon();
		String result;
		// int id_delete = Integer.parseInt(json_data);

		result = user_account.update_user_profile(json_data);
		return result;
	}

	// @POST
	@PostMapping("/select_update_user_profile_per_user")
	// @Produces(MediaType.APPLICATION_JSON)
	// //@Consumes(MediaType.APPLICATION_JSON)
	public String select_update_user_profile_per_user(@RequestBody String json_data) throws Exception {
		user_account.con = cls_db_config.getCon();
		String result;
		// int id_delete = Integer.parseInt(json_data);

		result = user_account.select_update_user_profile_per_user(json_data);
		return result;
	}

	// @POST
	@PostMapping("/user_profile_for_user")
	// //@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public String user_profile_for_user(@RequestBody String json_data) throws Exception {
		user_account.con = cls_db_config.getCon();
		String result;

		result = user_account.user_profile_for_user(json_data);
		return result;
	}

			// @POST
			@PostMapping("/select_user_by_user_id")
			// @Consumes(MediaType.APPLICATION_JSON)
			// //@Produces(MediaType.APPLICATION_JSON)
			public String select_user_by_user_id(@RequestBody String json_data) throws Exception {
				user_account.con = cls_db_config.getCon();
				String result = user_account.select_user_by_user_id(json_data);
				return result;
			}

	// @GET
	@GetMapping("/get_all_menu")
	// @Produces(MediaType.APPLICATION_JSON)
	public String get_all_menu() throws Exception {
		user_account.con = cls_db_config.getCon();
		return user_account.get_all_menu();
	}

	// @GET
	@GetMapping("/get_all_dashboard_menu")
	// @Produces(MediaType.APPLICATION_JSON)
	public String get_all_dashboard_menu() throws Exception {
		user_account.con = cls_db_config.getCon();
		return user_account.get_all_dashbaord_menu();
	}

	// @POST
	@PostMapping("/get_all_dashboard_menu_by_user")
	// @Consumes(MediaType.APPLICATION_JSON)
	// //@Produces(MediaType.APPLICATION_JSON)
	public String get_all_dashboard_menu_by_user(@RequestBody String json_data) throws Exception {
		user_account.con = cls_db_config.getCon();
		String result = user_account.get_select_all_dashboardmenu(json_data);
		return result;
	}

	@PostMapping("/select_all_dashboard_menu_per_user")
	// @Consumes(MediaType.APPLICATION_JSON)
	// //@Produces(MediaType.APPLICATION_JSON)
	public String select_all_dashboard_menu_per_user(@RequestBody String json_data) throws Exception {
		user_account.con = cls_db_config.getCon();
		String result = user_account.select_all_dashboard_menu_per_user(json_data);
		return result;
	}

	

	// @POST
	@PostMapping("/load_user_for_login")
	// @Consumes(MediaType.APPLICATION_JSON)
	// //@Produces(MediaType.APPLICATION_JSON)
	public String load_user_for_login(@RequestBody String json_data) throws Exception {
		user_account.con = cls_db_config.getCon();
		String result = user_account.load_user_for_login(json_data);
		return result;
	}

	// @POST
	@PostMapping("/post")
	// @Consumes(MediaType.APPLICATION_JSON)
	public String createDataInJSON(@RequestBody String json_data) {
		String result = "Data post: " + json_data;
		return result;
	}

	// @GET
	@GetMapping("/get_all_regions")
	// @Produces(MediaType.APPLICATION_JSON)
	public String get_all_regions() throws Exception {
		user_account.con = cls_db_config.getCon();
		return user_account.get_all_regions();
	}

	// @POST
	@PostMapping("/usp_users_access_logs")
	// //@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public String usp_users_access_logs(@RequestBody String json_data) throws Exception {
		user_account.con = cls_db_config.getCon();
		String result;
		result = user_account.usp_users_access_logs(json_data);
		return result;
	}

	// @POST
	@PostMapping("/select_user_for_two_factor_verification")
	// //@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public String select_user_for_two_factor_verification(@RequestBody String json_data) throws Exception {
		user_account.con = cls_db_config.getCon();
		String result;
		result = user_account.select_user_for_two_factor_verification(json_data);
		return result;
	}

	// @GET
	@GetMapping("/select_corporate_portal_sub_users_all")
	// @Produces(MediaType.APPLICATION_JSON)
	public String select_corporate_portal_sub_users_all() throws Exception {
		user_account.con = cls_db_config.getCon();
		// user_account.con = cls_db_config.getCon();
		return user_account.select_corporate_portal_sub_users_all();
	}

	@PostMapping("/office_verify_verification_token")
	// //@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public String office_verify_verification_token(@RequestBody String json_data) throws Exception {
		user_account.con = cls_db_config.getCon();
		String result;
		result = user_account.office_verify_verification_token(json_data);
		return result;
	}

}
