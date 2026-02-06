package com.mit.ticket_mgt_api.models.report_class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cls_reports {

	public Connection con = null;

	public String report_on_the_cases(String json_request) {
		String result = null;

		// String SQL = "SELECT * FROM csau.report_on_the_cases (?)";
		String SQL = "SELECT * FROM csau.report_on_the_cases (?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println((rs.getString(""));
				result = rs.getString("report_on_the_cases");
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

	public String report_on_the_cases_stamping(String json_request) {
		String result = null;

		// String SQL = "SELECT * FROM csau.report_on_the_cases (?)";
		String SQL = "SELECT * FROM csau.report_on_the_cases_stamping (?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println((rs.getString("report_on_the_cases"));
				result = rs.getString("report_on_the_cases_stamping");
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

	public String report_landed_cases_gra_revised(String json_request) {
		String result = null;

		// String SQL = "SELECT * FROM csau.report_on_the_cases (?)";
		String SQL = "SELECT * FROM csau.report_landed_cases_gra_revised (?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println((rs.getString("report_on_the_cases"));
				result = rs.getString("report_landed_cases_gra_revised");
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

	public String report_on_the_cases_file_with_officers(String json_request) {
		String result = null;

		// String SQL = "SELECT * FROM csau.report_on_the_cases (?)";
		String SQL = "SELECT * FROM csau.report_on_the_cases_file_with_officers (?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println((rs.getString("report_on_the_cases_file_with_officers"));
				result = rs.getString("report_on_the_cases_file_with_officers");
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

	public String select_business_processes_list() {
		String result = null;

		// String SQL = "SELECT * FROM csau.report_on_the_cases (?)";
		String SQL = "SELECT * FROM csau.select_business_processes_list ()";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			// pstmt.setString(1,json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println((rs.getString("select_business_processes_list"));
				result = rs.getString("select_business_processes_list");
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

	public String select_business_processes_sub_list(String json_request) {
		String result = null;
		Integer bpid = Integer.parseInt(json_request);
		String SQL = "SELECT * FROM csau.select_business_processes_sub_list(?)";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setInt(1, bpid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println((rs.getString("select_business_processes_sub_list"));
				result = rs.getString("select_business_processes_sub_list");
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

	public String select_business_processes_sub_list_all() {
		String result = null;

		String SQL = "SELECT * FROM csau.select_business_processes_sub_list_all()";
		Connection conn = con;
		try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println((rs.getString("select_business_processes_sub_list_all"));
				result = rs.getString("select_business_processes_sub_list_all");
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

	/*
	 * public String select_business_processes_sub_list(String json_request) {
	 * String result= null; Integer bpid=Integer.parseInt(json_request); String
	 * SQL = "SELECT * FROM csau.select_business_processes_sub_list(?)"; try (
	 * Connection conn = con; PreparedStatement pstmt =
	 * conn.prepareStatement(SQL)) { pstmt.setInt(1,bpid); ResultSet rs =
	 * pstmt.executeQuery(); while (rs.next()) {
	 * System.out.println(rs.getString("select_business_processes_sub_list"));
	 * result=rs.getString("select_business_processes_sub_list"); } } catch
	 * (SQLException e) { System.out.println(e.getMessage()); } finally {if
	 * (conn != null) {try {conn.close();} catch (SQLException ex)
	 * {ex.printStackTrace();}}}return result; }
	 */

}
