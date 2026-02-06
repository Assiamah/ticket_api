package com.mit.ticket_mgt_api.controllers;

import java.util.Map;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mit.ticket_mgt_api.conn_class.db_settings;
import com.mit.ticket_mgt_api.models.tickets.TicketService;
import com.mit.ticket_mgt_api.models.products.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/ticket_service")
@Tag(name = "Ticket Service", description = "Ticket Service for managing support tickets")
public class TicketController {

    // Removed shared instance to ensure thread safety
    // TicketService ticketService = new TicketService();
    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
     private db_settings cls_db_config;

    @GetMapping("/get_all_tickets")
    public ResponseEntity<?> getAllTickets(@RequestParam Map<String, Object> params) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.getAllTicketsAdmin(params);
            logger.info("get_all_tickets success params_keys={}, result_len={}", params.keySet(),
                    result != null ? result.length() : 0);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("get_all_tickets failed params_keys={}, error={}", params.keySet(), e.getMessage());
            return ResponseEntity.status(500)
                    .body("{\"status\": \"error\", \"message\": \"Failed to load tickets: " + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/get_ticket_by_id")
    public ResponseEntity<?> getTicketById(@RequestBody String jsonReq) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.getTicketById(jsonReq);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("get_ticket_by_id failed error={}", e.getMessage());
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/create_ticket")
    public ResponseEntity<?> createTicket(@RequestBody String jsonReq) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.createTicket(jsonReq);
            logger.info("create_ticket request_len={}, response_len={}", jsonReq != null ? jsonReq.length() : 0,
                    result != null ? result.length() : 0);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("create_ticket failed error={}", e.getMessage());
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/update_ticket")
    public ResponseEntity<?> updateTicket(@RequestBody String jsonReq) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.updateTicket(jsonReq);
            logger.info("update_ticket request_len={}, response_len={}", jsonReq != null ? jsonReq.length() : 0,
                    result != null ? result.length() : 0);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("update_ticket failed error={}", e.getMessage());
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/get_ticket_comments/{ticketId}")
    public ResponseEntity<?> getTicketComments(@PathVariable String ticketId) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.getTicketComments(ticketId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/add_ticket_comment")
    public ResponseEntity<?> addTicketComment(@RequestBody String jsonReq) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.addTicketComment(jsonReq);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/update_ticket_status")
    public ResponseEntity<?> updateTicketStatus(@RequestBody String jsonReq) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.updateTicketStatus(jsonReq);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("update_ticket_status failed error={}", e.getMessage());
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/get_ticket_history/{ticketId}")
    public ResponseEntity<?> getTicketHistory(@PathVariable String ticketId) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.getTicketHistory(ticketId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/get_priorities")
    public ResponseEntity<?> getPriorities(@RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "date_from", required = false) String dateFrom,
            @RequestParam(value = "date_to", required = false) String dateTo) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.getPrioritiesPaged(limit, offset, dateFrom, dateTo);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    "{\"status\": \"error\", \"message\": \"Failed to fetch priorities: " + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/get_statuses")
    public ResponseEntity<?> getStatuses(@RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "date_from", required = false) String dateFrom,
            @RequestParam(value = "date_to", required = false) String dateTo) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.getStatusesPaged(limit, offset, dateFrom, dateTo);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("{\"status\": \"error\", \"message\": \"Failed to fetch statuses: " + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/get_departments")
    public ResponseEntity<?> getDepartments() {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.getDepartments();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/get_remarks")
    public ResponseEntity<?> getRemarks(@RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "date_from", required = false) String dateFrom,
            @RequestParam(value = "date_to", required = false) String dateTo,
            @RequestParam(value = "include_inactive", required = false) Boolean includeInactive) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.getCategoriesPaged(limit, offset, dateFrom, dateTo, includeInactive);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("{\"status\": \"error\", \"message\": \"Failed to fetch remarks: " + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/get_tickets_by_org")
    public ResponseEntity<?> getTicketsByOrg(@RequestParam Map<String, Object> params) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.getTicketsByOrg(params);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/get_create_ticket_context")
    public ResponseEntity<?> getCreateTicketContext(@RequestParam("user_id") Long userId,
            @RequestParam(value = "org_id", required = false) String orgId) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String orgContext = ticketService.getUserOrgContext(userId);

            // Optionally fetch products for the chosen organization
            // We need a separate connection or reuse the same one if ProductService allows
            // ProductService also has a public con field.
            ProductService productService = new ProductService();
            productService.con = con; // Reuse connection

            String productsJson;
            if (orgId != null && !orgId.isEmpty()) {
                String payload = "{\"org_id\": \"" + orgId + "\"}";
                productsJson = productService.getProductsByOrganization(payload);
            } else {
                // Use the user's org_id from orgContext
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                com.fasterxml.jackson.databind.JsonNode node = mapper.readTree(orgContext);
                String chosenOrgId = node.has("org_id") && !node.get("org_id").isNull() ? node.get("org_id").asText()
                        : null;
                String payload = "{\"org_id\": \"" + chosenOrgId + "\"}";
                productsJson = productService.getProductsByOrganization(payload);
            }

            // Compose context response
            String response = "{" +
                    "\"org_context\":" + orgContext + "," +
                    "\"products\":" + productsJson +
                    "}";
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    // @GetMapping("/get_user_tickets")
    // public ResponseEntity<?> getUserTickets(@RequestParam Map<String, Object> params) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.getUserTickets(params);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    // @GetMapping(value = "/get_tickets_assigned_to_user", produces = "application/json")
    // public ResponseEntity<?> getTicketsAssignedToUser(@RequestParam Map<String, Object> params) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.getTicketsAssignedToUser(params);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    // @GetMapping(value = "/get_my_assigned_tasks", produces = "application/json")
    // public ResponseEntity<?> getMyAssignedTasks(@RequestParam Map<String, Object> params) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.getMyAssignedTasks(params);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    @PostMapping("/close_ticket")
    public ResponseEntity<?> closeTicket(@RequestBody String jsonReq) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.closeTicket(jsonReq);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/reopen_ticket/{ticketId}")
    public ResponseEntity<?> reopenTicket(@PathVariable String ticketId,
            @RequestParam("user_id") Integer userId) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.reopenTicket(ticketId, userId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    // @GetMapping("/get_ticket_stats")
    // public ResponseEntity<?> getTicketStats(@RequestParam(value = "user_id", required = false) String userId,
    //         @RequestParam(value = "date_from", required = false) String dateFrom,
    //         @RequestParam(value = "date_to", required = false) String dateTo) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.getAdminAnalyticsWithUser(userId, dateFrom, dateTo);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    @GetMapping("/get_admin_analytics")
    public ResponseEntity<?> getAdminAnalytics(@RequestParam(value = "date_from", required = false) String dateFrom,
            @RequestParam(value = "date_to", required = false) String dateTo) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.getAdminAnalytics(dateFrom, dateTo);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/get_user_analytics")
    public ResponseEntity<?> getUserAnalytics(@RequestParam("org_id") String orgId,
            @RequestParam(value = "date_from", required = false) String dateFrom,
            @RequestParam(value = "date_to", required = false) String dateTo) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.getUserAnalytics(orgId, dateFrom, dateTo);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    // @GetMapping(value = "/get_org_dashboard_data", produces = "application/json")
    // public ResponseEntity<?> getOrgDashboardData(@RequestParam Map<String, Object> params) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.getOrgDashboardData(params);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    // @GetMapping(value = "/get_user_org_dashboard_data", produces = "application/json")
    // public ResponseEntity<?> getUserOrgDashboardData(@RequestParam Map<String, Object> params) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.getUserOrgDashboardData(params);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    @GetMapping(value = "/get_org_dashboard_summary", produces = "application/json")
    public ResponseEntity<?> getOrgDashboardSummary(@RequestParam Map<String, Object> params) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.getOrgDashboardSummary(params);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping(value = "/get_org_category_chart_data", produces = "application/json")
    public ResponseEntity<?> getOrgCategoryChartData(@RequestParam Map<String, Object> params) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.getOrgCategoryChartData(params);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    // @GetMapping(value = "/get_organization_tickets_analytics", produces = "application/json")
    // public ResponseEntity<?> getOrganizationTicketsAnalytics(@RequestParam Map<String, Object> params) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.getOrganizationTicketsAnalytics(params);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         logger.error("get_organization_tickets_analytics failed error={}", e.getMessage());
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    @PostMapping("/update_task_status")
    public ResponseEntity<?> updateTaskStatus(@RequestBody String jsonReq) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.updateTaskStatus(jsonReq);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("update_task_status failed error={}", e.getMessage());
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/update_status")
    public ResponseEntity<?> updateStatus(@RequestBody String jsonReq) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.updateStatus(jsonReq);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("update_status failed error={}", e.getMessage());
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping(value = "/get_org_archived_tasks", produces = "application/json")
    public ResponseEntity<?> getOrgArchivedTasks(@RequestParam Map<String, Object> params) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            // Use new function with positional args
            String result = ticketService.getOrgArchivedTasksN(params);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("get_org_archived_tasks failed error={}", e.getMessage());
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping(value = "/get_all_archived_tasks", produces = "application/json")
    public ResponseEntity<?> getAllArchivedTasks(@RequestParam Map<String, Object> params) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            // Use new function with positional args
            String result = ticketService.getAllArchivedTasksSimple(params);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("get_all_archived_tasks failed error={}", e.getMessage());
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/reopen_archived_task")
    public ResponseEntity<?> reopenArchivedTask(@RequestBody String jsonReq) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.reopenArchivedTask(jsonReq);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("reopen_archived_task failed error={}", e.getMessage());
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    // @GetMapping(value = "/get_system_owner_users_with_job_count", produces = "application/json")
    // public ResponseEntity<?> getSystemOwnerUsersWithJobCount(@RequestParam Map<String, Object> params) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.getSystemOwnerUsersWithJobCount(params);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         logger.error("get_system_owner_users_with_job_count failed error={}", e.getMessage());
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    // @GetMapping(value = "/get_user_assigned_jobs", produces = "application/json")
    // public ResponseEntity<?> getUserAssignedJobs(@RequestParam Map<String, Object> params) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.getUserAssignedJobs(params);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         logger.error("get_user_assigned_jobs failed error={}", e.getMessage());
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    // @PostMapping(value = "/get_user_assigned_jobs", produces = "application/json")
    // public ResponseEntity<?> getUserAssignedJobsPost(@RequestBody String jsonReq) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         java.util.Map<String, Object> params;
    //         try {
    //             com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
    //             params = mapper.readValue(jsonReq, java.util.Map.class);
    //         } catch (Exception e) {
    //             params = java.util.Collections.emptyMap();
    //         }
    //         String result = ticketService.getUserAssignedJobs(params);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         logger.error("get_user_assigned_jobs POST failed error={}", e.getMessage());
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    // @GetMapping(value = "/get_user_assigned_tasks", produces = "application/json")
    // public ResponseEntity<?> getUserAssignedTasks(@RequestParam Map<String, Object> params) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.getUserAssignedTasks(params);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         logger.error("get_user_assigned_tasks failed error={}", e.getMessage());
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    @PostMapping("/assign_ticket")
    public ResponseEntity<?> assignTicket(@RequestBody String jsonReq) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.assignTicket(jsonReq);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    // @PostMapping("/get_users_for_assignment")
    // public ResponseEntity<?> getUsersForAssignment(@RequestBody String jsonReq) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         Long userId = null;
    //         try {
    //             com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
    //             com.fasterxml.jackson.databind.JsonNode node = mapper.readTree(jsonReq);
    //             if (node.has("user_id") && node.get("user_id").canConvertToInt()) {
    //                 userId = node.get("user_id").asLong();
    //             }
    //         } catch (Exception ignored) {
    //         }
    //         if (userId == null) {
    //             org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder
    //                     .getContext().getAuthentication();
    //             if (auth != null && auth.getDetails() != null) {
    //                 try {
    //                     userId = Long.parseLong(String.valueOf(auth.getDetails()));
    //                 } catch (Exception ignored) {
    //                 }
    //             }
    //         }
    //         String result = ticketService.getUsersForAssignment(userId);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500)
    //                 .body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    @PostMapping("/archive_ticket_main")
    public ResponseEntity<?> archiveTicketMain(@RequestBody String jsonReq) {
        try (Connection con = cls_db_config.getCon()) {
            TicketService ticketService = new TicketService();
            ticketService.con = con;
            String result = ticketService.archiveTicketMain(jsonReq);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    // @GetMapping(value = "/fetch_archived_tickets", produces = "application/json")
    // public ResponseEntity<?> fetchArchivedTickets(@RequestParam Map<String, Object> params) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.fetchArchivedTickets(params);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    // @GetMapping("/get_ticket_attachments/{ticketId}")
    // public ResponseEntity<?> getTicketAttachments(@PathVariable String ticketId) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.getTicketAttachments(ticketId);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    // @PostMapping("/add_ticket_attachment")
    // public ResponseEntity<?> addTicketAttachment(@RequestBody String jsonReq) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.addTicketAttachment(jsonReq);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    // @DeleteMapping("/delete_ticket_attachment/{attachmentId}")
    // public ResponseEntity<?> deleteTicketAttachment(@PathVariable String attachmentId,
    //         @RequestParam("user_id") Integer userId) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.deleteTicketAttachment(attachmentId, userId);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    // @GetMapping("/search_tickets")
    // public ResponseEntity<?> searchTickets(@RequestParam Map<String, Object> params) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.searchTickets(params);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    // @GetMapping("/get_agent_performance")
    // public ResponseEntity<?> getAgentPerformance(@RequestParam Map<String, Object> params) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.getAgentPerformance(params);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    // @GetMapping("/get_department_stats")
    // public ResponseEntity<?> getDepartmentStats(@RequestParam Map<String, Object> params) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.getDepartmentStats(params);
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    // @GetMapping("/get_task_assigned_to_user")
    // public ResponseEntity<?> assignTask(@RequestParam Map<String, Object> params) {
    //     try (Connection con = cls_db_config.getCon()) {
    //         TicketService ticketService = new TicketService();
    //         ticketService.con = con;
    //         String result = ticketService.getDepartmentStats(params); // Note: This seems to call getDepartmentStats in
    //                                                                   // original code too?
    //         return ResponseEntity.ok(result);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
    //     }
    // }

    // Helper method to safely close connection - No longer needed with
    // try-with-resources but kept if referenced elsewhere (unlikely)
    private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                logger.error("connection_close_failed error={}", e.getMessage());
            }
        }
    }
}
