package com.mit.ticket_mgt_api.controllers;

public class PdfRequest {
    private String jobNumber;
    private String caseNumber;
    private String doc_name;
    private String doc_description;
    private String doc_category;

    private String reference_number;
    private String doc_uploaded_by ;
    private String doc_uploaded_by_id;
    private String doc_uploaded_by_ip_address;

    private String doc_file_size;
    private String doc_file_size_byte ;
    private String doc_version;
    private String doc_type;
    



    private byte[] fileData;

    // Getters and Setters
    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }


    public String getdoc_name() {
        return doc_name;
    }

    public void setdoc_name(String doc_name) {
        this.doc_name = doc_name;
    }


    public String getdoc_description() {
        return doc_description;
    }

    public void setdoc_description(String doc_description) {
        this.doc_description = doc_description;
    }


    public String getdoc_category() {
        return doc_category;
    }

    public void setdoc_category(String doc_category) {
        this.doc_category = doc_category;
    }


    public String getreference_number() {
        return reference_number;
    }

    public void setreference_number(String reference_number) {
        this.reference_number = reference_number;
    }

    public String getdoc_uploaded_by() {
        return doc_uploaded_by;
    }

    public void setdoc_uploaded_by(String doc_uploaded_by) {
        this.doc_uploaded_by = doc_uploaded_by;
    }

    public String getdoc_uploaded_by_id() {
        return doc_uploaded_by_id;
    }

    public void setdoc_uploaded_by_id(String doc_uploaded_by_id) {
        this.doc_uploaded_by_id = doc_uploaded_by_id;
    }

    public String getdoc_uploaded_by_ip_address() {
        return doc_uploaded_by_ip_address;
    }

    public void setdoc_uploaded_by_ip_address(String doc_uploaded_by_ip_address) {
        this.doc_uploaded_by_ip_address = doc_uploaded_by_ip_address;
    }

       public String getdoc_file_size() {
        return doc_file_size;
    }

    public void setdoc_file_size(String doc_file_size) {
        this.doc_file_size = doc_file_size;
    }


       public String getdoc_file_size_byte() {
        return doc_file_size_byte;
    }

    public void setdoc_file_size_byte(String doc_file_size_byte) {
        this.doc_file_size_byte = doc_file_size_byte;
    }

       public String getdoc_version() {
        return doc_version;
    }

    public void setdoc_version(String doc_version) {
        this.doc_version = doc_version;
    }

       public String getdoc_type() {
        return doc_type;
    }

    public void setdoc_type(String doc_type) {
        this.doc_type = doc_type;
    }


    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}