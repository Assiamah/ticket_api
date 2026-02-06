package com.mit.ticket_mgt_api.conn_class;

// import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "app.config")
public class Ws_url_config {

    String web_service_url_ser;
    String web_service_url_ser_api_key;
    String web_service_url_dlrev_server;
    String get_public_docs_upload_location;

    String case_upload_location;
    String public_docs_upload_location;
    String softfile_location;
    String sample_sign_location;
    String batching_files_location;
    String publicaton_files_location;
    String tempfile_location;
    String lrd_jacket_raw_path;

    String egcr_url;
    String egcr_apikey;

    String geoserver_url;
    String geoserver_user;
    String geoserver_password;
    String geoserver_workspace_name;

    String legal_batch_list_location;
    String legal_document_location;
    String legal_request_document;
    String legal_provide_document;

    String cica_reply_document;
    String cica_created_ticket;

    String lc_temp_folder;

    String payment_gateway_base_uri;
    String lc_payment_redirect_url;
    String lc_payment_post_url;
    String gog_payment_api_key;
    String gog_payment_url;
    String email_config_host;
    String email_config_pass;
    String email_config_user;
    String payment_api_url;
    String payment_api_key;

}
