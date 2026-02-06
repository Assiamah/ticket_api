package com.mit.ticket_mgt_api.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.codehaus.jettison.json.JSONArray;
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
import java.util.Iterator;
import com.mit.ticket_mgt_api.conn_class.Ws_url_config;
import com.mit.ticket_mgt_api.conn_class.db_settings;
import com.mit.ticket_mgt_api.models.document_upload.cls_document_upload;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;

//@CrossOrigin(origins = "", allowedHeaders = "")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/doc_mgt_service")
@Tag(name = "Document Upload Service", description = "Document Upload Service for App")

public class doc_mgt_service {
    cls_document_upload cls_document_upload_cl = new cls_document_upload();
   // cls_case_management case_mgt_cl = new cls_case_management();

 
    @Autowired
    private Ws_url_config cls_url_config;
   

      @Autowired
    private db_settings cls_db_config;

    @GetMapping("/doc_mgt_serviceindex")
    public String hello() {
        return "doc_mgt_service";
    }

    @PostMapping("/get_download_public_document_file_online")
	public ResponseEntity<byte[]> generate_rent_demand_notice_pdf(@RequestBody String json_data)
			throws Exception {
                JSONObject jsonobject = new JSONObject(json_data);
                String file_path = jsonobject.getString("file_path");
            
                String folderpath = cls_url_config.getPublic_docs_upload_location();
                // System.out.println("how" + file_path);
        File pdfFile = new File(folderpath+file_path);
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



    // @POST
	@PostMapping("/load_case_scanned_document_public")
	// //@Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public String load_case_scanned_document_public(@RequestBody String json_data) throws Exception {
		
        ArrayList<String> arr_r = new ArrayList<String>();
	
		JSONObject jsonobject = new JSONObject(json_data);
		String case_number = jsonobject.getString("case_number");
		if (case_number.toLowerCase().equals("null")) {
			try {
				// arr_r.put(obj_r);
			} catch (Exception e) {

			}
		} else {
			// String pdf_dest =
			// Ws_url_config.get_public_docs_upload_location();
			String folderpath = cls_url_config.getPublic_docs_upload_location() + case_number;
			// System.out.println("folderpath: " +folderpath);
			// List All Files In a Folder
			File subdir = new File(folderpath.toString());
			String[] children = subdir.list();
			// int i =1;

			try {
				for (String file : children) {

					// System.out.println(Arrays.toString(children));

					// if (file.isFile()) {

					JSONObject obj_r = new JSONObject();

                    System.out.println(file);
					obj_r.put("document_name", file);
					obj_r.put("document_type", file.substring(file.lastIndexOf(".") + 1));
					obj_r.put("document_extention", file.substring(file.lastIndexOf(".")));
					//obj_r.put("document_file", folderpath.toString() + "/" + file);
                    obj_r.put("document_file", case_number + File.separator + file);
					obj_r.put("uploaded_by", "");
					obj_r.put("uploaded_date", "");

					arr_r.add(obj_r.toString());

				
				}
			} catch (Exception e) {

			}

		}
		// System.out.println(arr_r.toString());
		return arr_r.toString();
	}




    @PostMapping("/get_all_folders")
    @Operation(summary = "select_get_all_folders", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Verify User", required = true, content = @Content(mediaType = "application/json", schema = @Schema(type = "string", description = "the Sub", accessMode = Schema.AccessMode.READ_ONLY, example = "Schema example"), examples = {
            @ExampleObject(name = "Sample Request", value = "{\"user\" : \"test.user\",\"pass\" : \"0001\"}", summary = "Verify User"),

    })))
    public String select_get_all_folders(@RequestBody String json_data) throws Exception {
        cls_document_upload_cl.con = cls_db_config.getCon();
        //System.out.println(json_data);
        String result = cls_document_upload_cl.select_get_all_folders();
        return result;
    }

	@PostMapping("/select_doc_files_details_by_reference_number_docmgt")
    @Operation(summary = "select_doc_files_details_by_reference_number_docmgt", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Verify User", required = true, content = @Content(mediaType = "application/json", schema = @Schema(type = "string", description = "the Sub", accessMode = Schema.AccessMode.READ_ONLY, example = "Schema example"), examples = {
            @ExampleObject(name = "Sample Request", value = "{\"user\" : \"test.user\",\"pass\" : \"0001\"}", summary = "Verify User"),

    })))
    public String select_doc_files_details_by_reference_number_docmgt(@RequestBody String json_data) throws Exception {
        String result =null;
        JSONObject jsonor= new JSONObject(json_data);
        cls_document_upload_cl.con = cls_db_config.getCon();
        result  = cls_document_upload_cl.select_doc_files_details_by_reference_number_docmgt(json_data);
        return result;
    }

}
