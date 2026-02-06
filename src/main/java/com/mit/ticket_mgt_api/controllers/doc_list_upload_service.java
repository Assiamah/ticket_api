package com.mit.ticket_mgt_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mit.ticket_mgt_api.conn_class.Ws_url_config;
import com.mit.ticket_mgt_api.conn_class.db_settings;
import com.mit.ticket_mgt_api.models.auth_service_model;
import com.mit.ticket_mgt_api.models.document_upload.cls_document_upload;
import com.mit.ticket_mgt_api.models.report_class.Cls_reports;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.tags.Tag;

//Functions of this web service

//@PostMapping("/report_service")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/doc_list_upload_service")
@Tag(name = "DOC Service", description = "Authentication Service for App")

public class doc_list_upload_service {

 cls_document_upload cls_document_upload_cl = new cls_document_upload();

	@Autowired
	private db_settings cls_db_config;


	@Autowired
    private Ws_url_config cls_url_config;
	// @POST
	@PostMapping("/upload_public_docs")
	public String report_on_the_cases(@RequestBody String job_number) throws Exception {
		
		String folderpath = cls_url_config.getPublic_docs_upload_location();
        Path startPath = Paths.get(folderpath);
        List<FileInfo> fileList = new ArrayList<>();

        // Traverse files and directories
        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                try {
                    // Get file details
                    String fileName = file.getFileName().toString();
                    String fileType = Files.probeContentType(file); // Get file type (MIME)
                    long fileSize = attrs.size(); // File size in bytes
                    String fileExtension = getFileExtension(fileName); // Get file extension
                    String fullPath = file.toAbsolutePath().toString(); // Full file path
                    String caseFolder = file.getParent().getFileName().toString(); // Get folder name
        
                    int lastIndexOfDot = fileName.lastIndexOf(".");
                    String fileNameWithoutExtension = (lastIndexOfDot == -1) ? fileName : fileName.substring(0, lastIndexOfDot);
        
                    // Prepare JSON object for the upload
                    JSONObject obj = new JSONObject();
                    obj.put("organisation", "landcom");
                    obj.put("document_type", "public_docs");
                    obj.put("reference_number", caseFolder); // Use the folder name as the reference
                    obj.put("doc_name", fileNameWithoutExtension);
                    obj.put("doc_description", fileNameWithoutExtension);
                    obj.put("doc_path", folderpath + caseFolder + File.separator); // Full file path
                    obj.put("doc_path_upload", fullPath); // Full file path
                    obj.put("doc_category", "public_docs");
                    obj.put("doc_extension", fileExtension); // File extension
                    obj.put("doc_app_uploaded", "elis");
                    obj.put("doc_uploaded_by", "Initial Upload"); // Replace with actual uploader
                    obj.put("doc_uploaded_by_id", "Initial Upload"); // Replace with actual uploader ID


//long fileSizeBytes = Files.size(fullPath);  
 File file_p = new File(fullPath); 

long fileSizeBytes = file_p.length();// ← this is the size in bytes
    
    // Optional: human-readable format (e.g. KB, MB)
    String humanSize = formatFileSize(fileSizeBytes);

   // System.out.println("File: " + filename_doc + " → Size: " + fileSizeBytes + " bytes (" + humanSize + ")");

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
        
                    // Log the result (Optional)
                    // System.out.println("File Name: " + fileName);
                    // System.out.println("Upload Message: " + uploadMsg);
                    // System.out.println("---------------------------------------");
        
                } catch (IOException | JSONException e) {
                    // Catch any error and log it, but continue processing
                    System.err.println("Error processing file: " + file.toString() + " - " + e.getMessage());
                }
                return FileVisitResult.CONTINUE; // Continue with the next file, even if there is an error
            }
        
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                try {
                    // Log the directory being entered (Optional)
                    System.out.println("Entering Directory: " + dir.toString());
                } catch (Exception e) {
                    System.err.println("Error accessing directory: " + dir.toString() + " - " + e.getMessage());
                }
                return FileVisitResult.CONTINUE; // Continue even if there is an error with a directory
            }
        
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                // Handle file access failures
                System.err.println("Failed to access file: " + file.toString() + " - " + exc.getMessage());
                return FileVisitResult.CONTINUE; // Skip this file and continue to the next
            }
        });
       // return fileList; // Return file list as JSON
		return "Successfull done";
	}

	@PostMapping("/upload_public_case_docs")
	public String report_on_the_cases_stamping(@RequestBody String job_number) throws Exception {
		
	
		String folderpath = cls_url_config.getCase_upload_location();
        Path startPath = Paths.get(folderpath);
        List<FileInfo> fileList = new ArrayList<>();

        // Traverse files and directories
        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                try {
                    // Get file details
                    String fileName = file.getFileName().toString();
                    String fileType = Files.probeContentType(file); // Get file type (MIME)
                    long fileSize = attrs.size(); // File size in bytes
                    String fileExtension = getFileExtension(fileName); // Get file extension
                    String fullPath = file.toAbsolutePath().toString(); // Full file path
                    String caseFolder = file.getParent().getFileName().toString(); // Get folder name

                   
                    int lastIndexOfDot = fileName.lastIndexOf(".");

                    String extension = fileName.substring(fileName.lastIndexOf("."));
                    String fileNameWithoutExtension = (lastIndexOfDot == -1) ? fileName : fileName.substring(0, lastIndexOfDot);
        
                    // Prepare JSON object for the upload
                    JSONObject obj = new JSONObject();
                    obj.put("organisation", "landcom");
                    obj.put("document_type", "case_docs");
                    obj.put("reference_number", caseFolder); // Use the folder name as the reference
                    obj.put("doc_name", fileNameWithoutExtension);
                    obj.put("doc_description", fileNameWithoutExtension);
					obj.put("doc_path", folderpath+ caseFolder+File.separator); // Full file path
					obj.put("doc_path_upload", fullPath); // Full file path
                    obj.put("doc_category", "case_docs");
                    obj.put("doc_extension", fileExtension); // File extension
                    obj.put("doc_app_uploaded", "elis");
                    obj.put("doc_uploaded_by", "Initial Upload"); // Replace with actual uploader
                    obj.put("doc_uploaded_by_id", "Initial Upload"); // Replace with actual uploader ID


//long fileSizeBytes = Files.size(fullPath);  
 File file_p = new File(fullPath); 

long fileSizeBytes = file_p.length();// ← this is the size in bytes
    
    // Optional: human-readable format (e.g. KB, MB)
    String humanSize = formatFileSize(fileSizeBytes);

   // System.out.println("File: " + filename_doc + " → Size: " + fileSizeBytes + " bytes (" + humanSize + ")");

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

                    // Log the result
                    // System.out.println("File Name: " + fileName);
                    // System.out.println("File Extension: " + fileExtension);
                    // System.out.println("File Type: " + (fileType != null ? fileType : "Unknown"));
                    // System.out.println("File Size: " + fileSize + " bytes");
                    // System.out.println("Full Path: " + fullPath);
                    // System.out.println("Case Folder: " + caseFolder);
                    // System.out.println("Upload Message: " + uploadMsg);
                    // System.out.println("---------------------------------------");

                } catch (IOException | JSONException e) {
                    System.err.println("Error processing file: " + e.getMessage());
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                // If you want to handle directories, you can do something here, e.g., logging directory details
                System.out.println("Entering Directory: " + dir.toString());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                System.err.println("Failed to access file: " + file + " - " + exc.getMessage());
                return FileVisitResult.CONTINUE;
            }
        });
       // return fileList; // Return file list as JSON
		return "Successfull done";
	}


	@PostMapping("/upload_cica_docs")
	public String upload_cica_docs(@RequestBody String job_number) throws Exception {
		
	
		String folderpath = cls_url_config.getCica_reply_document();
        Path startPath = Paths.get(folderpath);
        List<FileInfo> fileList = new ArrayList<>();

        // Traverse files and directories
        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                try {
                    // Get file details
                    String fileName = file.getFileName().toString();
                    String fileType = Files.probeContentType(file); // Get file type (MIME)
                    long fileSize = attrs.size(); // File size in bytes
                    String fileExtension = getFileExtension(fileName); // Get file extension
                    String fullPath = file.toAbsolutePath().toString(); // Full file path
                    String caseFolder = file.getParent().getFileName().toString(); // Get folder name

                    // Prepare JSON object for the upload
                    JSONObject obj = new JSONObject();
                    obj.put("organisation", "landcom");
                    obj.put("document_type", "cica_docs");
                    obj.put("reference_number", caseFolder); // Use the folder name as the reference
                    obj.put("doc_name", fileName);
                    obj.put("doc_description", "Document description here");
					obj.put("doc_path", folderpath+ File.separator + caseFolder+File.separator); // Full file path
					obj.put("doc_path_upload", fullPath); // Full file path
                    obj.put("doc_category", "cica_docs");
                    obj.put("doc_extension", fileExtension); // File extension
                    obj.put("doc_app_uploaded", "elis");
                    obj.put("doc_uploaded_by", "Initial Upload"); // Replace with actual uploader
                    obj.put("doc_uploaded_by_id", "Initial Upload"); // Replace with actual uploader ID

                    // Upload document to the database using your custom class and methods
                    cls_document_upload_cl.con = cls_db_config.getCon();
                 String uploadMsg = cls_document_upload_cl.select_upload_a_new_file_initial_upload(obj.toString());

                    // Log the result
                    // System.out.println("File Name: " + fileName);
                    // System.out.println("File Extension: " + fileExtension);
                    // System.out.println("File Type: " + (fileType != null ? fileType : "Unknown"));
                    // System.out.println("File Size: " + fileSize + " bytes");
                    // System.out.println("Full Path: " + fullPath);
                    // System.out.println("Case Folder: " + caseFolder);
                    // System.out.println("Upload Message: " + uploadMsg);
                    // System.out.println("---------------------------------------");

                } catch (IOException | JSONException e) {
                    System.err.println("Error processing file: " + e.getMessage());
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                // If you want to handle directories, you can do something here, e.g., logging directory details
                System.out.println("Entering Directory: " + dir.toString());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                System.err.println("Failed to access file: " + file + " - " + exc.getMessage());
                return FileVisitResult.CONTINUE;
            }
        });
       // return fileList; // Return file list as JSON
		return "Successfull done";
	}


	@PostMapping("/upload_legal_docs")
	public String upload_legal_docs(@RequestBody String job_number) throws Exception {
		
	
		String folderpath = cls_url_config.getLegal_document_location();
        Path startPath = Paths.get(folderpath);
        List<FileInfo> fileList = new ArrayList<>();

        // Traverse files and directories
        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                try {
                    // Get file details
                    String fileName = file.getFileName().toString();
                    String fileType = Files.probeContentType(file); // Get file type (MIME)
                    long fileSize = attrs.size(); // File size in bytes
                    String fileExtension = getFileExtension(fileName); // Get file extension
                    String fullPath = file.toAbsolutePath().toString(); // Full file path
                    String caseFolder = file.getParent().getFileName().toString(); // Get folder name

                    // Prepare JSON object for the upload
                    JSONObject obj = new JSONObject();
                    obj.put("organisation", "landcom");
                    obj.put("document_type", "public_docs");
                    obj.put("reference_number", caseFolder); // Use the folder name as the reference
                    obj.put("doc_name", fileName);
                    obj.put("doc_description", "Document description here");
					obj.put("doc_path", folderpath+ File.separator + caseFolder+File.separator); // Full file path
					obj.put("doc_path_upload", fullPath); // Full file path
                    obj.put("doc_category", "legal_docs");
                    obj.put("doc_extension", fileExtension); // File extension
                    obj.put("doc_app_uploaded", "elis");
                    obj.put("doc_uploaded_by", "Initial Upload"); // Replace with actual uploader
                    obj.put("doc_uploaded_by_id", "Initial Upload"); // Replace with actual uploader ID

                    // Upload document to the database using your custom class and methods
                    cls_document_upload_cl.con = cls_db_config.getCon();
                 String uploadMsg = cls_document_upload_cl.select_upload_a_new_file_initial_upload(obj.toString());

                    // Log the result
                    // System.out.println("File Name: " + fileName);
                    // System.out.println("File Extension: " + fileExtension);
                    // System.out.println("File Type: " + (fileType != null ? fileType : "Unknown"));
                    // System.out.println("File Size: " + fileSize + " bytes");
                    // System.out.println("Full Path: " + fullPath);
                    // System.out.println("Case Folder: " + caseFolder);
                    // System.out.println("Upload Message: " + uploadMsg);
                    // System.out.println("---------------------------------------");

                } catch (IOException | JSONException e) {
                    System.err.println("Error processing file: " + e.getMessage());
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                // If you want to handle directories, you can do something here, e.g., logging directory details
                System.out.println("Entering Directory: " + dir.toString());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                System.err.println("Failed to access file: " + file + " - " + exc.getMessage());
                return FileVisitResult.CONTINUE;
            }
        });
       // return fileList; // Return file list as JSON
		return "Successfull done";
	}


	 // Method to print file details
    private void printFileDetails(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.getFileName().toString();
        String fileType = Files.probeContentType(file); // Get file type (MIME)
        long fileSize = attrs.size(); // File size in bytes
        String fileExtension = getFileExtension(fileName); // Get file extension
        String fullPath = file.toAbsolutePath().toString(); // Full file path

        // Print the details
        System.out.println("File Name: " + fileName);
        System.out.println("File Extension: " + fileExtension);
        System.out.println("File Type: " + (fileType != null ? fileType : "Unknown"));
        System.out.println("File Size: " + fileSize + " bytes");
        System.out.println("Full Path: " + fullPath);
        System.out.println("---------------------------------------");
    }

     // Helper method to get file or directory information
	 private FileInfo getFileInfo(Path path, BasicFileAttributes attrs, boolean isDirectory) throws IOException {
        String fileName = path.getFileName().toString();
        String fileType = isDirectory ? "Directory" : Files.probeContentType(path); // Get file type or mark as "Directory"
        long fileSize = isDirectory ? 0 : attrs.size(); // If it's a directory, size is 0
        String fileExtension = isDirectory ? "N/A" : getFileExtension(fileName); // Directories don't have extensions
        String fullPath = path.toAbsolutePath().toString(); // Full path of the file or directory

        // Return file or directory information encapsulated in FileInfo object
        return new FileInfo(fileName, fileExtension, fileType, fileSize, fullPath, isDirectory);
    }

    // Helper method to extract file extension
    private String getFileExtension(String fileName) {
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1))
                .orElse("No Extension");
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



// Model class to represent file information, with an additional flag for directories
class FileInfo {
    private String fileName;
    private String fileExtension;
    private String fileType;
    private long fileSize;
    private String fullPath;
    private boolean isDirectory;

    public FileInfo(String fileName, String fileExtension, String fileType, long fileSize, String fullPath, boolean isDirectory) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.fullPath = fullPath;
        this.isDirectory = isDirectory;
    }

    // Getters
    public String getFileName() {
        return fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public String getFileType() {
        return fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFullPath() {
        return fullPath;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

     

}