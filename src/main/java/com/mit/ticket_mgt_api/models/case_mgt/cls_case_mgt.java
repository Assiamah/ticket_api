package com.mit.ticket_mgt_api.models.case_mgt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class cls_case_mgt {
     public Connection con = null;

     public String select_er_land_application_all() {
        String result = null;
        String SQL = "SELECT * FROM case_mgt.select_er_land_application_all()";
       Connection conn = con;
        try {
            
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("select_er_land_application_all");
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

    public String select_er_land_application_confirmed_all() {
        String result = null;
        String SQL = "SELECT * FROM case_mgt.select_er_land_application_confirmed_all()";
       Connection conn = con;
        try {
            
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("select_er_land_application_confirmed_all");
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
    

    public String er_land_application_insert_update(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM case_mgt.er_land_application_insert_update(?)";
       Connection conn = con;
        try {
           
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("er_land_application_insert_update");
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


    public String er_land_application_insert_update_all(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM case_mgt.er_land_application_insert_update_all(?)";
       Connection conn = con;
        try {
           
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("er_land_application_insert_update_all");
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


    public String er_land_application_confirmed_insert_update(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM case_mgt.er_land_application_confirmed_insert_update(?)";
       Connection conn = con;
        try {
           
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("er_land_application_confirmed_insert_update");
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

    
    public String select_er_land_application_by_rela_id(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM case_mgt.select_er_land_application_by_rela_id(?)";
       Connection conn = con;
        try {
           
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("select_er_land_application_by_rela_id");
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

    public String select_er_land_application_confirmed_by_relac_id(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM case_mgt.select_er_land_application_confirmed_by_relac_id(?)";
       Connection conn = con;
        try {
           
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("select_er_land_application_confirmed_by_relac_id");
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
