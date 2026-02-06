package com.mit.ticket_mgt_api.filter;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.mit.ticket_mgt_api.conn_class.db_settings;
import com.mit.ticket_mgt_api.models.api_key_model;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Component
public class ApiKeyRequestFilter extends GenericFilterBean {

    @Autowired
    private db_settings cls_db_config;

    api_key_model api_key_service = new api_key_model();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

                HttpServletRequest req = (HttpServletRequest) request;
                String path = req.getRequestURI();
                String request_url = req.getRequestURI();
                String protocol = req.getProtocol();
                String path_info = req.getPathInfo();
                String ip_address = req.getRemoteAddr();
                String ip_address_new = req.getLocalAddr();
        
               //  byte[] requestBody = StreamUtils.copyToByteArray(request.getInputStream());
             //   log.info("request body = {}", new String(requestBody, StandardCharsets.UTF_8));
              //  String ip_address_new = req.();
        
               // String rdate = req.getQueryString();
        
        
                 System.out.println("Path");
                System.out.println(path);
        
                System.out.println("ip_address");
                System.out.println(ip_address);
        
                System.out.println("ip_address_new");
                //System.out.println(request.getInputStream().read());
                
        
               // System.out.println(new String(requestBody, StandardCharsets.UTF_8));
                
                
               // System.out.println(rdate);
               ///System.out.println(protocol);
               // System.out.println(path_info);
               // System.out.println(ip_address);
        
        
                String key = req.getHeader("x-api-key");
              //  String key = req.getHeader("x-api-key") == null ? "" : req.getHeader("x-api-key");
                System.out.println(req.getHeader("x-api-key"));
        
           try {
            
                      JSONObject request_log = new JSONObject();
                    request_log.put("al_name", "al_name");
                    request_log.put("al_token", key);
                    request_log.put("al_ip_address", ip_address);
                  
              
                    request_log.put("al_api_requested_url", path);
                    request_log.put("al_api_requested_data", path);
                   // request_log.put("al_api_requested_data", new String(requestBody, StandardCharsets.UTF_8));
                          
                System.out.println(req);
                if (key != null && ip_address != null) {
                    // check api key check in the database
                          String api_key_from_db = "";
                    // System.out.println(result);
                
                   
                   
                  
         try {
        
                    JSONObject request_json = new JSONObject();
                    request_json.put("api_key", key);
                    request_json.put("ip_address", ip_address);
           System.out.println("request_json.toString()");
            System.out.println(request_json.toString());
                  
                  api_key_service.con = cls_db_config.getCon();
                    String result = api_key_service.check_api_key_exist(request_json.toString());
        
             System.out.println(result);
                   
                        JSONObject api_key_obj = new JSONObject(result);
                        String api_key_data = api_key_obj.get("data").toString();
                        JSONObject api_key_obj_data = new JSONObject(api_key_data);
                        api_key_from_db = api_key_obj_data.get("token").toString();
        
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
        
                    if (key.equals(api_key_from_db)) {
                        // System.out.println("API Is valid");
                        request_log.put("al_api_status", "Success");
                        request_log.put("al_is_valid", 1);
                        api_key_service.con = cls_db_config.getCon();
                        String result = api_key_service.check_api_logs(request_log.toString());
                        chain.doFilter(request, response);
                    } else {
                        // chain.doFilter(request, response);
                        request_log.put("al_api_status", "Failed");
                        request_log.put("al_is_valid", 0);
                        api_key_service.con = cls_db_config.getCon();
                        String result = api_key_service.check_api_logs(request_log.toString());
        
                        HttpServletResponse resp = (HttpServletResponse) response;
                        String error = "Invalid API KEY";
                        // System.out.println("Start Validata");
                        resp.reset();
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.setContentLength(error.length());
                        response.getWriter().write(error);
                    }
        
                } else {
                    
                    request_log.put("al_api_status", "Failed");
                    request_log.put("al_is_valid", 0);
                    api_key_service.con = cls_db_config.getCon();
                    String result = api_key_service.check_api_logs(request_log.toString());
        
                    
                    HttpServletResponse resp = (HttpServletResponse) response;
                    String error = "Invalid API KEY";
                    resp.reset();
                    resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentLength(error.length());
                    response.getWriter().write(error);
                }
                 } catch (Exception e) {
            // TODO: handle exception
           }
    }

}
