package com.mit.ticket_mgt_api.controllers;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mit.ticket_mgt_api.conn_class.db_settings;
import com.mit.ticket_mgt_api.models.organizations.OrganizationService;

import io.swagger.v3.oas.annotations.tags.Tag;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.NullNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/v1/organization_service")
@Tag(name = "Organization Service", description = "Organization management")
public class OrganizationController {

    OrganizationService organizationService = new OrganizationService();
    private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
     private db_settings cls_db_config;

    @GetMapping("/get_all_organizations")
    public ResponseEntity<?> getAllOrganizations() {
        Connection conn = null;
        try {
            conn = cls_db_config.getCon();
            String result = organizationService.getAllOrganizations(conn);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    "{\"status\": \"error\", \"message\": \"Failed to load organizations: " + e.getMessage() + "\"}");
        } finally {
            closeConnection(conn);
        }
    }

    @PostMapping("/add_organization")
    public ResponseEntity<?> addOrganization(@RequestBody String jsonReq) throws Exception {
        try {
            String normalized = requireProductIdsArray(jsonReq);
            organizationService.con = cls_db_config.getCon();
            String result = organizationService.addOrganization(normalized);
            logger.info("Add organization payload validated and sent: {}", normalized);
            logger.info("Add organization DB result: {}", result);
            organizationService.con.close();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode resNode = mapper.readTree(result);
            boolean success = resNode.has("success") && resNode.get("success").asBoolean();
            if (success && resNode.has("org_id") && !resNode.get("org_id").isNull()) {
                String orgId = resNode.get("org_id").asText();
                // Verify persistence by fetching the organization by id
                String verifyPayload = mapper.createObjectNode().put("org_id", orgId).toString();
                organizationService.con = cls_db_config.getCon();
                String verifyResult = organizationService.getOrganizationById(verifyPayload);
                organizationService.con.close();
                logger.info("Verification fetch for org_id {}: {}", orgId, verifyResult);
                JsonNode verifyNode = mapper.readTree(verifyResult);
                boolean verified = verifyNode.has("success") && verifyNode.get("success").asBoolean()
                        && verifyNode.has("data") && !verifyNode.get("data").isNull();
                if (!verified) {
                    // If verification fails, surface as server error to avoid false success on
                    // frontend
                    return ResponseEntity.status(500)
                            .body("{\"success\": false, \"message\": \"Organization creation could not be verified\"}");
                }
            }
            return success ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"" + iae.getMessage() + "\"}");
        }
    }

    @PutMapping("/update_organization")
    public ResponseEntity<?> updateOrganization(@RequestBody String jsonReq) throws Exception {
        try {
            String normalized = requireProductIdsArray(jsonReq);
            organizationService.con = cls_db_config.getCon();
            String result = organizationService.updateOrganization(normalized);
            logger.info("Update organization payload validated and sent: {}", normalized);
            logger.info("Update organization DB result: {}", result);
            organizationService.con.close();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode resNode = mapper.readTree(result);
            boolean success = resNode.has("success") && resNode.get("success").asBoolean();
            return success ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"" + iae.getMessage() + "\"}");
        }
    }

    @PostMapping("/get_organization_by_id")
    public ResponseEntity<?> getOrganizationById(@RequestBody String jsonReq) throws Exception {
        organizationService.con = cls_db_config.getCon();
        String result = organizationService.getOrganizationById(jsonReq);
        organizationService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/get_organizations_by_product")
    public ResponseEntity<?> getOrganizationsByProduct(@RequestBody String jsonReq) throws Exception {
        organizationService.con = cls_db_config.getCon();
        String result = organizationService.getOrganizationsByProduct(jsonReq);
        organizationService.con.close();
        return ResponseEntity.ok(result);
    }

    private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    private String requireProductIdsArray(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        if (!(root instanceof ObjectNode obj)) {
            throw new IllegalArgumentException("Invalid JSON body");
        }
        JsonNode node = obj.get("product_ids");
        if (node == null) {
            throw new IllegalArgumentException("product_ids field is required and must be provided");
        }
        if (!node.isArray()) {
            throw new IllegalArgumentException("product_ids must be a valid JSON array");
        }
        if (node.isArray() && node.size() == 0) {
            throw new IllegalArgumentException(
                    "product_ids array cannot be empty - at least one product ID must be provided");
        }
        return mapper.writeValueAsString(root);
    }
}
