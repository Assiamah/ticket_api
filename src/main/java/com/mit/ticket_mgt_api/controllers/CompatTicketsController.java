package com.mit.ticket_mgt_api.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;

import com.mit.ticket_mgt_api.conn_class.db_settings;
import com.mit.ticket_mgt_api.models.products.ProductService;
import com.mit.ticket_mgt_api.models.tickets.TicketService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tickets")
@Tag(name = "Tickets Compat", description = "Compatibility endpoints matching legacy consumer paths")
public class CompatTicketsController {

    TicketService ticketService = new TicketService();
    private static final Logger logger = LoggerFactory.getLogger(CompatTicketsController.class);

    @Autowired
     private db_settings cls_db_config;

    @GetMapping("/priorities")
    public ResponseEntity<?> priorities(@RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "date_from", required = false) String dateFrom,
            @RequestParam(value = "date_to", required = false) String dateTo) throws Exception {
        ticketService.con = cls_db_config.getCon();
        String result = ticketService.getPrioritiesPaged(limit, offset, dateFrom, dateTo);
        ticketService.con.close();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/categories")
    public ResponseEntity<?> categories(@RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "date_from", required = false) String dateFrom,
            @RequestParam(value = "date_to", required = false) String dateTo,
            @RequestParam(value = "include_inactive", required = false) Boolean includeInactive) throws Exception {
        ticketService.con = cls_db_config.getCon();
        String result = ticketService.getCategoriesPaged(limit, offset, dateFrom, dateTo, includeInactive);
        ticketService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/categories/list")
    public ResponseEntity<?> categoriesList(@RequestBody(required = false) String body) throws Exception {
        Integer limit = null;
        Integer offset = null;
        String dateFrom = null;
        String dateTo = null;
        Boolean includeInactive = null;
        try {
            if (body != null && !body.isEmpty()) {
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                com.fasterxml.jackson.databind.JsonNode node = mapper.readTree(body);
                if (node.has("limit") && node.get("limit").canConvertToInt())
                    limit = node.get("limit").asInt();
                if (node.has("offset") && node.get("offset").canConvertToInt())
                    offset = node.get("offset").asInt();
                if (node.has("date_from"))
                    dateFrom = node.get("date_from").asText(null);
                if (node.has("date_to"))
                    dateTo = node.get("date_to").asText(null);
                if (node.has("include_inactive"))
                    includeInactive = node.get("include_inactive").asBoolean(false);
            }
        } catch (Exception e) {
        }
        ticketService.con = cls_db_config.getCon();
        String result = ticketService.getCategoriesPaged(limit, offset, dateFrom, dateTo, includeInactive);
        ticketService.con.close();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/statuses")
    public ResponseEntity<?> statuses(@RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "date_from", required = false) String dateFrom,
            @RequestParam(value = "date_to", required = false) String dateTo) throws Exception {
        ticketService.con = cls_db_config.getCon();
        String result = ticketService.getStatusesPaged(limit, offset, dateFrom, dateTo);
        ticketService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/statuses/list")
    public ResponseEntity<?> statusesList(@RequestBody(required = false) String body) throws Exception {
        Integer limit = null;
        Integer offset = null;
        String dateFrom = null;
        String dateTo = null;
        try {
            if (body != null && !body.isEmpty()) {
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                com.fasterxml.jackson.databind.JsonNode node = mapper.readTree(body);
                if (node.has("limit") && node.get("limit").canConvertToInt())
                    limit = node.get("limit").asInt();
                if (node.has("offset") && node.get("offset").canConvertToInt())
                    offset = node.get("offset").asInt();
                if (node.has("date_from"))
                    dateFrom = node.get("date_from").asText(null);
                if (node.has("date_to"))
                    dateTo = node.get("date_to").asText(null);
            }
        } catch (Exception e) {
        }
        ticketService.con = cls_db_config.getCon();
        String result = ticketService.getStatusesPaged(limit, offset, dateFrom, dateTo);
        ticketService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/priorities/list")
    public ResponseEntity<?> prioritiesList(@RequestBody(required = false) String body) throws Exception {
        Integer limit = null;
        Integer offset = null;
        String dateFrom = null;
        String dateTo = null;
        try {
            if (body != null && !body.isEmpty()) {
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                com.fasterxml.jackson.databind.JsonNode node = mapper.readTree(body);
                if (node.has("limit") && node.get("limit").canConvertToInt())
                    limit = node.get("limit").asInt();
                if (node.has("offset") && node.get("offset").canConvertToInt())
                    offset = node.get("offset").asInt();
                if (node.has("date_from"))
                    dateFrom = node.get("date_from").asText(null);
                if (node.has("date_to"))
                    dateTo = node.get("date_to").asText(null);
            }
        } catch (Exception e) {
        }
        ticketService.con = cls_db_config.getCon();
        String result = ticketService.getPrioritiesPaged(limit, offset, dateFrom, dateTo);
        ticketService.con.close();
        return ResponseEntity.ok(result);
    }

    // @GetMapping("/stats")
    // public ResponseEntity<?> stats(@RequestParam(value = "user_id", required = false) String userId,
    //         @RequestParam(value = "date_from", required = false) String dateFrom,
    //         @RequestParam(value = "date_to", required = false) String dateTo) throws Exception {
    //     ticketService.con = cls_db_config.getCon();
    //     String result = ticketService.getAdminAnalyticsWithUser(userId, dateFrom, dateTo);
    //     ticketService.con.close();
    //     return ResponseEntity.ok(result);
    // }

    // @PostMapping("/stats")
    // public ResponseEntity<?> statsPost(@RequestBody(required = false) String body) throws Exception {
    //     String userId = null;
    //     String dateFrom = null;
    //     String dateTo = null;
    //     try {
    //         if (body != null && !body.isEmpty()) {
    //             com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
    //             com.fasterxml.jackson.databind.JsonNode node = mapper.readTree(body);
    //             if (node.has("user_id"))
    //                 userId = node.get("user_id").asText(null);
    //             if (node.has("date_from"))
    //                 dateFrom = node.get("date_from").asText(null);
    //             if (node.has("date_to"))
    //                 dateTo = node.get("date_to").asText(null);
    //         }
    //     } catch (Exception e) {
    //     }
    //     ticketService.con = cls_db_config.getCon();
    //     String result = ticketService.getAdminAnalyticsWithUser(userId, dateFrom, dateTo);
    //     ticketService.con.close();
    //     return ResponseEntity.ok(result);
    // }

    // @GetMapping("/users_for_assignment")
    // public ResponseEntity<?> usersForAssignment(@RequestParam(value = "user_id", required = false) String userId,
    //         @RequestParam(value = "org_id", required = false) String orgId,
    //         @RequestParam(value = "search_text", required = false) String searchText,
    //         @RequestParam(value = "role_filter", required = false) String roleFilter) throws Exception {
    //     if (userId == null || userId.isEmpty()) {
    //         try {
    //             org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder
    //                     .getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 String jwtUserId = String.valueOf(auth.getDetails());
    //                 if (jwtUserId != null && !jwtUserId.isEmpty()) {
    //                     userId = jwtUserId;
    //                 }
    //             }
    //         } catch (Exception ignored) {
    //         }
    //     }
    //     Long uid = null;
    //     try {
    //         if (userId != null)
    //             uid = Long.parseLong(userId);
    //     } catch (Exception ignored) {
    //     }
    //     ticketService.con = cls_db_config.getCon();
    //     String result = ticketService.getUsersForAssignment(uid);
    //     ticketService.con.close();
    //     return ResponseEntity.ok(result);
    // }

    // @PostMapping("/users_for_assignment")
    // public ResponseEntity<?> usersForAssignmentPost(@RequestBody(required = false) String body) throws Exception {
    //     Long uid = null;
    //     try {
    //         if (body != null && !body.isEmpty()) {
    //             com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
    //             com.fasterxml.jackson.databind.JsonNode node = mapper.readTree(body);
    //             if (node.has("user_id")) {
    //                 String v = node.get("user_id").asText("");
    //                 if (!v.isEmpty()) {
    //                     try {
    //                         uid = Long.parseLong(v);
    //                     } catch (Exception ignored) {
    //                     }
    //                 }
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }
    //     if (uid == null) {
    //         try {
    //             org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder
    //                     .getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 String jwtUserId = String.valueOf(auth.getDetails());
    //                 if (jwtUserId != null && !jwtUserId.isEmpty()) {
    //                     try {
    //                         uid = Long.parseLong(jwtUserId);
    //                     } catch (Exception ignored) {
    //                     }
    //                 }
    //             }
    //         } catch (Exception ignored) {
    //         }
    //     }
    //     ticketService.con = cls_db_config.getCon();
    //     String result = ticketService.getUsersForAssignment(uid);
    //     ticketService.con.close();
    //     return ResponseEntity.ok(result);
    // }

    @GetMapping("/create_context")
    public ResponseEntity<?> createContext(@RequestParam("user_id") Long userId,
            @RequestParam(value = "org_id", required = false) String orgId) throws Exception {
        ticketService.con = cls_db_config.getCon();
        String orgContext = ticketService.getUserOrgContext(userId);
        ticketService.con.close();

        ProductService productService = new ProductService();
        productService.con = cls_db_config.getCon();
        String productsJson;
        if (orgId != null && !orgId.isEmpty()) {
            String payload = "{\"org_id\": \"" + orgId + "\"}";
            productsJson = productService.getProductsByOrganization(payload);
        } else {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            com.fasterxml.jackson.databind.JsonNode node = mapper.readTree(orgContext);
            String chosenOrgId = node.has("org_id") && !node.get("org_id").isNull() ? node.get("org_id").asText()
                    : null;
            String payload = "{\"org_id\": \"" + chosenOrgId + "\"}";
            productsJson = productService.getProductsByOrganization(payload);
        }
        productService.con.close();

        String response = "{" +
                "\"org_context\":" + orgContext + "," +
                "\"products\":" + productsJson +
                "}";
        return ResponseEntity.ok(response);
    }

    // @GetMapping("/list")
    // public ResponseEntity<?> list(@RequestParam Map<String, Object> params) throws Exception {
    //     try {
    //         if (!params.containsKey("user_id") || params.get("user_id") == null
    //                 || String.valueOf(params.get("user_id")).isEmpty()) {
    //             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 String userId = String.valueOf(auth.getDetails());
    //                 if (userId != null && !userId.isEmpty()) {
    //                     params.put("user_id", userId);
    //                 }
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }
    //     ticketService.con = cls_db_config.getCon();
    //     String result = ticketService.getAllTicketsAdmin(params);
    //     ticketService.con.close();
    //     return ResponseEntity.ok(result);
    // }

    // @PostMapping("/list")
    // public ResponseEntity<?> listPost(@RequestBody String jsonReq) throws Exception {
    //     com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
    //     Map<String, Object> params;
    //     try {
    //         params = mapper.readValue(jsonReq, java.util.Map.class);
    //     } catch (Exception e) {
    //         params = java.util.Collections.emptyMap();
    //     }
    //     try {
    //         if (!params.containsKey("user_id") || params.get("user_id") == null
    //                 || String.valueOf(params.get("user_id")).isEmpty()) {
    //             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 String userId = String.valueOf(auth.getDetails());
    //                 if (userId != null && !userId.isEmpty()) {
    //                     params.put("user_id", userId);
    //                 }
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }
    //     ticketService.con = cls_db_config.getCon();
    //     String result = ticketService.getAllTicketsAdmin(params);
    //     ticketService.con.close();
    //     return ResponseEntity.ok(result);
    // }

    // @GetMapping(value = "/assigned_to_user", produces = "application/json")
    // public ResponseEntity<?> assignedToUser(@RequestParam Map<String, Object> params) throws Exception {
    //     try {
    //         if (!params.containsKey("user_id") || params.get("user_id") == null
    //                 || String.valueOf(params.get("user_id")).isEmpty()) {
    //             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 String userId = String.valueOf(auth.getDetails());
    //                 if (userId != null && !userId.isEmpty()) {
    //                     params.put("user_id", userId);
    //                 }
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }
    //     ticketService.con = cls_db_config.getCon();
    //     String result = ticketService.getUserTickets(params);
    //     ticketService.con.close();
    //     return ResponseEntity.ok(result);
    // }

    // @PostMapping(value = "/assigned_to_user", produces = "application/json")
    // public ResponseEntity<?> assignedToUserPost(@RequestBody String jsonReq) throws Exception {
    //     com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
    //     Map<String, Object> params;
    //     try {
    //         params = mapper.readValue(jsonReq, java.util.Map.class);
    //     } catch (Exception e) {
    //         params = java.util.Collections.emptyMap();
    //     }
    //     try {
    //         if (!params.containsKey("user_id") || params.get("user_id") == null
    //                 || String.valueOf(params.get("user_id")).isEmpty()) {
    //             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 String userId = String.valueOf(auth.getDetails());
    //                 if (userId != null && !userId.isEmpty()) {
    //                     params.put("user_id", userId);
    //                 }
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }
    //     ticketService.con = cls_db_config.getCon();
    //     String result = ticketService.getUserTickets(params);
    //     ticketService.con.close();
    //     return ResponseEntity.ok(result);
    // }

    // @GetMapping(value = "/get_my_assigned_tasks", produces = "application/json")
    // public ResponseEntity<?> getMyAssignedTasks(@RequestParam Map<String, Object> params) throws Exception {
    //     try {
    //         if (!params.containsKey("user_id") || params.get("user_id") == null
    //                 || String.valueOf(params.get("user_id")).isEmpty()) {
    //             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 String userId = String.valueOf(auth.getDetails());
    //                 if (userId != null && !userId.isEmpty()) {
    //                     params.put("user_id", userId);
    //                 }
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }
    //     ticketService.con = cls_db_config.getCon();
    //     String result = ticketService.getMyAssignedTasks(params);
    //     ticketService.con.close();
    //     return ResponseEntity.ok(result);
    // }

    // @PostMapping(value = "/get_my_assigned_tasks", produces = "application/json")
    // public ResponseEntity<?> getMyAssignedTasksPost(@RequestBody String jsonReq) throws Exception {
    //     com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
    //     Map<String, Object> params;
    //     try {
    //         params = mapper.readValue(jsonReq, java.util.Map.class);
    //     } catch (Exception e) {
    //         params = java.util.Collections.emptyMap();
    //     }
    //     try {
    //         if (!params.containsKey("user_id") || params.get("user_id") == null
    //                 || String.valueOf(params.get("user_id")).isEmpty()) {
    //             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 String userId = String.valueOf(auth.getDetails());
    //                 if (userId != null && !userId.isEmpty()) {
    //                     params.put("user_id", userId);
    //                 }
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }
    //     ticketService.con = cls_db_config.getCon();
    //     String result = ticketService.getMyAssignedTasks(params);
    //     ticketService.con.close();
    //     return ResponseEntity.ok(result);
    // }

    @GetMapping("/get_org_archived_tasks")
    public ResponseEntity<?> getOrgArchivedTasks(@RequestParam Map<String, Object> params) throws Exception {
        ticketService.con = cls_db_config.getCon();
        String result = ticketService.getOrgArchivedTasksN(params);
        ticketService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/get_org_archived_tasks")
    public ResponseEntity<?> getOrgArchivedTasksPost(@RequestBody String jsonReq) throws Exception {
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        Map<String, Object> params;
        try {
            params = mapper.readValue(jsonReq, java.util.Map.class);
        } catch (Exception e) {
            params = java.util.Collections.emptyMap();
        }
        ticketService.con = cls_db_config.getCon();
        String result = ticketService.getOrgArchivedTasksN(params);
        ticketService.con.close();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get_all_archived_tasks")
    public ResponseEntity<?> getAllArchivedTasks(@RequestParam Map<String, Object> params) throws Exception {
        ticketService.con = cls_db_config.getCon();
        String result = ticketService.getAllArchivedTasksSimple(params);
        ticketService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/get_all_archived_tasks")
    public ResponseEntity<?> getAllArchivedTasksPost(@RequestBody String jsonReq) throws Exception {
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        Map<String, Object> params;
        try {
            params = mapper.readValue(jsonReq, java.util.Map.class);
        } catch (Exception e) {
            params = java.util.Collections.emptyMap();
        }
        ticketService.con = cls_db_config.getCon();
        String result = ticketService.getAllArchivedTasksSimple(params);
        ticketService.con.close();
        return ResponseEntity.ok(result);
    }

    // @GetMapping(value = "/get_user_assigned_jobs", produces = "application/json")
    // public ResponseEntity<?> getUserAssignedJobs(@RequestParam Map<String, Object> params) throws Exception {
    //     try {
    //         if (!params.containsKey("user_id") && !params.containsKey("requesting_user_id")) {
    //             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 String userId = String.valueOf(auth.getDetails());
    //                 if (userId != null && !userId.isEmpty()) {
    //                     params.put("requesting_user_id", userId);
    //                 }
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }
    //     ticketService.con = cls_db_config.getCon();
    //     String result = ticketService.getUserAssignedJobs(params);
    //     ticketService.con.close();
    //     return ResponseEntity.ok(result);
    // }

    // @PostMapping(value = "/get_user_assigned_jobs", produces = "application/json")
    // public ResponseEntity<?> getUserAssignedJobsPost(@RequestBody String jsonReq) throws Exception {
    //     com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
    //     Map<String, Object> params;
    //     try {
    //         params = mapper.readValue(jsonReq, java.util.Map.class);
    //     } catch (Exception e) {
    //         params = java.util.Collections.emptyMap();
    //     }
    //     try {
    //         if (!params.containsKey("user_id") && !params.containsKey("requesting_user_id")) {
    //             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 String userId = String.valueOf(auth.getDetails());
    //                 if (userId != null && !userId.isEmpty()) {
    //                     params.put("requesting_user_id", userId);
    //                 }
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }
    //     ticketService.con = cls_db_config.getCon();
    //     String result = ticketService.getUserAssignedJobs(params);
    //     ticketService.con.close();
    //     return ResponseEntity.ok(result);
    // }

    // @GetMapping(value = "/get_system_owner_users_with_job_count", produces = "application/json")
    // public ResponseEntity<?> getSystemOwnerUsersWithJobCount(@RequestParam Map<String, Object> params)
    //         throws Exception {
    //     try {
    //         if (!params.containsKey("user_id") && !params.containsKey("requesting_user_id")) {
    //             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 String userId = String.valueOf(auth.getDetails());
    //                 if (userId != null && !userId.isEmpty()) {
    //                     params.put("requesting_user_id", userId);
    //                 }
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }
    //     ticketService.con = cls_db_config.getCon();
    //     String result = ticketService.getSystemOwnerUsersWithJobCount(params);
    //     ticketService.con.close();
    //     return ResponseEntity.ok(result);
    // }

    // @PostMapping(value = "/get_system_owner_users_with_job_count", produces = "application/json")
    // public ResponseEntity<?> getSystemOwnerUsersWithJobCountPost(@RequestBody String jsonReq) throws Exception {
    //     com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
    //     Map<String, Object> params;
    //     try {
    //         params = mapper.readValue(jsonReq, java.util.Map.class);
    //     } catch (Exception e) {
    //         params = java.util.Collections.emptyMap();
    //     }
    //     try {
    //         if (!params.containsKey("user_id") && !params.containsKey("requesting_user_id")) {
    //             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 String userId = String.valueOf(auth.getDetails());
    //                 if (userId != null && !userId.isEmpty()) {
    //                     params.put("requesting_user_id", userId);
    //                 }
    //             }
    //         }
    //     } catch (Exception ignored) {
    //     }
    //     ticketService.con = cls_db_config.getCon();
    //     String result = ticketService.getSystemOwnerUsersWithJobCount(params);
    //     ticketService.con.close();
    //     return ResponseEntity.ok(result);
    // }

    @PostMapping(value = "/update_ticket_status", produces = "application/json")
    public ResponseEntity<?> updateTicketStatus(@RequestBody String jsonReq) {
        try {
            ticketService.con = cls_db_config.getCon();
            String result = ticketService.updateTicketStatus(jsonReq);
            ticketService.con.close();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            String msg = e.getMessage();
            if (msg != null) {
                msg = msg.replaceAll("<[^>]*>", "");
            }
            return ResponseEntity.status(500)
                    .body("{\"status\": \"error\", \"message\": \"" + (msg == null ? "Unknown error" : msg) + "\"}");
        }
    }

    @PostMapping(value = "/update_task_status", produces = "application/json")
    public ResponseEntity<?> updateTaskStatus(@RequestBody String jsonReq) {
        try {
            ticketService.con = cls_db_config.getCon();
            String result = ticketService.updateTaskStatus(jsonReq);
            ticketService.con.close();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            String msg = e.getMessage();
            if (msg != null) {
                msg = msg.replaceAll("<[^>]*>", "");
            }
            return ResponseEntity.status(500)
                    .body("{\"status\": \"error\", \"message\": \"" + (msg == null ? "Unknown error" : msg) + "\"}");
        }
    }

    @PostMapping(value = "/update_status", produces = "application/json")
    public ResponseEntity<?> updateStatus(@RequestBody String jsonReq) throws Exception {
        ticketService.con = cls_db_config.getCon();
        String result = ticketService.updateStatus(jsonReq);
        ticketService.con.close();
        return ResponseEntity.ok(result);
    }
}
