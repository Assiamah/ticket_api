package com.mit.ticket_mgt_api.models.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.glassfish.jersey.client.ClientResponse;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;

import jakarta.ws.rs.client.ClientBuilder;

public class cls_sms {

	public Connection con = null;

	public String lc_outgoing_sms_log(String json_request) {
		String result = null;
		String SQL = "SELECT * FROM csau.lc_outgoing_sms_log(?)";
		Connection conn = con;
		try (

				PreparedStatement pstmt = conn.prepareStatement(SQL)) {
			pstmt.setString(1, json_request);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("lc_outgoing_sms_log"));
				result = rs.getString("lc_outgoing_sms_log");
			}
		} catch (SQLException e) {
			// Print Errors in console.
			System.out.println(e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public String send_register_message(String msg1, String reciplent) {
		try {
			// Construct data
			// http://txtconnect.co/api/send/
			// String url =
			// "https://www.txtconnect.co/v2/app/api/send/sms.json";
			String url = "https://txtconnect.net/sms/api?action=send-sms";

			// String url =
			// "https://www.txtconnect.co/v2/app/api/send/sms.json";
			String token = "&api_key=" + "Z0NDS2hyQWdNSE9kcmtzanhEaWE";
			String to = "&to=" + reciplent;
			String from = "&from=" + "Landscom";

			String msg = "&sms=" + msg1;

			String others = "&response=json&unicode=0";
			// https://txtconnect.net/sms/api?action=send-sms&api_key=Z0NDS2hyQWdNSE9kcmtzanhEaWE=&to=233203447900&from=Landscom&sms=API_Test&response=json&unicode=0
			// https://txtconnect.net/sms/api?action=send-sms&api_key=Z0NDS2hyQWdNSE9kcmtzanhEaWE=&to=PhoneNumber&from=SenderID&sms=YourMessage&response=json&unicode=0

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			String data = token + from + to + msg + others;
			// System.out.println(data);
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

	public String send_register_message1(String msg1, String reciplent) {
		try {

			String baseURL = "https://txtconnect.net/sms/api?action=send-sms";

			// String url =
			// "https://www.txtconnect.co/v2/app/api/send/sms.json";
			String token = "&api_key=" + "Z0NDS2hyQWdNSE9kcmtzanhEaWE=";
			String to = "&to=" + reciplent;
			String from = "&from=" + "Landscom";

			String others = "&response=json&unicode=0";

			String msg = "&sms=" + java.net.URLEncoder.encode(msg1, "UTF-8").replace("+", "%20");
			// https://txtconnect.net/sms/api?action=send-sms&api_key=Z0NDS2hyQWdNSE9kcmtzanhEaWE=&to=233203447900&from=Landscom&sms=API_Test&response=json&unicode=0
			// https://txtconnect.net/sms/api?action=send-sms&api_key=Z0NDS2hyQWdNSE9kcmtzanhEaWE=&to=PhoneNumber&from=SenderID&sms=YourMessage&response=json&unicode=0
			String data = token + from + to + msg + others;
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL(baseURL).openConnection();

			String url1 = baseURL + data;

			// System.out.println(url1);

			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Length", Integer.toString(url1.length()));
			conn.getOutputStream().write(url1.getBytes("UTF-8"));
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

	public String select_send_sms_new(String msg1, String recipient) {
		String output = "{\"messageId\": \"0\",\"msg\": \"Sms Send Not Sucessfull\"}";

		JSONObject obj = new JSONObject();

		try {
			obj.put("to", recipient);
			obj.put("from", "Landscom");
			obj.put("unicode", "0");
			obj.put("sms", msg1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println("No error");

		try {
		Client client = ClientBuilder.newClient();
		WebTarget webResource = client.target("https://txtconnect.net/dev/api/sms/send");
		Response response = webResource.request("application/json").header("Authorization", "Bearer Z0NDS2hyQWdNSE9kcmtzanhEaWE=").header("Content-Type", "application/json;charset=UTF-8").post(Entity.entity(obj.toString(), MediaType.APPLICATION_JSON));
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		output = response.readEntity(String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	public String check_sms_balance() {
		String output = "Data Not Received";
		try {
			Client client = ClientBuilder.newClient();
			WebTarget webResource = client.target("https://txtconnect.net/dev/api/sms/checkbalance");
			Response response = webResource.request("application/json").header("Authorization", "Bearer Z0NDS2hyQWdNSE9kcmtzanhEaWE=")
					.header("Content-Type", "application/json;charset=UTF-8").get();
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			output = response.readEntity(String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

}
