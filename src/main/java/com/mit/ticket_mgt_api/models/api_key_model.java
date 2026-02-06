package com.mit.ticket_mgt_api.models;

import java.sql.*;

public class api_key_model {
    public Connection con = null;

    public String check_api_key_exist(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM user_mgt.check_api_key_exist(?)";
        Connection conn = con;
        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("check_api_key_exist");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            // System.exit(0);
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


    public String check_api_logs(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM user_mgt.check_api_logs(?)";
        Connection conn = con;
        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("check_api_logs");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            // System.exit(0);
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
