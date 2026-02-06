package com.mit.ticket_mgt_api.models.pdfgeneration;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CSAU_billgeneration {
	
	public Connection con = null;

	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	Font font = new Font(FontFamily.TIMES_ROMAN);
	Font font14pt = new Font(FontFamily.TIMES_ROMAN, 14);
	Font font12pt = new Font(FontFamily.TIMES_ROMAN, 12);
	Font font10pt = new Font(FontFamily.TIMES_ROMAN, 10);

	/**
	 * @param args
	 */
	public static Document createPDF(String file) {

		Document document = null;

		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			addMetaData(document);

			addTitlePage(document);

			createTable(document);

			document.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;

	}

	private static void addMetaData(Document document) {
		document.addTitle("Generate PDF report");
		document.addSubject("Generate PDF report");
		document.addAuthor("Java Honk");
		document.addCreator("Java Honk");
	}

	private static void addTitlePage(Document document) throws DocumentException {

		Paragraph preface = new Paragraph();
		creteEmptyLine(preface, 1);
		preface.add(new Paragraph("PDF Report", TIME_ROMAN));

		creteEmptyLine(preface, 1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		preface.add(new Paragraph("Report created on " + simpleDateFormat.format(new Date()), TIME_ROMAN_SMALL));
		document.add(preface);

	}

	private static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private static void createTable(Document document) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		creteEmptyLine(paragraph, 2);
		document.add(paragraph);
		PdfPTable table = new PdfPTable(3);

		PdfPCell c1 = new PdfPCell(new Phrase("First Name"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Last Name"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Test"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		for (int i = 0; i < 5; i++) {
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell("Java");
			table.addCell("Honk");
			table.addCell("Success");
		}

		document.add(table);
	}

	public String createregionalnumberbill(String filename) throws DocumentException, IOException {

		try {

			OutputStream file = new FileOutputStream(new File(filename));
			// OutputStream file = new FileOutputStream(new
			// File("D://timer_new_new.pdf"));
			// Document document = new Document();
			Document document = new Document(PageSize.A5, 50, 50, 50, 50);

			// Read more:
			// http://mrbool.com/how-to-create-and-export-pdf-files-in-java/27343#ixzz596YBPXaJ
			// Pdf writer = PdfWriter.getInstance(file, new
			// FileOutput("C:\\Test.pdf"));

			PdfWriter.getInstance(document, file);

			// PdfWriter.getInstance(doc, new FileOutputStream(filename));

			// Inserting Table in PDF
			PdfPTable table = new PdfPTable(3);

			PdfPCell cell = new PdfPCell(new Paragraph("Java4s.com"));

			cell.setColspan(3);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPadding(10.0f);
			cell.setBackgroundColor(new BaseColor(140, 221, 8));

			table.addCell(cell);

			table.addCell("Name");
			table.addCell("Address");
			table.addCell("Country");
			table.addCell("Java4s");
			table.addCell("NC");
			table.addCell("United States");
			table.setSpacingBefore(30.0f); // Space Before table starts, like
											// margin-top in CSS
			table.setSpacingAfter(30.0f); // Space After table starts, like
											// margin-Bottom in CSS

			// Inserting List in PDF
			List list = new List(true, 30);
			list.add(new ListItem("Java4s"));
			list.add(new ListItem("Php4s"));
			list.add(new ListItem("Some Thing..."));

			// Text formating in PDF
			Chunk chunk = new Chunk("Welecome To Java4s Programming Blog...");
			chunk.setUnderline(+1f, -2f);// 1st co-ordinate is for line
											// width,2nd is space between
			Chunk chunk1 = new Chunk("Php4s.com");
			chunk1.setUnderline(+4f, -8f);
			chunk1.setBackground(new BaseColor(17, 46, 193));

			// Now Insert Every Thing Into PDF Document
			document.open();// PDF document opened........
			document.add(Chunk.NEWLINE); // Something like in HTML :-)
			document.add(new Paragraph("Dear Java4s.com"));
			document.add(new Paragraph("Document Generated On - "));
			document.add(table);
			document.add(list); // In the new page we are going to add list
			document.close();

			file.close();

			// System.out.println("Pdf created successfully..");

			/*
			 * response.setContentType("application/pdf");
			 * response.setHeader("Cache-Control", "no-cache");
			 * response.setHeader("Cache-Control", "max-age=0");
			 * response.setHeader("Content-disposition", "attachment; " +
			 * "filename=new.pdf");
			 */

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "sucessfully executed";
	}

	// private static final String FILE_PATH = "C:\\tmp\\testry.pdf";
	// public static final String IMG = ws_url_config.get_softfile_location() +
	// "NewLogo.jpg";
	/*
	 * public String send_sms(){ String WebResponseString = "";
	 * 
	 * System.Net.ServicePointManager.Expect100Continue = false; String url =
	 * "http://txtconnect.co/api/send/"; System.Net.WebClient client = new
	 * System.Net.WebClient();
	 * System.Collections.Specialized.NameValueCollection postData = new
	 * System.Collections.Specialized.NameValueCollection();
	 * postData.Add("token", "619f72f9599e9f4a5fdbf73d4a16067d65e770a2");
	 * postData.Add("from", CRSSenderName); postData.Add("to", str_recipient);
	 * postData.Add("msg", str_Message); byte[] responseBytes =
	 * client.UploadValues(url, postData); string response =
	 * System.Text.Encoding.ASCII.GetString(responseBytes); //
	 * response.Write(response) //MessageBox.Show(response, "Send SMS",
	 * MessageBoxButtons.OK, MessageBoxIcon.Information);
	 * 
	 * }
	 * 
	 */

	public String Helliosms() {
		try {
			// Construct data
			String url = "http://txtconnect.co/api/send/";
			String token = "token=" + "619f72f9599e9f4a5fdbf73d4a16067d65e770a2";
			String from = "&from=" + "Landscom";
			String to = "&to=" + "233242012137";
			String msg = "&msg=" + " This is your message Eddy's Pizza";

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			String data = token + from + to + msg;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();

			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS " + e);
			return "Error " + e;
		}
	}

	public String create_service_plan_approval_hard_copy_submission(String output_file)
			throws Exception {

		String client_name = "";
		String bd_job_number = "";
		String bd_business_process_id = "";
		String bd_business_process_name = "";
		String bd_bill_date = "";
		String bd_bill_amount = "";
		String bd_business_process_sub_id = "";
		String bd_business_process_sub_name = "";

		String bill_number = "";
		String licensed_surveyor_number = "";
		String licensed_surveyor_name = "";
		String bill_description = "";

		OutputStream file = new FileOutputStream(new File(output_file));
		// file.;
		try {

			// JSONObject jsonobject_data = new JSONObject(ws_bill_details_db);
			// String ws_bill_number = jsonobject_data.getString("data");

			// // JSONObject jsonobj = new JSONObject(ws_bill_details_db);
			// // String bill_details = (String)jsonobj.getString("data");
			// JSONObject jsonobj = new JSONObject(ws_bill_number);
			// // String bill_details = (String)jsonobj.getString("data");

			// // JSONArray jArr = new JSONArray(ws_bill_details_db);
			// // for (int i=0; i < jArr.length(); i++) {
			// // JSONObject obj = jArr.getJSONObject(i);

			// client_name = (String) jsonobj.getString("ar_name");
			// bd_job_number = (String) jsonobj.getString("job_number");
			// // bd_business_process_id=
			// // (String)obj.getString("business_process_id");
			// // bill_number= (String)jsonobj.getString("bill_number");
			// // bd_bill_date= (String)obj.getString("bill_date");
			// // bd_bill_amount= (String)jsonobj.getString("bill_amount");
			// // bd_business_process_sub_id=
			// // (String)obj.getString("business_process_sub_id");
			// // bd_business_process_sub_name=
			// // (String)obj.getString("business_process_sub_name");

			// licensed_surveyor_number = (String) jsonobj.getString("licensed_no");
			// licensed_surveyor_name = (String) jsonobj.getString("ar_name");
			// bill_description = (String) jsonobj.getString("business_process_sub_name");

			// // }

			Document document = new Document(PageSize.A5, 25, 25, 25, 25);
			PdfWriter writer = PdfWriter.getInstance(document, file);
			document.open();// PDF document opened........
			PdfContentByte cb = writer.getDirectContent();

			// Image image = Image.getInstance(software_file_location + "NewLogo.jpg");
			// // imgPDF2.ScaleToFit(100.0F, 70.0F)
			// image.scaleToFit(120.0F, 100.0F);
			// image.setAbsolutePosition(170, 485);
			// document.add(image);

			BaseFont bfaddress = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			PdfContentByte cbaddress = writer.getDirectContent();
			cbaddress.beginText();
			cbaddress.setFontAndSize(bfaddress, 7);

			cbaddress.setTextMatrix(310, 560);
			cbaddress.showText("cls_general_query.comp_address");
			cbaddress.setTextMatrix(310, 550);
			cbaddress.showText("cls_general_query.city");
			cbaddress.setTextMatrix(310, 540);
			cbaddress.showText(("Tel: " + "cls_general_query.telephone"));
			cbaddress.setTextMatrix(310, 530);
			cbaddress.showText(("Fax: " + "cls_general_query.fax_number"));
			cbaddress.setTextMatrix(310, 520);
			cbaddress.showText(("Email: " + "cls_general_query.email"));
			cbaddress.setTextMatrix(310, 510);
			cbaddress.showText("Web: www.lc.gov.gh");

			cbaddress.endText();

			PdfContentByte canvas = writer.getDirectContent();
			canvas.moveTo(20, 480);
			canvas.lineTo(400, 480);
			canvas.closePathStroke();

			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));

			// document.add(new Phrase(Chunk.NEWLINE));
			// document.add(new Phrase(Chunk.NEWLINE));

			Paragraph p_1 = new Paragraph("LANDS COMMISSION", new Font(FontFamily.TIMES_ROMAN, 14));
			p_1.setAlignment(Element.ALIGN_CENTER);
			document.add(p_1);

			Paragraph p_2 = new Paragraph("SURVEY AND MAPPING DIVISION", new Font(FontFamily.TIMES_ROMAN, 10));
			p_2.setAlignment(Element.ALIGN_CENTER);
			document.add(p_2);

			Paragraph p_3 = new Paragraph("PLAN APPROVAL HARD COPY SUBMISSION SLIP",
					new Font(FontFamily.TIMES_ROMAN, 10));
			p_3.setAlignment(Element.ALIGN_CENTER);
			document.add(p_3);

			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));

			Date now = new Date();
			Paragraph p4 = new Paragraph("Date: " + now.toGMTString(), new Font(FontFamily.TIMES_ROMAN, 10));
			p4.setAlignment(Element.ALIGN_RIGHT);
			document.add(p4);
			/*
			 * Paragraph p5 = new Paragraph("Bill No: " + bill_number, new
			 * Font(FontFamily.TIMES_ROMAN, 10));
			 * p5.setAlignment(Element.ALIGN_LEFT); document.add(p5);
			 */

			Paragraph p6 = new Paragraph("Surveyor's Name: " + licensed_surveyor_name,
					new Font(FontFamily.TIMES_ROMAN, 10));
			p6.setAlignment(Element.ALIGN_LEFT);
			document.add(p6);

			Paragraph p7 = new Paragraph("Surveyor's No: " + licensed_surveyor_number,
					new Font(FontFamily.TIMES_ROMAN, 10));
			p7.setAlignment(Element.ALIGN_LEFT);
			document.add(p7);

			Paragraph p11 = new Paragraph("Surveyor's Client Name: " + client_name,
					new Font(FontFamily.TIMES_ROMAN, 10));
			p11.setAlignment(Element.ALIGN_LEFT);
			document.add(p11);

			Paragraph p8 = new Paragraph("Service Type: " + bill_description, new Font(FontFamily.TIMES_ROMAN, 10));
			p8.setAlignment(Element.ALIGN_LEFT);
			document.add(p8);

			Paragraph p9 = new Paragraph("Job Number: " + bd_job_number, new Font(FontFamily.TIMES_ROMAN, 10));
			p9.setAlignment(Element.ALIGN_LEFT);
			document.add(p9);

			/*
			 * //Inserting Table in PDF PdfPTable table = new PdfPTable(3);
			 * table.setWidthPercentage(100); //Left aLign
			 * table.setTotalWidth((float) 300.0);;
			 * table.setHorizontalAlignment(0); table.setSpacingAfter(10);
			 * table.setTotalWidth(new float[]{ 35, 160, 100 });
			 * 
			 * PdfPCell cell = new PdfPCell(new Paragraph("Details of Fees"));
			 * 
			 * cell.setColspan(3);
			 * cell.setHorizontalAlignment(Element.ALIGN_CENTER); //
			 * cell.setPadding(10.0f); cell.setBackgroundColor(new
			 * BaseColor(140, 221, 8));
			 * 
			 * table.addCell(cell);
			 * 
			 * table.addCell(new Phrase("S/N", new Font(FontFamily.TIMES_ROMAN,
			 * 10))); table.addCell(new Phrase("Description", new
			 * Font(FontFamily.TIMES_ROMAN, 10))); table.addCell(new
			 * Phrase("Amount (GHS)", new Font(FontFamily.TIMES_ROMAN, 10)));
			 * 
			 * Integer NumberCount = 1;
			 * 
			 * table.addCell(new Phrase(NumberCount.toString(), new
			 * Font(FontFamily.TIMES_ROMAN, 10))); table.addCell(new
			 * Phrase(bill_description, new Font(FontFamily.TIMES_ROMAN, 10)));
			 * table.addCell(new Phrase(bd_bill_amount, new
			 * Font(FontFamily.TIMES_ROMAN, 10)));
			 * 
			 * table.addCell(new Phrase("", new Font(FontFamily.TIMES_ROMAN,
			 * 10))); table.addCell(new Phrase("Total Amount", new
			 * Font(FontFamily.TIMES_ROMAN, 10))); table.addCell(new
			 * Phrase(bd_bill_amount, new Font(FontFamily.TIMES_ROMAN, 10)));
			 * 
			 * 
			 * table.setSpacingBefore(5.0f); // Space Before table starts, like
			 * margin-top in CSS table.setSpacingAfter(5.0f); // Space After
			 * table starts, like margin-Bottom in CSS
			 * 
			 * 
			 * document.add(table);
			 * 
			 * Paragraph p12 = new Paragraph("Amount In Words: " +
			 * AmountInWords.convertToCurrency(bd_bill_amount), new
			 * Font(FontFamily.TIMES_ROMAN, 10));
			 * p12.setAlignment(Element.ALIGN_LEFT); document.add(p12);
			 * 
			 */

			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));

			/*
			 * Paragraph p14 = new Paragraph("Client Service Officer: " +
			 * "Assiamah John", new Font(FontFamily.TIMES_ROMAN, 10));
			 * p14.setAlignment(Element.ALIGN_LEFT); document.add(p14);
			 */

			BarcodeQRCode barcodeQRCode_sign = new BarcodeQRCode("bd_job_number", 1000, 1000, null);
			Image codeQrImage_sign = barcodeQRCode_sign.getImage();
			codeQrImage_sign.scaleAbsolute(100, 100);
			// codeQrImage_sign.setAbsolutePosition(310,380);
			document.add(codeQrImage_sign);

			/*
			 * Paragraph p15 = new Paragraph("For: Regional Lands Officer", new
			 * Font(FontFamily.TIMES_ROMAN, 10));
			 * p15.setAlignment(Element.ALIGN_LEFT); document.add(p15);
			 */

			/*
			 * Barcode128 code128 = new Barcode128();
			 * code128.setCode(bd_job_number);
			 * code128.setCodeType(Barcode128.CODE128); Image code128Image =
			 * code128.createImageWithBarcode(cb, null, null);
			 * code128Image.scaleAbsolute(100,100);
			 * code128Image.setAbsolutePosition(20,420);
			 * code128Image.scalePercent(100); document.add(code128Image);
			 */

			BarcodeQRCode barcodeQRCode = new BarcodeQRCode("bd_job_number", 1000, 1000, null);
			Image codeQrImage = barcodeQRCode.getImage();
			codeQrImage.scaleAbsolute(100, 100);
			codeQrImage.setAbsolutePosition(310, 380);
			document.add(codeQrImage);

			document.close();
			file.close();
			return "ok";
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		finally {

			// release resources, if any
			// outputStream.close();
			// response_ws.close();
			// client.close();

		}
		return "ok";

	}



	public String create_lease_document(String output_file,String ltt_template_details)
			throws Exception {

		String client_name = "";
	

		OutputStream file = new FileOutputStream(new File(output_file));
		// file.;
		try {

			

			Document document = new Document(PageSize.A5, 25, 25, 25, 25);
			PdfWriter writer = PdfWriter.getInstance(document, file);
			document.open();// PDF document opened........
			PdfContentByte cb = writer.getDirectContent();

			

			// BaseFont bfaddress = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			// PdfContentByte cbaddress = writer.getDirectContent();
			// cbaddress.beginText();
			// cbaddress.setFontAndSize(bfaddress, 7);

			// cbaddress.setTextMatrix(310, 560);
			// cbaddress.showText("cls_general_query.comp_address");
			// cbaddress.setTextMatrix(310, 550);
			// cbaddress.showText("cls_general_query.city");
			// cbaddress.setTextMatrix(310, 540);
			// cbaddress.showText(("Tel: " + "cls_general_query.telephone"));
			// cbaddress.setTextMatrix(310, 530);
			// cbaddress.showText(("Fax: " + "cls_general_query.fax_number"));
			// cbaddress.setTextMatrix(310, 520);
			// cbaddress.showText(("Email: " + "cls_general_query.email"));
			// cbaddress.setTextMatrix(310, 510);
			// cbaddress.showText("Web: www.lc.gov.gh");

			// cbaddress.endText();

			// PdfContentByte canvas = writer.getDirectContent();
			// canvas.moveTo(20, 480);
			// canvas.lineTo(400, 480);
			// canvas.closePathStroke();

			// document.add(new Phrase(Chunk.NEWLINE));
			// document.add(new Phrase(Chunk.NEWLINE));
			// document.add(new Phrase(Chunk.NEWLINE));
			// document.add(new Phrase(Chunk.NEWLINE));
			// document.add(new Phrase(Chunk.NEWLINE));
			// document.add(new Phrase(Chunk.NEWLINE));

			

			// Date now = new Date();
			// Paragraph p4 = new Paragraph("Date: " + now.toGMTString(), new Font(FontFamily.TIMES_ROMAN, 10));
			// p4.setAlignment(Element.ALIGN_RIGHT);
			// document.add(p4);
			
			
            Font small_bold = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);
			Font small = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
			
			
			Paragraph p_2 = new Paragraph(ltt_template_details, new Font(FontFamily.TIMES_ROMAN, 10));
			p_2.setSpacingAfter(72f);
			p_2.setAlignment(Element.ALIGN_JUSTIFIED);
			//Paragraph p4 = new Paragraph(ltt_template_details, new Font(FontFamily.TIMES_ROMAN, 10));
	
			document.add(p_2);
			

			
			
			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));

		
			// BarcodeQRCode barcodeQRCode_sign = new BarcodeQRCode("bd_job_number", 1000, 1000, null);
			// Image codeQrImage_sign = barcodeQRCode_sign.getImage();
			// codeQrImage_sign.scaleAbsolute(100, 100);
			// // codeQrImage_sign.setAbsolutePosition(310,380);
			// document.add(codeQrImage_sign);

			

			// BarcodeQRCode barcodeQRCode = new BarcodeQRCode("bd_job_number", 1000, 1000, null);
			// Image codeQrImage = barcodeQRCode.getImage();
			// codeQrImage.scaleAbsolute(100, 100);
			// codeQrImage.setAbsolutePosition(310, 380);
			// document.add(codeQrImage);

			document.close();
			file.close();
			return "ok";
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		finally {

			// release resources, if any
			// outputStream.close();
			// response_ws.close();
			// client.close();

		}
		return "ok";

	}



	public String create_offer_letter(String output_file)
			throws Exception {

		String client_name = "";
		String bd_job_number = "";
		String bd_business_process_id = "";
		String bd_business_process_name = "";
		String bd_bill_date = "";
		String bd_bill_amount = "";
		String bd_business_process_sub_id = "";
		String bd_business_process_sub_name = "";

		String bill_number = "";
		String licensed_surveyor_number = "";
		String licensed_surveyor_name = "";
		String bill_description = "";

		OutputStream file = new FileOutputStream(new File(output_file));
		// file.;
		try {

			// JSONObject jsonobject_data = new JSONObject(ws_bill_details_db);
			// String ws_bill_number = jsonobject_data.getString("data");

			// // JSONObject jsonobj = new JSONObject(ws_bill_details_db);
			// // String bill_details = (String)jsonobj.getString("data");
			// JSONObject jsonobj = new JSONObject(ws_bill_number);
			// // String bill_details = (String)jsonobj.getString("data");

			// // JSONArray jArr = new JSONArray(ws_bill_details_db);
			// // for (int i=0; i < jArr.length(); i++) {
			// // JSONObject obj = jArr.getJSONObject(i);

			// client_name = (String) jsonobj.getString("ar_name");
			// bd_job_number = (String) jsonobj.getString("job_number");
			// // bd_business_process_id=
			// // (String)obj.getString("business_process_id");
			// // bill_number= (String)jsonobj.getString("bill_number");
			// // bd_bill_date= (String)obj.getString("bill_date");
			// // bd_bill_amount= (String)jsonobj.getString("bill_amount");
			// // bd_business_process_sub_id=
			// // (String)obj.getString("business_process_sub_id");
			// // bd_business_process_sub_name=
			// // (String)obj.getString("business_process_sub_name");

			// licensed_surveyor_number = (String) jsonobj.getString("licensed_no");
			// licensed_surveyor_name = (String) jsonobj.getString("ar_name");
			// bill_description = (String) jsonobj.getString("business_process_sub_name");

			// // }

			Document document = new Document(PageSize.A5, 25, 25, 25, 25);
			PdfWriter writer = PdfWriter.getInstance(document, file);
			document.open();// PDF document opened........
			PdfContentByte cb = writer.getDirectContent();

			// Image image = Image.getInstance(software_file_location + "NewLogo.jpg");
			// // imgPDF2.ScaleToFit(100.0F, 70.0F)
			// image.scaleToFit(120.0F, 100.0F);
			// image.setAbsolutePosition(170, 485);
			// document.add(image);

			BaseFont bfaddress = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			PdfContentByte cbaddress = writer.getDirectContent();
			cbaddress.beginText();
			cbaddress.setFontAndSize(bfaddress, 7);

			cbaddress.setTextMatrix(310, 560);
			cbaddress.showText("cls_general_query.comp_address");
			cbaddress.setTextMatrix(310, 550);
			cbaddress.showText("cls_general_query.city");
			cbaddress.setTextMatrix(310, 540);
			cbaddress.showText(("Tel: " + "cls_general_query.telephone"));
			cbaddress.setTextMatrix(310, 530);
			cbaddress.showText(("Fax: " + "cls_general_query.fax_number"));
			cbaddress.setTextMatrix(310, 520);
			cbaddress.showText(("Email: " + "cls_general_query.email"));
			cbaddress.setTextMatrix(310, 510);
			cbaddress.showText("Web: www.lc.gov.gh");

			cbaddress.endText();

			PdfContentByte canvas = writer.getDirectContent();
			canvas.moveTo(20, 480);
			canvas.lineTo(400, 480);
			canvas.closePathStroke();

			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));

			// document.add(new Phrase(Chunk.NEWLINE));
			// document.add(new Phrase(Chunk.NEWLINE));

			Paragraph p_1 = new Paragraph("LANDS COMMISSION", new Font(FontFamily.TIMES_ROMAN, 14));
			p_1.setAlignment(Element.ALIGN_CENTER);
			document.add(p_1);

			Paragraph p_2 = new Paragraph("SURVEY AND MAPPING DIVISION", new Font(FontFamily.TIMES_ROMAN, 10));
			p_2.setAlignment(Element.ALIGN_CENTER);
			document.add(p_2);

			Paragraph p_3 = new Paragraph("PLAN APPROVAL HARD COPY SUBMISSION SLIP",
					new Font(FontFamily.TIMES_ROMAN, 10));
			p_3.setAlignment(Element.ALIGN_CENTER);
			document.add(p_3);

			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));

			Date now = new Date();
			Paragraph p4 = new Paragraph("Date: " + now.toGMTString(), new Font(FontFamily.TIMES_ROMAN, 10));
			p4.setAlignment(Element.ALIGN_RIGHT);
			document.add(p4);
			/*
			 * Paragraph p5 = new Paragraph("Bill No: " + bill_number, new
			 * Font(FontFamily.TIMES_ROMAN, 10));
			 * p5.setAlignment(Element.ALIGN_LEFT); document.add(p5);
			 */

			Paragraph p6 = new Paragraph("Surveyor's Name: " + licensed_surveyor_name,
					new Font(FontFamily.TIMES_ROMAN, 10));
			p6.setAlignment(Element.ALIGN_LEFT);
			document.add(p6);

			Paragraph p7 = new Paragraph("Surveyor's No: " + licensed_surveyor_number,
					new Font(FontFamily.TIMES_ROMAN, 10));
			p7.setAlignment(Element.ALIGN_LEFT);
			document.add(p7);

			Paragraph p11 = new Paragraph("Surveyor's Client Name: " + client_name,
					new Font(FontFamily.TIMES_ROMAN, 10));
			p11.setAlignment(Element.ALIGN_LEFT);
			document.add(p11);

			Paragraph p8 = new Paragraph("Service Type: " + bill_description, new Font(FontFamily.TIMES_ROMAN, 10));
			p8.setAlignment(Element.ALIGN_LEFT);
			document.add(p8);

			Paragraph p9 = new Paragraph("Job Number: " + bd_job_number, new Font(FontFamily.TIMES_ROMAN, 10));
			p9.setAlignment(Element.ALIGN_LEFT);
			document.add(p9);

			/*
			 * //Inserting Table in PDF PdfPTable table = new PdfPTable(3);
			 * table.setWidthPercentage(100); //Left aLign
			 * table.setTotalWidth((float) 300.0);;
			 * table.setHorizontalAlignment(0); table.setSpacingAfter(10);
			 * table.setTotalWidth(new float[]{ 35, 160, 100 });
			 * 
			 * PdfPCell cell = new PdfPCell(new Paragraph("Details of Fees"));
			 * 
			 * cell.setColspan(3);
			 * cell.setHorizontalAlignment(Element.ALIGN_CENTER); //
			 * cell.setPadding(10.0f); cell.setBackgroundColor(new
			 * BaseColor(140, 221, 8));
			 * 
			 * table.addCell(cell);
			 * 
			 * table.addCell(new Phrase("S/N", new Font(FontFamily.TIMES_ROMAN,
			 * 10))); table.addCell(new Phrase("Description", new
			 * Font(FontFamily.TIMES_ROMAN, 10))); table.addCell(new
			 * Phrase("Amount (GHS)", new Font(FontFamily.TIMES_ROMAN, 10)));
			 * 
			 * Integer NumberCount = 1;
			 * 
			 * table.addCell(new Phrase(NumberCount.toString(), new
			 * Font(FontFamily.TIMES_ROMAN, 10))); table.addCell(new
			 * Phrase(bill_description, new Font(FontFamily.TIMES_ROMAN, 10)));
			 * table.addCell(new Phrase(bd_bill_amount, new
			 * Font(FontFamily.TIMES_ROMAN, 10)));
			 * 
			 * table.addCell(new Phrase("", new Font(FontFamily.TIMES_ROMAN,
			 * 10))); table.addCell(new Phrase("Total Amount", new
			 * Font(FontFamily.TIMES_ROMAN, 10))); table.addCell(new
			 * Phrase(bd_bill_amount, new Font(FontFamily.TIMES_ROMAN, 10)));
			 * 
			 * 
			 * table.setSpacingBefore(5.0f); // Space Before table starts, like
			 * margin-top in CSS table.setSpacingAfter(5.0f); // Space After
			 * table starts, like margin-Bottom in CSS
			 * 
			 * 
			 * document.add(table);
			 * 
			 * Paragraph p12 = new Paragraph("Amount In Words: " +
			 * AmountInWords.convertToCurrency(bd_bill_amount), new
			 * Font(FontFamily.TIMES_ROMAN, 10));
			 * p12.setAlignment(Element.ALIGN_LEFT); document.add(p12);
			 * 
			 */

			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));

			/*
			 * Paragraph p14 = new Paragraph("Client Service Officer: " +
			 * "Assiamah John", new Font(FontFamily.TIMES_ROMAN, 10));
			 * p14.setAlignment(Element.ALIGN_LEFT); document.add(p14);
			 */

			BarcodeQRCode barcodeQRCode_sign = new BarcodeQRCode("bd_job_number", 1000, 1000, null);
			Image codeQrImage_sign = barcodeQRCode_sign.getImage();
			codeQrImage_sign.scaleAbsolute(100, 100);
			// codeQrImage_sign.setAbsolutePosition(310,380);
			document.add(codeQrImage_sign);

			/*
			 * Paragraph p15 = new Paragraph("For: Regional Lands Officer", new
			 * Font(FontFamily.TIMES_ROMAN, 10));
			 * p15.setAlignment(Element.ALIGN_LEFT); document.add(p15);
			 */

			/*
			 * Barcode128 code128 = new Barcode128();
			 * code128.setCode(bd_job_number);
			 * code128.setCodeType(Barcode128.CODE128); Image code128Image =
			 * code128.createImageWithBarcode(cb, null, null);
			 * code128Image.scaleAbsolute(100,100);
			 * code128Image.setAbsolutePosition(20,420);
			 * code128Image.scalePercent(100); document.add(code128Image);
			 */

			BarcodeQRCode barcodeQRCode = new BarcodeQRCode("bd_job_number", 1000, 1000, null);
			Image codeQrImage = barcodeQRCode.getImage();
			codeQrImage.scaleAbsolute(100, 100);
			codeQrImage.setAbsolutePosition(310, 380);
			document.add(codeQrImage);

			document.close();
			file.close();
			return "ok";
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		finally {

			// release resources, if any
			// outputStream.close();
			// response_ws.close();
			// client.close();

		}
		return "ok";

	}



	public String create_lc_rent_demand_notice(String software_file_location, 
			 String output_file)
			throws IOException, SQLException, JSONException {
		Font bold = new Font(Font.FontFamily.HELVETICA, 8f, Font.BOLD);
		Font normal = new Font(Font.FontFamily.HELVETICA, 8f, Font.NORMAL);

		String bd_customer_name = "";
		// String bd_job_number ="";
		String bd_business_process_id = "";
		String bd_business_process_name = "";
		String bd_land_size = "";

		String bd_business_process_sub_id = "";
		String bd_business_process_sub_name = "";

		String bd_type_of_interest = "";

		String bd_application_type = "";
		String bd_locality_of_parcel = "";

		String bd_job_number = "";
		String bd_case_number = "";
		String bd_stamp_duty_amount = "";

		String bd_rent_passing_per_app = "";

		OutputStream file = new FileOutputStream(new File(output_file));
		// file.;
		try {

			bd_job_number = "PVLMD043err";
			bd_case_number = "LHUYUIUIUI";
			bd_rent_passing_per_app = "2000";


			Document document = new Document(PageSize.A5, 25, 25, 25, 25);
			PdfWriter writer = PdfWriter.getInstance(document, file);
			document.open();// PDF document opened........
			PdfContentByte cb = writer.getDirectContent();

			Image image = Image.getInstance(software_file_location + "NewLogo.jpg");
			// imgPDF2.ScaleToFit(100.0F, 70.0F)
			image.scaleToFit(120.0F, 100.0F);
			image.setAbsolutePosition(170, 485);
			document.add(image);

			BaseFont bfaddress = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			PdfContentByte cbaddress = writer.getDirectContent();
			cbaddress.beginText();
			cbaddress.setFontAndSize(bfaddress, 7);

			cbaddress.setTextMatrix(310, 560);
			//cbaddress.showText(web_comp_address);
			cbaddress.showText("Box CT 5008");
			cbaddress.setTextMatrix(310, 550);
			//cbaddress.showText(web_city);
			cbaddress.showText("Accra-Ghana");
			cbaddress.setTextMatrix(310, 540);
			//cbaddress.showText(("Tel: " + web_telephone));
			cbaddress.showText(("Tel: " + "0302777343"));
			cbaddress.setTextMatrix(310, 530);
			//cbaddress.showText(("Fax: " + web_fax_number));
			cbaddress.showText(("Fax: " + "0302777343"));
			cbaddress.setTextMatrix(310, 520);
			//cbaddress.showText(("Email: " + web_email));
			cbaddress.showText(("Email: " + "info.lc.gov.gh"));
			cbaddress.setTextMatrix(310, 510);
			cbaddress.showText("Web: www.lc.gov.gh");

			cbaddress.endText();

			PdfContentByte canvas = writer.getDirectContent();
			canvas.moveTo(20, 480);
			canvas.lineTo(400, 480);
			canvas.closePathStroke();

			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));
			// document.add(new Phrase(Chunk.NEWLINE));

			Paragraph p_1 = new Paragraph("LANDS COMMISSION", new Font(FontFamily.TIMES_ROMAN, 14));
			p_1.setAlignment(Element.ALIGN_CENTER);
			document.add(p_1);

			Paragraph p_3 = new Paragraph("RENT DEMAND NOTICE", new Font(FontFamily.TIMES_ROMAN, 10));
			p_3.setAlignment(Element.ALIGN_CENTER);
			document.add(p_3);

			/*
			 * Barcode128 code128 = new Barcode128(); code128.setCode((String)
			 * jsonobj.getString("job_number"));
			 * code128.setCodeType(Barcode128.CODE128); Image code128Image =
			 * code128.createImageWithBarcode(cb, null, null);
			 * code128Image.scaleAbsolute(100, 100);
			 * code128Image.setAbsolutePosition(20, 420);
			 * code128Image.scalePercent(100); document.add(code128Image);
			 */
			BarcodeQRCode barcodeQRCode = new BarcodeQRCode(bd_job_number, 1000, 1000, null);
			Image codeQrImage = barcodeQRCode.getImage();
			codeQrImage.scaleAbsolute(100, 100);
			codeQrImage.setAbsolutePosition(20, 480);
			document.add(codeQrImage);

			// document.add(new Phrase(Chunk.NEWLINE));
			// document.add(new Phrase(Chunk.NEWLINE));
			document.add(new Phrase(Chunk.NEWLINE));

			Date now = new Date();
			Paragraph p4 = new Paragraph("Date: " + now.toGMTString(), new Font(FontFamily.TIMES_ROMAN, 10));
			p4.setAlignment(Element.ALIGN_RIGHT);
			document.add(p4);

			Paragraph p5 = new Paragraph(
					"TAKE NOTICE that ground rent on the under mentioned premises which has been leased to you by the Lands Commission is due for Payment."
							+ bd_customer_name,
					new Font(FontFamily.TIMES_ROMAN, 10));
			p5.setAlignment(Element.ALIGN_LEFT);
			document.add(p5);

			Paragraph p6 = new Paragraph("The details are provided below" + bd_customer_name,
					new Font(FontFamily.TIMES_ROMAN, 10));
			p6.setAlignment(Element.ALIGN_LEFT);
			document.add(p6);
			// System.out.println(bd_business_process_name);

			// document.add(new Phrase(Chunk.NEWLINE));

			// Inserting Table in PDF
			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			// Left aLign
			table.setTotalWidth((float) 300.0);
			;
			table.setHorizontalAlignment(0);
			table.setSpacingAfter(10);
			table.setTotalWidth(new float[] { 80, 50, 50, 50 });

			PdfPCell cell = new PdfPCell(new Phrase("RENT ASSESSMENT", font10pt));

			cell.setColspan(5);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			// cell.setPadding(10.0f);
			cell.setBackgroundColor(new BaseColor(140, 221, 8));

			table.addCell(cell);

			table.addCell(new PdfPCell(new Phrase("Rent Period", font10pt)));
			table.addCell(new PdfPCell(new Phrase("Rate", font10pt)));
			table.addCell(new PdfPCell(new Phrase("Interest Rate", font10pt)));
			table.addCell(new PdfPCell(new Phrase("Amount (GHS)", font10pt)));

			table.addCell(
					new PdfPCell(new Phrase("01/01/2002-31-12-2023", font10pt)));
			table.addCell(new PdfPCell(new Phrase(bd_rent_passing_per_app, font10pt)));
			table.addCell(new PdfPCell(new Phrase("N/A", font10pt)));
			table.addCell(new PdfPCell(new Phrase(bd_rent_passing_per_app, font10pt)));

			// table.addCell("Bill Item Number");

			Integer NumberCount = 1;

			/*
			 * String bill_details = (String)jsonobj.getString("bills");
			 * 
			 * JSONArray jArr = new JSONArray(bill_details); for (int i=0; i <
			 * jArr.length(); i++) { // JSONObject obj = new
			 * JSONObject(bill_details);
			 * 
			 * JSONObject obj = jArr.getJSONObject(i);
			 * System.out.println(obj.toString());
			 * 
			 * ;
			 * 
			 * table.addCell(new PdfPCell(new
			 * Phrase(NumberCount.toString(),font10pt))); table.addCell(new
			 * PdfPCell(new
			 * Phrase((String)obj.getString("bill_number"),font10pt)));
			 * table.addCell(new PdfPCell(new
			 * Phrase((String)obj.getString("customer_id"),font10pt)));
			 * table.addCell(new PdfPCell(new
			 * Phrase((String)obj.getString("bill_amount"),font10pt)));
			 * table.addCell(new PdfPCell(new
			 * Phrase((String)obj.getString("division"),font10pt)));
			 * 
			 * NumberCount++; }
			 */

			table.addCell(new PdfPCell(new Phrase("1", font10pt)));
			// table.addCell(new PdfPCell(new
			// Phrase((String)jsonobj.getString("unique"),font10pt)));

			/*
			 * if (bd_business_process_name ==
			 * "APPLICATION FOR REGIONAL NUMBER") { table.addCell(new
			 * PdfPCell(new Phrase("Regional Number Fee", font10pt))); } else if
			 * (bd_business_process_name == "APPLICATION FOR PLAN APPROVAL") {
			 * table.addCell(new PdfPCell(new Phrase("Plan Approval Fee",
			 * font10pt))); } else if (bd_business_process_name ==
			 * "APPLICATION FOR REGISTRATION") { table.addCell(new PdfPCell(new
			 * Phrase("Consolidated Regsitration Fee", font10pt))); } else if
			 * (bd_business_process_name == "APPLICATION FOR PART TRANSFER") {
			 * table.addCell(new PdfPCell(new
			 * Phrase("Consolidated Transfer Fee", font10pt))); } else {
			 * table.addCell(new PdfPCell(new Phrase("Service Fee", font10pt)));
			 * }
			 */

			// String sc = String.format("%.2f", (Double)
			// jsonobj.getDouble("total_amount"));

			// table.addCell(new PdfPCell(new Phrase(sc, font10pt)));
			// table.addCell("Consolidated Regsitration Fee");

			// table.addCell((String) jsonobj.getString("total_amount"));
			// table.addCell("LC");

			table.setSpacingBefore(5.0f); // Space Before table starts, like
											// margin-top in CSS
			// table.setSpacingAfter(5.0f); // Space After table starts, like
			// margin-Bottom in CSS

			document.add(table);

			// document.add(new Phrase(Chunk.NEWLINE));

			Paragraph p7 = new Paragraph(
					"Payment of the amount due is hereby demanded. Rent that goes into arrears attracts a compound interest. Failure to settle the amount may compel the Commission to institute legal action to recover the said amount or take steps to re-enter and hereby cancel the lease.",
					new Font(FontFamily.TIMES_ROMAN, 10));
			p7.setAlignment(Element.ALIGN_LEFT);
			document.add(p7);

			document.add(new Phrase(Chunk.NEWLINE));

			Paragraph p8 = new Paragraph(
					"You are also required to notify the Commission with details of any charges in your address (Postal or E-mail).",
					new Font(FontFamily.TIMES_ROMAN, 10));
			p8.setAlignment(Element.ALIGN_LEFT);
			document.add(p8);

			document.add(new Phrase(Chunk.NEWLINE));

			Paragraph p9 = new Paragraph("ALL MONIES SHOULD BE PAID EITHER IN CASH OR BANKERS DEAFT",
					new Font(FontFamily.TIMES_ROMAN, 10));
			p9.setAlignment(Element.ALIGN_LEFT);
			document.add(p9);

			Paragraph p10 = new Paragraph("Please contact the number below for any clarification",
					new Font(FontFamily.TIMES_ROMAN, 10));
			p10.setAlignment(Element.ALIGN_LEFT);
			document.add(p10);

			Paragraph p11 = new Paragraph("Hotlines: 0506430172", new Font(FontFamily.TIMES_ROMAN, 10));
			p11.setAlignment(Element.ALIGN_LEFT);
			document.add(p11);
			/*
			 * Paragraph p14 = new Paragraph("Assessd By: " + ws_login_user, new
			 * Font(FontFamily.TIMES_ROMAN, 10));
			 * p14.setAlignment(Element.ALIGN_LEFT); document.add(p14);
			 */

			/*
			 * BarcodeQRCode barcodeQRCode_sign = new
			 * BarcodeQRCode((String)jsonobj.getString("unique"), 1000, 1000,
			 * null); Image codeQrImage_sign = barcodeQRCode_sign.getImage();
			 * codeQrImage_sign.scaleAbsolute(100, 100); //
			 * codeQrImage_sign.setAbsolutePosition(310,380);
			 * document.add(codeQrImage_sign);
			 */

			document.add(new Phrase(Chunk.NEWLINE));

			Paragraph p15 = new Paragraph("For: Regional Lands Officer", new Font(FontFamily.TIMES_ROMAN, 10));
			p15.setAlignment(Element.ALIGN_LEFT);
			document.add(p15);

			Paragraph p16 = new Paragraph(
					"Please ignore this notice if you are not the legitimate lessee of the plot and / or if you have already settled your ground rent.",
					new Font(FontFamily.TIMES_ROMAN, 10));
			p16.setAlignment(Element.ALIGN_LEFT);
			document.add(p16);

			document.close();
			file.close();
			return "ok";
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		finally {

			// release resources, if any
			// outputStream.close();
			// response_ws.close();
			// client.close();

		}
		return "ok";

	}




	public String create_comparable_report(String db_data,String software_file_location, 
			 String output_file)
			throws IOException, SQLException, JSONException {
		Font bold = new Font(Font.FontFamily.HELVETICA, 8f, Font.BOLD);
		Font normal = new Font(Font.FontFamily.HELVETICA, 8f, Font.NORMAL);

		String bd_customer_name = "";
		// String bd_job_number ="";
	

		String bd_job_number = "";
	

		OutputStream file = new FileOutputStream(new File(output_file));
		// file.;
		try {

			bd_job_number = "PVLMD043err";
			


			Document document = new Document(PageSize.A5, 25, 25, 25, 25);
			PdfWriter writer = PdfWriter.getInstance(document, file);
			document.open();// PDF document opened........
			PdfContentByte cb = writer.getDirectContent();

			// Image image = Image.getInstance(software_file_location + "NewLogo.jpg");
			// // imgPDF2.ScaleToFit(100.0F, 70.0F)
			// image.scaleToFit(120.0F, 100.0F);
			// image.setAbsolutePosition(170, 485);
			// document.add(image);

			// BaseFont bfaddress = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			// PdfContentByte cbaddress = writer.getDirectContent();
			// cbaddress.beginText();
			// cbaddress.setFontAndSize(bfaddress, 7);

			// cbaddress.setTextMatrix(310, 560);
			// //cbaddress.showText(web_comp_address);
			// cbaddress.showText("Box CT 5008");
			// cbaddress.setTextMatrix(310, 550);
			// //cbaddress.showText(web_city);
			// cbaddress.showText("Accra-Ghana");
			// cbaddress.setTextMatrix(310, 540);
			// //cbaddress.showText(("Tel: " + web_telephone));
			// cbaddress.showText(("Tel: " + "0302777343"));
			// cbaddress.setTextMatrix(310, 530);
			// //cbaddress.showText(("Fax: " + web_fax_number));
			// cbaddress.showText(("Fax: " + "0302777343"));
			// cbaddress.setTextMatrix(310, 520);
			// //cbaddress.showText(("Email: " + web_email));
			// cbaddress.showText(("Email: " + "info.lc.gov.gh"));
			// cbaddress.setTextMatrix(310, 510);
			// cbaddress.showText("Web: www.lc.gov.gh");

			// cbaddress.endText();

			// PdfContentByte canvas = writer.getDirectContent();
			// canvas.moveTo(20, 480);
			// canvas.lineTo(400, 480);
			// canvas.closePathStroke();

			// document.add(new Phrase(Chunk.NEWLINE));
			// document.add(new Phrase(Chunk.NEWLINE));
			// document.add(new Phrase(Chunk.NEWLINE));
			// document.add(new Phrase(Chunk.NEWLINE));
			// document.add(new Phrase(Chunk.NEWLINE));
			// document.add(new Phrase(Chunk.NEWLINE));
			
			
			
			
			// document.add(new Phrase(Chunk.NEWLINE));

			// Paragraph p_1 = new Paragraph("LANDS COMMISSION", new Font(FontFamily.TIMES_ROMAN, 14));
			// p_1.setAlignment(Element.ALIGN_CENTER);
			// document.add(p_1);

			Paragraph p_3 = new Paragraph("COMPARABLE REPORT", new Font(FontFamily.TIMES_ROMAN, 10));
			p_3.setAlignment(Element.ALIGN_CENTER);
			document.add(p_3);

			

			document.add(new Phrase(Chunk.NEWLINE));

			Date now = new Date();
			Paragraph p4 = new Paragraph("Date: " + now.toGMTString(), new Font(FontFamily.TIMES_ROMAN, 10));
			p4.setAlignment(Element.ALIGN_RIGHT);
			document.add(p4);


			// Paragraph p6 = new Paragraph("The details are provided below" + bd_customer_name,
			// 		new Font(FontFamily.TIMES_ROMAN, 10));
			// p6.setAlignment(Element.ALIGN_LEFT);
			// document.add(p6);
			

			// Inserting Table in PDF
			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100);
			// Left aLign
			table.setTotalWidth((float) 300.0);
			;
			table.setHorizontalAlignment(0);
			table.setSpacingAfter(10);
			table.setTotalWidth(new float[] { 10, 50, 50, 50, 50 });

			PdfPCell cell = new PdfPCell(new Phrase("COMPARABLES", font10pt));

			cell.setColspan(5);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			// cell.setPadding(10.0f);
			cell.setBackgroundColor(new BaseColor(140, 221, 8));

			table.addCell(cell);

			table.addCell(new PdfPCell(new Phrase("S/N", font10pt)));
			table.addCell(new PdfPCell(new Phrase("GLPIN", font10pt)));
			table.addCell(new PdfPCell(new Phrase("Locality", font10pt)));
			table.addCell(new PdfPCell(new Phrase("Land Size", font10pt)));
			table.addCell(new PdfPCell(new Phrase("Geom Type", font10pt)));

			// table.addCell(
			// 		new PdfPCell(new Phrase("01/01/2002-31-12-2023", font10pt)));
			// table.addCell(new PdfPCell(new Phrase(bd_rent_passing_per_app, font10pt)));
			// table.addCell(new PdfPCell(new Phrase("N/A", font10pt)));
			// table.addCell(new PdfPCell(new Phrase(bd_rent_passing_per_app, font10pt)));

			// table.addCell("Bill Item Number");

			Integer NumberCount = 1;

			
			JSONObject obj_new = new JSONObject(db_data);
			 String comparable_details = (String)obj_new.getString("data");
			 
			 JSONArray jArr = new JSONArray(comparable_details); for (int i=0; i <
			  jArr.length(); i++) { // 
				//JSONObject obj = new JSONObject(bill_details);
			  
			  JSONObject obj = jArr.getJSONObject(i);
			  System.out.println(obj.toString());
			 
			  table.addCell(new PdfPCell(new Phrase(NumberCount.toString(),font10pt))); 
			  table.addCell(new PdfPCell(new Phrase((String)obj.getString("cm_glpin"),font10pt)));
			  table.addCell(new PdfPCell(new Phrase((String)obj.getString("cm_locality"),font10pt)));
			  table.addCell(new PdfPCell(new Phrase((String)obj.getString("cm_land_size"),font10pt)));
			  table.addCell(new PdfPCell(new Phrase((String)obj.getString("cm_geom_type"),font10pt)));
			  
			  NumberCount++; }
			

			table.addCell(new PdfPCell(new Phrase("1", font10pt)));
			// table.addCell(new PdfPCell(new
			// Phrase((String)jsonobj.getString("unique"),font10pt)));

			/*
			 * if (bd_business_process_name ==
			 * "APPLICATION FOR REGIONAL NUMBER") { table.addCell(new
			 * PdfPCell(new Phrase("Regional Number Fee", font10pt))); } else if
			 * (bd_business_process_name == "APPLICATION FOR PLAN APPROVAL") {
			 * table.addCell(new PdfPCell(new Phrase("Plan Approval Fee",
			 * font10pt))); } else if (bd_business_process_name ==
			 * "APPLICATION FOR REGISTRATION") { table.addCell(new PdfPCell(new
			 * Phrase("Consolidated Regsitration Fee", font10pt))); } else if
			 * (bd_business_process_name == "APPLICATION FOR PART TRANSFER") {
			 * table.addCell(new PdfPCell(new
			 * Phrase("Consolidated Transfer Fee", font10pt))); } else {
			 * table.addCell(new PdfPCell(new Phrase("Service Fee", font10pt)));
			 * }
			 */

			// String sc = String.format("%.2f", (Double)
			// jsonobj.getDouble("total_amount"));

			// table.addCell(new PdfPCell(new Phrase(sc, font10pt)));
			// table.addCell("Consolidated Regsitration Fee");

			// table.addCell((String) jsonobj.getString("total_amount"));
			// table.addCell("LC");

			table.setSpacingBefore(5.0f); // Space Before table starts, like
											// margin-top in CSS
			// table.setSpacingAfter(5.0f); // Space After table starts, like
			// margin-Bottom in CSS

			document.add(table);

			// document.add(new Phrase(Chunk.NEWLINE));

			// Paragraph p7 = new Paragraph(
			// 		"Payment of the amount due is hereby demanded. Rent that goes into arrears attracts a compound interest. Failure to settle the amount may compel the Commission to institute legal action to recover the said amount or take steps to re-enter and hereby cancel the lease.",
			// 		new Font(FontFamily.TIMES_ROMAN, 10));
			// p7.setAlignment(Element.ALIGN_LEFT);
			// document.add(p7);

			// document.add(new Phrase(Chunk.NEWLINE));

			// Paragraph p8 = new Paragraph(
			// 		"You are also required to notify the Commission with details of any charges in your address (Postal or E-mail).",
			// 		new Font(FontFamily.TIMES_ROMAN, 10));
			// p8.setAlignment(Element.ALIGN_LEFT);
			// document.add(p8);

			// document.add(new Phrase(Chunk.NEWLINE));

			// Paragraph p9 = new Paragraph("ALL MONIES SHOULD BE PAID EITHER IN CASH OR BANKERS DEAFT",
			// 		new Font(FontFamily.TIMES_ROMAN, 10));
			// p9.setAlignment(Element.ALIGN_LEFT);
			// document.add(p9);

			// Paragraph p10 = new Paragraph("Please contact the number below for any clarification",
			// 		new Font(FontFamily.TIMES_ROMAN, 10));
			// p10.setAlignment(Element.ALIGN_LEFT);
			// document.add(p10);

			// Paragraph p11 = new Paragraph("Hotlines: 0506430172", new Font(FontFamily.TIMES_ROMAN, 10));
			// p11.setAlignment(Element.ALIGN_LEFT);
			// document.add(p11);
			// /*
			//  * Paragraph p14 = new Paragraph("Assessd By: " + ws_login_user, new
			//  * Font(FontFamily.TIMES_ROMAN, 10));
			//  * p14.setAlignment(Element.ALIGN_LEFT); document.add(p14);
			//  */

			// /*
			//  * BarcodeQRCode barcodeQRCode_sign = new
			//  * BarcodeQRCode((String)jsonobj.getString("unique"), 1000, 1000,
			//  * null); Image codeQrImage_sign = barcodeQRCode_sign.getImage();
			//  * codeQrImage_sign.scaleAbsolute(100, 100); //
			//  * codeQrImage_sign.setAbsolutePosition(310,380);
			//  * document.add(codeQrImage_sign);
			//  */

			// document.add(new Phrase(Chunk.NEWLINE));

			// Paragraph p15 = new Paragraph("For: Regional Lands Officer", new Font(FontFamily.TIMES_ROMAN, 10));
			// p15.setAlignment(Element.ALIGN_LEFT);
			// document.add(p15);

			// Paragraph p16 = new Paragraph(
			// 		"Please ignore this notice if you are not the legitimate lessee of the plot and / or if you have already settled your ground rent.",
			// 		new Font(FontFamily.TIMES_ROMAN, 10));
			// p16.setAlignment(Element.ALIGN_LEFT);
			// document.add(p16);

			document.close();
			file.close();
			return "ok";
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		finally {

			// release resources, if any
			// outputStream.close();
			// response_ws.close();
			// client.close();

		}
		return "ok";

	}




}
