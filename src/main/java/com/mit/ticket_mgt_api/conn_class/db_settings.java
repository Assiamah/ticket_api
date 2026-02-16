package com.mit.ticket_mgt_api.conn_class;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class db_settings {
    String url;
    String username;
    String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    static Connection con = null;

    public Connection getCon() {
        try {
            // System.out.println("DB Url: "+getUrl());
            Class.forName("org.postgresql.Driver");
            // Always create a new connection instead of reusing a static one
            // con = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
            return DriverManager.getConnection(getUrl(), getUsername(), getPassword());
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
            return null;
        }
    }
}
