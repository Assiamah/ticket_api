package com.mit.ticket_mgt_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mit.ticket_mgt_api.conn_class.db_settings;
import com.mit.ticket_mgt_api.models.products.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/product_service")
@Tag(name = "Product Service", description = "Product management")
public class ProductController {

    @Autowired
     private db_settings cls_db_config;

    @PostMapping("/add_product")
    public ResponseEntity<?> addProduct(@RequestBody String jsonReq) throws Exception {
        ProductService productService = new ProductService();
        productService.con = cls_db_config.getCon();
        String result = productService.addProduct(jsonReq);
        productService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/delete_product")
    public ResponseEntity<?> deleteProduct(@RequestBody String jsonReq) throws Exception {
        ProductService productService = new ProductService();
        productService.con = cls_db_config.getCon();
        String result = productService.deleteProduct(jsonReq);
        productService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/get_all_products")
    public ResponseEntity<?> getAllProducts(@RequestBody(required = false) String jsonReq) throws Exception {
        if (jsonReq == null) jsonReq = "{}";
        ProductService productService = new ProductService();
        productService.con = cls_db_config.getCon();
        String result = productService.getAllProducts(jsonReq);
        productService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/get_product_by_id")
    public ResponseEntity<?> getProductById(@RequestBody String jsonReq) throws Exception {
        ProductService productService = new ProductService();
        productService.con = cls_db_config.getCon();
        String result = productService.getProductById(jsonReq);
        productService.con.close();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/get_products_by_organization")
    public ResponseEntity<?> getProductsByOrganization(@RequestBody String jsonReq) throws Exception {
        ProductService productService = new ProductService();
        productService.con = cls_db_config.getCon();
        String result = productService.getProductsByOrganization(jsonReq);
        productService.con.close();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update_product")
    public ResponseEntity<?> updateProduct(@RequestBody String jsonReq) throws Exception {
        ProductService productService = new ProductService();
        productService.con = cls_db_config.getCon();
        String result = productService.updateProduct(jsonReq);
        productService.con.close();
        return ResponseEntity.ok(result);
    }
}