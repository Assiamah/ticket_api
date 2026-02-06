package com.mit.ticket_mgt_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mit.ticket_mgt_api.conn_class.Ws_url_config;
import com.mit.ticket_mgt_api.conn_class.db_settings;
import com.mit.ticket_mgt_api.models.auth_service_model;

import com.mit.ticket_mgt_api.models.sms.cls_sms;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import io.swagger.v3.oas.annotations.tags.Tag;

//@PostMapping("/email_service")
@RestController
@RequestMapping("/email_service")
@Tag(name = "Email Service", description = "Authentication Service for App")

public class email_service {
	cls_sms cls_sms_cl = new cls_sms();

	@Autowired
	private db_settings cls_db_config;

	@Autowired
	private Ws_url_config cls_url_config;
	// // @POST
	// @PostMapping("/send")
	// // //@Consumes(MediaType.APPLICATION_JSON)
	// // @Produces(MediaType.APPLICATION_JSON)
	// public String send_mail(@RequestBody String json_data) throws Exception {
	// user_account.con = cls_db_config.getCon();
	// String result = "";
	// String to = "sonoojaiswal1988@gmail.com";// change accordingly
	// String from = "sonoojaiswal1987@gmail.com";// change accordingly
	// String host = "localhost";// or IP address

	// // Get the session object
	// Properties properties = System.getProperties();
	// properties.setProperty("mail.smtp.host", host);
	// Session session = Session.getDefaultInstance(properties);

	// // compose the message
	// try {
	// MimeMessage message = new MimeMessage(session);
	// message.setFrom(new InternetAddress(from));
	// message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	// message.setSubject("Ping");
	// message.setText("Hello, this is example of sending email ");

	// // Send message
	// Transport.send(message);
	// System.out.println("message sent successfully....");
	// result = "sent";
	// } catch (MessagingException mex) {
	// result = "not sent";
	// mex.printStackTrace();
	// }

	// return result;
	// }

	// // @POST
	// @PostMapping("/send_smtp")
	// // //@Consumes(MediaType.APPLICATION_JSON)
	// // @Produces(MediaType.APPLICATION_JSON)
	// public String send_mail_smtp(@RequestBody String json_data) throws Exception
	// {
	// user_account.con = cls_db_config.getCon();
	// // Prepare data as Json Object
	// JSONObject jsonobject = new JSONObject(json_data);
	// String sbj = jsonobject.getString("subject");
	// String msg = jsonobject.getString("message");
	// String to = jsonobject.getString("to");
	// String msg_html = jsonobject.getString("message_html");
	// // System.out.println("tetsing mail serrfv");
	// // Ws_url_config.get_public_docs_upload_location();
	// String result = "";
	// String host = Ws_url_config.get_email_config_host();
	// String user = Ws_url_config.get_email_config_user();// change
	// // accordingly
	// String password = Ws_url_config.get_email_config_pass();// change
	// // accordingly

	// // String to = "judeyamoah@gmail.com";// change accordingly

	// // Get the session object
	// Properties props = new Properties();
	// props.put("mail.smtp.host", host);
	// props.put("mail.smtp.auth", "true");

	// Session session = Session.getDefaultInstance(props, new
	// javax.mail.Authenticator() {
	// protected PasswordAuthentication getPasswordAuthentication() {
	// return new PasswordAuthentication(user, password);
	// }
	// });

	// // Compose the message
	// try {
	// MimeMessage message = new MimeMessage(session);
	// message.setFrom(new InternetAddress(user));
	// message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	// message.setSubject(sbj);
	// message.setText(msg);
	// // Send the actual HTML message, as big as you like
	// if (!msg_html.isEmpty()) {
	// message.setContent(msg_html, "text/html");
	// }

	// // send the message
	// Transport.send(message);

	// // System.out.println("message sent successfully...");
	// result = "sent";
	// } catch (MessagingException e) {
	// result = "not sent";
	// e.printStackTrace();
	// }

	// return result;
	// }

	/**
	 * Read the body of the message until EOF.
	 */
	// public static String collect(BufferedReader in) throws IOException {
	// String line;
	// StringBuffer sb = new StringBuffer();
	// while ((line = in.readLine()) != null) {
	// sb.append(line);
	// sb.append("\n");
	// }
	// return sb.toString();
	// }

	// // @POST
	// @PostMapping("/sent_smtp_exchange")
	// // @Produces(MediaType.APPLICATION_JSON)
	// public String send_mail_smtp_exchange(@RequestBody String json_data) throws
	// Exception {
	// user_account.con = cls_db_config.getCon();
	// String to, subject = null, from = null, cc = null, bcc = null, url = null;
	// String mailhost = null;
	// String mailer = "smtpsend";
	// String file = null;
	// String protocol = null, host = null, user = null, password = null;
	// String record = null; // name of folder in which to record mail
	// boolean debug = false;
	// boolean verbose = false;
	// boolean auth = true;
	// String prot = "smtp";
	// BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	// int optind;

	// /*
	// * Process command line arguments.
	// */

	// try {
	// /*
	// * Prompt for To and Subject, if not specified.
	// */JSONObject jsonobject = new JSONObject(json_data);
	// String sbj = jsonobject.getString("subject");
	// String msg_string = jsonobject.getString("message");
	// to = jsonobject.getString("to");
	// String msg_html = jsonobject.getString("message_html");
	// // System.out.println("tetsing mail serrfv");
	// // Ws_url_config.get_public_docs_upload_location();
	// String result = "";
	// mailhost = Ws_url_config.get_email_config_host();
	// user = Ws_url_config.get_email_config_user();// change
	// // accordingly
	// password = Ws_url_config.get_email_config_pass();// change
	// from = user; // accordingly

	// /*
	// * Initialize the JavaMail Session.
	// */
	// Properties props = System.getProperties();
	// if (mailhost != null)
	// props.put(mailhost, mailhost);
	// if (auth)
	// props.put("mail." + prot + ".auth", "true");

	// /*
	// * Create a Provider representing our extended SMTP transport and
	// * set the property to use our provider.
	// *
	// * Provider p = new Provider(Provider.Type.TRANSPORT, prot,
	// * "smtpsend$SMTPExtension", "JavaMail demo", "no version");
	// * props.put("mail." + prot + ".class", "smtpsend$SMTPExtension");
	// */

	// // Get a Session object
	// Session session = Session.getInstance(props, null);
	// if (debug)
	// session.setDebug(true);

	// /*
	// * Register our extended SMTP transport.
	// *
	// * session.addProvider(p);
	// */

	// /*
	// * Construct the message and send it.
	// */
	// Message msg = new MimeMessage(session);

	// msg.setFrom(new InternetAddress(from));

	// msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,
	// false));

	// msg.setSubject("test subject");

	// String text = collect(in);

	// // If the desired charset is known, you can use
	// // setText(text, charset)
	// msg.setText(text);

	// msg.setHeader("X-Mailer", mailer);
	// msg.setSentDate(new Date());

	// // send the thing off
	// /*
	// * The simple way to send a message is this:
	// *
	// * Transport.send(msg);
	// *
	// * But we're going to use some SMTP-specific features for
	// * demonstration purposes so we need to manage the Transport object
	// * explicitly.
	// */
	// SMTPTransport t = (SMTPTransport) session.getTransport(prot);
	// try {
	// if (auth)
	// t.connect(mailhost, user, password);
	// else
	// t.connect();
	// t.sendMessage(msg, msg.getAllRecipients());
	// } finally {
	// if (verbose)
	// // System.out.println("Response: " +
	// // t.getLastServerResponse());
	// t.close();
	// }

	// System.out.println("\nMail was sent successfully.");

	// /*
	// * Save a copy of the message, if requested.
	// */
	// if (record != null) {
	// // Get a Store object
	// Store store = null;
	// if (url != null) {
	// URLName urln = new URLName(url);
	// store = session.getStore(urln);
	// store.connect();
	// } else {
	// if (protocol != null)
	// store = session.getStore(protocol);
	// else
	// store = session.getStore();

	// // Connect
	// if (host != null || user != null || password != null)
	// store.connect(host, user, password);
	// else
	// store.connect();
	// }

	// // Get record Folder. Create if it does not exist.
	// Folder folder = store.getFolder(record);
	// if (folder == null) {
	// System.err.println("Can't get record folder.");
	// System.exit(1);
	// }
	// if (!folder.exists())
	// folder.create(Folder.HOLDS_MESSAGES);

	// Message[] msgs = new Message[1];
	// msgs[0] = msg;
	// folder.appendMessages(msgs);

	// System.out.println("Mail was recorded successfully.");
	// }

	// } catch (Exception e) {
	// /*
	// * Handle SMTP-specific exceptions.
	// */
	// // result = "not sent";
	// e.printStackTrace();
	// if (e instanceof SendFailedException) {
	// MessagingException sfe = (MessagingException) e;
	// if (sfe instanceof SendFailedException) {
	// SendFailedException ssfe = (SendFailedException) sfe;
	// System.out.println("SMTP SEND FAILED:");
	// if (verbose)
	// System.out.println(ssfe.toString());
	// /*
	// * System.out.println(" Command: " + ssfe.getCommand());
	// * System.out.println(" RetCode: " + ssfe.getReturnCode());
	// */
	// System.out.println(" Response: " + ssfe.getMessage());
	// } else {
	// if (verbose)
	// System.out.println("Send failed: " + sfe.toString());
	// }
	// Exception ne;
	// while ((ne = sfe.getNextException()) != null && ne instanceof
	// MessagingException) {
	// sfe = (MessagingException) ne;
	// if (sfe instanceof SendFailedException) {
	// SendFailedException ssfe = (SendFailedException) sfe;
	// System.out.println("ADDRESS FAILED:");
	// if (verbose)
	// System.out.println(ssfe.toString());
	// /*
	// * System.out.println(" Address: " +
	// * ssfe.getAddress()); System.out.println(" Command: "
	// * + ssfe.getCommand());
	// * System.out.println(" RetCode: " +
	// * ssfe.getReturnCode());
	// */
	// System.out.println(" Response: " + ssfe.getMessage());
	// } /*
	// * else if (sfe instanceof
	// * SMTPAddressSucceededException) {
	// * System.out.println("ADDRESS SUCCEEDED:");
	// * SMTPAddressSucceededException ssfe =
	// * (SMTPAddressSucceededException)sfe; if (verbose)
	// * System.out.println(ssfe.toString());
	// * System.out.println(" Address: " +
	// * ssfe.getAddress()); System.out.println(" Command: "
	// * + ssfe.getCommand());
	// * System.out.println(" RetCode: " +
	// * ssfe.getReturnCode());
	// * System.out.println(" Response: " +
	// * ssfe.getMessage()); }
	// */
	// }
	// } else {
	// System.out.println("Got Exception: " + e);
	// if (verbose)
	// e.printStackTrace();
	// }
	// }

	// return "";
	// }

}
