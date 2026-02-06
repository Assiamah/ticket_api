package com.mit.ticket_mgt_api.models.document_upload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class cls_document_upload {

    public Connection con = null;

    public String select_upload_a_new_file(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM doc_mgt.select_upload_a_new_file(?)";
        Connection conn = con;
        try (

                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // System.out.println(rs.getString("lc_outgoing_sms_log"));
                result = rs.getString("select_upload_a_new_file");
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


    public String select_upload_a_new_file_initial_upload(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM doc_mgt.select_upload_a_new_file_initial_upload(?)";
        Connection conn = con;
        try (

                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // System.out.println(rs.getString("lc_outgoing_sms_log"));
                result = rs.getString("select_upload_a_new_file_initial_upload");
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
    
    public String get_file_location_for_upload(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM doc_mgt.get_file_location_for_upload(?)";
        Connection conn = con;
        try (

                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // System.out.println(rs.getString("lc_outgoing_sms_log"));
                result = rs.getString("get_file_location_for_upload");
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


    public String select_doc_files_details_by_reference_number(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM doc_mgt.select_doc_files_details_by_reference_number(?)";
       Connection conn = con;
        try {
           
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("select_doc_files_details_by_reference_number");
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



    public String select_doc_files_details_by_reference_number_backend(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM doc_mgt.select_doc_files_details_by_reference_number_backend(?)";
       Connection conn = con;
        try {
           
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("select_doc_files_details_by_reference_number_backend");
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

     public String select_doc_files_details_by_reference_number_docmgt(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM doc_mgt.select_doc_files_details_by_reference_number_docmgt(?)";
       Connection conn = con;
        try {
           
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("select_doc_files_details_by_reference_number_docmgt");
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





    public String select_doc_files_details_by_reference_number_backend_correct(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM doc_mgt.select_doc_files_details_by_reference_number_backend_correct(?)";
       Connection conn = con;
        try {
           
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("select_doc_files_details_by_reference_number_backend_correct");
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


    public String select_doc_files_details_by_doc_uuid(String json_request) {
        String result = null;
        String SQL = "SELECT * FROM doc_mgt.select_doc_files_details_by_doc_uuid(?)";
       Connection conn = con;
        try {
           
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("select_doc_files_details_by_doc_uuid");
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



    public String select_get_all_folders() {
        String result = null;
        String SQL = "SELECT * FROM doc_mgt.select_get_all_folders()";
       Connection conn = con;
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
           // pstmt.setString(1, json_request);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("select_get_all_folders");
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
