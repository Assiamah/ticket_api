package com.mit.ticket_mgt_api.controllers.tickets_mgt_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mit.ticket_mgt_api.conn_class.db_settings;
import com.mit.ticket_mgt_api.models.cls_tickets_mgt.cls_tickets_mgt;

import io.swagger.v3.oas.annotations.tags.Tag;

// @PostMapping("/tickets_mgt_services")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/tickets_mgt_services")
@Tag(name = "Tickets Service", description = "Authentication Service for App")

public class tickets_mgt_services {
	// Removed shared instance to ensure thread safety
	// cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();

	@Autowired
	private db_settings cls_db_config;

	// @POST
	@PostMapping("/select_verify_task_ticket")
	public String select_verify_task_ticket(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.select_verify_task_ticket(json_data);
		return result;
	}

	// @POST
	@PostMapping("/select_insert_task_record")
	public String select_insert_task_record(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.select_insert_task_record(json_data);
		return result;
	}

	@PostMapping("/create_ticket")
	public String create_ticket(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.create_ticket(json_data);
		return result;
	}

	// @POST
	@PostMapping("/select_update_task_record")
	public String select_update_task_record(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.select_update_task_record(json_data);
		return result;
	}

	@PostMapping("/update_ticket")
	public String update_ticket(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.update_ticket(json_data);
		return result;
	}

	// @POST
	@PostMapping("/select_delete_task_record")
	public String select_delete_task_record(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.select_delete_task_record(json_data);
		return result;
	}

	// @POST
	@PostMapping("/select_get_all_task_app_comp")
	public String select_get_all_task_app_comp(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.select_get_all_task_app_comp(json_data);
		return result;
	}

	@PostMapping("/get_all_tickets")
	public String get_all_tickets(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.get_all_tickets(json_data);
		return result;
	}

	// @POST
	@PostMapping("/select_get_all_task_pending")
	public String select_get_all_task_pending(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.select_get_all_task_pending(json_data);
		return result;
	}

	@PostMapping("/archive_ticket")
	public String archive_ticket(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.archive_ticket(json_data);
		return result;
	}

	// @PostMapping("/select_assign_task")
	// public String select_assign_task(@RequestBody String json_data) throws
	// Exception {
	// cls_tickets_cl.con = cls_db_config.getCon();
	// String result = cls_tickets_cl.select_assign_task(json_data);
	// return result;
	// }

	@PostMapping("/select_update_task_status")
	public String select_update_task_status(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.select_update_task_status(json_data);
		return result;
	}

	@PostMapping("/update_ticket_status")
	public String update_ticket_status(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.update_ticket_status(json_data);
		return result;
	}

	@PostMapping("/select_get_all_task_app_comp_dashboard")
	public String select_get_all_task_app_comp_dashboard(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.select_get_all_task_app_comp_dashboard(json_data);
		return result;
	}

	@PostMapping("/get_user_org_dashboard_data")
	public String get_user_org_dashboard_data(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.get_user_org_dashboard_data(json_data);
		return result;
	}

	@PostMapping("/get_system_dashboard_data")
	public String get_system_dashboard_data(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.get_system_dashboard_data(json_data);
		return result;
	}

	@PostMapping("/get_tickets_list_for_dashboard")
	public String get_tickets_list_for_dashboard(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.get_tickets_list_for_dashboard(json_data);
		return result;
	}

	@PostMapping("/get_ticket_by_id")
	public String get_ticket_by_id(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.get_ticket_by_id(json_data);
		return result;
	}

	@PostMapping("/get_next_ticket_sequence")
	public String get_next_ticket_sequence(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.get_next_ticket_sequence(json_data);
		return result;
	}

	@PostMapping("/add_category")
	public String add_category(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.add_category(json_data);
		return result;
	}

	@PutMapping("/update_category")
	public String update_category(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.update_category(json_data);
		return result;
	}

	@DeleteMapping("/delete_category")
	public String delete_category(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.delete_category(json_data);
		return result;
	}

	@PostMapping("/add_priority")
	public String add_priority(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.add_priority(json_data);
		return result;
	}

	@PostMapping("/delete_priority")
	public String delete_priority(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.delete_priority(json_data);
		return result;
	}

	@PostMapping("/get_categories")
	public String get_categories(@RequestBody(required = false) String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String payload = (json_data == null || json_data.isEmpty()) ? "{}" : json_data;
		String result = cls_tickets_cl.get_categories(payload);
		return result;
	}

	@PostMapping("/get_priorities")
	public String get_priorities(@RequestBody(required = false) String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String payload = (json_data == null || json_data.isEmpty()) ? "{}" : json_data;
		String result = cls_tickets_cl.get_priorities(payload);
		return result;
	}

	@PostMapping("/add_status")
	public String add_status(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.add_status(json_data);
		return result;
	}

	@PostMapping("/update_status")
	public String update_status(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.update_status(json_data);
		return result;
	}

	@PostMapping("/delete_status")
	public String delete_status(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.delete_status(json_data);
		return result;
	}

	@PostMapping("/get_statuses")
	public String get_statuses(@RequestBody(required = false) String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String payload = (json_data == null || json_data.isEmpty()) ? "{}" : json_data;
		String result = cls_tickets_cl.get_statuses(payload);
		return result;
	}

	@PostMapping("/get_status_by_id")
	public String get_status_by_id(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.get_status_by_id(json_data);
		return result;
	}

	@PostMapping("/select_get_all_task_per_user")
	public String select_get_all_task_per_user(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.select_get_all_task_per_user(json_data);
		return result;
	}

	@PostMapping("/select_get_active_all_task")
	public String select_get_active_all_task(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.select_get_active_all_task(json_data);
		return result;
	}

	@PostMapping("/select_update_task_by_id")
	public String select_update_task_by_id(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.select_update_task_by_id(json_data);
		return result;
	}

	@PostMapping("/update_task_by_id")
	public String update_task_by_id(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.update_task_by_id(json_data);
		return result;
	}

	@PostMapping("/select_archive_task_record")
	public String select_archive_task_record(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.select_archive_task_record(json_data);
		return result;
	}

	@PostMapping("/select_assign_task")
	public String select_assign_task(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.select_assign_task(json_data);
		return result;
	}

	@PostMapping("/get_user_assigned_tasks")
	public String get_user_assigned_tasks(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.get_user_assigned_tasks(json_data);
		return result;
	}

	@PostMapping("/update_task_status")
	public String update_task_status(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.update_task_status(json_data);
		return result;
	}

	@PostMapping("/get_org_archived_tasks")
	public String get_org_archived_tasks(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.get_org_archived_tasks(json_data);
		return result;
	}

	@PostMapping("/fetch_archived_tickets")
	public String fetch_archived_tickets(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.fetch_archived_tickets(json_data);
		return result;
	}

	@PostMapping("/reopen_archived_task")
	public String reopen_archived_task(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.reopen_archived_task(json_data);
		return result;
	}

	@PostMapping("/get_all_archived_tasks")
	public String get_all_archived_tasks(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.get_all_archived_tasks(json_data);
		return result;
	}

	@PostMapping("/get_system_owner_users_with_job_count")
	public String get_system_owner_users_with_job_count(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.get_system_owner_users_with_job_count(json_data);
		return result;
	}

	@PostMapping("/get_user_assigned_jobs")
	public String get_user_assigned_jobs(@RequestBody String json_data) throws Exception {
		cls_tickets_mgt cls_tickets_cl = new cls_tickets_mgt();
		cls_tickets_cl.con = cls_db_config.getCon();
		String result = cls_tickets_cl.get_user_assigned_jobs(json_data);
		return result;
	}

}
