package com.mit.ticket_mgt_api.models.cls_tickets_mgt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;

public class cls_tickets_mgt {

	public Connection con = null;

	public String select_verify_task_ticket(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.select_verify_task_ticket(?)";
		Connection conn = con;
		try (

				PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("lc_payment_confirmation_for_bill"));
				result = rs.getString("select_verify_task_ticket");
			}
		} catch (SQLException e) {
			// Print Errors in console.
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {

					conn.close();
				} catch (

				SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_user_org_dashboard_data(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.get_user_org_dashboard_data(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("get_user_org_dashboard_data");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_system_dashboard_data(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.get_system_dashboard_data(?::json ->> 'start_date', ?::json ->> 'end_date')";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			pstmt.setString(2, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("get_system_dashboard_data");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_tickets_list_for_dashboard(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.get_tickets_list_for_dashboard(?::json ->> 'start_date', ?::json ->> 'end_date')";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			pstmt.setString(2, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("get_tickets_list_for_dashboard");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String select_insert_task_record(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.select_insert_task_record(?)";
		Connection conn = con;
		try (

				PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("lc_payment_confirmation_for_bill_egcr"));
				result = rs.getString("select_insert_task_record");
			}
		} catch (SQLException e) {
			// Print Errors in console.
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String select_update_task_record(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.select_update_task_record(?)";
		Connection conn = con;
		try (

				PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("lc_payment_confirmation_for_bill_egcr"));
				result = rs.getString("select_update_task_record");
			}
		} catch (SQLException e) {
			// Print Errors in console.
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String select_delete_task_record(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.select_delete_task_record(?)";
		Connection conn = con;
		try (

				PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("lc_payment_confirmation_for_bill_egcr"));
				result = rs.getString("select_delete_task_record");
			}
		} catch (SQLException e) {
			// Print Errors in console.
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String select_get_all_task_app_comp(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.select_get_all_task_app_comp(?)";

		Connection conn = con;
		try (

				PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("lc_payment_verification_for_bill"));
				result = rs.getString("select_get_all_task_app_comp");
			}
		} catch (SQLException e) {
			// Print Errors in console.
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String select_get_all_task_pending(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.select_get_all_task_pending(?)";

		Connection conn = con;
		try (

				PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("lc_payment_verification_for_bill"));
				result = rs.getString("select_get_all_task_pending");
			}
		} catch (SQLException e) {
			// Print Errors in console.
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String select_assign_task(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.select_assign_task(?)";

		Connection conn = con;
		try (

				PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("lc_payment_verification_for_bill"));
				result = rs.getString("select_assign_task");
			}
		} catch (SQLException e) {
			// Print Errors in console.
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String select_update_task_status(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.select_update_task_status(?)";

		Connection conn = con;
		try (

				PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("lc_payment_verification_for_bill"));
				result = rs.getString("select_update_task_status");
			}
		} catch (SQLException e) {
			// Print Errors in console.
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String select_get_all_task_app_comp_dashboard(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.select_get_all_task_app_comp_dashboard(?)";

		Connection conn = con;
		try (

				PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("lc_payment_verification_for_bill"));
				result = rs.getString("select_get_all_task_app_comp_dashboard");
			}
		} catch (SQLException e) {
			// Print Errors in console.
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String select_get_all_task_per_user(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.select_get_all_task_per_user(?)";

		Connection conn = con;
		try (

				PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("lc_payment_verification_for_bill"));
				result = rs.getString("select_get_all_task_per_user");
			}
		} catch (SQLException e) {
			// Print Errors in console.
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_user_assigned_tasks(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.get_user_assigned_tasks(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("get_user_assigned_tasks");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String select_get_active_all_task(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.select_get_active_all_task(?)";

		Connection conn = con;
		try (

				PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("lc_payment_verification_for_bill"));
				result = rs.getString("select_get_active_all_task");
			}
		} catch (SQLException e) {
			// Print Errors in console.
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String select_update_task_by_id(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.select_update_task_by_id(?)";

		Connection conn = con;
		try (

				PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("lc_payment_verification_for_bill"));
				result = rs.getString("select_update_task_by_id");
			}
		} catch (SQLException e) {
			// Print Errors in console.
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String update_task_by_id(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.update_task_by_id(?)";

		Connection conn = con;
		try (

				PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("lc_payment_verification_for_bill"));
				result = rs.getString("update_task_by_id");
			}
		} catch (SQLException e) {
			// Print Errors in console.
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String select_archive_task_record(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.select_archive_task_record(?)";

		Connection conn = con;
		try (

				PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("lc_payment_verification_for_bill"));
				result = rs.getString("select_archive_task_record");
			}
		} catch (SQLException e) {
			// Print Errors in console.
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String create_ticket(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.create_ticket(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("create_ticket");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String update_ticket(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.update_ticket(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("update_ticket");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String update_ticket_status(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.update_ticket_status(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("update_ticket_status");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String update_task_status(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.update_task_status(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("update_task_status");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_org_archived_tasks(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.get_org_archived_tasks(?, ?, ?)";
		Connection conn = con;
		java.util.UUID orgId = null;
		Integer limit = 50;
		Integer offset = 0;

		try {
			com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
			java.util.Map params = mapper.readValue(json_request, java.util.Map.class);

			Object orgIdObj = params.get("p_org_id");
			if (orgIdObj != null) {
				orgId = java.util.UUID.fromString(String.valueOf(orgIdObj));
			}

			Object limitObj = params.get("p_limit");
			if (limitObj != null) {
				limit = Integer.valueOf(String.valueOf(limitObj));
			}

			Object offsetObj = params.get("p_offset");
			if (offsetObj != null) {
				offset = Integer.valueOf(String.valueOf(offsetObj));
			}

		} catch (Exception e) {
			System.out.println("Error parsing JSON for get_org_archived_tasks: " + e.getMessage());
			return "{\"success\": false, \"message\": \"Invalid JSON request: " + e.getMessage() + "\"}";
		}

		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setObject(1, orgId);
			pstmt.setInt(2, limit);
			pstmt.setInt(3, offset);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("get_org_archived_tasks");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String fetch_archived_tickets(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.fetch_archived_tickets(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("fetch_archived_tickets");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String reopen_archived_task(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.reopen_archived_task(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("reopen_archived_task");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_all_archived_tasks(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.get_all_archived_tasks(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("get_all_archived_tasks");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_all_tickets(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.get_all_tickets(?::json) AS result";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("result");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_users_for_assignment() {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.get_users_for_assignment()";
		Connection conn = con;

		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("get_users_for_assignment");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_system_owner_users_with_job_count(String json_request) {
		String result = null;
		String userIdStr = null;
		Integer limit = 10;
		Integer offset = 0;
		Integer page = null;
		String searchText = null;
		try {
			com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
			java.util.Map params = mapper.readValue(json_request, java.util.Map.class);
			Object u = params.get("user_id");
			if (u == null)
				u = params.get("requesting_user_id");
			if (u != null)
				userIdStr = String.valueOf(u);
			Object l = params.get("limit");
			if (l != null) {
				try {
					limit = Integer.valueOf(String.valueOf(l));
				} catch (Exception ignored) {
				}
			}
			Object o = params.get("offset");
			if (o != null) {
				try {
					offset = Integer.valueOf(String.valueOf(o));
				} catch (Exception ignored) {
				}
			}
			Object p = params.get("page");
			if (p != null) {
				try {
					page = Integer.valueOf(String.valueOf(p));
				} catch (Exception ignored) {
				}
			}
			Object s = params.get("search_text");
			if (s != null)
				searchText = String.valueOf(s);
			if (offset == 0 && page != null && page > 0)
				offset = (page - 1) * limit;
		} catch (Exception ignored) {
		}

		String SQL = "SELECT tickets_mgt.get_system_owner_users_with_job_count(?, ?, ?, ?) AS get_system_owner_users_with_job_count";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			if (userIdStr != null)
				pstmt.setString(1, userIdStr);
			else
				pstmt.setNull(1, java.sql.Types.VARCHAR);
			pstmt.setInt(2, limit);
			pstmt.setInt(3, offset);
			if (searchText != null && !searchText.isEmpty())
				pstmt.setString(4, searchText);
			else
				pstmt.setNull(4, java.sql.Types.VARCHAR);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("get_system_owner_users_with_job_count");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_user_assigned_jobs(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.get_user_assigned_jobs(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("get_user_assigned_jobs");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String archive_ticket(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.archive_ticket(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("archive_ticket");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_next_ticket_sequence(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.get_next_ticket_sequence(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("get_next_ticket_sequence");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_org_dashboard_analytics(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.get_org_dashboard_analytics(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("get_org_dashboard_analytics");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_ticket_by_id(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.get_ticket_by_id(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("get_ticket_by_id");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String add_category(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.add_category(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("add_category");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String update_category(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.update_category(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("update_category");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String delete_category(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.delete_category(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("delete_category");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String add_priority(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.add_priority(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("add_priority");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String delete_priority(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.delete_priority(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("delete_priority");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_categories(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.get_categories(?::json) AS result";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("result");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_priorities(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.get_priorities(?::json) AS result";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("result");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String add_status(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.add_status(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("add_status");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String update_status(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.update_status(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("update_status");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String delete_status(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.delete_status(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("delete_status");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_statuses(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.get_statuses(?::json) AS result";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("result");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String get_status_by_id(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM tickets_mgt.get_status_by_id(?::json) AS result";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString("result");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

}
