package com.mit.ticket_mgt_api.models.useraccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*import javax.naming.Context;
import javax.naming.InitialContext;*/
import javax.naming.NamingException;
//import javax.sql.DataSource;
//import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class useraccount {
	public Connection con = null;

	/*
	 * public String get_all_users() throws Exception { user_account.con =
	 * cls_db_config.getCon();
	 * 
	 * JSONArray jsonArr = new JSONArray(); Statement stmt = null; Connection
	 * con = dbcon.getCon(); try { con.setAutoCommit(false); stmt =
	 * con.createStatement(); ResultSet rs = stmt.executeQuery(
	 * "SELECT * FROM user_account;" ); while ( rs.next() ) { int id =
	 * rs.getInt("uid"); String user_name = rs.getString("user_name"); String
	 * gender = rs.getString("gender"); String password =
	 * rs.getString("password");
	 * 
	 * String full_name = rs.getString("full_name"); String staff_number =
	 * rs.getString("staff_number");
	 * 
	 * JSONObject obj = new JSONObject(); obj.put( "uid" , id ); obj.put(
	 * "user_name" , user_name ); obj.put( "gender" , gender ); obj.put(
	 * "password" , password );
	 * 
	 * obj.put( "full_name" , full_name ); obj.put( "staff_number" ,
	 * staff_number );
	 * 
	 * jsonArr.put(obj); } rs.close(); stmt.close(); con.close(); } catch (
	 * Exception e ) { System.err.println( e.getClass().getName()+": "+
	 * e.getMessage() ); //System.exit(0); } return jsonArr.toString(); }
	 */

	public String get_all_users() {
		String result = null;
		String SQL = "SELECT * FROM user_mgt.select_user_account_all()";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("select_user_account_all"));
				result = rs.getString("select_user_account_all");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e.toString());
			System.out.println(e.fillInStackTrace());
			System.out.println(e.getCause());
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

	public String get_all_users_per_region(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM user_mgt.select_user_account_all(?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("select_user_account_all"));
				result = rs.getString("select_user_account_all");
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

	public String user_to_batch_to_list_short() {
		String result = null;
		String SQL = "SELECT * FROM user_mgt.user_to_batch_to_list_short()";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("user_to_batch_to_list_short"));
				result = rs.getString("user_to_batch_to_list_short");
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

	public String get_all_users_division(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM user_mgt.select_user_account_all_by_division(?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("select_user_account_all_by_division"));
				result = rs.getString("select_user_account_all_by_division");
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

	public String add_new_user(String json_request) {
		String result = null;
		// System.out.println(json_request);
		String SQL = "SELECT * FROM users.add_user(?::json)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("add_user"));
				result = rs.getString("add_user");
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

	public String update_user(String json_request) {
		String result = null;

		String SQL = "SELECT * FROM user_mgt.select_user_account_update(?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("select_user_account_update"));
				result = rs.getString("select_user_account_update");
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

	public String delete_user(int id) {
		System.out.println(id);
		String result = null;
		String SQL = "SELECT * FROM user_mgt.select_user_account_delete(?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("select_user_account_delete"));
				result = rs.getString("select_user_account_delete");
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

	public String update_user_profile(String json_request) throws JSONException {

		String ws_userid = null;
		String ws_user_profile = null;
		JSONArray jsonarray = new JSONArray(json_request);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonobject = jsonarray.getJSONObject(i);
			ws_userid = jsonobject.getString("userid");
			ws_user_profile = jsonobject.getString("user_profile");
		}
		int id_delete = Integer.parseInt(ws_userid);

		/*
		 * System.out.println(ws_userid); System.out.println(ws_user_profile);
		 */

		String result = null;
		String SQL = "SELECT * FROM user_mgt.select_user_profile_update(?,?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, id_delete);
			pstmt.setString(2, ws_user_profile);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("select_user_profile_update"));
				result = rs.getString("select_user_profile_update");
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

	public String user_profile_for_user(String json_request) throws JSONException {

		// int id_user = Integer.parseInt(json_request);
		String result = null;
		String SQL = "SELECT * FROM user_mgt.user_profile_for_user(?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("user_profile_for_user"));
				result = rs.getString("user_profile_for_user");
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

	public String select_user_by_user_id(String json_request) throws JSONException {

		// int id_user = Integer.parseInt(json_request);
		String result = null;
		String SQL = "SELECT * FROM user_mgt.select_user_by_user_id(?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("select_user_by_user_id"));
				result = rs.getString("select_user_by_user_id");
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

	public String load_user_for_login(String json_request) throws JSONException {
		String result = null;
		String ws_user_name = null;
		String ws_password = null;
		JSONArray jsonarray = new JSONArray(json_request);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonobject = jsonarray.getJSONObject(i);
			ws_user_name = jsonobject.getString("user_name");
			ws_password = jsonobject.getString("password");
		}
		String SQL = "SELECT * FROM user_mgt.select_user_for_login(?,?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, ws_user_name);
			pstmt.setString(2, ws_password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("select_user_for_login"));
				result = rs.getString("select_user_for_login");
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

	public String get_all_dashbaord_menu() {
		String result = null;
		String SQL = "SELECT * FROM user_mgt.select_all_dashboardmenu()";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("select_all_dashboardmenu"));
				result = rs.getString("select_all_dashboardmenu");
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

	public String get_select_all_dashboardmenu(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM user_mgt.select_all_dashboardmenu(?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// System.out.println(rs.getString("select_all_dashboardmenu"));
				result = rs.getString("select_all_dashboardmenu");
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

	public String select_all_dashboard_menu_per_user(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM user_mgt.select_all_dashboard_menu_per_user(?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// System.out.println(rs.getString("select_all_dashboardmenu"));
				result = rs.getString("select_all_dashboard_menu_per_user");
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

	public String get_all_menu() throws Exception {
		// user_account.con = cls_db_config.getCon();

		String info_menu = "";
		Statement stmt = null;
		Connection conn = con;
		try {
			con.setAutoCommit(false);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT info FROM orders;");
			while (rs.next()) {

				info_menu = rs.getString("info");

			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}
		return info_menu;
	}

	public String get_all_regions() {
		String result = null;
		String SQL = "SELECT * FROM user_mgt.select_all_regions_list ()";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("select_all_regions_list"));
				result = rs.getString("select_all_regions_list");
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

	public String select_update_user_profile_per_user(String json_request) throws JSONException {

		String result = null;

		String ws_userid = null;
		String ws_profile_list = null;
		String modified_by = null;
		String modified_by_id = null;
		// JSONArray jsonarray = new JSONArray(json_request);
		// for (int i = 0; i < jsonarray.length(); i++) {
		JSONObject jsonobject = new JSONObject(json_request);
		ws_userid = jsonobject.getString("user_id");
		ws_profile_list = jsonobject.getString("profile_list");
		// modified_by = jsonobject.getString("modified_by");
		// modified_by_id = jsonobject.getString("modified_by_id");

		// }
		int id_user = Integer.parseInt(ws_userid);
		String SQL = "SELECT * FROM user_mgt.select_update_user_profile_per_user(?,?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, ws_profile_list);
			pstmt.setInt(2, id_user);
			// pstmt.setString(3, modified_by);
			// pstmt.setString(4, modified_by_id);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("select_update_user_profile_per_user"));
				result = rs.getString("select_update_user_profile_per_user");
			}
			// iygser husndrsndf ngsjdf g dngsdfngs iuhskjdrltgsuidfh sduiugijkdfgsdiop skj
			// a

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

	public String usp_users_access_logs(String json_request) {
		String result = null;

		String SQL = "SELECT * FROM user_mgt.usp_users_access_logs(?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("select_user_account_update"));
				result = rs.getString("usp_users_access_logs");
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

	public String select_user_for_two_factor_verification(String json_request) {
		String result = null;

		String SQL = "SELECT * FROM user_mgt.select_user_for_two_factor_verification(?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("select_user_account_update"));
				result = rs.getString("select_user_for_two_factor_verification");
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

	public String select_corporate_portal_sub_users_all() {
		String result = null;
		String SQL = "SELECT * FROM corporate.select_corporate_portal_sub_users_all()";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("select_user_account_all"));
				result = rs.getString("select_corporate_portal_sub_users_all");
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

	public String office_verify_verification_token(String json_request) {
		String result = null;

		String SQL = "SELECT * FROM user_mgt.office_verify_verification_token(?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("select_user_account_update"));
				result = rs.getString("office_verify_verification_token");
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
