package com.mit.ticket_mgt_api.models.report_class;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class cls_createpdf {
	public String createregionalnumberbill(String filelocation, String folderpath, String foldername)
			throws DocumentException, IOException {

		try {
			// System.out.println(filelocation+foldername +".pdf");
			OutputStream file = new FileOutputStream(new File(filelocation + foldername + ".pdf"));
			// OutputStream file = new FileOutputStream(new
			// File("D://timer_new_new.pdf"));
			// Document document = new Document();
			Document document = new Document(PageSize.A4, 10, 10, 10, 10);

			// Read more:
			// http://mrbool.com/how-to-create-and-export-pdf-files-in-java/27343#ixzz596YBPXaJ
			// Pdf writer = PdfWriter.getInstance(file, new
			// FileOutput("C:\\Test.pdf"));

			PdfWriter.getInstance(document, file);

			// PdfWriter.getInstance(doc, new FileOutputStream(filename));

			/*
			 * //Inserting Table in PDF PdfPTable table = new PdfPTable(3);
			 * 
			 * PdfPCell cell = new PdfPCell(new Paragraph("Java4s.com"));
			 * 
			 * cell.setColspan(3);
			 * cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 * cell.setPadding(10.0f); cell.setBackgroundColor(new
			 * BaseColor(140, 221, 8));
			 * 
			 * table.addCell(cell);
			 * 
			 * table.addCell("Name"); table.addCell("Address");
			 * table.addCell("Country"); table.addCell("Java4s");
			 * table.addCell("NC"); table.addCell("United States");
			 * table.setSpacingBefore(30.0f); // Space Before table starts, like
			 * margin-top in CSS table.setSpacingAfter(30.0f); // Space After
			 * table starts, like margin-Bottom in CSS
			 * 
			 * //Inserting List in PDF List list = new List(true, 30);
			 * list.add(new ListItem("Java4s")); list.add(new
			 * ListItem("Php4s")); list.add(new ListItem("Some Thing..."));
			 * 
			 * //Text formating in PDF Chunk chunk = new
			 * Chunk("Welecome To Java4s Programming Blog...");
			 * chunk.setUnderline(+1f, -2f);//1st co-ordinate is for line
			 * width,2nd is space between Chunk chunk1 = new Chunk("Php4s.com");
			 * chunk1.setUnderline(+4f, -8f); chunk1.setBackground(new
			 * BaseColor(17, 46, 193));
			 */

			// Now Insert Every Thing Into PDF Document
			document.open();// PDF document opened........

			document.add(Chunk.NEWLINE); // Something like in HTML :-)

			document.add(new Paragraph("Scan Data For"));
			document.add(new Paragraph(foldername));

			// List All Files In a Folder
			File subdir = new File(folderpath.toString());
			String[] children = subdir.list();
			if (children == null) {
				// System.out.println( "Either dir does not exist or is not a
				// directory");
			} else {
				for (int j = 0; j < children.length; j++) {
					String filename_doc = children[j];
					// System.out.println(("File: " +filename_doc);
					String IMG = folderpath.toString() + "\\" + filename_doc;
					// document.add(new Paragraph(filename_doc ));

					Image image = Image.getInstance(IMG);
					// Image image = new Image(data);
					// imgPDF2.ScaleToFit(100.0F, 70.0F)
					// image.scaleToFit(120.0F, 100.0F);
					/// image.setAbsolutePosition(170, 485);
					// image.scaleToFit(PageSize.A4.getWidth(),
					// PageSize.A4.getHeight());

					/*
					 * float docW = PageSize.A4.getWidth() - 2 *
					 * PageSize.A4.getBorder(); float docH =
					 * PageSize.A4.getHeight() - 2 * PageSize.A4.getBorder(); //
					 * fit the image to the page image.scaleToFit(docW, docH);
					 */

					image.setRotationDegrees(90);

					// image.scalePercent(110 * 72 / 300);
					/*
					 * int indentation = 0; float scaler =
					 * ((document.getPageSize().getWidth() -
					 * document.leftMargin() - document.rightMargin() -
					 * indentation) / image.getWidth()) * 100;
					 * 
					 * image.scalePercent(scaler);
					 */

					/*
					 * float documentWidth = document.getPageSize().getWidth() -
					 * document.leftMargin() - document.rightMargin(); float
					 * documentHeight = document.getPageSize().getHeight() -
					 * document.topMargin() - document.bottomMargin();
					 * image.scaleToFit(documentWidth, documentHeight);
					 */

					image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());

					document.add(image);
					document.newPage();
				}
			}
			// List All Files In a Folder// End

			document.close();

			file.close();

			// System.out.println(("Pdf created successfully..");

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

	public String create_pdf_from_scanned_images_by_list_of_files(String filelocation, String folderpath,
			List<String> list_of_files, String foldername) throws DocumentException, IOException {

		try {
			// System.out.println((filelocation+foldername +".pdf");
			OutputStream file = new FileOutputStream(new File(filelocation + foldername + ".pdf"));
			Document document = new Document(PageSize.A4.rotate(), 10, 10, 10, 10);
			PdfWriter.getInstance(document, file);
			document.open();// PDF document opened........
			document.add(Chunk.NEWLINE); // Something like in HTML :-)
			document.add(new Paragraph("This is Jacket for "));
			document.add(new Paragraph(foldername));

			// List All Files In a Folder
			// File subdir = new File(folderpath.toString());
			// String[] children = subdir.list();
			int size = list_of_files.size();

			// Iterator<String> iterator = list_of_files.iterator();
			/*
			 * while(iterator.hasNext()){ String obj = iterator.next(); }
			 */

			Iterator<String> iterator = list_of_files.iterator();

			if (size <= 0) {
				// System.out.println(( "Either dir does not exist or is not a
				// directory");
			} else {
				while (iterator.hasNext()) {
					String filename_doc = iterator.next();
					;
					// System.out.println(("File: " +filename_doc);
					String IMG = filename_doc;
					// System.out.println(("correct correct: " +filename_doc);
					// String IMG = folderpath.toString()+"\\"+filename_doc;
					// document.add(new Paragraph(filename_doc ));

					Image image = Image.getInstance(IMG);
					// imgPDF2.ScaleToFit(100.0F, 70.0F)
					// image.scaleToFit(120.0F, 100.0F);
					/// image.setAbsolutePosition(170, 485);
					// image.scaleToFit(PageSize.A4.getWidth(),
					/// PageSize.A4.getHeight());

					/*
					 * float docW = PageSize.A4.getWidth() - 2 *
					 * PageSize.A4.getBorder(); float docH =
					 * PageSize.A4.getHeight() - 2 * PageSize.A4.getBorder(); //
					 * fit the image to the page image.scaleToFit(docW, docH);
					 */

					int indentation = 0;
					float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()
							- indentation) / image.getWidth()) * 100;
					image.scalePercent(scaler);

					document.add(image);
					document.newPage();
				}
			}
			// List All Files In a Folder// End

			document.close();

			file.close();

			// System.out.println(("Pdf created successfully..");

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

	public String create_pdf_from_scanned_images_by_files_in_a_folder(String filelocation, String folderpath,
			String foldername) throws DocumentException, IOException {

		try {
			// System.out.println((filelocation+foldername +".pdf");
			OutputStream file = new FileOutputStream(new File(filelocation + foldername + ".pdf"));
			Document document = new Document(PageSize.A4, 10, 10, 10, 10);
			PdfWriter.getInstance(document, file);
			document.open();// PDF document opened........
			document.add(Chunk.NEWLINE); // Something like in HTML :-)
			document.add(new Paragraph("This is Jacket for "));
			document.add(new Paragraph("Document Generated On - "));

			// List All Files In a Folder
			File subdir = new File(folderpath.toString());
			String[] children = subdir.list();
			if (children == null) {
				// System.out.println(( "Either dir does not exist or is not a
				// directory");
			} else {
				for (int j = 0; j < children.length; j++) {
					String filename_doc = children[j];
					// System.out.println(("File: " +filename_doc);
					String IMG = folderpath.toString() + "\\" + filename_doc;
					// document.add(new Paragraph(filename_doc ));

					Image image = Image.getInstance(IMG);
					// imgPDF2.ScaleToFit(100.0F, 70.0F)
					// image.scaleToFit(120.0F, 100.0F);
					/// image.setAbsolutePosition(170, 485);
					// image.scaleToFit(PageSize.A4.getWidth(),
					/// PageSize.A4.getHeight());

					/*
					 * float docW = PageSize.A4.getWidth() - 2 *
					 * PageSize.A4.getBorder(); float docH =
					 * PageSize.A4.getHeight() - 2 * PageSize.A4.getBorder(); //
					 * fit the image to the page image.scaleToFit(docW, docH);
					 */

					int indentation = 0;
					float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()
							- indentation) / image.getWidth()) * 100;

					image.scalePercent(scaler);

					document.add(image);
					document.newPage();
				}
			}
			// List All Files In a Folder// End

			document.close();

			file.close();

			// System.out.println(("Pdf created successfully..");

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

	public static final String myFilePathHeaderImage = "C:\\gelisdocs\\newLogo.jpg";
	public static final String myFilePathHeaderImage2 = "C:\\gelisdocs\\newLogo.jpg";
	private static Font fntTableFontHdr = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	private static Font fntTableFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);

	public String HTMLtoPDF_trynewformat_Register_Full_Digital()
			throws DocumentException, JSONException, FileNotFoundException {

		// myFilePathHeaderImage = Context.Server.MapPath("~/Resources/" +
		// "CoatofArmsLarge.jpg")

		// myFilePathHeaderImage2 = Context.Server.MapPath("~/Resources/" +
		// "newLogo1.jpg")
		try {
			OutputStream file;

			file = new FileOutputStream(new File("C:\\Testregister.pdf"));

			Document doc = new Document(PageSize.A4.rotate(), 40, 40, 40, 40);
			// pdfFilePath As String =
			// Context.Server.MapPath("~/TemporaryPDFFiles/" + "Batch" +
			// txt_job_number.Text + ".pdf")
			// File.Delete(pdfFilePath)
			// File.Delete("C:\\Testregister.pdf");

			PdfWriter writer = PdfWriter.getInstance(doc, file);

			doc.open();// PDF document opened........

			BarcodeQRCode barcodeQRCode = new BarcodeQRCode("fgfjhhgfjg", 1000, 1000, null);
			Image codeQrImage = barcodeQRCode.getImage();
			codeQrImage.scaleAbsolute(120.0F, 70.0F);
			codeQrImage.setAbsolutePosition(250, 740);
			doc.add(codeQrImage);

			Paragraph reportTitle = new Paragraph("LAND REGISTER", new Font(FontFamily.TIMES_ROMAN, 14));
			reportTitle.setAlignment(Element.ALIGN_CENTER);
			doc.add(reportTitle);

			// 'document.add(new Phrase(Chunk.NEWLINE));
			doc.add(new Phrase(Chunk.NEWLINE));

			// 'document.add(new Phrase(Chunk.NEWLINE));

			// '
			// 'land_certificate Details
			// Inserting Table in PDF
			PdfPTable myTable_admin = new PdfPTable(4);
			myTable_admin.setWidthPercentage(60);
			// Left aLign
			myTable_admin.setTotalWidth((float) 410.0);
			;
			myTable_admin.setHorizontalAlignment(0);
			myTable_admin.setSpacingAfter(10);
			myTable_admin.setTotalWidth(new float[] { 120, 80, 80, 80 });

			// table.addCell(new Phrase(NumberCount.toString(), new
			// Font(FontFamily.TIMES_ROMAN, 10)));
			myTable_admin.addCell(new Phrase("Administrative District", fntTableFontHdr));
			myTable_admin.addCell(new Phrase("txt_District.Text", fntTableFontHdr));
			myTable_admin.addCell(new Phrase("Vol: " + "txt_volume_number.Text", fntTableFontHdr));
			myTable_admin.addCell(new Phrase("Folio: " + "txt_folio_number.Text", fntTableFontHdr));

			PdfPCell cell;

			myTable_admin.addCell(new Phrase("Nature of Interest", new Font(FontFamily.TIMES_ROMAN, 10)));

			cell = new PdfPCell(new Phrase("Name"));
			cell.setColspan(3);
			myTable_admin.addCell(cell);

			PdfPCell CellTwoHdr_admin = new PdfPCell(new Phrase("cbo_nature_of_instrument.Text", fntTableFontHdr));
			CellTwoHdr_admin.setColspan(3);
			// CellTwoHdr_admin.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			myTable_admin.addCell(CellTwoHdr_admin);

			PdfPCell CellTreeHdr_admin = new PdfPCell(new Phrase("Date of Registration: ", fntTableFontHdr));
			myTable_admin.addCell(CellTreeHdr_admin);

			PdfPCell CellFourHdr_admin = new PdfPCell(new Phrase("dtp_date_of_registration", fntTableFontHdr));
			// dtp_date_of_registration.Value.ToString(\"dd/MM/yyyy\")
			CellFourHdr_admin.setColspan(3);
			// CellFourHdr_admin.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			myTable_admin.addCell(CellFourHdr_admin);

			PdfPCell cell_admin = new PdfPCell(new Phrase("Description of Land ", fntTableFontHdr));
			cell_admin.setColspan(4);
			// cell_admin.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell_admin.setVerticalAlignment(50);
			myTable_admin.addCell(cell_admin);

			String description = "";
			// 'description = "All that piece or parcel of land in extent " +
			// txt_extent.Text
			// 'description += " more or less being " +
			// txt_parcel_description.Text + " situate at " + txt_locality.Text
			// 'description += " in the " + "Greater Accra Region" + " of the
			// Republic of Ghana as delineated on "
			// 'description += " Registry Map No. " + txt_registry_mapref.Text +
			// " in the Land Title Registry, Victoriaborg, Accra "
			// 'description += " and being the piece or parcel of land shown and
			// edged with pink colour "
			// 'description += " on plan No. " + txt_plan_no.Text + " annexed to
			// the Land Certificate "

			description = "txt_remark_or_comment.Text";
			PdfPCell cell_admin1 = new PdfPCell(new Phrase("description", fntTableFontHdr));
			cell_admin1.setColspan(4);
			cell_admin1.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell_admin1.setVerticalAlignment(50);
			myTable_admin.addCell(cell_admin1);

			// 'myTable_admin.WriteSelectedRows(0, -1, 750, 550,
			// wri.DirectContent)
			doc.add(myTable_admin);

			// 'Valuation section
			PdfPTable myTable_valuation = new PdfPTable(3);
			myTable_valuation.setWidthPercentage(60);
			// Left aLign
			myTable_valuation.setTotalWidth((float) 410.0);
			;
			myTable_valuation.setHorizontalAlignment(0);
			myTable_valuation.setSpacingAfter(10);
			myTable_valuation.setTotalWidth(new float[] { 70, 100, 160 });

			// myTable_valuation.SetWidths(sglTblHdWidths_valuation)

			PdfPCell cell_valuation = new PdfPCell(new Phrase("Valuation", fntTableFontHdr));

			cell_valuation.setColspan(3);
			cell_valuation.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			cell_valuation.setVerticalAlignment(50);

			myTable_valuation.addCell(cell_valuation);

			PdfPCell CellOneHdr_valuation = new PdfPCell(new Phrase("Date ", fntTableFontHdr));
			myTable_valuation.addCell(CellOneHdr_valuation);
			PdfPCell CellTwoHdr_valuation = new PdfPCell(new Phrase("Amount", fntTableFontHdr));
			myTable_valuation.addCell(CellTwoHdr_valuation);
			PdfPCell CellTreeHdr_valuation = new PdfPCell(new Phrase("Remarks", fntTableFontHdr));
			myTable_valuation.addCell(CellTreeHdr_valuation);

			PdfPCell CellOne = new PdfPCell(new Phrase("dtp_date_of_valuation", fntTableFont));

			myTable_valuation.addCell(CellOne);

			PdfPCell CellTwo = new PdfPCell(new Phrase("txt_AmountInvolve.Text", fntTableFont));
			myTable_valuation.addCell(CellTwo);

			PdfPCell CellTree = new PdfPCell(new Phrase("txt_ValuationRemarks.Text", fntTableFont));
			myTable_valuation.addCell(CellTree);

			// ' myTable_valuation.WriteSelectedRows(0, -1, 750, 670,
			// wri.DirectContent)
			doc.add(myTable_valuation);

			// 'document.add(new Phrase(Chunk.NEWLINE));

			// 'Reservation

			PdfPTable myTable_reservation = new PdfPTable(2);
			myTable_reservation.setWidthPercentage(60);
			// Left aLign
			myTable_reservation.setTotalWidth((float) 410.0);
			;
			myTable_reservation.setHorizontalAlignment(0);
			myTable_reservation.setSpacingAfter(10);
			myTable_reservation.setTotalWidth(new float[] { 5, 160 });

			PdfPCell cell_reservation = new PdfPCell(new Phrase("Reservation. etc. ", fntTableFontHdr));
			// 'Dim cell As PdfPCell = new PdfPCell(new Phrase("Bill of " +
			// "lbl_Service_Name.Text"))
			cell_reservation.setColspan(2);
			cell_reservation.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			// 'cell_land_certificate.setVerticalAlignment(50);
			myTable_reservation.addCell(cell_reservation);

			String reservation = "";
			reservation = "Subject to the reservations, exceptions, restrictions, restrictive covenants and conditions";
			// 'reservation += " contained or referred to in a lease (a true
			// copy of which is annexed to the Land Certificate) made "
			// 'reservation += "between the " + txt_grantors_name.Text + " of
			// the one part and " + txt_customer_name.Text + " of the "
			// 'reservation += "other part."
			reservation = "txt_Reservations.Text";

			myTable_reservation.addCell(new Phrase("dtp_date_of_registration", fntTableFontHdr));
			myTable_reservation.addCell(new Phrase(reservation, fntTableFontHdr));

			// 'myTable_land_certificate.WriteSelectedRows(0, -1, 750, 550,
			// wri.DirectContent)
			doc.add(myTable_reservation);

			// 'land_certificate Details

			PdfPTable myTable_land_certificate = new PdfPTable(4);
			myTable_land_certificate.setWidthPercentage(60);
			// Left aLign
			myTable_land_certificate.setTotalWidth((float) 410.0);
			;
			myTable_land_certificate.setHorizontalAlignment(0);
			myTable_land_certificate.setSpacingAfter(10);
			myTable_land_certificate.setTotalWidth(new float[] { 100, 160, 100, 160 });

			PdfPCell cell_land_certificate = new PdfPCell(
					new Phrase("Land Certificate/Provisional Certificate ", fntTableFontHdr));
			// 'Dim cell As PdfPCell = new PdfPCell(new Phrase("Bill of " +
			// "lbl_Service_Name.Text"))
			cell_land_certificate.setColspan(4);
			cell_land_certificate.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cell_land_certificate.setVerticalAlignment(50);

			myTable_land_certificate.addCell(cell_land_certificate);

			PdfPCell CellOneHdr_land_certificate = new PdfPCell(new Phrase("Date of Issue", fntTableFontHdr));
			myTable_land_certificate.addCell(CellOneHdr_land_certificate);
			PdfPCell CellTwoHdr_land_certificate = new PdfPCell(new Phrase("To whom issued", fntTableFontHdr));
			myTable_land_certificate.addCell(CellTwoHdr_land_certificate);
			PdfPCell CellTreeHdr_land_certificate = new PdfPCell(new Phrase("Serial No.", fntTableFontHdr));
			myTable_land_certificate.addCell(CellTreeHdr_land_certificate);

			PdfPCell CellFourHdr_land_certificate = new PdfPCell(new Phrase("Official Notes", fntTableFontHdr));
			myTable_land_certificate.addCell(CellFourHdr_land_certificate);

			String json_data_land_certificate = "";
			JSONArray jArr_land_certificate = new JSONArray(json_data_land_certificate);
			for (int i = 0; i < jArr_land_certificate.length(); i++) {
				JSONObject obj = jArr_land_certificate.getJSONObject(i);
				// Toast.makeText(getApplicationContext(),obj.toString(),Toast.LENGTH_SHORT).show();

				PdfPCell admin_CellOne = new PdfPCell(
						new Phrase((String) obj.getString("valuation_number"), fntTableFont));

				myTable_land_certificate.addCell(admin_CellOne);

				PdfPCell admin_CellTwo = new PdfPCell(
						new Phrase((String) obj.getString("valuation_number"), fntTableFont));
				myTable_land_certificate.addCell(admin_CellTwo);

				PdfPCell admin_CellTree = new PdfPCell(
						new Phrase((String) obj.getString("valuation_number"), fntTableFont));
				myTable_land_certificate.addCell(admin_CellTree);

				PdfPCell admin_CellFour = new PdfPCell(
						new Phrase((String) obj.getString("valuation_number"), fntTableFont));
				myTable_land_certificate.addCell(admin_CellFour);
			}

			doc.add(myTable_land_certificate);
			// 'document.add(new Phrase(Chunk.NEWLINE));

			PdfPTable myTable_proprietors = new PdfPTable(10);
			myTable_proprietors.setWidthPercentage(100);
			// Left aLign
			myTable_proprietors.setTotalWidth((float) 410.0);
			;
			myTable_proprietors.setHorizontalAlignment(0);
			myTable_proprietors.setSpacingAfter(10);
			myTable_proprietors.setTotalWidth(new float[] { 35, 90, 190, 90, 90, 90, 130, 120, 130, 120 });

			PdfPCell cell_proprietors = new PdfPCell(new Phrase("Proprietorship", fntTableFontHdr));
			// 'Dim cell As PdfPCell = new PdfPCell(new Phrase("Bill of " +
			// "lbl_Service_Name.Text")) Proprietorship
			cell_proprietors.setColspan(10);

			cell_proprietors.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cell_proprietors.setVerticalAlignment(50);
			myTable_proprietors.addCell(cell_proprietors);

			PdfPCell CellOneHdr_proprietors = new PdfPCell(new Phrase("Entry No ", fntTableFontHdr));

			myTable_proprietors.addCell(CellOneHdr_proprietors);

			PdfPCell CellTwoHdr_proprietors = new PdfPCell(new Phrase("Registered No.", fntTableFontHdr));
			/// 'CellTwoHdr_proprietors.Rowspan = 2
			myTable_proprietors.addCell(CellTwoHdr_proprietors);

			PdfPCell CellTreeHdr_proprietors = new PdfPCell(
					new Phrase("Proprietors (names addresses and descriptions)", fntTableFontHdr));
			// 'CellTreeHdr_proprietors.Rowspan = 2
			myTable_proprietors.addCell(CellTreeHdr_proprietors);

			PdfPCell cell_interest = new PdfPCell(new Phrase("Instruments Relevant to the Title", fntTableFontHdr));
			// 'Dim cell As PdfPCell = new PdfPCell(new Phrase("Bill of " +
			// "lbl_Service_Name.Text"))
			cell_interest.setColspan(5);
			// 'cell_interest.s
			cell_interest.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cell_interest.setVerticalAlignment(50);
			myTable_proprietors.addCell(cell_interest);

			PdfPCell CellNineHdr_proprietors = new PdfPCell(new Phrase("Remark", fntTableFontHdr));

			myTable_proprietors.addCell(CellNineHdr_proprietors);

			PdfPCell CellTenHdr_proprietors = new PdfPCell(new Phrase("Signature of Registrar ", fntTableFontHdr));

			myTable_proprietors.addCell(CellTenHdr_proprietors);

			PdfPCell EmptyCell_proprietors = new PdfPCell(new Phrase("", fntTableFontHdr));
			EmptyCell_proprietors.setBorderWidth(0);
			myTable_proprietors.addCell(EmptyCell_proprietors);
			myTable_proprietors.addCell(EmptyCell_proprietors);
			myTable_proprietors.addCell(EmptyCell_proprietors);

			PdfPCell CellFourHdr_proprietors = new PdfPCell(new Phrase("Date of Instrument", fntTableFontHdr));
			myTable_proprietors.addCell(CellFourHdr_proprietors);

			PdfPCell CellFiveHdr_proprietors = new PdfPCell(new Phrase("Nature of Instrument ", fntTableFontHdr));
			myTable_proprietors.addCell(CellFiveHdr_proprietors);

			PdfPCell CellSixHdr_proprietors = new PdfPCell(new Phrase("Date of Registration", fntTableFontHdr));
			myTable_proprietors.addCell(CellSixHdr_proprietors);

			PdfPCell CellSevenHdr_proprietors = new PdfPCell(new Phrase("Parties", fntTableFontHdr));
			myTable_proprietors.addCell(CellSevenHdr_proprietors);

			PdfPCell CellEightHdr_proprietors = new PdfPCell(new Phrase("Price Paid", fntTableFontHdr));
			myTable_proprietors.addCell(CellEightHdr_proprietors);

			myTable_proprietors.addCell(EmptyCell_proprietors);
			myTable_proprietors.addCell(EmptyCell_proprietors);

			Integer NumberCount = 1;

			// 'Future
			// myImage2 As String = Context.Server.MapPath("~/Resources/" +
			// "sign2.png")
			// myImage As iTextSharp.text.Image =
			// iTextSharp.text.Image.GetInstance(myImage2)
			// myImage.ScaleToFit(80.0F, 40.0F)
			// memo_10 As PdfPCell = new PdfPCell(myImage)
			// myTable_proprietors.addCell(memo_10)

			// 'End If

			String json_data_proprietorship = "";

			JSONArray jArrproprietorship = new JSONArray(json_data_proprietorship);
			for (int i = 0; i < jArrproprietorship.length(); i++) {
				JSONObject obj = jArrproprietorship.getJSONObject(i);
				// Toast.makeText(getApplicationContext(),obj.toString(),Toast.LENGTH_SHORT).show();

				PdfPCell proprietorship_CellOne = new PdfPCell(new Phrase(NumberCount.toString(), fntTableFont));
				// 'CellOne.Rotation = -90
				myTable_proprietors.addCell(proprietorship_CellOne);

				PdfPCell proprietorship_CellTwo = new PdfPCell(
						new Phrase((String) obj.getString("division_number"), fntTableFont));
				myTable_proprietors.addCell(proprietorship_CellTwo);

				PdfPCell proprietorship_CellTree = new PdfPCell(
						new Phrase((String) obj.getString("division_number"), fntTableFont));
				myTable_proprietors.addCell(proprietorship_CellTree);

				// 'Dim date_of_interes String =
				PdfPCell proprietorship_CellFour = new PdfPCell(
						new Phrase((String) obj.getString("division_number"), fntTableFont));
				myTable_proprietors.addCell(proprietorship_CellFour);

				PdfPCell proprietorship_CellFive = new PdfPCell(
						new Phrase((String) obj.getString("division_number"), fntTableFont));
				myTable_proprietors.addCell(proprietorship_CellFive);

				PdfPCell memo_6 = new PdfPCell(new Phrase((String) obj.getString("division_number"), fntTableFont));
				myTable_proprietors.addCell(memo_6);

				PdfPCell memo_7 = new PdfPCell(new Phrase((String) obj.getString("division_number"), fntTableFont));
				myTable_proprietors.addCell(memo_7);

				PdfPCell memo_8 = new PdfPCell(new Phrase((String) obj.getString("division_number"), fntTableFont));
				myTable_proprietors.addCell(memo_8);

				PdfPCell memo_9 = new PdfPCell(new Phrase((String) obj.getString("division_number"), fntTableFont));
				myTable_proprietors.addCell(memo_9);
				NumberCount += 1;
			}

			doc.add(myTable_proprietors);

			doc.newPage();

			PdfPTable myTable_memorials = new PdfPTable(9);
			myTable_memorials.setWidthPercentage(100);
			// Left aLign
			myTable_memorials.setTotalWidth((float) 410.0);
			;
			myTable_memorials.setHorizontalAlignment(0);
			myTable_memorials.setSpacingAfter(10);
			myTable_memorials.setTotalWidth(new float[] { 35, 90, 80, 90, 150, 90, 130, 120, 130 });

			PdfPCell EmptyCell_memorials = new PdfPCell(new Phrase("", fntTableFontHdr));
			EmptyCell_memorials.setBorderWidth(0);

			PdfPCell cell_memorials = new PdfPCell(
					new Phrase("Leases, Charges, Encumbrances, etc. Affecting Land", fntTableFontHdr));

			cell_memorials.setColspan(7);

			cell_memorials.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cell_memorials.setVerticalAlignment(50);
			myTable_memorials.addCell(cell_memorials);

			myTable_memorials.addCell(EmptyCell_memorials);
			myTable_memorials.addCell(EmptyCell_memorials);

			PdfPCell CellOneHdr_memorials = new PdfPCell(new Phrase("Entry No ", fntTableFontHdr));

			myTable_memorials.addCell(CellOneHdr_memorials);

			PdfPCell CellTwoHdr_memorials = new PdfPCell(new Phrase("Date of Instrument.", fntTableFontHdr));

			myTable_memorials.addCell(CellTwoHdr_memorials);

			PdfPCell CellTreeHdr_memorials = new PdfPCell(new Phrase("Date of Registration", fntTableFontHdr));

			myTable_memorials.addCell(CellTreeHdr_memorials);

			PdfPCell CellNineHdr_memorials = new PdfPCell(new Phrase("Registered No.", fntTableFontHdr));

			myTable_memorials.addCell(CellNineHdr_memorials);

			PdfPCell CellTenHdr_memorials = new PdfPCell(new Phrase("Memorials", fntTableFontHdr));

			myTable_memorials.addCell(CellTenHdr_memorials);

			PdfPCell cell_folio_reference = new PdfPCell(new Phrase("Folio Reference No.", fntTableFontHdr));
			cell_folio_reference.setColspan(2);
			cell_folio_reference.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cell_folio_reference.setVerticalAlignment(50);

			myTable_memorials.addCell(cell_folio_reference);

			PdfPCell CellFourHdr_memorials = new PdfPCell(new Phrase("Remarks", fntTableFontHdr));
			myTable_memorials.addCell(CellFourHdr_memorials);

			PdfPCell CellFiveHdr_memorials = new PdfPCell(new Phrase("Signature of Registrar", fntTableFontHdr));
			myTable_memorials.addCell(CellFiveHdr_memorials);

			myTable_memorials.addCell(EmptyCell_memorials);
			myTable_memorials.addCell(EmptyCell_memorials);
			myTable_memorials.addCell(EmptyCell_memorials);
			myTable_memorials.addCell(EmptyCell_memorials);
			myTable_memorials.addCell(EmptyCell_memorials);

			PdfPCell CellSixHdr_memorials = new PdfPCell(new Phrase("Back", fntTableFontHdr));
			myTable_memorials.addCell(CellSixHdr_memorials);

			PdfPCell CellSevenHdr_memorials = new PdfPCell(new Phrase("Forward", fntTableFontHdr));
			myTable_memorials.addCell(CellSevenHdr_memorials);
			myTable_memorials.addCell(EmptyCell_memorials);
			myTable_memorials.addCell(EmptyCell_memorials);
			Integer NumberCount_memo = 1;

			String json_data_memo = "";
			JSONArray jArr_memo = new JSONArray(json_data_memo);
			for (int i = 0; i < jArr_memo.length(); i++) {
				JSONObject obj = jArr_memo.getJSONObject(i);
				// Toast.makeText(getApplicationContext(),obj.toString(),Toast.LENGTH_SHORT).show();
				PdfPCell memo_CellOne = new PdfPCell(new Phrase(NumberCount_memo.toString(), fntTableFont));

				myTable_memorials.addCell(memo_CellOne);

				PdfPCell memo_CellTwo = new PdfPCell(
						new Phrase((String) obj.getString("division_number"), fntTableFont));
				myTable_memorials.addCell(memo_CellTwo);

				PdfPCell memo_CellTree = new PdfPCell(
						new Phrase((String) obj.getString("division_number"), fntTableFont));
				myTable_memorials.addCell(memo_CellTree);

				PdfPCell memo_CellFour = new PdfPCell(
						new Phrase((String) obj.getString("division_number"), fntTableFont));
				myTable_memorials.addCell(memo_CellFour);

				PdfPCell memo_CellFive = new PdfPCell(
						new Phrase((String) obj.getString("division_number"), fntTableFont));
				myTable_memorials.addCell(memo_CellFive);

				PdfPCell memo_6 = new PdfPCell(new Phrase((String) obj.getString("division_number"), fntTableFont));
				myTable_memorials.addCell(memo_6);

				PdfPCell memo_7 = new PdfPCell(new Phrase((String) obj.getString("division_number"), fntTableFont));
				myTable_memorials.addCell(memo_7);

				PdfPCell memo_8 = new PdfPCell(new Phrase((String) obj.getString("division_number"), fntTableFont));
				myTable_memorials.addCell(memo_8);

				PdfPCell memo_9 = new PdfPCell(new Phrase(" ", fntTableFont));
				myTable_memorials.addCell(memo_9);
				NumberCount_memo += 1;

			}

			doc.add(myTable_memorials);

			doc.close();

		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Process completed";

	}

	/*
	 * 
	 * public String HTMLtoPDF_Certificate_Template(){
	 * 
	 * // myFilePathHeaderImage = Context.Server.MapPath("~/Resources/" +
	 * "new_CoatofArmsLarge.jpg") // myFilePathHeaderImage2 =
	 * Context.Server.MapPath("~/Resources/" + "newLogo1.jpg")
	 * //'CoatofArmsLarge
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // fntTableFontHdr As iTextSharp.text.Font = FontFactory.GetFont("Arial",
	 * 10, iTextSharp.text.Font.BOLD, iTextSharp.text.Color.BLACK)
	 * //fntTableFont As iTextSharp.text.Font = FontFactory.GetFont("Arial", 8,
	 * iTextSharp.text.Font.NORMAL, iTextSharp.text.Color.BLACK)
	 * 
	 * OutputStream file = new FileOutputStream(new File("C:\\Test.pdf"));
	 * 
	 * Document doc = new Document(PageSize.A4, 40, 40, 40, 40); // pdfFilePath
	 * As String = Context.Server.MapPath("~/TemporaryPDFFiles/" + "Batch" +
	 * txt_job_number.Text + ".pdf") //File.Delete(pdfFilePath)
	 * File.Delete("C:\\Test.pdf")
	 * 
	 * PdfWriter writer =PdfWriter.getInstance(doc, file);
	 * 
	 * doc.open();//PDF document opened........ PdfContentByte canvas =
	 * writer.getDirectContent();
	 * 
	 * 
	 * Image image = Image.getInstance(IMG); //imgPDF2.ScaleToFit(100.0F, 70.0F)
	 * image.scaleToFit(120.0F, 100.0F); image.setAbsolutePosition(170, 485);
	 * document.add(image);
	 * 
	 * 
	 * 
	 * 
	 * BarcodeQRCode barcodeQRCode = new BarcodeQRCode("fgfjhhgfjg", 1000, 1000,
	 * null); Image codeQrImage = barcodeQRCode.getImage();
	 * codeQrImage.scaleAbsolute(120.0F, 70.0F);
	 * codeQrImage.setAbsolutePosition(480, 650); doc.add(codeQrImage);
	 * 
	 * 
	 * doc.add(new Phrase(Chunk.NEWLINE)); doc.add(new Phrase(Chunk.NEWLINE));
	 * doc.add(new Phrase(Chunk.NEWLINE)); doc.add(new Phrase(Chunk.NEWLINE));
	 * doc.add(new Phrase(Chunk.NEWLINE)); doc.add(new Phrase(Chunk.NEWLINE));
	 * doc.add(new Phrase(Chunk.NEWLINE)); doc.add(new Phrase(Chunk.NEWLINE));
	 * 
	 * 
	 * 
	 * 
	 * BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252,
	 * BaseFont.NOT_EMBEDDED); PdfContentByte cb = writer.getDirectContent();
	 * cb.beginText(); cb.setFontAndSize(bf, 10);
	 * 
	 * cb.setTextMatrix(420, 680); cb.showText("LAND TITLE");
	 * 
	 * cb.setTextMatrix(422, 660); cb.showText("REGISTRY");
	 * 
	 * cb.setTextMatrix(40, 760); cb.showText("Cert. No. " +
	 * "txt_certificate_number.Text");
	 * 
	 * cb.setTextMatrix(40, 740); cb.showText("Volume " +
	 * "txt_volume_number.Text");
	 * 
	 * 
	 * cb.setTextMatrix(40, 720); cb.showText("Folio " +
	 * "txt_folio_number.Text");
	 * 
	 * cb.endText();
	 * 
	 * 
	 * 
	 * Paragraph p_1 = new Paragraph("LANDS COMMISSION", new
	 * Font(FontFamily.TIMES_ROMAN, 14));
	 * p_1.setAlignment(Element.ALIGN_CENTER); doc.add(p_1);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // 'Create the title for the report Paragraph reportTitle_ROG = new
	 * Paragraph("REPULIC OF GHANA", new Font(FontFamily.TIMES_ROMAN, 10));
	 * reportTitle_ROG.setAlignment(Element.ALIGN_CENTER);
	 * doc.add(reportTitle_ROG);
	 * 
	 * // 'Create the title for the report Paragraph reportTitle = new
	 * Paragraph("LAND CERTIFICATE", new Font(FontFamily.TIMES_ROMAN, 20));
	 * reportTitle.setAlignment(Element.ALIGN_CENTER); doc.add(reportTitle);
	 * 
	 * 
	 * doc.add(new Phrase(Chunk.NEWLINE));
	 * 
	 * // '" + txt_pid.Text + "
	 * 
	 * 
	 * String description = ""; description = "txt_customer_name.Text" +
	 * " of Accra in the " + "Greater Accra Region"; // description +=
	 * "of the Republic of Ghana is registered as tenant or lessee for the unexpired residue of a term  of "
	 * + txt_NumberofLeaseTerm.Text + " year(s) from the " +
	 * Now.ToLongDateString +
	 * " subject to the reservations, restrictions, encumbrances, liens and interests as are notified by memorial underwritten or endorsed hereon, of and in ALL THAT piece or parcel of land in extent "
	 * + txt_extent.Text + " more or less being " + txt_parcel_description.Text
	 * + " situate at " + txt_locality.Text + " in the " +
	 * "GREATER ACCRA REGION" +
	 * " of the Republic of Ghana aforesaid as delineated on Registry Map No. "
	 * + txt_registry_mapref.Text // description +=
	 * "in the Land Title Registry, Victoriaborg, Accra and being the piece or parcel of land shown and edged with pink colour on Plan No. "
	 * + txt_plan_no.Text +
	 * " annexed to this Certificate except and reserved all minerals, oils, precious stones and timber whatsoever upon or under the said piece or parcel of land."
	 * 
	 * 
	 * 
	 * // in the Greater Accra Region of the Republic of Ghana aforesaid as
	 * delineated on Registry Map No.03/3/88 361/90 annexed to this Certificate
	 * except and reserved all minerals, oils, precious stones and timber
	 * whatsoever upon or under the said piece or parcel of land
	 * 
	 * 
	 * boldFont As iTextSharp.text.Font =
	 * FontFactory.GetFont(FontFactory.HELVETICA_BOLD, 12) c1 As Chunk = new
	 * Chunk("THIS IS TO CERTIFY THAT ", boldFont)
	 * 
	 * titleFont As iTextSharp.text.Font = new Font(FontFamily.TIMES_ROMAN, 10)
	 * reportTitle_details As Paragraph = new Paragraph
	 * reportTitle_details.Add(c1)
	 * 
	 * 
	 * 
	 * reportTitle_details.Add(description); reportTitle_details.Font =
	 * titleFont; reportTitle_details.Alignment = Element.ALIGN_JUSTIFIED;
	 * 
	 * 
	 * // 'Dim reportTitle_details = new Paragraph(new
	 * Chunk("REASON(S) FOR CANCELLATION:", boldFont) +
	 * "THIS IS TO CERTIFY THAT " + txt_Certificate_template.Text, new
	 * Font(FontFamily.TIMES_ROMAN, 10))
	 * 
	 * 
	 * doc.add(reportTitle_details)
	 * 
	 * doc.add(new Phrase(Chunk.NEWLINE)); doc.add(new Phrase(Chunk.NEWLINE));
	 * doc.add(new Phrase(Chunk.NEWLINE));
	 * 
	 * doc.add(new Phrase(Chunk.NEWLINE)); doc.add(new Phrase(Chunk.NEWLINE));
	 * 
	 * doc.add(new Phrase(Chunk.NEWLINE)); doc.add(new Phrase(Chunk.NEWLINE));
	 * doc.add(new Phrase(Chunk.NEWLINE));
	 * 
	 * doc.add(new Phrase(Chunk.NEWLINE)); doc.add(new Phrase(Chunk.NEWLINE));
	 * 
	 * doc.add(new Phrase(Chunk.NEWLINE)); doc.add(new Phrase(Chunk.NEWLINE));
	 * 
	 * doc.add(new Phrase(Chunk.NEWLINE)); doc.add(new Phrase(Chunk.NEWLINE));
	 * 
	 * // 'Create the title for the report Paragraph reportTitle_folio = new
	 * Paragraph("IN WITNESS WHEREOF I have hereunto signed my name and affixed the seal of the Land Title Registry this "
	 * + Now.ToLongDateString, FontFactory.GetFont("Arial", 10,
	 * iTextSharp.text.Font.BOLD, iTextSharp.text.Color.BLACK))
	 * reportTitle_folio.Alignment = Element.ALIGN_LEFT
	 * doc.Add(reportTitle_folio)
	 * 
	 * doc.add(new Phrase(Chunk.NEWLINE));
	 * 
	 * doc.add(new Phrase(Chunk.NEWLINE)); document.add(new
	 * Phrase(Chunk.NEWLINE)); doc // 'Create the title for the report Paragraph
	 * reportTitle_volume = new
	 * Paragraph(".................................................",
	 * FontFactory.GetFont("Arial", 10, iTextSharp.text.Font.BOLD,
	 * iTextSharp.text.Color.BLACK))
	 * reportTitle_volumesetAlignment(Element.ALIGN_CENTER);
	 * doc.Add(reportTitle_volume)
	 * 
	 * 
	 * // 'Create the title for the report Paragraph reportTitle_cert = new
	 * Paragraph("CHIEF REGISTRAR OF LANDS", FontFactory.GetFont("Arial", 10,
	 * iTextSharp.text.Font.BOLD, iTextSharp.text.Color.BLACK))
	 * reportTitle_certsetAlignment(Element.ALIGN_CENTER);
	 * doc.Add(reportTitle_cert);
	 * 
	 * doc.newPage()
	 * 
	 * 
	 * 
	 * myTable_memorials As PdfPTable = new PdfPTable(6) //' Table size is set
	 * to 100% of the page myTable_memorials.WidthPercentage = 100 //'Left aLign
	 * myTable_memorials.HorizontalAlignment = 0 myTable_memorials.SpacingAfter
	 * = 10 sglTblHdWidths_memorials() As Single = new Single((6) - 1) {}
	 * sglTblHdWidths_memorials(0) = 35.0! sglTblHdWidths_memorials(1) = 90.0!
	 * sglTblHdWidths_memorials(2) = 90.0! sglTblHdWidths_memorials(3) = 90.0!
	 * sglTblHdWidths_memorials(4) = 180.0!
	 * 
	 * sglTblHdWidths_memorials(5) = 90.0!
	 * 
	 * 
	 * 
	 * //' Set the column widths on table creation. Unlike HTML cells cannot be
	 * sized. myTable_memorials.SetWidths(sglTblHdWidths_memorials);
	 * 
	 * 
	 * 
	 * PdfPCellCellOneHdr_memorials As PdfPCell = new PdfPCell(new
	 * Phrase("Entry No ", fntTableFontHdr)); //'CellOneHdr_memorials.rows() //
	 * 'CellOneHdr_memorials.Rowspan = 2
	 * myTable_memorials.addCell(CellOneHdr_memorials);
	 * 
	 * PdfPCell CellTwoHdr_memorials As PdfPCell = new PdfPCell(new
	 * Phrase("Date of Instrument.", fntTableFontHdr)); //
	 * 'CellTwoHdr_memorials.Rowspan = 2
	 * myTable_memorials.addCell(CellTwoHdr_memorials);
	 * 
	 * PdfPCell CellTreeHdr_memorials = PdfPCell = new PdfPCell(new
	 * Phrase("Date of Registration", fntTableFontHdr));
	 * myTable_memorials.addCell(CellTreeHdr_memorials)
	 * 
	 * 
	 * PdfPCell CellNineHdr_memorials = new PdfPCell = new PdfPCell(new
	 * Phrase("Registered No.", fntTableFontHdr));
	 * myTable_memorials.addCell(CellNineHdr_memorials)
	 * 
	 * PdfPCell CellTenHdr_memorials As PdfPCell = new PdfPCell(new
	 * Phrase("Memorials", fntTableFontHdr));
	 * myTable_memorials.addCell(CellTenHdr_memorials)
	 * 
	 * 
	 * PdfPCell CellFourHdr_memorials As PdfPCell = new PdfPCell(new
	 * Phrase("Cancellation", fntTableFontHdr));
	 * myTable_memorials.addCell(CellFourHdr_memorials);
	 * 
	 * doc.Add(myTable_memorials);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * doc.close();
	 * 
	 * 
	 * }
	 * 
	 */

	/*
	 * Private Sub HTMLtoPDF_memo_Template()
	 * 
	 * myFilePathHeaderImage = Context.Server.MapPath("~/Resources/" +
	 * "CoatofArmsLarge.jpg") myFilePathHeaderImage2 =
	 * Context.Server.MapPath("~/Resources/" + "newLogo1.jpg")
	 * 
	 * 
	 * bitmapimage As Bitmap
	 * 
	 * 
	 * oQR As new QRCodeEncoder myFilePathimage As String =
	 * Context.Server.MapPath("~/TemporaryPDFFiles/" + "2D" + "Barcode_batched"
	 * + Now.ToString("ddMMyyyyhhmmss") + ".jpg") bitmapimage =
	 * oQR.Encode(txt_job_number.Text) bitmapimage.Save(myFilePathimage) imgPDF2
	 * As iTextSharp.text.Image =
	 * iTextSharp.text.Image.GetInstance(myFilePathimage)
	 * imgPDF2.ScaleToFit(120.0F, 70.0F) imgPDF2.SetAbsolutePosition(480, 730)
	 * 
	 * 
	 * 
	 * fntTableFontHdr As iTextSharp.text.Font = FontFactory.GetFont("Arial",
	 * 10, iTextSharp.text.Font.BOLD, iTextSharp.text.Color.BLACK) fntTableFont
	 * As iTextSharp.text.Font = FontFactory.GetFont("Arial", 8,
	 * iTextSharp.text.Font.NORMAL, iTextSharp.text.Color.BLACK)
	 * 
	 * 
	 * 
	 * doc As Document = new Document(iTextSharp.text.PageSize.A4, 40, 40, 40,
	 * 40)
	 * 
	 * pdfFilePath As String = Context.Server.MapPath("~/TemporaryPDFFiles/" +
	 * "Batch" + txt_job_number.Text + ".pdf") File.Delete(pdfFilePath) wri As
	 * PdfWriter = PdfWriter.GetInstance(doc, new FileStream((pdfFilePath),
	 * FileMode.Create)) wri.PageEvent = ev4_new doc.Open()
	 * 
	 * 
	 * doc.Add(imgPDF2)
	 * 
	 * 
	 * document.add(new Phrase(Chunk.NEWLINE)); document.add(new
	 * Phrase(Chunk.NEWLINE)); document.add(new Phrase(Chunk.NEWLINE));
	 * document.add(new Phrase(Chunk.NEWLINE)); document.add(new
	 * Phrase(Chunk.NEWLINE));
	 * 
	 * reportTitle_ROG = new Paragraph("REPUBLIC OF GHANA", new
	 * Font(FontFamily.TIMES_ROMAN, 10))
	 * reportTitle_ROGsetAlignment(Element.ALIGN_CENTER);
	 * doc.Add(reportTitle_ROG)
	 * 
	 * // 'Create the title for the report reportTitle = new
	 * Paragraph("MEMORANDUM OF REGISTRATION", FontFactory.GetFont("Arial", 17,
	 * iTextSharp.text.Font.NORMAL, iTextSharp.text.Color.BLACK))
	 * reportTitlesetAlignment(Element.ALIGN_CENTER); doc.Add(reportTitle)
	 * 
	 * 
	 * 
	 * cb As PdfContentByte = wri.DirectContent cb.BeginText() bf As BaseFont =
	 * BaseFont.CreateFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252,
	 * BaseFont.NOT_EMBEDDED) cb.SetFontAndSize(bf, 10)
	 * 
	 * 
	 * 
	 * cb.SetTextMatrix(370, 750) cb.ShowText("LAND TITLE")
	 * 
	 * cb.SetTextMatrix(370, 730) cb.ShowText("REGISTRATION LAW, 1986")
	 * 
	 * 
	 * 
	 * cb.SetTextMatrix(40, 740) cb.ShowText("LAND REGISTRATION DIVISION ")
	 * 
	 * 
	 * 
	 * cb.EndText()
	 * 
	 * document.add(new Phrase(Chunk.NEWLINE));
	 * 
	 * reportTitle_1 = new
	 * Paragraph("This instrument has been registered according to law on " +
	 * dtp_date_of_registration.Text, FontFactory.GetFont("Arial", 10,
	 * iTextSharp.text.Font.BOLD, iTextSharp.text.Color.BLACK))
	 * reportTitle_1.Alignment = Element.ALIGN_LEFT doc.Add(reportTitle_1)
	 * 
	 * reportTitle_2 = new Paragraph("Registered No:" + "",
	 * FontFactory.GetFont("Arial", 10, iTextSharp.text.Font.BOLD,
	 * iTextSharp.text.Color.BLACK)) reportTitle_2.Alignment =
	 * Element.ALIGN_LEFT doc.Add(reportTitle_2)
	 * 
	 * reportTitle_3 = new Paragraph("Fees Paid GHS: " + txt_MemoFeesPaid.Text,
	 * FontFactory.GetFont("Arial", 10, iTextSharp.text.Font.BOLD,
	 * iTextSharp.text.Color.BLACK)) reportTitle_3.Alignment =
	 * Element.ALIGN_LEFT doc.Add(reportTitle_3)
	 * 
	 * reportTitle_4 = new Paragraph("Receipt No: " + txt_MemoReceiptNo.Text +
	 * "Date: " + dtp_memo_date_of_payment.Text, FontFactory.GetFont("Arial",
	 * 10, iTextSharp.text.Font.BOLD, iTextSharp.text.Color.BLACK))
	 * reportTitle_4.Alignment = Element.ALIGN_LEFT doc.Add(reportTitle_4)
	 * 
	 * reportTitle_5 = new Paragraph("Register: Vol: " + txt_volume_number.Text
	 * + "  Folio: " + txt_folio_number.Text, FontFactory.GetFont("Arial", 10,
	 * iTextSharp.text.Font.BOLD, iTextSharp.text.Color.BLACK))
	 * reportTitle_5.Alignment = Element.ALIGN_LEFT doc.Add(reportTitle_5)
	 * 
	 * reportTitle_6 = new Paragraph("Land Title Registry" + "",
	 * FontFactory.GetFont("Arial", 10, iTextSharp.text.Font.BOLD,
	 * iTextSharp.text.Color.BLACK)) reportTitle_6.Alignment =
	 * Element.ALIGN_LEFT doc.Add(reportTitle_6)
	 * 
	 * reportTitle_7 = new Paragraph("District: " + txt_District.Text +
	 * "                                        .....................",
	 * FontFactory.GetFont("Arial", 10, iTextSharp.text.Font.BOLD,
	 * iTextSharp.text.Color.BLACK)) reportTitle_7.Alignment =
	 * Element.ALIGN_LEFT doc.Add(reportTitle_7)
	 * 
	 * reportTitle_8 = new Paragraph("FORM LR. 34" +
	 * "                                        LAND REGISTRAR",
	 * FontFactory.GetFont("Arial", 10, iTextSharp.text.Font.BOLD,
	 * iTextSharp.text.Color.BLACK)) reportTitle_8.Alignment =
	 * Element.ALIGN_LEFT doc.Add(reportTitle_8)
	 * 
	 * 
	 * 
	 * 
	 * 
	 * document.add(new Phrase(Chunk.NEWLINE)); document.add(new
	 * Phrase(Chunk.NEWLINE));
	 * 
	 * document.add(new Phrase(Chunk.NEWLINE)); document.add(new
	 * Phrase(Chunk.NEWLINE));
	 * 
	 * document.add(new Phrase(Chunk.NEWLINE)); document.add(new
	 * Phrase(Chunk.NEWLINE));
	 * 
	 * // 'Create the title for the report reportTitle_folio = new
	 * Paragraph("PINK CARD ", FontFactory.GetFont("Arial", 10,
	 * iTextSharp.text.Font.BOLD, iTextSharp.text.Color.BLACK))
	 * reportTitle_foliosetAlignment(Element.ALIGN_CENTER);
	 * doc.Add(reportTitle_folio)
	 * 
	 * 
	 * pinkTitle_8 = new Paragraph("FORM LR. 10", FontFactory.GetFont("Arial",
	 * 10, iTextSharp.text.Font.BOLD, iTextSharp.text.Color.BLACK))
	 * pinkTitle_8.Alignment = Element.ALIGN_LEFT doc.Add(pinkTitle_8)
	 * 
	 * 
	 * pinkTitle_5 = new Paragraph("Vol: " + txt_volume_number.Text + "Folio: "
	 * + txt_folio_number.Text, FontFactory.GetFont("Arial", 10,
	 * iTextSharp.text.Font.BOLD, iTextSharp.text.Color.BLACK))
	 * pinkTitle_5.Alignment = Element.ALIGN_LEFT doc.Add(pinkTitle_5)
	 * 
	 * 
	 * pinkTitle_1 = new Paragraph("Parcel :" + txt_parcel_description.Text,
	 * FontFactory.GetFont("Arial", 10, iTextSharp.text.Font.BOLD,
	 * iTextSharp.text.Color.BLACK)) pinkTitle_1.Alignment = Element.ALIGN_LEFT
	 * doc.Add(pinkTitle_1)
	 * 
	 * pinkTitle_2 = new Paragraph("Registry Map Ref: " +
	 * txt_registry_mapref.Text + "Plan No: " + "", FontFactory.GetFont("Arial",
	 * 10, iTextSharp.text.Font.BOLD, iTextSharp.text.Color.BLACK))
	 * pinkTitle_2.Alignment = Element.ALIGN_LEFT doc.Add(pinkTitle_2)
	 * 
	 * pinkTitle_3 = new Paragraph("Proprietor: " + txt_customer_name.Text,
	 * FontFactory.GetFont("Arial", 10, iTextSharp.text.Font.BOLD,
	 * iTextSharp.text.Color.BLACK)) pinkTitle_3.Alignment = Element.ALIGN_LEFT
	 * doc.Add(pinkTitle_3)
	 * 
	 * pinkTitle_4 = new Paragraph("Address: " + txt_lessees_address.Text,
	 * FontFactory.GetFont("Arial", 10, iTextSharp.text.Font.BOLD,
	 * iTextSharp.text.Color.BLACK)) pinkTitle_4.Alignment = Element.ALIGN_LEFT
	 * doc.Add(pinkTitle_4)
	 * 
	 * 
	 * pinkTitle_6 = new Paragraph("Nature of Dealing: " +
	 * cbo_nature_of_instrument.Text, FontFactory.GetFont("Arial", 10,
	 * iTextSharp.text.Font.BOLD, iTextSharp.text.Color.BLACK))
	 * pinkTitle_6.Alignment = Element.ALIGN_LEFT doc.Add(pinkTitle_6)
	 * 
	 * pinkTitle_7 = new Paragraph("Registered No: " + txt_District.Text,
	 * FontFactory.GetFont("Arial", 10, iTextSharp.text.Font.BOLD,
	 * iTextSharp.text.Color.BLACK)) pinkTitle_7.Alignment = Element.ALIGN_LEFT
	 * doc.Add(pinkTitle_7)
	 * 
	 * pinkTitle_9 = new Paragraph("Vide Folio(s):" + txt_VideFolio.Text,
	 * FontFactory.GetFont("Arial", 10, iTextSharp.text.Font.BOLD,
	 * iTextSharp.text.Color.BLACK)) pinkTitle_9.Alignment = Element.ALIGN_LEFT
	 * doc.Add(pinkTitle_9)
	 * 
	 * 
	 * 
	 * doc.Close() Application.DoEvents() If File.Exists(pdfFilePath) = False
	 * Then Exit Sub
	 * 
	 * PreviewBatchReportForm As new frm_Preview_Batch_Report
	 * PreviewBatchReportForm.txt_Report_Path.Text = pdfFilePath
	 * PreviewBatchReportForm.ShowDialog()
	 * 
	 * 
	 * 
	 * End Sub
	 */

}
