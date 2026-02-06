package com.mit.ticket_mgt_api.models.organizations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganizationService {

    public Connection con = null;

    public String getAllOrganizations(Connection conn) throws Exception {
        if (conn == null || conn.isClosed()) {
            throw new Exception("Database connection is not established");
        }

        String result = null;
        String SQL = "SELECT * FROM organizations.get_all_organizations() AS result";

        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error loading organizations: " + e.getMessage());
            throw e;
        }

        return (result != null) ? result : "{}";
    }

    public String addOrganization(String jsonReq) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM organizations.add_organization(?::json) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return result;
    }

    public String updateOrganization(String jsonReq) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM organizations.update_organization(?::json) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return result;
    }

    public String getOrganizationById(String jsonReq) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM organizations.get_organization_by_id(?::json) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return result;
    }

    public String getOrganizationsByProduct(String jsonReq) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM organizations.get_organizations_by_product(?::json) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return result;
    }
}