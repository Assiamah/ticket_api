package com.mit.ticket_mgt_api.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.mit.ticket_mgt_api.conn_class.Ws_url_config;
import com.mit.ticket_mgt_api.conn_class.db_settings;
import com.mit.ticket_mgt_api.models.document_upload.cls_document_upload;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

//@CrossOrigin(origins = "", allowedHeaders = "")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/document_upload")
@Tag(name = "Document Upload Service", description = "Document Upload Service for App")

public class document_upload {
    cls_document_upload cls_document_upload_cl = new cls_document_upload();
    @Autowired
    private Ws_url_config cls_url_config;

      @Autowired
    private db_settings cls_db_config;

    @GetMapping("/index")
    public String hello() {
        return "uploader";
    }

    @GetMapping("/new_service_working")
    public String work() {
        return "Everything is working";
    }

    @PostMapping("/upload_document")
    public ResponseEntity<?> handleFileUploadcdcpntent(
            @RequestParam("file") MultipartFile file, // The File to be uploaded
            @RequestParam("doc_name") String doc_name, // Name of Document
            @RequestParam("doc_description") String doc_description, // Description
            // @RequestParam("doc_path") String doc_path, // Path to be stored
            @RequestParam("doc_category") String doc_category, //
            @RequestParam("doc_extension") String doc_extension, // File Extension e.g .pdf
            @RequestParam("doc_app_uploaded") String doc_app_uploaded, // Appm the document is been uploaded from
            @RequestParam("doc_uploaded_by") String doc_uploaded_by, // Who is uploading it
            @RequestParam("doc_uploaded_by_id") String doc_uploaded_by_id, // ID of the officer uploading
            @RequestParam("organisation_prefix") String organisation_prefix // prefix of the organisation

    ) {

        cls_document_upload_cl.con = cls_db_config.getCon();
        String fileName = file.getOriginalFilename();
        System.out.println("e");
        System.out.println(doc_category);
        System.out.println(doc_category);
        System.out.println(organisation_prefix);

        try {

            JSONObject obj = new JSONObject();

            obj.put("organisation", organisation_prefix);
            obj.put("document_type", doc_category);

            String pdf_dest = cls_document_upload_cl.get_file_location_for_upload(obj.toString());
            System.out.println(pdf_dest);
            // File files_pdf_jackets = new File(pdf_dest + case_number);
            File files_pdf_jackets = new File(pdf_dest);
            System.out.println(pdf_dest);
            if (!files_pdf_jackets.exists()) {
                if (files_pdf_jackets.mkdirs()) {
                    // System.out.println("Multiple directories are
                    // created!");
                } else {
                    System.out.println("Failed to create multiple directories!");
                }
            }

            // String savePath = pdf_dest + case_number;
            file.transferTo(new File(files_pdf_jackets + fileName));
        } catch (Exception e) {
            System.out.println("e.getMessage()");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            // System.out.println(e);
        }
        return ResponseEntity.ok("CD Content uploaded successfully.");
    }


    @PostMapping("/upload_document_all")
    public ResponseEntity<?> upload_document_all(
            @RequestParam("file") MultipartFile file, // The File to be uploaded
            @RequestParam("doc_name") String doc_name, // Name of Document
            @RequestParam("doc_description") String doc_description // Description

    ) {
        cls_document_upload_cl.con = cls_db_config.getCon();
        String fileName = file.getOriginalFilename();
        System.out.println("e");
        //System.out.println(doc_category);
       // System.out.println(doc_category);
        //System.out.println(organisation_prefix);

        try {

            JSONObject obj = new JSONObject();

           // obj.put("organisation", organisation_prefix);
           // obj.put("document_type", doc_category);

            String pdf_dest = cls_document_upload_cl.get_file_location_for_upload(obj.toString());
            System.out.println(pdf_dest);
            // File files_pdf_jackets = new File(pdf_dest + case_number);
            File files_pdf_jackets = new File(pdf_dest);
            System.out.println(pdf_dest);
            if (!files_pdf_jackets.exists()) {
                if (files_pdf_jackets.mkdirs()) {
                    // System.out.println("Multiple directories are
                    // created!");
                } else {
                    System.out.println("Failed to create multiple directories!");
                }
            }

            // String savePath = pdf_dest + case_number;
            file.transferTo(new File(files_pdf_jackets + fileName));
        } catch (Exception e) {
            System.out.println("e.getMessage()");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            // System.out.println(e);
        }
        return ResponseEntity.ok("CD Content uploaded successfully.");
    }



    @PostMapping("/upload_scanned_pdf")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file,
            @RequestParam("case_number") String case_number, @RequestParam("job_number") String job_number) {

        String fileName = file.getOriginalFilename();
        String pdfFileName = "acnkwledgeslip.pdf";
        try {

            String pdf_dest = cls_url_config.getPublic_docs_upload_location();
            String files_pdf_jackets_p = pdf_dest + case_number + File.separator + "client_document"
                    + "_" + job_number + pdfFileName;
            File files_pdf_jackets = new File(pdf_dest + case_number);

            if (!files_pdf_jackets.exists()) {
                if (files_pdf_jackets.mkdirs()) {
                    // System.out.println("Multiple directories are
                    // created!");
                } else {
                    System.out.println("Failed to create multiple directories!");
                }
            }

            file.transferTo(new File(files_pdf_jackets_p));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @PostMapping("/upload_cd_content")
    public ResponseEntity<?> handleFileUploadcdcpntent(@RequestParam("file") MultipartFile file,
            @RequestParam("case_number") String case_number, @RequestParam("job_number") String job_number) {

        String fileName = file.getOriginalFilename();
        try {

            String pdf_dest = cls_url_config.getPublic_docs_upload_location();
            // String files_pdf_jackets_p = pdf_dest + case_number + File.separator +
            // service_bill_descdription_formated + "_" + job_number + pdfFileName;
            File files_pdf_jackets = new File(pdf_dest + case_number);

            if (!files_pdf_jackets.exists()) {
                if (files_pdf_jackets.mkdirs()) {
                    // System.out.println("Multiple directories are created!");
                } else {
                    System.out.println("Failed to create multiple directories!");
                }
            }

            // String savePath = pdf_dest + case_number;
            System.out.println(pdf_dest);
            System.out.println(files_pdf_jackets);
            System.out.println(files_pdf_jackets + File.separator + fileName);
            file.transferTo(new File(files_pdf_jackets + File.separator + fileName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("CD Content uploaded successfully.");
    }


    @PostMapping("/upload_all_files")
    public ResponseEntity<?> upload_all_files(@RequestParam("file") MultipartFile file,
            @RequestParam("case_number") String case_number) {

              //  System.out.println(case_number);
               // System.out.println(file);
        String fileName = file.getOriginalFilename();
        try {

            String pdf_dest = cls_url_config.getPublic_docs_upload_location();
            // String files_pdf_jackets_p = pdf_dest + case_number + File.separator +
            // service_bill_descdription_formated + "_" + job_number + pdfFileName;
            File files_pdf_jackets = new File(pdf_dest + case_number);

            if (!files_pdf_jackets.exists()) {
                if (files_pdf_jackets.mkdirs()) {
                    // System.out.println("Multiple directories are created!");
                } else {
                    System.out.println("Failed to create multiple directories!");
                }
            }

            // String savePath = pdf_dest + case_number;
            System.out.println(pdf_dest);
            System.out.println(files_pdf_jackets);
            System.out.println(files_pdf_jackets + File.separator + fileName);
            file.transferTo(new File(files_pdf_jackets + File.separator + fileName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("CD Content uploaded successfully.");
    }

    @PostMapping("/upload_all_files_excel")
    public String upload_all_files_excel(@RequestParam("file") MultipartFile file,
            @RequestParam("case_number") String case_number) {
                String result_of_csv ="";
              //  System.out.println(case_number);
               // System.out.println(file);
        String fileName = file.getOriginalFilename();
        try {

            String pdf_dest = cls_url_config.getPublic_docs_upload_location();
            String file_to_work_with ="";
          
            // String files_pdf_jackets_p = pdf_dest + case_number + File.separator +
            // service_bill_descdription_formated + "_" + job_number + pdfFileName;
            File files_pdf_jackets = new File(pdf_dest + case_number);

            if (!files_pdf_jackets.exists()) {
                if (files_pdf_jackets.mkdirs()) {
                    // System.out.println("Multiple directories are created!");
                } else {
                    System.out.println("Failed to create multiple directories!");
                }
            }

            // String savePath = pdf_dest + case_number;
            System.out.println(pdf_dest);
            System.out.println(files_pdf_jackets);
            System.out.println(files_pdf_jackets + File.separator + fileName);

            file.transferTo(new File(files_pdf_jackets + File.separator + fileName));

            file_to_work_with=files_pdf_jackets + File.separator + fileName;


        String csvFile = file_to_work_with;

        String line = "";  
String splitBy = ",";  

  JSONArray jsonArr = new JSONArray();

                        BufferedReader br = new BufferedReader(new FileReader(csvFile));  
                        while ((line = br.readLine()) != null)   //returns a Boolean value  
                        {  
                            JSONObject obj = new JSONObject();
                            System.out.println("line");
                            System.out.println(line);
                            String[] employee = line.split(splitBy);  
                        obj.put("number", employee[0]);
                        obj.put("easterns", employee[1]);
                        obj.put("northings",  employee[2]);
                        jsonArr.put(obj);


                       }  
                       result_of_csv=jsonArr.toString();
                      
                       System.out.println(result_of_csv);
        } catch (Exception e) {
           // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            result_of_csv="error has occured";
        }
        System.out.println(result_of_csv);
        return result_of_csv;
    }


    @PostMapping("/upload_polygon_csv")
    public String upload_polygon_csv(@RequestParam("file") MultipartFile file,
            @RequestParam("case_number") String case_number) {
                String result_of_csv ="";
              //  System.out.println(case_number);
               // System.out.println(file);
        String fileName = file.getOriginalFilename();
        try {

            String pdf_dest = cls_url_config.getPublic_docs_upload_location();
            String file_to_work_with ="";
          
            // String files_pdf_jackets_p = pdf_dest + case_number + File.separator +
            // service_bill_descdription_formated + "_" + job_number + pdfFileName;
            File files_pdf_jackets = new File(pdf_dest + case_number);

            if (!files_pdf_jackets.exists()) {
                if (files_pdf_jackets.mkdirs()) {
                    // System.out.println("Multiple directories are created!");
                } else {
                    System.out.println("Failed to create multiple directories!");
                }
            }

            // String savePath = pdf_dest + case_number;
            System.out.println(pdf_dest);
            System.out.println(files_pdf_jackets);
            System.out.println(files_pdf_jackets + File.separator + fileName);

            file.transferTo(new File(files_pdf_jackets + File.separator + fileName));

            file_to_work_with=files_pdf_jackets + File.separator + fileName;


        String csvFile = file_to_work_with;

        String line = "";  
String splitBy = ",";  

  JSONArray jsonArr = new JSONArray();
Integer coordinate_count =0;
                        BufferedReader br = new BufferedReader(new FileReader(csvFile));  
                        String wkt_str = "POLYGON((";
                        String first_coordinate = "POLYGON((";
                        while ((line = br.readLine()) != null)   //returns a Boolean value  
                        {  
                            JSONObject obj = new JSONObject();
                            System.out.println("line");
                            System.out.println(line);
                            String[] coordinates = line.split(splitBy);  
                       
                       
                        obj.put("number", coordinates[0]);
                        obj.put("easterns", coordinates[2]);
                        obj.put("northings",  coordinates[1]);
                        if (coordinate_count==0){
                            first_coordinate=coordinates[2] + " " + coordinates[1];
                        }

                        jsonArr.put(obj);
                        wkt_str = wkt_str + coordinates[2] + " " + coordinates[1] + ",";
                        coordinate_count=+1;
                       }  
                      

				var wkt_wkt_str = wkt_str + first_coordinate+ "))";
			   //wkt_wkt_str = wkt_wkt_str.replace(", ))", "))");
                 System.out.println(wkt_wkt_str);
                 result_of_csv=jsonArr.toString();
                 System.out.println(result_of_csv);
                 result_of_csv=wkt_wkt_str;
                       System.out.println(result_of_csv);
        } catch (Exception e) {
           // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            result_of_csv="error has occured";
        }
        System.out.println(result_of_csv);
        return result_of_csv;
    }


    @PostMapping("/file_upload_public")
    public ResponseEntity<?> file_upload_public(
            @RequestParam("file") MultipartFile file, // The File to be uploaded
            @RequestParam(value="job_number", required=false) String job_number, // Name of Document
            @RequestParam("case_number") String case_number, // Description
            @RequestParam(value="document_type", defaultValue="public_docs") String document_type, 
            @RequestParam(value="document_open_type", required=false) String document_open_type,//

              @RequestParam(value="doc_file_size", required=false) String doc_file_size,
      @RequestParam(value="doc_file_size_byte", required=false) String doc_file_size_byte,
     @RequestParam(value="doc_version", required=false) String doc_version,
      @RequestParam(value="doc_type", required=false) String doc_type

           
    ) {
        cls_document_upload_cl.con = cls_db_config.getCon();
        String fileName = file.getOriginalFilename();
       
       
       //   System.out.println("e");
       // System.out.println(doc_category);
      //  System.out.println(doc_category);
 

        try {

            JSONObject obj = new JSONObject();

           obj.put("organisation", "landcom");
           obj.put("document_type", document_type);
        
           System.out.println("obj.toString()");
           System.out.println(obj.toString());

            String doc_path = cls_document_upload_cl.get_file_location_for_upload(obj.toString());
            System.out.println("pdf_dest");
            System.out.println(doc_path+case_number);
            // File files_pdf_jackets = new File(pdf_dest + case_number);
            File files_pdf_jackets = new File(doc_path+case_number);
            System.out.println(doc_path+case_number);
            if (!files_pdf_jackets.exists()) {
                if (files_pdf_jackets.mkdirs()) {
                    // System.out.println("Multiple directories are
                    // created!");
                } else {
                    System.out.println("Failed to create multiple directories!");
                }
            }

            // String savePath = pdf_dest + case_number;
           // Get the index of the last dot
      int lastIndexOfDot = fileName.lastIndexOf(".");

            String extension = fileName.substring(fileName.lastIndexOf("."));
            String fileNameWithoutExtension = (lastIndexOfDot == -1) ? fileName : fileName.substring(0, lastIndexOfDot);
      String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(Calendar.getInstance().getTime());
        
		//File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
       // File tempFile = File.createTempFile();

            fileNameWithoutExtension="client_application_upload"+
            obj.put("reference_number", case_number);
            obj.put("doc_name", "client_scanned_document_"+timeStamp);
            obj.put("doc_description", "client_scanned_document_"+timeStamp);
            obj.put("doc_path", doc_path+case_number+File.separator);
            obj.put("doc_category", document_type);
            obj.put("doc_extension", extension);
            obj.put("doc_app_uploaded", "elis");
            obj.put("doc_uploaded_by", "elis_backend");
            obj.put("doc_uploaded_by_id", "elis_backend");

             obj.put("doc_file_size", doc_file_size);
      obj.put("doc_file_size_byte", doc_file_size_byte);
    obj.put("doc_version", doc_version);
      obj.put("doc_type", doc_type);

            cls_document_upload_cl.con = cls_db_config.getCon();
            String upload_msg= cls_document_upload_cl.select_upload_a_new_file(obj.toString());
            
            JSONObject obj1 = new JSONObject(upload_msg);
            String unique_id = obj1.getString("unique_id");

            System.out.println(files_pdf_jackets+ File.separator + "client_scanned_document_"+timeStamp+extension);
            file.transferTo(new File(files_pdf_jackets+ File.separator + "client_scanned_document_"+timeStamp+extension));

           
            System.out.println(upload_msg);

        } catch (Exception e) {
            System.out.println("e.getMessage()");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            // System.out.println(e);
        }
        return ResponseEntity.ok("File for Job Number"+ job_number+ " uploaded successfully.");
    }
   

    @PostMapping("/file_upload_public_backend")
    public ResponseEntity<?> file_upload_public_backend(
            @RequestParam("file") MultipartFile file, // The File to be uploaded
            @RequestParam(value="job_number", required=false) String job_number, // Name of Document
            @RequestParam("case_number") String case_number, // Description
            @RequestParam(value="document_type", defaultValue="public_docs") String document_type, 
            @RequestParam(value="document_open_type", required=false) String document_open_type,//
            @RequestParam(value="doc_name", required=false) String doc_name,
              @RequestParam(value="doc_file_size", required=false) String doc_file_size,
      @RequestParam(value="doc_file_size_byte", required=false) String doc_file_size_byte,
     @RequestParam(value="doc_version", required=false) String doc_version,
      @RequestParam(value="doc_type", required=false) String doc_type

           
    ) {
        cls_document_upload_cl.con = cls_db_config.getCon();
        String fileName = file.getOriginalFilename();
       
       
       //   System.out.println("e");
       // System.out.println(doc_category);
      //  System.out.println(doc_category);
 

        try {

            JSONObject obj = new JSONObject();

           obj.put("organisation", "landcom");
           obj.put("document_type", document_type);
        
           System.out.println("obj.toString()");
           System.out.println(obj.toString());

            String doc_path = cls_document_upload_cl.get_file_location_for_upload(obj.toString());
            System.out.println("pdf_dest");
            System.out.println(doc_path+case_number);
            // File files_pdf_jackets = new File(pdf_dest + case_number);
            File files_pdf_jackets = new File(doc_path+case_number);
            System.out.println(doc_path+case_number);
            if (!files_pdf_jackets.exists()) {
                if (files_pdf_jackets.mkdirs()) {
                    // System.out.println("Multiple directories are
                    // created!");
                } else {
                    System.out.println("Failed to create multiple directories!");
                }
            }

            // String savePath = pdf_dest + case_number;
           // Get the index of the last dot
      int lastIndexOfDot = fileName.lastIndexOf(".");

            String extension = fileName.substring(fileName.lastIndexOf("."));
            String fileNameWithoutExtension = (lastIndexOfDot == -1) ? fileName : fileName.substring(0, lastIndexOfDot);
      String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(Calendar.getInstance().getTime());
        
		//File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
       // File tempFile = File.createTempFile();
       doc_name = doc_name.replaceAll("[ /-]", "_");

            fileNameWithoutExtension="client_application_upload"+
            obj.put("reference_number", case_number);
            obj.put("doc_name", doc_name);
            obj.put("doc_description", doc_name);
            obj.put("doc_path", doc_path+case_number+File.separator);
            obj.put("doc_category", document_type);
            obj.put("doc_extension", extension);
            obj.put("doc_app_uploaded", "elis");
            obj.put("doc_uploaded_by", "elis_backend");
            obj.put("doc_uploaded_by_id", "elis_backend");

            obj.put("doc_file_size", doc_file_size);
            obj.put("doc_file_size_byte", doc_file_size_byte);
            obj.put("doc_version", doc_version);
            obj.put("doc_type", doc_type);

  System.out.println(obj.toString());

            cls_document_upload_cl.con = cls_db_config.getCon();
            String upload_msg= cls_document_upload_cl.select_upload_a_new_file(obj.toString());
            
            JSONObject obj1 = new JSONObject(upload_msg);
            String unique_id = obj1.getString("unique_id");

            System.out.println(files_pdf_jackets+ File.separator + doc_name+extension);
            file.transferTo(new File(files_pdf_jackets+ File.separator + doc_name+extension));

            System.out.println(upload_msg);

        } catch (Exception e) {
            System.out.println("e.getMessage()");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            // System.out.println(e);
        }
        return ResponseEntity.ok("File for Job Number"+ job_number+ " uploaded successfully.");
    }


    @PostMapping("/file_upload_public_with_name_and_type")
    public ResponseEntity<?> file_upload_public_with_name_and_type(
            @RequestParam("file") MultipartFile file, // The File to be uploaded
            @RequestParam(value="job_number", required=false) String job_number, // Name of Document
            @RequestParam("case_number") String case_number, // Description
            @RequestParam(value="document_type", defaultValue="public_docs") String document_type, 
            @RequestParam(value="document_open_type", required=false) String document_open_type,//
            @RequestParam(value="doc_name", required=false) String document_open_name,//
            @RequestParam(value="doc_type", required=false) String document_open_type_name,
            @RequestParam(value="doc_file_size", required=false) String doc_file_size,
      @RequestParam(value="doc_file_size_byte", required=false) String doc_file_size_byte,
     @RequestParam(value="doc_version", required=false) String doc_version,
      @RequestParam(value="doc_type", required=false) String doc_type//

           
    ) {
        cls_document_upload_cl.con = cls_db_config.getCon();
        String fileName = file.getOriginalFilename();
       
       
       //   System.out.println("e");
       // System.out.println(doc_category);
      //  System.out.println(doc_category);
 

        try {

            JSONObject obj = new JSONObject();

           obj.put("organisation", "landcom");
           obj.put("document_type", document_type);
        
           System.out.println("obj.toString()");
           System.out.println(obj.toString());

            String doc_path = cls_document_upload_cl.get_file_location_for_upload(obj.toString());
            System.out.println("pdf_dest");
            System.out.println(doc_path+case_number);
            // File files_pdf_jackets = new File(pdf_dest + case_number);
            File files_pdf_jackets = new File(doc_path+case_number);
            System.out.println(doc_path+case_number);
            if (!files_pdf_jackets.exists()) {
                if (files_pdf_jackets.mkdirs()) {
                    // System.out.println("Multiple directories are
                    // created!");
                } else {
                    System.out.println("Failed to create multiple directories!");
                }
            }

            // String savePath = pdf_dest + case_number;
           // Get the index of the last dot
      int lastIndexOfDot = fileName.lastIndexOf(".");

            String extension = fileName.substring(fileName.lastIndexOf("."));
            String fileNameWithoutExtension = (lastIndexOfDot == -1) ? fileName : fileName.substring(0, lastIndexOfDot);
      String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(Calendar.getInstance().getTime());
        
		//File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
       // File tempFile = File.createTempFile();

            fileNameWithoutExtension="client_application_upload"+
            obj.put("reference_number", case_number);
            obj.put("doc_name", document_open_type_name+"_"+document_open_name+"_"+timeStamp);
            obj.put("doc_description", document_open_type_name+"_"+document_open_name+"_"+timeStamp);
            obj.put("doc_path", doc_path+case_number+File.separator);
            obj.put("doc_category", document_type);
            obj.put("doc_extension", extension);
            obj.put("doc_app_uploaded", "elis");
            obj.put("doc_uploaded_by", "elis_backend");
            obj.put("doc_uploaded_by_id", "elis_backend");

                obj.put("doc_file_size", doc_file_size);
            obj.put("doc_file_size_byte", doc_file_size_byte);
            obj.put("doc_version", doc_version);
            obj.put("doc_type", doc_type);

            cls_document_upload_cl.con = cls_db_config.getCon();
            String upload_msg= cls_document_upload_cl.select_upload_a_new_file(obj.toString());
            
            JSONObject obj1 = new JSONObject(upload_msg);
            String unique_id = obj1.getString("unique_id");

            System.out.println(files_pdf_jackets+ File.separator + unique_id+extension);
            //System.out.println(files_pdf_jackets+ File.separator + unique_id+extension);

            //file.transferTo(new File(files_pdf_jackets+ File.separator + unique_id+extension));
            file.transferTo(new File(files_pdf_jackets+ File.separator + document_open_type_name+"_"+document_open_name+"_"+timeStamp+extension));
            
           
            System.out.println(upload_msg);

        } catch (Exception e) {
            System.out.println("e.getMessage()");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            // System.out.println(e);
        }
        return ResponseEntity.ok("File for Job Number"+ job_number+ " uploaded successfully.");
    }


    @PostMapping("/case_document")
    public ResponseEntity<?> file_upload_case(
            @RequestParam("file") MultipartFile file, // The File to be uploaded
            @RequestParam("job_number") String job_number, // Name of Document
            @RequestParam("case_number") String case_number, // Description
            @RequestParam("document_type") String document_type,
                 @RequestParam(value="doc_file_size", required=false) String doc_file_size,
      @RequestParam(value="doc_file_size_byte", required=false) String doc_file_size_byte,
     @RequestParam(value="doc_version", required=false) String doc_version,
      @RequestParam(value="doc_type", required=false) String doc_type//

           
    ) {
        cls_document_upload_cl.con = cls_db_config.getCon();
        String fileName = file.getOriginalFilename();
       
        try {

            JSONObject obj = new JSONObject();

           obj.put("organisation", "landcom");
           obj.put("document_type", "case_docs");
        
           System.out.println("obj.toString()");
           System.out.println(obj.toString());

            String doc_path = cls_document_upload_cl.get_file_location_for_upload(obj.toString());
            System.out.println("pdf_dest");
            System.out.println(doc_path+case_number);
            // File files_pdf_jackets = new File(pdf_dest + case_number);
            File files_pdf_jackets = new File(doc_path+case_number);
            System.out.println(doc_path+File.separator+case_number);
            if (!files_pdf_jackets.exists()) {
                if (files_pdf_jackets.mkdirs()) {
                    // System.out.println("Multiple directories are
                    // created!");
                } else {
                    System.out.println("Failed to create multiple directories!");
                }
            }

            // String savePath = pdf_dest + case_number;
           
            String extension = fileName.substring(fileName.lastIndexOf("."));
            
            
            obj.put("reference_number", case_number);
            obj.put("doc_name", document_type);
            obj.put("doc_description", fileName);
            obj.put("doc_path", doc_path);
            obj.put("doc_category", "public_docs");
            obj.put("doc_extension", extension);
            obj.put("doc_app_uploaded", "elis");
            obj.put("doc_uploaded_by", "elis_portal");
            obj.put("doc_uploaded_by_id", "elis_portal");

            obj.put("doc_file_size", doc_file_size);
            obj.put("doc_file_size_byte", doc_file_size_byte);
            obj.put("doc_version", doc_version);
            obj.put("doc_type", doc_type);

            cls_document_upload_cl.con = cls_db_config.getCon();
            String upload_msg= cls_document_upload_cl.select_upload_a_new_file(obj.toString());
            
            JSONObject obj1 = new JSONObject(upload_msg);
            String unique_id = obj1.getString("unique_id");
           

            System.out.println(files_pdf_jackets + fileName);
            file.transferTo(new File(files_pdf_jackets+ File.separator + unique_id+extension));

           
            System.out.println(upload_msg);

        } catch (Exception e) {
            System.out.println("e.getMessage()");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            // System.out.println(e);
        }
        return ResponseEntity.ok("File for Job Number"+ job_number+ " uploaded successfully.");
    }




    @PostMapping("/upload_document_elis")
    public ResponseEntity<?> upload_document_elis(
            @RequestParam("file") MultipartFile file, // The File to be uploaded
            @RequestParam("doc_name") String doc_name, // Name of Document
            @RequestParam("doc_description") String doc_description, // Description
            @RequestParam("reference_number") String reference_number, //
            @RequestParam("doc_uploaded_by") String doc_uploaded_by,
            @RequestParam("doc_uploaded_by_id") String doc_uploaded_by_id,
               @RequestParam(value="doc_file_size", required=false) String doc_file_size,
      @RequestParam(value="doc_file_size_byte", required=false) String doc_file_size_byte,
     @RequestParam(value="doc_version", required=false) String doc_version,
      @RequestParam(value="doc_type", required=false) String doc_type//

           
    ) {
        cls_document_upload_cl.con = cls_db_config.getCon();
        String fileName = file.getOriginalFilename();
    

        try {

            JSONObject obj = new JSONObject();
           obj.put("organisation", "landcom");
           obj.put("document_type", "public_docs");
           System.out.println("obj.toString()");
           System.out.println(obj.toString());

            String doc_path = cls_document_upload_cl.get_file_location_for_upload(obj.toString());
            System.out.println("pdf_dest");
            System.out.println(doc_path);
            // File files_pdf_jackets = new File(pdf_dest + case_number);
            File files_pdf_jackets = new File(doc_path);
            System.out.println(doc_path);
            if (!files_pdf_jackets.exists()) {
                if (files_pdf_jackets.mkdirs()) {
                    // System.out.println("Multiple directories are
                    // created!");
                } else {
                    System.out.println("Failed to create multiple directories!");
                }
            }

            // String savePath = pdf_dest + case_number;
           
            String extension = fileName.substring(fileName.lastIndexOf("."));
            
            doc_name = doc_name.replaceAll("[ /-]", "_");


            obj.put("reference_number", reference_number);
            obj.put("doc_name", doc_name);
            obj.put("doc_description", fileName);
            obj.put("doc_path", doc_path+reference_number+File.separator);
            obj.put("doc_category", "public_docs");
            obj.put("doc_extension", extension);
            obj.put("doc_app_uploaded", "elis");
            obj.put("doc_uploaded_by", doc_uploaded_by);
            obj.put("doc_uploaded_by_id", doc_uploaded_by_id);


             obj.put("doc_file_size", doc_file_size);
            obj.put("doc_file_size_byte", doc_file_size_byte);
            obj.put("doc_version", doc_version);
            obj.put("doc_type", doc_type);

            cls_document_upload_cl.con = cls_db_config.getCon();
            String upload_msg= cls_document_upload_cl.select_upload_a_new_file(obj.toString());
            
            JSONObject obj1 = new JSONObject(upload_msg);
            String unique_id = obj1.getString("unique_id");
            System.out.println(files_pdf_jackets + fileName);
            file.transferTo(new File(files_pdf_jackets+ File.separator + doc_name+extension));
            System.out.println(upload_msg);

        } catch (Exception e) {
            System.out.println("e.getMessage()");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            // System.out.println(e);
        }
        return ResponseEntity.ok("CD Content uploaded successfully.");
    }



    @PostMapping("/select_doc_files_details_by_reference_number")
    @Operation(summary = "select_doc_files_details_by_reference_number", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Verify User", required = true, content = @Content(mediaType = "application/json", schema = @Schema(type = "string", description = "the Sub", accessMode = Schema.AccessMode.READ_ONLY, example = "Schema example"), examples = {
            @ExampleObject(name = "Sample Request", value = "{\"user\" : \"test.user\",\"pass\" : \"0001\"}", summary = "Verify User"),

    })))
    public String select_doc_files_details_by_reference_number(@RequestBody String json_data) throws Exception {
        // cls_document_upload_cl.con = cls_db_config.getCon();
		// //System.out.println(json_data);
        // String result = cls_document_upload_cl.select_doc_files_details_by_reference_number(json_data);
        // return result;

        String result =null;
        JSONObject jsonor= new JSONObject(json_data);
        String document_type = jsonor.getString("document_type");
        String doc_reference_number = jsonor.getString("doc_reference_number");
        
        cls_document_upload_cl.con = cls_db_config.getCon();
       result  = cls_document_upload_cl.select_doc_files_details_by_reference_number(json_data);
      // result="null";

        JSONObject jsonobject = new JSONObject(result);
       // String doc_data = jsonobject.getString("data");

            // Check if the key "data" exists and is not null
            if (jsonobject.has("data") && !jsonobject.isNull("data")) {
                //String doc_data = jsonobject.getString("data");
                
                // // Further check if the value of "data" is not empty
                // if (doc_data != null && !doc_data.isEmpty()) {
                //     // Process doc_data
                //     System.out.println("Data: " + doc_data);
                // } else {
                //     System.out.println("Data is empty");
                // }
            } else {
                String doc_data = jsonobject.getString("data");
                
               // System.out.println("Data is null or does not exist");
               if (doc_data != null && !doc_data.isEmpty()) {
                    // Process doc_data
                    System.out.println("Data: " + doc_data);
                } else {
                    String folderpath = null;
                    if (document_type.equals("public_docs")) {
                         folderpath = cls_url_config.getPublic_docs_upload_location() + doc_reference_number;
                    } else {
                         folderpath = cls_url_config.getCase_upload_location() + doc_reference_number;
                    }
			
			// System.out.println("folderpath: " +folderpath);
			// List All Files In a Folder
			File subdir = new File(folderpath.toString());
			String[] children = subdir.list();
			if (children == null) {
				//cls_document_upload_cl.con = cls_db_config.getCon();
                result  = result;
       
                System.out.println("Either dir does not exist or is not a directory");
			} else {
				for (int j = 0; j < children.length; j++) {
					String filename_doc = children[j];
					System.out.println("case_number: " + doc_reference_number);
					System.out.println("File: " + filename_doc);
					String path = folderpath.toString() + File.separatorChar + filename_doc;

					String extension = filename_doc.substring(filename_doc.lastIndexOf("."));


                     String fileName = filename_doc;
                  //  String fileType = Files.probeContentType(file); // Get file type (MIME)
                    //long fileSize = attrs.size(); // File size in bytes
                    String fileExtension = extension; // Get file extension
                    String fullPath = path; // Full file path
                    String caseFolder = doc_reference_number; // Get folder name
        
                    int lastIndexOfDot = fileName.lastIndexOf(".");
                    String fileNameWithoutExtension = (lastIndexOfDot == -1) ? fileName : fileName.substring(0, lastIndexOfDot);
        
                    // Prepare JSON object for the upload
                    JSONObject obj = new JSONObject();
                    obj.put("organisation", "landcom");
                    obj.put("document_type", document_type);
                    obj.put("reference_number", caseFolder); // Use the folder name as the reference
                    obj.put("doc_name", fileNameWithoutExtension);
                    obj.put("doc_description", fileNameWithoutExtension);
                    obj.put("doc_path", folderpath +  File.separator); // Full file path
                    obj.put("doc_path_upload", fullPath); // Full file path
                    obj.put("doc_category", "public_docs");
                    obj.put("doc_extension", fileExtension); // File extension
                    obj.put("doc_app_uploaded", "elis");
                    obj.put("doc_uploaded_by", "Initial Upload"); // Replace with actual uploader
                    obj.put("doc_uploaded_by_id", "Initial Upload"); // Replace with actual uploader ID

//long fileSizeBytes = Files.size(fullPath);  
File file = new File(fullPath); 

long fileSizeBytes = file.length();// ← this is the size in bytes
    
    // Optional: human-readable format (e.g. KB, MB)
    String humanSize = formatFileSize(fileSizeBytes);

    System.out.println("File: " + filename_doc + " → Size: " + fileSizeBytes + " bytes (" + humanSize + ")");

    // Add to your JSON
    obj.put("doc_file_size", humanSize);                // e.g. "2.34 MB"
    obj.put("doc_file_size_byte", fileSizeBytes);       // exact bytes (long)

                         obj.put("doc_file_size", humanSize);
                       obj.put("doc_file_size_byte", fileSizeBytes);
                     obj.put("doc_version", "1");
              obj.put("doc_type", "Public Document Format");
        
                    // Upload document to the database using your custom class and methods
                    cls_document_upload_cl.con = cls_db_config.getCon();
                    String uploadMsg = cls_document_upload_cl.select_upload_a_new_file_initial_upload(obj.toString());
        

				}

			}
                   // document_type
                   cls_document_upload_cl.con = cls_db_config.getCon();
                result = cls_document_upload_cl.select_doc_files_details_by_reference_number_backend(json_data);
               // System.out.println(result);
                    System.out.println("Data is empty");
                }
            }

       
        return result;
    }


    @PostMapping("/select_doc_files_details_by_reference_number_backend")
    @Operation(summary = "select_doc_files_details_by_reference_number", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Verify User", required = true, content = @Content(mediaType = "application/json", schema = @Schema(type = "string", description = "the Sub", accessMode = Schema.AccessMode.READ_ONLY, example = "Schema example"), examples = {
            @ExampleObject(name = "Sample Request", value = "{\"user\" : \"test.user\",\"pass\" : \"0001\"}", summary = "Verify User"),

    })))
    public String select_doc_files_details_by_reference_number_backend(@RequestBody String json_data) throws Exception {
        
		//System.out.println(json_data);
        String result =null;
        JSONObject jsonor= new JSONObject(json_data);
        String document_type = jsonor.getString("document_type");
        String doc_reference_number = jsonor.getString("doc_reference_number");
        
        cls_document_upload_cl.con = cls_db_config.getCon();
        result  = cls_document_upload_cl.select_doc_files_details_by_reference_number_backend(json_data);
        //result="null";
        JSONObject jsonobject = new JSONObject(result);
       // String doc_data = jsonobject.getString("data");

            // Check if the key "data" exists and is not null
            if (jsonobject.has("data") && !jsonobject.isNull("data")) {
                //String doc_data = jsonobject.getString("data");
                
                // // Further check if the value of "data" is not empty
                // if (doc_data != null && !doc_data.isEmpty()) {
                //     // Process doc_data
                //     System.out.println("Data: " + doc_data);
                // } else {
                //     System.out.println("Data is empty");
                // }
            } else {
                String doc_data = jsonobject.getString("data");
                
               // System.out.println("Data is null or does not exist");
               if (doc_data != null && !doc_data.isEmpty()) {
                    // Process doc_data
                    System.out.println("Data: " + doc_data);
                } else {
                    String folderpath = null;
                    if (document_type.equals("public_docs")) {
                         folderpath = cls_url_config.getPublic_docs_upload_location() + doc_reference_number;
                    } else {
                         folderpath = cls_url_config.getCase_upload_location() + doc_reference_number;
                    }
			
			// System.out.println("folderpath: " +folderpath);
			// List All Files In a Folder
			File subdir = new File(folderpath.toString());
			String[] children = subdir.list();
			if (children == null) {
				//cls_document_upload_cl.con = cls_db_config.getCon();
                result  = result;
       
                System.out.println("Either dir does not exist or is not a directory");
			} else {
				for (int j = 0; j < children.length; j++) {
					String filename_doc = children[j];
					System.out.println("case_number: " + doc_reference_number);
					System.out.println("File: " + filename_doc);
					String path = folderpath.toString() + File.separatorChar + filename_doc;

					String extension = filename_doc.substring(filename_doc.lastIndexOf("."));


                     String fileName = filename_doc;
                  //  String fileType = Files.probeContentType(file); // Get file type (MIME)
                    //long fileSize = attrs.size(); // File size in bytes
                    String fileExtension = extension; // Get file extension
                    String fullPath = path; // Full file path
                    String caseFolder = doc_reference_number; // Get folder name
        
                    int lastIndexOfDot = fileName.lastIndexOf(".");
                    String fileNameWithoutExtension = (lastIndexOfDot == -1) ? fileName : fileName.substring(0, lastIndexOfDot);
        
                    // Prepare JSON object for the upload
                    JSONObject obj = new JSONObject();
                    obj.put("organisation", "landcom");
                    obj.put("document_type", document_type);
                    obj.put("reference_number", caseFolder); // Use the folder name as the reference
                    obj.put("doc_name", fileNameWithoutExtension);
                    obj.put("doc_description", fileNameWithoutExtension);
                    obj.put("doc_path", folderpath +  File.separator); // Full file path
                    obj.put("doc_path_upload", fullPath); // Full file path
                    obj.put("doc_category", document_type);
                    obj.put("doc_extension", fileExtension); // File extension
                    obj.put("doc_app_uploaded", "elis");
                    obj.put("doc_uploaded_by", "Initial Upload"); // Replace with actual uploader
                    obj.put("doc_uploaded_by_id", "Initial Upload"); // Replace with actual uploader ID



//long fileSizeBytes = Files.size(fullPath);  
File file = new File(fullPath); 

long fileSizeBytes = file.length();// ← this is the size in bytes
    
    // Optional: human-readable format (e.g. KB, MB)
    String humanSize = formatFileSize(fileSizeBytes);

    System.out.println("File: " + filename_doc + " → Size: " + fileSizeBytes + " bytes (" + humanSize + ")");

    // Add to your JSON
    obj.put("doc_file_size", humanSize);                // e.g. "2.34 MB"
    obj.put("doc_file_size_byte", fileSizeBytes);       // exact bytes (long)

                         obj.put("doc_file_size", humanSize);
                       obj.put("doc_file_size_byte", fileSizeBytes);
                     obj.put("doc_version", "1");
              obj.put("doc_type", "Public Document Format");
        
                    // Upload document to the database using your custom class and methods
                    cls_document_upload_cl.con = cls_db_config.getCon();
                    String uploadMsg = cls_document_upload_cl.select_upload_a_new_file_initial_upload(obj.toString());
				}

			}
                   // document_type
                   cls_document_upload_cl.con = cls_db_config.getCon();
                result = cls_document_upload_cl.select_doc_files_details_by_reference_number_backend_correct(json_data);
               // System.out.println(result);
                    System.out.println("Data is empty");
                }
            }

       
        return result;
    }


    @PostMapping("/open_doc_by_unique_id")
	public ResponseEntity<byte[]> generate_rent_demand_notice_pdf(
			@RequestBody String json_data)
			throws Exception {
                cls_document_upload_cl.con = cls_db_config.getCon();
                //System.out.println(json_data);
        String result_db = cls_document_upload_cl.select_doc_files_details_by_doc_uuid(json_data);
       
        JSONObject jsonobject = new JSONObject(result_db);
        String doc_data = jsonobject.getString("data");

        JSONObject obj1 = new JSONObject(doc_data);
        String doc_path_file = obj1.getString("doc_path");
        String doc_unique_id = obj1.getString("doc_uuid");
        String doc_name = obj1.getString("doc_name");
        String doc_doc_extension = obj1.getString("doc_extension");
        String doc_reference_number = obj1.getString("doc_reference_number");

		//File pdfFile = new File(doc_path_file+doc_name+"."+doc_doc_extension);

        File pdfFile;
        if (doc_doc_extension.equals("pdf")) {
            // When the extension is 'pdf'
            pdfFile = new File(doc_path_file + doc_name + "." + doc_doc_extension);
        } else if (doc_doc_extension.equals(".pdf")) {
            // When the extension is '.pdf'
            pdfFile = new File(doc_path_file + doc_name + doc_doc_extension);  // No need to add extra "."
        } else if (doc_doc_extension.equals(".PDF")) {
            // When the extension is '.pdf'
            pdfFile = new File(doc_path_file + doc_name + doc_doc_extension);  
        } else {
            // Default case or for other extensions
            pdfFile = new File(doc_path_file + doc_name + "." + doc_doc_extension);
        }

		byte[] buffer = null;
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(pdfFile));
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) pdfFile.length());

		int ch;
		long actual = 0;
		while ((ch = is.read()) != -1) {
			bos.write(ch);
			actual++;
		}
		bos.flush();
		bos.close();
		buffer = bos.toByteArray();
		// Set the headers for the response
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "example.pdf"); // Change the filename if needed
		return new ResponseEntity<>(buffer, headers, HttpStatus.OK);
	}


    @PostMapping("/open_doc_by_unique_id_with_reference_number")
	public ResponseEntity<byte[]> open_doc_by_unique_id_new(
			@RequestBody String json_data)throws Exception {
                cls_document_upload_cl.con = cls_db_config.getCon();
                System.out.println(json_data);
        String result_db = cls_document_upload_cl.select_doc_files_details_by_doc_uuid(json_data);
       
        JSONObject jsonobject = new JSONObject(result_db);
        String doc_data = jsonobject.getString("data");

        JSONObject obj1 = new JSONObject(doc_data);
        String doc_path_file = obj1.getString("doc_path");
        String doc_unique_id = obj1.getString("doc_uuid");
        String doc_name = obj1.getString("doc_name");
        String doc_doc_extension = obj1.getString("doc_extension");
        String doc_reference_number = obj1.getString("doc_reference_number");
       // System.out.println(doc_path_file+File.separator+doc_reference_number+File.separator+doc_unique_id+doc_doc_extension);
	 	//File pdfFile = new File(doc_path_file+File.separator+doc_reference_number+File.separator+doc_name+doc_doc_extension);
        System.out.println(doc_path_file+doc_name+"."+doc_doc_extension);
        //System.out.println(doc_path_file+doc_name+"."+doc_doc_extension);
        
       // File pdfFile = new File(doc_path_file+doc_name+"."+doc_doc_extension);
        File pdfFile;
        if (doc_doc_extension.equals("pdf")) {
            // When the extension is 'pdf'
            pdfFile = new File(doc_path_file + doc_name + "." + doc_doc_extension);
        } else if (doc_doc_extension.equals(".pdf")) {
            // When the extension is '.pdf'
            System.out.println(doc_path_file+doc_name+doc_doc_extension);
            pdfFile = new File(doc_path_file + doc_name + doc_doc_extension); 
        } else if (doc_doc_extension.equals(".PDF")) {
            // When the extension is '.pdf'
            pdfFile = new File(doc_path_file + doc_name + doc_doc_extension);  
        } else {
            // Default case or for other extensions
            pdfFile = new File(doc_path_file + doc_name + "." + doc_doc_extension);
        }
		byte[] buffer = null;
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(pdfFile));
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) pdfFile.length());

		int ch;
		long actual = 0;
		while ((ch = is.read()) != -1) {
			bos.write(ch);
			actual++;
		}
		bos.flush();
		bos.close();
		buffer = bos.toByteArray();
		// Set the headers for the response
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("attachment", "example.pdf"); // Change the filename if needed
		return new ResponseEntity<>(buffer, headers, HttpStatus.OK);
	}


    // @PostMapping("/convert")
    // public ResponseEntity<Map<String, Object>> convertToPdf(
    //         @RequestParam("job_number") String jobNumber, // Document name
    //         @RequestParam("case_number") String caseNumber, // Case number
    //         @RequestBody byte[] byteData) {
        
    //     String destination = "/path/to/save/" + jobNumber + ".pdf";  // Save as job_number.pdf
    //     Map<String, Object> responseAttributes = new HashMap<>();

    //     try (FileOutputStream fos = new FileOutputStream(new File(destination))) {
    //         // Write byte[] data to the PDF file
    //         fos.write(byteData);

    //         // Add attributes to the response
    //         responseAttributes.put("fileName", jobNumber + ".pdf");
    //         responseAttributes.put("fileSize", byteData.length);
    //         responseAttributes.put("jobNumber", jobNumber);
    //         responseAttributes.put("caseNumber", caseNumber);
    //         responseAttributes.put("message", "PDF file saved successfully.");

    //         // Adding custom headers
    //         HttpHeaders headers = new HttpHeaders();
    //         headers.add("X-Custom-Header", "FileUploadSuccess");

    //         // Return ResponseEntity with additional headers and attributes
    //         return ResponseEntity
    //                 .status(HttpStatus.OK)
    //                 .headers(headers)
    //                 .body(responseAttributes);

    //     } catch (IOException e) {
    //         responseAttributes.put("message", "Failed to save PDF: " + e.getMessage());

    //         // Return ResponseEntity with error message and 500 status code
    //         return ResponseEntity
    //                 .status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body(responseAttributes);
    //     }
    // }

  // API to convert byte array to PDF, job_number, and case_number in the request body
  @PostMapping("/recieve_binary_pdf")
  public String convertToPdf(@RequestBody PdfRequest pdfRequest) throws JSONException {
      String jobNumber = pdfRequest.getJobNumber();
      String caseNumber = pdfRequest.getCaseNumber();
      byte[] fileData = pdfRequest.getFileData();


    String doc_name = pdfRequest.getdoc_name();
    String doc_description= pdfRequest.getdoc_description();
    String doc_category= pdfRequest.getdoc_category();
    
    String reference_number = pdfRequest.getCaseNumber();
    String doc_uploaded_by= pdfRequest.getdoc_uploaded_by();
    String doc_uploaded_by_id  = pdfRequest.getdoc_uploaded_by_id();
    String doc_uploaded_by_ip_address = pdfRequest.getdoc_uploaded_by_ip_address();

    String doc_file_size = pdfRequest.getdoc_file_size();
    String doc_file_size_byte= pdfRequest.getdoc_file_size_byte();
    String doc_version  = pdfRequest.getdoc_version();
    String doc_type = pdfRequest.getdoc_type();
       
       JSONObject obj = new JSONObject();
       obj.put("organisation", "landcom");
       obj.put("document_type", doc_category);
      // obj.put("document_type", "public_docs");

       
       System.out.println("obj.toString()");
       System.out.println(obj.toString());

       cls_document_upload_cl.con = cls_db_config.getCon();
        String doc_path = cls_document_upload_cl.get_file_location_for_upload(obj.toString());
        System.out.println("pdf_dest");
        System.out.println(doc_path);
       
      File files_pdf_jackets = new File(doc_path + caseNumber);

      if (!files_pdf_jackets.exists()) {
          if (files_pdf_jackets.mkdirs()) {
              // System.out.println("Multiple directories are created!");
          } else {
              System.out.println("Failed to create multiple directories!");
          }
      }

   //   String doc_name = "/Users/johnassiamah/elis/documents/case-docs/cleinry app-ine file in java.pdf";

        // Replace spaces, dashes, and forward slashes with underscores
        doc_name = doc_name.replaceAll("[ /-]", "_");


      obj.put("reference_number", reference_number);
      obj.put("doc_name", doc_name);
      obj.put("doc_description", doc_description);
      obj.put("doc_path", doc_path + caseNumber+File.separator);
      obj.put("doc_category", doc_category);
      obj.put("doc_extension", "pdf");
      obj.put("doc_app_uploaded", "elis");
      obj.put("doc_uploaded_by", doc_uploaded_by);
      obj.put("doc_uploaded_by_id", doc_uploaded_by_id);



      obj.put("doc_file_size", doc_file_size);
      obj.put("doc_file_size_byte", doc_file_size_byte);
    obj.put("doc_version", doc_version);
      obj.put("doc_type", doc_type);



      cls_document_upload_cl.con = cls_db_config.getCon();
      String upload_msg= cls_document_upload_cl.select_upload_a_new_file(obj.toString());
      
      JSONObject obj1 = new JSONObject(upload_msg);
      String unique_id = obj1.getString("unique_id");
      String destination = doc_path + caseNumber + File.separator +doc_name + ".pdf";  // Save as jobNumber.pdf
    
      try (FileOutputStream fos = new FileOutputStream(new File(destination))) {
          // Write byte[] data to the PDF file
          fos.write(fileData);
          return "Success";

      } catch (IOException e) {
          return e.getMessage();
      }
  }

  @PostMapping("/cica_reply_document")
  public ResponseEntity<?> cica_reply_document(
          @RequestParam("file") MultipartFile file, // The File to be uploaded
          @RequestParam("ticket_number") String ticket_number, // Name of Document
          //@RequestParam("case_number") String case_number, // Description
          @RequestParam("document_type") String document_type//

         
  ) {
      cls_document_upload_cl.con = cls_db_config.getCon();
      String fileName = file.getOriginalFilename();
     
     
     //   System.out.println("e");
     // System.out.println(doc_category);
    //  System.out.println(doc_category);


      try {

          JSONObject obj = new JSONObject();

         obj.put("organisation", "landcom");
         obj.put("document_type", "cica_reply_document");
      
         System.out.println("obj.toString()");
         System.out.println(obj.toString());

          String doc_path = cls_document_upload_cl.get_file_location_for_upload(obj.toString());
          System.out.println("pdf_dest");
          System.out.println(doc_path+ticket_number);
          // File files_pdf_jackets = new File(pdf_dest + case_number);
          File files_pdf_jackets = new File(doc_path+ticket_number);
          System.out.println(doc_path+File.separator+ticket_number);
          if (!files_pdf_jackets.exists()) {
              if (files_pdf_jackets.mkdirs()) {
                  // System.out.println("Multiple directories are
                  // created!");
              } else {
                  System.out.println("Failed to create multiple directories!");
              }
          }

          // String savePath = pdf_dest + case_number;
         
          String extension = fileName.substring(fileName.lastIndexOf("."));
          
          
          obj.put("reference_number", ticket_number);
          obj.put("doc_name", document_type);
          obj.put("doc_description", fileName);
          obj.put("doc_path", doc_path);
          obj.put("doc_category", "cica_reply_document");
          obj.put("doc_extension", extension);
          obj.put("doc_app_uploaded", "elis");
          obj.put("doc_uploaded_by", "elis_portal");
          obj.put("doc_uploaded_by_id", "elis_portal");

          cls_document_upload_cl.con = cls_db_config.getCon();
          String upload_msg= cls_document_upload_cl.select_upload_a_new_file(obj.toString());
          
          JSONObject obj1 = new JSONObject(upload_msg);
          String unique_id = obj1.getString("unique_id");
         

          System.out.println(files_pdf_jackets + fileName);
          file.transferTo(new File(files_pdf_jackets+ File.separator + unique_id+extension));

         
          System.out.println(upload_msg);

      } catch (Exception e) {
          System.out.println("e.getMessage()");
          System.out.println(e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          // System.out.println(e);
      }
      return ResponseEntity.ok("File for Ticket Number"+ ticket_number+ " uploaded successfully.");
  }


  @PostMapping("/cica_request_form")
  public ResponseEntity<?> cica_request_form(
          @RequestParam("file") MultipartFile file, // The File to be uploaded
          @RequestParam("ticket_number") String ticket_number, // Name of Document
          //@RequestParam("case_number") String case_number, // Description
          @RequestParam("document_type") String document_type//

         
  ) {
      cls_document_upload_cl.con = cls_db_config.getCon();
      String fileName = file.getOriginalFilename();
     
     
     //   System.out.println("e");
     // System.out.println(doc_category);
    //  System.out.println(doc_category);


      try {

          JSONObject obj = new JSONObject();

         obj.put("organisation", "landcom");
         obj.put("document_type", "cica_request_form");
      
         System.out.println("obj.toString()");
         System.out.println(obj.toString());

          String doc_path = cls_document_upload_cl.get_file_location_for_upload(obj.toString());
          System.out.println("pdf_dest");
          System.out.println(doc_path+ticket_number);
          // File files_pdf_jackets = new File(pdf_dest + case_number);
          File files_pdf_jackets = new File(doc_path+ticket_number);
          System.out.println(doc_path+File.separator+ticket_number);
          if (!files_pdf_jackets.exists()) {
              if (files_pdf_jackets.mkdirs()) {
                  // System.out.println("Multiple directories are
                  // created!");
              } else {
                  System.out.println("Failed to create multiple directories!");
              }
          }

          // String savePath = pdf_dest + case_number;
         
          String extension = fileName.substring(fileName.lastIndexOf("."));
          fileName = fileName.replaceAll("[ /-]", "_");
          
          obj.put("reference_number", ticket_number);
          obj.put("doc_name", document_type);
          obj.put("doc_description", fileName);
          obj.put("doc_path", doc_path);
          obj.put("doc_category", "cica_request_form");
          obj.put("doc_extension", extension);
          obj.put("doc_app_uploaded", "elis");
          obj.put("doc_uploaded_by", "elis_portal");
          obj.put("doc_uploaded_by_id", "elis_portal");

          cls_document_upload_cl.con = cls_db_config.getCon();
          String upload_msg= cls_document_upload_cl.select_upload_a_new_file(obj.toString());
          
          JSONObject obj1 = new JSONObject(upload_msg);
          String unique_id = obj1.getString("unique_id");
         

          System.out.println(files_pdf_jackets + fileName);
          file.transferTo(new File(files_pdf_jackets+ File.separator + unique_id+extension));

         
          System.out.println(upload_msg);

      } catch (Exception e) {
          System.out.println("e.getMessage()");
          System.out.println(e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          // System.out.println(e);
      }
      return ResponseEntity.ok("File for Ticket Number"+ ticket_number+ " uploaded successfully.");
  }

  @PostMapping("/legal_document_location")
  public ResponseEntity<?> legal_document_location(
          @RequestParam("file") MultipartFile file, // The File to be uploaded
          @RequestParam("suit_number") String suit_number, // Name of Document
          //@RequestParam("case_number") String case_number, // Description
          @RequestParam("document_type") String document_type//

         
  ) {
      cls_document_upload_cl.con = cls_db_config.getCon();
      String fileName = file.getOriginalFilename();
     
     
     //   System.out.println("e");
     // System.out.println(doc_category);
    //  System.out.println(doc_category);


      try {

          JSONObject obj = new JSONObject();

         obj.put("organisation", "landcom");
         obj.put("document_type", "legal_document_location");
      
         System.out.println("obj.toString()");
         System.out.println(obj.toString());

          String doc_path = cls_document_upload_cl.get_file_location_for_upload(obj.toString());
          System.out.println("pdf_dest");
          System.out.println(doc_path+suit_number);
          // File files_pdf_jackets = new File(pdf_dest + case_number);
          File files_pdf_jackets = new File(doc_path+suit_number);
          System.out.println(doc_path+File.separator+suit_number);
          if (!files_pdf_jackets.exists()) {
              if (files_pdf_jackets.mkdirs()) {
                  // System.out.println("Multiple directories are
                  // created!");
              } else {
                  System.out.println("Failed to create multiple directories!");
              }
          }

          // String savePath = pdf_dest + case_number;
         
          String extension = fileName.substring(fileName.lastIndexOf("."));
          
          fileName = fileName.replaceAll("[ /-]", "_");
          obj.put("reference_number", suit_number);
          obj.put("doc_name", document_type);
          obj.put("doc_description", fileName);
          obj.put("doc_path", doc_path);
          obj.put("doc_category", "legal_document_location");
          obj.put("doc_extension", extension);
          obj.put("doc_app_uploaded", "elis");
          obj.put("doc_uploaded_by", "elis_portal");
          obj.put("doc_uploaded_by_id", "elis_portal");

          cls_document_upload_cl.con = cls_db_config.getCon();
          String upload_msg= cls_document_upload_cl.select_upload_a_new_file(obj.toString());
          
          JSONObject obj1 = new JSONObject(upload_msg);
          String unique_id = obj1.getString("unique_id");
         

          System.out.println(files_pdf_jackets + fileName);
          file.transferTo(new File(files_pdf_jackets+ File.separator + unique_id+extension));

         
          System.out.println(upload_msg);

      } catch (Exception e) {
          System.out.println("e.getMessage()");
          System.out.println(e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          // System.out.println(e);
      }
      return ResponseEntity.ok("File for Suit Number"+ suit_number+ " uploaded successfully.");
  }

  @PostMapping("/legal_provide_documents")
  public ResponseEntity<?> legal_provide_documents(
          @RequestParam("file") MultipartFile file, // The File to be uploaded
          @RequestParam("suit_number") String suit_number, // Name of Document
          //@RequestParam("case_number") String case_number, // Description
          @RequestParam("document_type") String document_type//

         
  ) {
      cls_document_upload_cl.con = cls_db_config.getCon();
      String fileName = file.getOriginalFilename();
     
     
     //   System.out.println("e");
     // System.out.println(doc_category);
    //  System.out.println(doc_category);


      try {

          JSONObject obj = new JSONObject();

         obj.put("organisation", "landcom");
         obj.put("document_type", "legal_provide_documents");
      
         System.out.println("obj.toString()");
         System.out.println(obj.toString());

          String doc_path = cls_document_upload_cl.get_file_location_for_upload(obj.toString());
          System.out.println("pdf_dest");
          System.out.println(doc_path+suit_number);
          // File files_pdf_jackets = new File(pdf_dest + case_number);
          File files_pdf_jackets = new File(doc_path+suit_number);
          System.out.println(doc_path+File.separator+suit_number);
          if (!files_pdf_jackets.exists()) {
              if (files_pdf_jackets.mkdirs()) {
                  // System.out.println("Multiple directories are
                  // created!");
              } else {
                  System.out.println("Failed to create multiple directories!");
              }
          }

          // String savePath = pdf_dest + case_number;
         
          String extension = fileName.substring(fileName.lastIndexOf("."));
          
          fileName = fileName.replaceAll("[ /-]", "_");

          obj.put("reference_number", suit_number);
          obj.put("doc_name", document_type);
          obj.put("doc_description", fileName);
          obj.put("doc_path", doc_path);
          obj.put("doc_category", "legal_provide_documents");
          obj.put("doc_extension", extension);
          obj.put("doc_app_uploaded", "elis");
          obj.put("doc_uploaded_by", "elis_portal");
          obj.put("doc_uploaded_by_id", "elis_portal");

          cls_document_upload_cl.con = cls_db_config.getCon();
          String upload_msg= cls_document_upload_cl.select_upload_a_new_file(obj.toString());
          
          JSONObject obj1 = new JSONObject(upload_msg);
          String unique_id = obj1.getString("unique_id");
         

          System.out.println(files_pdf_jackets + fileName);
          file.transferTo(new File(files_pdf_jackets+ File.separator + unique_id+extension));

         
          System.out.println(upload_msg);

      } catch (Exception e) {
          System.out.println("e.getMessage()");
          System.out.println(e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          // System.out.println(e);
      }
      return ResponseEntity.ok("File for Suit Number"+ suit_number+ " uploaded successfully.");
  }

  @PostMapping("/legal_request_documents")
  public ResponseEntity<?> legal_request_documents(
          @RequestParam("file") MultipartFile file, // The File to be uploaded
          @RequestParam("suit_number") String suit_number, // Name of Document
          //@RequestParam("case_number") String case_number, // Description
          @RequestParam("document_type") String document_type//

         
  ) {
      cls_document_upload_cl.con = cls_db_config.getCon();
      String fileName = file.getOriginalFilename();
     
     
     //   System.out.println("e");
     // System.out.println(doc_category);
    //  System.out.println(doc_category);


      try {

          JSONObject obj = new JSONObject();

         obj.put("organisation", "landcom");
         obj.put("document_type", "legal_request_documents");
      
         System.out.println("obj.toString()");
         System.out.println(obj.toString());

          String doc_path = cls_document_upload_cl.get_file_location_for_upload(obj.toString());
          System.out.println("pdf_dest");
          System.out.println(doc_path+suit_number);
          // File files_pdf_jackets = new File(pdf_dest + case_number);
          File files_pdf_jackets = new File(doc_path+suit_number);
          System.out.println(doc_path+File.separator+suit_number);
          if (!files_pdf_jackets.exists()) {
              if (files_pdf_jackets.mkdirs()) {
                  // System.out.println("Multiple directories are
                  // created!");
              } else {
                  System.out.println("Failed to create multiple directories!");
              }
          }

          // String savePath = pdf_dest + case_number;
         
          String extension = fileName.substring(fileName.lastIndexOf("."));
          
          fileName = fileName.replaceAll("[ /-]", "_");
          
          obj.put("reference_number", suit_number);
          obj.put("doc_name", document_type);
          obj.put("doc_description", fileName);
          obj.put("doc_path", doc_path);
          obj.put("doc_category", "legal_request_documents");
          obj.put("doc_extension", extension);
          obj.put("doc_app_uploaded", "elis");
          obj.put("doc_uploaded_by", "elis_portal");
          obj.put("doc_uploaded_by_id", "elis_portal");

          cls_document_upload_cl.con = cls_db_config.getCon();
          String upload_msg= cls_document_upload_cl.select_upload_a_new_file(obj.toString());
          
          JSONObject obj1 = new JSONObject(upload_msg);
          String unique_id = obj1.getString("unique_id");
         

          System.out.println(files_pdf_jackets + fileName);
          file.transferTo(new File(files_pdf_jackets+ File.separator + unique_id+extension));

         
          System.out.println(upload_msg);

      } catch (Exception e) {
          System.out.println("e.getMessage()");
          System.out.println(e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          // System.out.println(e);
      }
      return ResponseEntity.ok("File for Suit Number"+ suit_number+ " uploaded successfully.");
  }


  @PostMapping("/corporate_upload_document")
    public ResponseEntity<?> corporate_upload_document(
            @RequestParam("file") MultipartFile file, // The File to be uploaded
            @RequestParam(value="job_number", required=false) String job_number, // Name of Document
            @RequestParam("case_number") String case_number, // Description
            @RequestParam("document_type") String document_type,//
            @RequestParam("document_name") String document_name//

           
    ) {
        cls_document_upload_cl.con = cls_db_config.getCon();
        String fileName = file.getOriginalFilename();
       
       
       //   System.out.println("e");
       // System.out.println(doc_category);
      //  System.out.println(doc_category);
 

        try {

            JSONObject obj = new JSONObject();

           obj.put("organisation", "landcom");
           obj.put("document_type", "public_docs");
        
           System.out.println("obj.toString()");
           System.out.println(obj.toString());

            String doc_path = cls_document_upload_cl.get_file_location_for_upload(obj.toString());
            System.out.println("pdf_dest");
            System.out.println(doc_path+case_number);
            // File files_pdf_jackets = new File(pdf_dest + case_number);
            File files_pdf_jackets = new File(doc_path+case_number);
            System.out.println(doc_path+File.separator+case_number);
            if (!files_pdf_jackets.exists()) {
                if (files_pdf_jackets.mkdirs()) {
                    // System.out.println("Multiple directories are
                    // created!");
                } else {
                    System.out.println("Failed to create multiple directories!");
                }
            }

            // String savePath = pdf_dest + case_number;
           
            String extension = fileName.substring(fileName.lastIndexOf("."));

            String file_name_incoming = document_name.replaceAll(" ", "_").toLowerCase();
            String file_type_incoming = document_type.replaceAll(" ", "_").toLowerCase();

            String doc_description = file_type_incoming + "_" + file_name_incoming + ".pdf";
            
            fileName = fileName.replaceAll("[ /-]", "_");

            obj.put("reference_number", case_number);
            obj.put("doc_name", document_name);
            obj.put("doc_type", document_type);
            obj.put("doc_description", doc_description);
            obj.put("doc_path", doc_path);
            obj.put("doc_category", "public_docs");
            obj.put("doc_extension", extension);
            obj.put("doc_app_uploaded", "elis");
            obj.put("doc_uploaded_by", "elis_portal");
            obj.put("doc_uploaded_by_id", "elis_portal");

            cls_document_upload_cl.con = cls_db_config.getCon();
            String upload_msg= cls_document_upload_cl.select_upload_a_new_file(obj.toString());
            
            JSONObject obj1 = new JSONObject(upload_msg);
            String unique_id = obj1.getString("unique_id");
           

            System.out.println(files_pdf_jackets + fileName);
            file.transferTo(new File(files_pdf_jackets+ File.separator + unique_id+extension));

           
            System.out.println(upload_msg);

        } catch (Exception e) {
            System.out.println("e.getMessage()");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            // System.out.println(e);
        }
        return ResponseEntity.ok("File for Job Number"+ job_number+ " uploaded successfully.");
    }
    

    public static String formatFileSize(long bytes) {
    if (bytes <= 0) return "0 B";
    
    final String[] units = new String[] {"B", "KB", "MB", "GB", "TB"};
    int digitGroups = (int) (Math.log10(bytes) / Math.log10(1024));
    
    return String.format("%.1f %s", 
        bytes / Math.pow(1024, digitGroups), 
        units[digitGroups]);
}

   

}
