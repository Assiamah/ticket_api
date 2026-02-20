package com.mit.ticket_mgt_api.models;

import java.sql.*;
import java.util.UUID;
import org.codehaus.jettison.json.JSONObject;

public class auth_service_model {
    public Connection con = null;

    public String select_user_for_login(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM security.user_login(?)";
        Connection conn = con;

        if (conn == null) {
            System.out.println("Database connection is null. Cannot execute query.");
            return "{\"status\":\"error\", \"message\":\"Database connection failed\"}";
        }

        try {

            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("user_login");
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

    public String setForcePasswordChange(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM security.set_force_password_change(?::uuid)";
        Connection conn = con;

        if (conn == null) {
            System.out.println("Database connection is null. Cannot execute query.");
            return "{\"status\":\"error\", \"message\":\"Database connection failed\"}";
        }

        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            JSONObject json = new JSONObject(json_request);
            String userId = json.getString("user_id");
            pstmt.setObject(1, java.util.UUID.fromString(userId));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("set_force_password_change");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (org.codehaus.jettison.json.JSONException e) {
            System.out.println("JSON Parsing Error: " + e.getMessage());
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

    public String setDefaultPassword(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM security.set_default_password(?::uuid, ?)";
        Connection conn = con;

        if (conn == null) {
            System.out.println("Database connection is null. Cannot execute query.");
            return "{\"status\":\"error\", \"message\":\"Database connection failed\"}";
        }

        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            JSONObject json = new JSONObject(json_request);
            String userId = json.getString("user_id");
            String defaultPassword = json.getString("default_password");
            pstmt.setObject(1, java.util.UUID.fromString(userId));
            pstmt.setString(2, defaultPassword);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("set_default_password");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (org.codehaus.jettison.json.JSONException e) {
            System.out.println("JSON Parsing Error: " + e.getMessage());
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

    public String resetPasswordWithDefault(String json_request) {
        String result = null;
        // Change to single JSON parameter
        String SQL = "SELECT * FROM security.reset_password_with_default(?::json)";
        Connection conn = con;

        if (conn == null) {
            System.out.println("Database connection is null. Cannot execute query.");
            return "{\"status\":\"error\", \"message\":\"Database connection failed\"}";
        }

        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            JSONObject json = new JSONObject(json_request);

            // Pass the entire JSON string as one parameter
            pstmt.setString(1, json_request); // Pass the whole JSON string

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("reset_password_with_default");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (org.codehaus.jettison.json.JSONException e) {
            System.out.println("JSON Parsing Error: " + e.getMessage());
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

    // public String resetPasswordWithDefault(String json_request) {
    // String result = null;
    // String SQL = "SELECT * FROM security.reset_password_with_default(?::uuid, ?,
    // ?, ?)";
    // Connection conn = con;

    // if (conn == null) {
    // System.out.println("Database connection is null. Cannot execute query.");
    // return "{\"status\":\"error\", \"message\":\"Database connection failed\"}";
    // }

    // try {
    // PreparedStatement pstmt = conn.prepareStatement(SQL);
    // JSONObject json = new JSONObject(json_request);
    // String userId = json.getString("user_id");
    // String defaultPassword = json.getString("default_password");
    // String newPassword = json.getString("new_password");
    // String confirmPassword = json.getString("confirm_password");

    // pstmt.setObject(1, java.util.UUID.fromString(userId));
    // pstmt.setString(2, defaultPassword);
    // pstmt.setString(3, newPassword);
    // pstmt.setString(4, confirmPassword);

    // ResultSet rs = pstmt.executeQuery();
    // while (rs.next()) {
    // result = rs.getString("reset_password_with_default");
    // }
    // } catch (SQLException e) {
    // System.out.println(e.getMessage());
    // } catch (org.codehaus.jettison.json.JSONException e) {
    // System.out.println("JSON Parsing Error: " + e.getMessage());
    // } finally {
    // if (conn != null) {
    // try {
    // conn.close();
    // } catch (SQLException ex) {
    // ex.printStackTrace();
    // }
    // }
    // }
    // return result;
    // }

    public String changePassword(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM security.change_password(?::uuid, ?, ?, ?)";
        Connection conn = con;

        if (conn == null) {
            System.out.println("Database connection is null. Cannot execute query.");
            return "{\"status\":\"error\", \"message\":\"Database connection failed\"}";
        }

        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            JSONObject json = new JSONObject(json_request);
            String userId = json.getString("user_id");
            String currentPassword = json.getString("current_password");
            String newPassword = json.getString("new_password");
            String confirmPassword = json.getString("confirm_password");

            pstmt.setObject(1, java.util.UUID.fromString(userId));
            pstmt.setString(2, currentPassword);
            pstmt.setString(3, newPassword);
            pstmt.setString(4, confirmPassword);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("change_password");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (org.codehaus.jettison.json.JSONException e) {
            System.out.println("JSON Parsing Error: " + e.getMessage());
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