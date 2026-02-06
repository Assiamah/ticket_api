package com.mit.ticket_mgt_api.models;

import java.sql.*;

public class auth_service_model {
    public Connection con = null;

    public String select_user_for_login(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM user_mgt.select_user_for_login(?)";
        Connection conn = con;
        try {
           
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("select_user_for_login");
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
}
