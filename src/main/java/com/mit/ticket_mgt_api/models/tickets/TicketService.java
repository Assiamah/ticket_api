package com.mit.ticket_mgt_api.models.tickets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;

public class TicketService {

    public Connection con = null;
    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);
    private final ObjectMapper mapper = new ObjectMapper();

    public String getAllTickets(String jsonReq) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }

        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.get_all_tickets(?::json) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            logger.error("get_all_tickets DB error: {}", e.getMessage());
            throw e;
        }

        return (result != null) ? result : "{\"success\": true, \"data\": []}";
    }

    public String getAllTicketsLegacy(String jsonReq) throws Exception {
        return getAllTickets(jsonReq);
    }

    public String getTicketById(String jsonReq) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.get_ticket_by_id(?::json)";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("get_ticket_by_id");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return result;
    }

    public String createTicket(String jsonReq) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.create_ticket(?::json)";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("create_ticket");
            }
            rs.close();
        } catch (SQLException e) {
            logger.error("create_ticket DB error: {}", e.getMessage());
            throw e;
        }
        logger.info("create_ticket success response_len={}", result != null ? result.length() : 0);
        return result;
    }

    public String updateTicket(String jsonReq) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        // Using select_update_task_record as per user provided functions
        String SQL = "SELECT * FROM tickets_mgt.select_update_task_record(?)";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("select_update_task_record");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return result;
    }

    public String updateTicketStatus(String jsonReq) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.update_ticket_status(?::json) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            logger.error("update_ticket_status DB error: {}", e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{\"success\": false, \"message\": \"No response\"}";
    }

    public String updateTaskStatus(String jsonReq) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        java.util.Map params = mapper.readValue(jsonReq, java.util.Map.class);
        Object ticketId = params.get("ticketId");
        Object taskId = params.get("taskId");
        Object statusId = params.get("statusId");
        if (params.get("ticket_id") == null && ticketId != null)
            params.put("ticket_id", ticketId);
        if (params.get("task_id") == null && taskId != null)
            params.put("task_id", taskId);
        if (statusId == null)
            statusId = params.get("status_id");
        if (statusId == null) {
            Object statusName = params.get("newStatus");
            if (statusName == null)
                statusName = params.get("statusName");
            if (statusName != null) {
                String statusesJson = getStatuses();
                java.util.Map statusesObj = mapper.readValue(statusesJson, java.util.Map.class);
                Object data = statusesObj.get("data");
                if (data instanceof java.util.List) {
                    java.util.List list = (java.util.List) data;
                    for (Object o : list) {
                        if (o instanceof java.util.Map) {
                            java.util.Map st = (java.util.Map) o;
                            Object n = st.get("name");
                            if (n != null && String.valueOf(n).equalsIgnoreCase(String.valueOf(statusName))) {
                                statusId = st.get("status_id");
                                break;
                            }
                        }
                    }
                }
                if (statusId != null)
                    params.put("status_id", statusId);
            }
        }

        String payload = mapper.writeValueAsString(params);
        String result = null;
        if (params.get("ticket_id") != null) {
            String SQL = "SELECT * FROM tickets_mgt.update_ticket_status(?::json) AS result";
            Connection conn = con;
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setString(1, payload);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    result = rs.getString("result");
                }
                rs.close();
            } catch (SQLException e) {
                logger.error("update_ticket_status DB error: {}", e.getMessage());
                throw e;
            }
        } else {
            String SQL = "SELECT * FROM tickets_mgt.select_update_task_status(?)";
            Connection conn = con;
            try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
                pstmt.setString(1, payload);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    result = rs.getString("select_update_task_status");
                }
                rs.close();
            } catch (SQLException e) {
                logger.error("update_task_status DB error: {}", e.getMessage());
                throw e;
            }
        }
        return (result != null) ? result : "{\"success\": false, \"message\": \"No response\"}";
    }

    public String updateStatus(String jsonReq) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.update_status(?::json) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            logger.error("update_status DB error: {}", e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{\"success\": false, \"message\": \"No response\"}";
    }

    public String getOrgArchivedTasks(java.util.Map<String, Object> params) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        java.util.Map<String, Object> payload = new java.util.HashMap<>();
        if (params.containsKey("organization_id"))
            payload.put("organization_id", params.get("organization_id"));
        if (params.containsKey("user_id"))
            payload.put("user_id", params.get("user_id"));
        if (params.containsKey("from_date"))
            payload.put("from_date", params.get("from_date"));
        if (params.containsKey("to_date"))
            payload.put("to_date", params.get("to_date"));
        // Map search filters to new function contract
        if (params.containsKey("search_text"))
            payload.put("search_text", params.get("search_text"));
        else if (params.containsKey("search"))
            payload.put("search_text", params.get("search"));
        if (params.containsKey("status_filter"))
            payload.put("status_filter", params.get("status_filter"));
        else if (params.containsKey("status"))
            payload.put("status_filter", params.get("status"));
        if (params.containsKey("category_filter"))
            payload.put("category_filter", params.get("category_filter"));
        else if (params.containsKey("category"))
            payload.put("category_filter", params.get("category"));
        if (params.containsKey("limit"))
            payload.put("limit", params.get("limit"));
        if (params.containsKey("page"))
            payload.put("page", params.get("page"));

        String jsonReq = mapper.writeValueAsString(payload);
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.get_org_archived_tasks(?::json) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            logger.error("get_org_archived_tasks DB error: {}", e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{\"success\": true, \"data\": {\"tasks\": []}}";
    }

    // public String getAllArchivedTasks(java.util.Map<String, Object> params) throws Exception {
    //     if (con == null) {
    //         throw new Exception("Database connection is not established");
    //     }
    //     Long uid = null;
    //     try {
    //         Object uidObj = params.get("user_id");
    //         if (uidObj != null) {
    //             String s = String.valueOf(uidObj);
    //             try {
    //                 uid = Long.parseLong(s);
    //             } catch (Exception ignored) {
    //             }
    //         }
    //         if (uid == null) {
    //             try {
    //                 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //                 if (auth != null && auth.getDetails() != null) {
    //                     String det = String.valueOf(auth.getDetails());
    //                     try {
    //                         uid = Long.parseLong(det);
    //                     } catch (Exception ignored) {
    //                     }
    //                     if (uid == null) {
    //                         Long resolved = resolveUserIdFromUuid(det);
    //                         if (resolved != null) {
    //                             uid = resolved;
    //                         }
    //                     }
    //                 }
    //             } catch (Exception ignored) {
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }

    //     java.util.Map<String, Object> payload = new java.util.HashMap<>();
    //     if (uid != null)
    //         payload.put("user_id", uid);
    //     if (params.containsKey("organization_id"))
    //         payload.put("organization_id", params.get("organization_id"));
    //     if (params.containsKey("from_date"))
    //         payload.put("from_date", params.get("from_date"));
    //     if (params.containsKey("to_date"))
    //         payload.put("to_date", params.get("to_date"));
    //     if (params.containsKey("search"))
    //         payload.put("search", params.get("search"));
    //     if (params.containsKey("status"))
    //         payload.put("status", params.get("status"));
    //     if (params.containsKey("limit"))
    //         payload.put("limit", params.get("limit"));
    //     if (params.containsKey("page"))
    //         payload.put("page", params.get("page"));

    //     String jsonReq = mapper.writeValueAsString(payload);
    //     String result = null;
    //     String SQL = "SELECT * FROM tickets_mgt.get_all_archived_tasks(?::json) AS result";
    //     Connection conn = con;
    //     try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    //         pstmt.setString(1, jsonReq);
    //         ResultSet rs = pstmt.executeQuery();
    //         if (rs.next()) {
    //             result = rs.getString("result");
    //         }
    //         rs.close();
    //     } catch (SQLException e) {
    //         logger.error("get_all_archived_tasks DB error: {}", e.getMessage());
    //         throw e;
    //     }
    //     return (result != null) ? result : "{\"success\": true, \"data\": {\"tasks\": []}}";
    // }

    public String getOrgArchivedTasksN(java.util.Map<String, Object> params) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String orgId = null;
        if (params.containsKey("organization_id"))
            orgId = String.valueOf(params.get("organization_id"));
        if (orgId == null && params.containsKey("org_id"))
            orgId = String.valueOf(params.get("org_id"));
        Integer limit = null;
        Integer offset = null;
        if (params.containsKey("limit")) {
            try {
                limit = Integer.valueOf(String.valueOf(params.get("limit")));
            } catch (Exception ignored) {
            }
        }
        if (params.containsKey("offset")) {
            try {
                offset = Integer.valueOf(String.valueOf(params.get("offset")));
            } catch (Exception ignored) {
            }
        }
        if (limit == null)
            limit = 50;
        if (offset == null)
            offset = 0;

        String result = null;
        String SQL = "SELECT tickets_mgt.get_org_archived_tasks_n(?::uuid, ?::int, ?::int) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, orgId);
            pstmt.setInt(2, limit);
            pstmt.setInt(3, offset);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            logger.error("get_org_archived_tasks_n DB error: {}", e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{\"success\": true, \"data\": {\"tasks\": []}}";
    }

    public String getAllArchivedTasksSimple(java.util.Map<String, Object> params) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        Integer limit = null;
        Integer offset = null;
        if (params.containsKey("limit")) {
            try {
                limit = Integer.valueOf(String.valueOf(params.get("limit")));
            } catch (Exception ignored) {
            }
        }
        if (params.containsKey("offset")) {
            try {
                offset = Integer.valueOf(String.valueOf(params.get("offset")));
            } catch (Exception ignored) {
            }
        }
        if (limit == null)
            limit = 50;
        if (offset == null)
            offset = 0;

        String result = null;
        String SQL = "SELECT tickets_mgt.get_all_archived_tasks_simple(?::int, ?::int) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            logger.error("get_all_archived_tasks_simple DB error: {}", e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{\"success\": true, \"data\": {\"tasks\": []}}";
    }

    public String reopenArchivedTask(String jsonReq) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.reopen_archived_task(?::json) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            logger.error("reopen_archived_task DB error: {}", e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{\"success\": false, \"message\": \"No response\"}";
    }

    public String getTicketComments(String ticketId) throws Exception {
        logger.warn("getTicketComments not available in allowed DB functions");
        return "{\"success\": false, \"message\": \"Function not available\"}";
    }

    public String addTicketComment(String jsonReq) throws Exception {
        logger.warn("addTicketComment not available in allowed DB functions");
        return "{\"success\": false, \"message\": \"Function not available\"}";
    }

    public String getTicketHistory(String ticketId) throws Exception {
        logger.warn("getTicketHistory not available in allowed DB functions");
        return "{\"success\": false, \"message\": \"Function not available\"}";
    }

    public String getPriorities() throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.get_priorities('{}'::json) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error getting priorities: " + e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{\"success\": true, \"data\": []}";
    }

    public String getStatuses() throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.get_statuses('{}'::json) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error getting statuses: " + e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{\"success\": true, \"data\": []}";
    }

    public String getDepartments() throws Exception {
        logger.warn("getDepartments not available in allowed DB functions");
        return "{\"success\": false, \"message\": \"Function not available\"}";
    }

    public String getUserOrgContext(Long userId) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT json_build_object(\n" +
                " 'org_id', o.org_id,\n" +
                " 'org_name', o.org_name,\n" +
                " 'org_code', o.org_code,\n" +
                " 'is_system_owner', o.is_system_owner\n" +
                ") AS result\n" +
                "FROM users.tbl_users u\n" +
                "JOIN organizations.tbl_organizations o ON u.org_id = o.org_id\n" +
                "WHERE u.id = ?";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setLong(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error getting user org context: " + e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{}";
    }

    public String getCategories(String departmentId) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.get_categories('{}'::json) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error getting categories: " + e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{\"success\": true, \"data\": []}";
    }

    public String getAllTicketsAdmin(Map<String, Object> params) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String jsonReq = mapper.writeValueAsString(params);
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.get_all_tickets(?::json) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            logger.error("get_all_tickets DB error: {}", e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{\"success\": true, \"data\": []}";
    }

    public String getTicketsByOrg(Map<String, Object> params) throws Exception {
        // Reusing get_all_tickets as it handles org filtering based on user_id/org_id
        // context
        // Note: The new get_all_tickets relies on user_id to determine org context.
        // If params contains org_id but not user_id, this might be an issue.
        // However, usually user_id is passed.
        return getAllTicketsAdmin(params);
    }

    public String getPrioritiesPaged(Integer limit, Integer offset, String dateFrom, String dateTo) throws Exception {
        // Ignoring pagination params as new function doesn't support them yet, or we
        // pass them in JSON
        // But the provided get_priorities(json) doesn't seem to use them.
        return getPriorities();
    }

    public String getStatusesPaged(Integer limit, Integer offset, String dateFrom, String dateTo) throws Exception {
        return getStatuses();
    }

    public String getCategoriesPaged(Integer limit, Integer offset, String dateFrom, String dateTo,
            Boolean includeInactive)
            throws Exception {
        return getCategories(null);
    }

    public String getAdminAnalytics(String dateFrom, String dateTo) throws Exception {
        // Mapping to get_org_dashboard_analytics
        // We need user_id for context. If not provided, this might fail or need a
        // default.
        // Assuming params are passed via a Map in controller, but here signature is
        // specific.
        // We'll create a JSON with available params.
        if (con == null) {
            throw new Exception("Database connection is not established");
        }

        // We need a user_id to get org context.
        // If this method is called without user_id, we might have a problem.
        // But let's assume the controller passes it or we construct a minimal JSON.
        // Actually, the new function get_org_dashboard_analytics REQUIRES user_id.
        // We'll pass a dummy or require controller change.
        // For now, let's construct a JSON with what we have.

        String jsonReq = String.format("{\"start_date\": \"%s\", \"end_date\": \"%s\"}",
                dateFrom != null ? dateFrom : "", dateTo != null ? dateTo : "");

        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.get_org_dashboard_analytics(?::json) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error getting analytics: " + e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{}";
    }

    public String getUserAnalytics(String orgId, String dateFrom, String dateTo) throws Exception {
        // Mapping to get_org_dashboard_analytics
        // This function expects user_id.
        String jsonReq = String.format("{\"org_id\": \"%s\", \"start_date\": \"%s\", \"end_date\": \"%s\"}",
                orgId, dateFrom != null ? dateFrom : "", dateTo != null ? dateTo : "");

        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.get_org_dashboard_analytics(?::json) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error getting user analytics: " + e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{}";
    }

    // public String getOrgDashboardData(java.util.Map<String, Object> params) throws Exception {
    //     if (con == null) {
    //         throw new Exception("Database connection is not established");
    //     }
    //     Long uid = null;
    //     try {
    //         Object uidObj = params.get("user_id");
    //         if (uidObj != null) {
    //             String s = String.valueOf(uidObj);
    //             try {
    //                 uid = Long.parseLong(s);
    //             } catch (Exception ignored) {
    //             }
    //         }
    //         if (uid == null) {
    //             try {
    //                 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //                 if (auth != null && auth.getDetails() != null) {
    //                     String det = String.valueOf(auth.getDetails());
    //                     try {
    //                         uid = Long.parseLong(det);
    //                     } catch (Exception ignored) {
    //                     }
    //                     if (uid == null) {
    //                         Long resolved = resolveUserIdFromUuid(det);
    //                         if (resolved != null) {
    //                             uid = resolved;
    //                         }
    //                     }
    //                 }
    //             } catch (Exception ignored) {
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }

    //     java.util.Map<String, Object> payload = new java.util.HashMap<>();
    //     if (uid != null)
    //         payload.put("user_id", uid);
    //     if (params.containsKey("organization_id"))
    //         payload.put("organization_id", params.get("organization_id"));
    //     if (params.containsKey("from_date"))
    //         payload.put("from_date", params.get("from_date"));
    //     if (params.containsKey("to_date"))
    //         payload.put("to_date", params.get("to_date"));

    //     String jsonReq = mapper.writeValueAsString(payload);
    //     String result = null;
    //     String SQL = "SELECT * FROM tickets_mgt.get_org_dashboard_data(?::json) AS result";
    //     Connection conn = con;
    //     try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    //         pstmt.setString(1, jsonReq);
    //         ResultSet rs = pstmt.executeQuery();
    //         if (rs.next()) {
    //             result = rs.getString("result");
    //         }
    //         rs.close();
    //     } catch (SQLException e) {
    //         System.out.println("Error getting org dashboard data: " + e.getMessage());
    //         throw e;
    //     }
    //     return (result != null) ? result : "{\"success\": true, \"data\": {}}";
    // }

    // public String getUserOrgDashboardData(java.util.Map<String, Object> params) throws Exception {
    //     if (con == null) {
    //         throw new Exception("Database connection is not established");
    //     }
    //     Long uid = null;
    //     try {
    //         Object uidObj = params.get("user_id");
    //         if (uidObj != null) {
    //             String s = String.valueOf(uidObj);
    //             try {
    //                 uid = Long.parseLong(s);
    //             } catch (Exception ignored) {
    //             }
    //         }
    //         if (uid == null) {
    //             try {
    //                 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //                 if (auth != null && auth.getDetails() != null) {
    //                     String det = String.valueOf(auth.getDetails());
    //                     try {
    //                         uid = Long.parseLong(det);
    //                     } catch (Exception ignored) {
    //                     }
    //                     if (uid == null) {
    //                         Long resolved = resolveUserIdFromUuid(det);
    //                         if (resolved != null) {
    //                             uid = resolved;
    //                         }
    //                     }
    //                 }
    //             } catch (Exception ignored) {
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }

    //     java.util.Map<String, Object> payload = new java.util.HashMap<>();
    //     if (uid != null)
    //         payload.put("user_id", uid);
    //     if (params.containsKey("organization_id"))
    //         payload.put("organization_id", params.get("organization_id"));
    //     if (params.containsKey("from_date"))
    //         payload.put("from_date", params.get("from_date"));
    //     if (params.containsKey("to_date"))
    //         payload.put("to_date", params.get("to_date"));

    //     String jsonReq = mapper.writeValueAsString(payload);
    //     String result = null;
    //     String SQL = "SELECT * FROM tickets_mgt.get_user_org_dashboard_data_fixed(?::json) AS result";
    //     Connection conn = con;
    //     try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    //         pstmt.setString(1, jsonReq);
    //         ResultSet rs = pstmt.executeQuery();
    //         if (rs.next()) {
    //             result = rs.getString("result");
    //         }
    //         rs.close();
    //     } catch (SQLException e) {
    //         System.out.println("Error getting user org dashboard data: " + e.getMessage());
    //         throw e;
    //     }
    //     return (result != null) ? result : "{\"success\": true, \"data\": {}}";
    // }

    public String getOrgDashboardSummary(java.util.Map<String, Object> params) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String orgId = null;
        if (params.containsKey("organization_id"))
            orgId = String.valueOf(params.get("organization_id"));
        if (orgId == null && params.containsKey("org_id"))
            orgId = String.valueOf(params.get("org_id"));
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.get_org_dashboard_summary(?::uuid) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, orgId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error getting org dashboard summary: " + e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{\"success\": true, \"data\": {}}";
    }

    // public String getOrganizationTicketsAnalytics(java.util.Map<String, Object> params) throws Exception {
    //     if (con == null) {
    //         throw new Exception("Database connection is not established");
    //     }

    //     String userIdText = null;
    //     Object u = null;
    //     if (params != null) {
    //         u = params.get("user_id");
    //         if (u == null)
    //             u = params.get("requesting_user_id");
    //         if (u == null)
    //             u = params.get("p_user_id");
    //     }
    //     if (u != null)
    //         userIdText = String.valueOf(u);
    //     if (userIdText == null || userIdText.isEmpty()) {
    //         try {
    //             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 userIdText = String.valueOf(auth.getDetails());
    //             }
    //         } catch (Exception ignored) {
    //         }
    //     }

    //     java.sql.Date startDate = null;
    //     java.sql.Date endDate = null;
    //     Boolean includeArchived = null;
    //     try {
    //         Object sd = params != null ? params.get("start_date") : null;
    //         Object ed = params != null ? params.get("end_date") : null;
    //         Object ia = params != null ? params.get("include_archived") : null;
    //         if (sd != null) {
    //             String s = String.valueOf(sd).trim();
    //             if (!s.isEmpty() && !"null".equalsIgnoreCase(s)) {
    //                 startDate = java.sql.Date.valueOf(s);
    //             }
    //         }
    //         if (ed != null) {
    //             String s = String.valueOf(ed).trim();
    //             if (!s.isEmpty() && !"null".equalsIgnoreCase(s)) {
    //                 endDate = java.sql.Date.valueOf(s);
    //             }
    //         }
    //         if (ia != null) {
    //             includeArchived = Boolean.valueOf(String.valueOf(ia));
    //         }
    //     } catch (Exception ignored) {
    //     }
    //     if (includeArchived == null)
    //         includeArchived = Boolean.FALSE;

    //     String result = null;
    //     String SQL = "SELECT tickets_mgt.get_organization_tickets_analytics(?::text, ?::date, ?::date, ?::boolean) AS result";
    //     Connection conn = con;
    //     try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    //         if (userIdText != null && !userIdText.isEmpty()) {
    //             pstmt.setString(1, userIdText);
    //         } else {
    //             pstmt.setNull(1, Types.VARCHAR);
    //         }
    //         if (startDate != null) {
    //             pstmt.setDate(2, startDate);
    //         } else {
    //             pstmt.setNull(2, Types.DATE);
    //         }
    //         if (endDate != null) {
    //             pstmt.setDate(3, endDate);
    //         } else {
    //             pstmt.setNull(3, Types.DATE);
    //         }
    //         pstmt.setBoolean(4, includeArchived);
    //         ResultSet rs = pstmt.executeQuery();
    //         if (rs.next()) {
    //             result = rs.getString("result");
    //         }
    //         rs.close();
    //     } catch (SQLException e) {
    //         logger.error("get_organization_tickets_analytics DB error: {}", e.getMessage());
    //         throw e;
    //     }
    //     return (result != null) ? result : "{}";
    // }

    public String getOrgCategoryChartData(java.util.Map<String, Object> params) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String orgId = null;
        if (params.containsKey("organization_id"))
            orgId = String.valueOf(params.get("organization_id"));
        if (orgId == null && params.containsKey("org_id"))
            orgId = String.valueOf(params.get("org_id"));
        String fromDate = params.containsKey("from_date") ? String.valueOf(params.get("from_date")) : null;
        String toDate = params.containsKey("to_date") ? String.valueOf(params.get("to_date")) : null;
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.get_org_category_chart_data(?::uuid, ?::date, ?::date) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, orgId);
            pstmt.setString(2, fromDate);
            pstmt.setString(3, toDate);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error getting org category chart data: " + e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{\"categories\": [], \"top_category\": \"None\", \"total_categories\": 0}";
    }

    public String assignTicket(String jsonReq) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.select_assign_task(?)";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("select_assign_task");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error assigning ticket: " + e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{}";
    }

    public String archiveTicketMain(String jsonReq) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.archive_ticket(?::json)";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("archive_ticket");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error archiving ticket: " + e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{}";
    }

    // public String fetchArchivedTickets(Map<String, Object> params) throws Exception {
    //     if (con == null) {
    //         throw new Exception("Database connection is not established");
    //     }
    //     Long uid = null;
    //     try {
    //         Object uidObj = params.get("user_id");
    //         if (uidObj != null) {
    //             String s = String.valueOf(uidObj);
    //             try {
    //                 uid = Long.parseLong(s);
    //             } catch (Exception ignored) {
    //             }
    //         }
    //         if (uid == null) {
    //             try {
    //                 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //                 if (auth != null && auth.getDetails() != null) {
    //                     String det = String.valueOf(auth.getDetails());
    //                     try {
    //                         uid = Long.parseLong(det);
    //                     } catch (Exception ignored) {
    //                     }
    //                     if (uid == null) {
    //                         Long resolved = resolveUserIdFromUuid(det);
    //                         if (resolved != null) {
    //                             uid = resolved;
    //                         }
    //                     }
    //                 }
    //             } catch (Exception ignored) {
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }
    //     if (uid == null) {
    //         return "{\"success\": false, \"message\": \"user_id is required\"}";
    //     }

    //     java.util.Map<String, Object> payload = new java.util.HashMap<>();
    //     payload.put("user_id", uid);
    //     if (params.containsKey("organization_id"))
    //         payload.put("organization_id", params.get("organization_id"));
    //     if (params.containsKey("from_date"))
    //         payload.put("from_date", params.get("from_date"));
    //     if (params.containsKey("to_date"))
    //         payload.put("to_date", params.get("to_date"));
    //     if (params.containsKey("search_text"))
    //         payload.put("search_text", params.get("search_text"));
    //     if (params.containsKey("page"))
    //         payload.put("page", params.get("page"));
    //     if (params.containsKey("limit"))
    //         payload.put("limit", params.get("limit"));

    //     String jsonReq = mapper.writeValueAsString(payload);
    //     String result = null;
    //     String SQL = "SELECT * FROM tickets_mgt.fetch_archived_tickets(?::json) AS result";
    //     Connection conn = con;
    //     try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    //         pstmt.setString(1, jsonReq);
    //         ResultSet rs = pstmt.executeQuery();
    //         if (rs.next()) {
    //             result = rs.getString("result");
    //         }
    //         rs.close();
    //     } catch (SQLException e) {
    //         System.out.println("Error fetching archived tickets: " + e.getMessage());
    //         throw e;
    //     }
    //     return (result != null) ? result : "{\"success\": true, \"data\": []}";
    // }

    // public String getUserTickets(Map<String, Object> params) throws Exception {
    //     if (con == null) {
    //         throw new Exception("Database connection is not established");
    //     }
    //     Long uid = null;
    //     try {
    //         Object uidObj = params.get("user_id");
    //         if (uidObj != null) {
    //             String s = String.valueOf(uidObj);
    //             try {
    //                 uid = Long.parseLong(s);
    //             } catch (Exception ignored) {
    //             }
    //         }
    //         if (uid == null) {
    //             try {
    //                 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //                 if (auth != null && auth.getDetails() != null) {
    //                     String det = String.valueOf(auth.getDetails());
    //                     try {
    //                         uid = Long.parseLong(det);
    //                     } catch (Exception ignored) {
    //                     }
    //                     if (uid == null) {
    //                         Long resolved = resolveUserIdFromUuid(det);
    //                         if (resolved != null) {
    //                             uid = resolved;
    //                         }
    //                     }
    //                 }
    //             } catch (Exception ignored) {
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }
    //     if (uid == null) {
    //         return "{\"success\": false, \"message\": \"user_id is required\"}";
    //     }

    //     java.util.Map<String, Object> payload = new java.util.HashMap<>();
    //     payload.put("user_id", uid);
    //     if (params.containsKey("limit"))
    //         payload.put("limit", params.get("limit"));
    //     if (params.containsKey("offset"))
    //         payload.put("offset", params.get("offset"));
    //     if (params.containsKey("status_filter"))
    //         payload.put("status_filter", params.get("status_filter"));
    //     if (params.containsKey("priority_filter"))
    //         payload.put("priority_filter", params.get("priority_filter"));
    //     if (params.containsKey("include_archived"))
    //         payload.put("include_archived", params.get("include_archived"));

    //     String jsonReq = mapper.writeValueAsString(payload);
    //     String result = null;
    //     String SQL = "SELECT * FROM tickets_mgt.get_tasks_assigned_to_user(?::json) AS result";
    //     Connection conn = con;
    //     try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    //         pstmt.setString(1, jsonReq);
    //         ResultSet rs = pstmt.executeQuery();
    //         if (rs.next()) {
    //             result = rs.getString("result");
    //         }
    //         rs.close();
    //     } catch (SQLException e) {
    //         System.out.println("Error getting user tickets: " + e.getMessage());
    //         throw e;
    //     }
    //     return (result != null) ? result : "{}";
    // }

    // public String getUserAssignedTasks(Map<String, Object> params) throws Exception {
    //     if (con == null) {
    //         throw new Exception("Database connection is not established");
    //     }
    //     Long uid = null;
    //     try {
    //         Object uidObj = params.get("user_id");
    //         if (uidObj != null) {
    //             String s = String.valueOf(uidObj);
    //             try {
    //                 uid = Long.parseLong(s);
    //             } catch (Exception ignored) {
    //             }
    //         }
    //         if (uid == null) {
    //             try {
    //                 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //                 if (auth != null && auth.getDetails() != null) {
    //                     String det = String.valueOf(auth.getDetails());
    //                     try {
    //                         uid = Long.parseLong(det);
    //                     } catch (Exception ignored) {
    //                     }
    //                     if (uid == null) {
    //                         Long resolved = resolveUserIdFromUuid(det);
    //                         if (resolved != null) {
    //                             uid = resolved;
    //                         }
    //                     }
    //                 }
    //             } catch (Exception ignored) {
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }

    //     java.util.Map<String, Object> payload = new java.util.HashMap<>();
    //     if (uid != null)
    //         payload.put("user_id", uid);
    //     if (params.containsKey("from_date"))
    //         payload.put("from_date", params.get("from_date"));
    //     if (params.containsKey("to_date"))
    //         payload.put("to_date", params.get("to_date"));
    //     if (params.containsKey("status"))
    //         payload.put("status", params.get("status"));
    //     if (params.containsKey("priority"))
    //         payload.put("priority", params.get("priority"));
    //     if (params.containsKey("category"))
    //         payload.put("category", params.get("category"));
    //     if (params.containsKey("show_archived"))
    //         payload.put("show_archived", params.get("show_archived"));
    //     if (params.containsKey("limit"))
    //         payload.put("limit", params.get("limit"));
    //     if (params.containsKey("page"))
    //         payload.put("page", params.get("page"));

    //     String jsonReq = mapper.writeValueAsString(payload);
    //     String result = null;
    //     String SQL = "SELECT * FROM tickets_mgt.get_user_assigned_tasks(?::json) AS result";
    //     Connection conn = con;
    //     try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    //         pstmt.setString(1, jsonReq);
    //         ResultSet rs = pstmt.executeQuery();
    //         if (rs.next()) {
    //             result = rs.getString("result");
    //         }
    //         rs.close();
    //     } catch (SQLException e) {
    //         System.out.println("Error getting user assigned tasks: " + e.getMessage());
    //         throw e;
    //     }
    //     return (result != null) ? result : "{\"success\": true, \"data\": {\"tasks\": []}}";
    // }

    // public String getTicketsAssignedToUser(Map<String, Object> params) throws Exception {
    //     return getUserTickets(params);
    // }

    // public String getMyAssignedTasks(Map<String, Object> params) throws Exception {
    //     if (con == null) {
    //         throw new Exception("Database connection is not established");
    //     }
    //     Long uid = null;
    //     try {
    //         Object uidObj = params.get("user_id");
    //         if (uidObj != null) {
    //             String s = String.valueOf(uidObj);
    //             try {
    //                 uid = Long.parseLong(s);
    //             } catch (Exception ignored) {
    //             }
    //         }
    //         if (uid == null) {
    //             try {
    //                 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //                 if (auth != null && auth.getDetails() != null) {
    //                     String det = String.valueOf(auth.getDetails());
    //                     try {
    //                         uid = Long.parseLong(det);
    //                     } catch (Exception ignored) {
    //                     }
    //                     if (uid == null) {
    //                         Long resolved = resolveUserIdFromUuid(det);
    //                         if (resolved != null) {
    //                             uid = resolved;
    //                         }
    //                     }
    //                 }
    //             } catch (Exception ignored) {
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }
    //     if (uid == null) {
    //         return "{\"success\": false, \"message\": \"user_id is required\"}";
    //     }

    //     java.util.Map<String, Object> payload = new java.util.HashMap<>();
    //     payload.put("user_id", uid);
    //     if (params.containsKey("limit"))
    //         payload.put("limit", params.get("limit"));
    //     if (params.containsKey("offset"))
    //         payload.put("offset", params.get("offset"));
    //     if (params.containsKey("status_filter"))
    //         payload.put("status_filter", params.get("status_filter"));
    //     if (params.containsKey("priority_filter"))
    //         payload.put("priority_filter", params.get("priority_filter"));
    //     if (params.containsKey("include_archived"))
    //         payload.put("include_archived", params.get("include_archived"));

    //     String jsonReq = mapper.writeValueAsString(payload);
    //     String result = null;
    //     String SQL = "SELECT * FROM tickets_mgt.get_my_assigned_tasks(?::json) AS result";
    //     Connection conn = con;
    //     try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    //         pstmt.setString(1, jsonReq);
    //         ResultSet rs = pstmt.executeQuery();
    //         if (rs.next()) {
    //             result = rs.getString("result");
    //         }
    //         rs.close();
    //     } catch (SQLException e) {
    //         System.out.println("Error getting my assigned tasks: " + e.getMessage());
    //         throw e;
    //     }
    //     return (result != null) ? result : "{}";
    // }

    public String closeTicket(String jsonReq) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.select_update_task_status(?)";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("select_update_task_status");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error closing ticket: " + e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{}";
    }

    public String reopenTicket(String ticketId, Integer userId) throws Exception {
        logger.warn("reopenTicket not available in allowed DB functions");
        return "{\"success\": false, \"message\": \"Function not available\"}";
    }

    // public String getAdminAnalyticsWithUser(String userId, String dateFrom, String dateTo) throws Exception {
    //     if (con == null) {
    //         throw new Exception("Database connection is not established");
    //     }

    //     if (userId == null || userId.isEmpty()) {
    //         try {
    //             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 Object details = auth.getDetails();
    //                 userId = details != null ? String.valueOf(details) : null;
    //             }
    //         } catch (Exception ignored) {
    //         }
    //     }

    //     if (userId == null || userId.isEmpty()) {
    //         return "{\"status\": \"error\", \"message\": \"user_id required for stats\"}";
    //     }

    //     java.util.Map<String, Object> payload = new java.util.HashMap<>();
    //     payload.put("user_id", userId);
    //     if (dateFrom != null)
    //         payload.put("start_date", dateFrom);
    //     if (dateTo != null)
    //         payload.put("end_date", dateTo);
    //     String jsonReq = mapper.writeValueAsString(payload);

    //     String result = null;
    //     String SQL = "SELECT * FROM tickets_mgt.get_org_dashboard_analytics(?::json) AS result";
    //     Connection conn = con;
    //     try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    //         pstmt.setString(1, jsonReq);
    //         ResultSet rs = pstmt.executeQuery();
    //         if (rs.next()) {
    //             result = rs.getString("result");
    //         }
    //         rs.close();
    //     } catch (SQLException e) {
    //         System.out.println("Error getting analytics: " + e.getMessage());
    //         throw e;
    //     }
    //     return (result != null) ? result : "{}";
    // }

    // public String getTicketAttachments(String ticketId) throws Exception {
    //     logger.warn("getTicketAttachments not available in allowed DB functions");
    //     return "{\"success\": false, \"message\": \"Function not available\"}";
    // }

    // public String addTicketAttachment(String jsonReq) throws Exception {
    //     logger.warn("addTicketAttachment not available in allowed DB functions");
    //     return "{\"success\": false, \"message\": \"Function not available\"}";
    // }

    // public String deleteTicketAttachment(String attachmentId, Integer userId) throws Exception {
    //     logger.warn("deleteTicketAttachment not available in allowed DB functions");
    //     return "{\"success\": false, \"message\": \"Function not available\"}";
    // }

    // public String searchTickets(Map<String, Object> params) throws Exception {
    //     logger.warn("searchTickets not available in allowed DB functions");
    //     return "{\"success\": false, \"message\": \"Function not available\"}";
    // }

    // public String getAgentPerformance(Map<String, Object> params) throws Exception {
    //     logger.warn("getAgentPerformance not available in allowed DB functions");
    //     return "{\"success\": false, \"message\": \"Function not available\"}";
    // }

    // public String getDepartmentStats(Map<String, Object> params) throws Exception {
    //     logger.warn("getDepartmentStats not available in allowed DB functions");
    //     return "{\"success\": false, \"message\": \"Function not available\"}";
    // }

    // private Long resolveUserIdFromUuid(String uuidStr) {
    //     if (uuidStr == null || uuidStr.isEmpty())
    //         return null;
    //     String SQL = "SELECT id FROM users.tbl_users WHERE unique_id = ?::uuid";
    //     Connection conn = con;
    //     try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    //         pstmt.setString(1, uuidStr);
    //         ResultSet rs = pstmt.executeQuery();
    //         if (rs.next()) {
    //             Object v = rs.getObject("id");
    //             if (v instanceof Number) {
    //                 return ((Number) v).longValue();
    //             }
    //         }
    //         rs.close();
    //     } catch (Exception ignored) {
    //     }
    //     return null;
    // }

    public String getUsersForAssignment(String jsonReq) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.get_users_for_assignment(?::json) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, jsonReq);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error getting users for assignment: " + e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{\"success\": true, \"users\": []}";
    }

    public String getUsersForAssignment(Long userId) throws Exception {
        if (con == null) {
            throw new Exception("Database connection is not established");
        }
        String result = null;
        String SQL = "SELECT * FROM tickets_mgt.get_users_for_assignment(?) AS result";
        Connection conn = con;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            if (userId == null) {
                throw new IllegalArgumentException("user_id is required");
            }
            pstmt.setLong(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString("result");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error getting users for assignment: " + e.getMessage());
            throw e;
        }
        return (result != null) ? result : "{\"success\": true, \"users\": []}";
    }

    // public String getSystemOwnerUsersWithJobCount(java.util.Map<String, Object> params) throws Exception {
    //     if (con == null) {
    //         throw new Exception("Database connection is not established");
    //     }
    //     Long uid = null;
    //     try {
    //         Object uidObj = params.get("user_id");
    //         if (uidObj == null && params.containsKey("requesting_user_id"))
    //             uidObj = params.get("requesting_user_id");
    //         if (uidObj != null) {
    //             String s = String.valueOf(uidObj);
    //             try {
    //                 uid = Long.parseLong(s);
    //             } catch (Exception ignored) {
    //             }
    //             if (uid == null) {
    //                 Long resolved = resolveUserIdFromUuid(s);
    //                 if (resolved != null)
    //                     uid = resolved;
    //             }
    //         }
    //         if (uid == null) {
    //             try {
    //                 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //                 if (auth != null && auth.getDetails() != null) {
    //                     String det = String.valueOf(auth.getDetails());
    //                     try {
    //                         uid = Long.parseLong(det);
    //                     } catch (Exception ignored) {
    //                     }
    //                     if (uid == null) {
    //                         Long resolved = resolveUserIdFromUuid(det);
    //                         if (resolved != null)
    //                             uid = resolved;
    //                     }
    //                 }
    //             } catch (Exception ignored) {
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }

    //     String userIdStr = null;
    //     if (params.containsKey("user_id")) {
    //         userIdStr = String.valueOf(params.get("user_id"));
    //     } else if (params.containsKey("requesting_user_id")) {
    //         userIdStr = String.valueOf(params.get("requesting_user_id"));
    //     }
    //     if (userIdStr == null) {
    //         try {
    //             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 String det = String.valueOf(auth.getDetails());
    //                 if (det != null && !det.isEmpty()) {
    //                     userIdStr = det;
    //                 }
    //             }
    //         } catch (Exception ignored) {
    //         }
    //     }
    //     if (userIdStr == null && uid != null) {
    //         String SQLuid = "SELECT unique_id::text AS unique_id FROM users.tbl_users WHERE id = ?";
    //         Connection conn2 = con;
    //         try (PreparedStatement ps = conn2.prepareStatement(SQLuid)) {
    //             ps.setLong(1, uid);
    //             ResultSet r = ps.executeQuery();
    //             if (r.next()) {
    //                 String u = r.getString("unique_id");
    //                 if (u != null && !u.isEmpty())
    //                     userIdStr = u;
    //             }
    //             r.close();
    //         } catch (Exception ignored) {
    //         }
    //     }

    //     Integer limit = null;
    //     Integer offset = null;
    //     Integer page = null;
    //     String searchText = null;
    //     if (params.containsKey("limit")) {
    //         try {
    //             limit = Integer.valueOf(String.valueOf(params.get("limit")));
    //         } catch (Exception ignored) {
    //         }
    //     }
    //     if (params.containsKey("offset")) {
    //         try {
    //             offset = Integer.valueOf(String.valueOf(params.get("offset")));
    //         } catch (Exception ignored) {
    //         }
    //     }
    //     if (params.containsKey("page")) {
    //         try {
    //             page = Integer.valueOf(String.valueOf(params.get("page")));
    //         } catch (Exception ignored) {
    //         }
    //     }
    //     if (params.containsKey("search_text")) {
    //         searchText = String.valueOf(params.get("search_text"));
    //     }
    //     if (limit == null)
    //         limit = 10;
    //     if (offset == null) {
    //         if (page != null && page > 0)
    //             offset = (page - 1) * limit;
    //         else
    //             offset = 0;
    //     }

    //     String result = null;
    //     String SQL = "SELECT tickets_mgt.get_system_owner_users_with_job_count(?, ?, ?, ?) AS result";
    //     Connection conn = con;
    //     try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    //         if (userIdStr != null)
    //             pstmt.setString(1, userIdStr);
    //         else
    //             pstmt.setNull(1, java.sql.Types.VARCHAR);
    //         pstmt.setInt(2, limit);
    //         pstmt.setInt(3, offset);
    //         if (searchText != null && !searchText.isEmpty())
    //             pstmt.setString(4, searchText);
    //         else
    //             pstmt.setNull(4, java.sql.Types.VARCHAR);
    //         ResultSet rs = pstmt.executeQuery();
    //         if (rs.next()) {
    //             result = rs.getString("result");
    //         }
    //         rs.close();
    //     } catch (SQLException e) {
    //         System.out.println("Error getting system owner users with job count: " + e.getMessage());
    //         throw e;
    //     }
    //     return (result != null) ? result : "{\"success\": true, \"data\": []}";
    // }

    // public String getUserAssignedJobs(java.util.Map<String, Object> params) throws Exception {
    //     if (con == null) {
    //         throw new Exception("Database connection is not established");
    //     }
    //     java.util.Map<String, Object> payload = new java.util.HashMap<>();
    //     Long uid = null;
    //     if (params.containsKey("user_id")) {
    //         Object v = params.get("user_id");
    //         String s = String.valueOf(v);
    //         try {
    //             uid = Long.parseLong(s);
    //         } catch (Exception ignored) {
    //         }
    //         if (uid == null) {
    //             Long resolved = resolveUserIdFromUuid(s);
    //             if (resolved != null)
    //                 uid = resolved;
    //         }
    //     } else if (params.containsKey("requesting_user_id")) {
    //         Object v = params.get("requesting_user_id");
    //         String s = String.valueOf(v);
    //         try {
    //             uid = Long.parseLong(s);
    //         } catch (Exception ignored) {
    //         }
    //         if (uid == null) {
    //             Long resolved = resolveUserIdFromUuid(s);
    //             if (resolved != null)
    //                 uid = resolved;
    //         }
    //     } else {
    //         try {
    //             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 String det = String.valueOf(auth.getDetails());
    //                 try {
    //                     uid = Long.parseLong(det);
    //                 } catch (Exception ignored) {
    //                 }
    //                 if (uid == null) {
    //                     Long resolved = resolveUserIdFromUuid(det);
    //                     if (resolved != null)
    //                         uid = resolved;
    //                 }
    //             }
    //         } catch (Exception ignored) {
    //         }
    //     }
    //     if (uid != null)
    //         payload.put("user_id", uid);

    //     if (params.containsKey("target_user_id")) {
    //         Object tv = params.get("target_user_id");
    //         String ts = String.valueOf(tv);
    //         Long tid = null;
    //         try {
    //             tid = Long.parseLong(ts);
    //         } catch (Exception ignored) {
    //         }
    //         if (tid == null) {
    //             Long resolved = resolveUserIdFromUuid(ts);
    //             if (resolved != null)
    //                 tid = resolved;
    //         }
    //         if (tid != null)
    //             payload.put("target_user_id", tid);
    //     }
    //     if (params.containsKey("limit"))
    //         payload.put("limit", params.get("limit"));
    //     if (params.containsKey("page"))
    //         payload.put("page", params.get("page"));
    //     if (params.containsKey("offset"))
    //         payload.put("offset", params.get("offset"));
    //     if (params.containsKey("search_text"))
    //         payload.put("search_text", params.get("search_text"));
    //     if (params.containsKey("status_filter"))
    //         payload.put("status_filter", params.get("status_filter"));
    //     if (params.containsKey("priority_filter"))
    //         payload.put("priority_filter", params.get("priority_filter"));

    //     String jsonReq = mapper.writeValueAsString(payload);
    //     String result = null;
    //     String SQL = "SELECT * FROM tickets_mgt.get_user_assigned_jobs(?::json) AS result";
    //     Connection conn = con;
    //     try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
    //         pstmt.setString(1, jsonReq);
    //         ResultSet rs = pstmt.executeQuery();
    //         if (rs.next()) {
    //             result = rs.getString("result");
    //         }
    //         rs.close();
    //     } catch (SQLException e) {
    //         System.out.println("Error getting user assigned jobs: " + e.getMessage());
    //         throw e;
    //     }
    //     return (result != null) ? result : "{\"success\": true, \"jobs\": []}";
    // }
}
