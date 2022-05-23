package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class Billing {

	public Connection connect()
	{ 
		 Connection con = null; 
		 
		 try { 
			 Class.forName("com.mysql.jdbc.Driver"); 
			 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/EGCompanyDb", "root", ""); 
			 
			 //For testing
			 System.out.print("Successfully connected"); 
		} catch(Exception e){ 
			 e.printStackTrace(); 
		} 
		return con; 
	}
	
	public String addBilling(String cName, String cAccNo, String billingDate, String noOfUnitis, String unitPrice) {
		String output = ""; 
		try { 
			Connection con = connect(); 
			if (con == null) 
			{ 
				return "Error while connecting to the database"; 
			} 
			// create a prepared statement
			String query = "insert into Billingt(`cID`,`CName`,`cAccNo`,`billingDate`,`noOfUnitis`,`unitPrice`) values (?,?,?,?,?,?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cName);
			preparedStmt.setInt(3, Integer.parseInt(cAccNo));
			preparedStmt.setString(4, billingDate);
			preparedStmt.setInt(5, Integer.parseInt(noOfUnitis));
			preparedStmt.setString(6, unitPrice);
					
			 
			//execute the statement
			 preparedStmt.execute(); 
			 con.close(); 

			 String Billings = readBillings();
			 output = "{\"status\":\"success\", \"data\": \"" + Billings + "\"}";
		}catch (Exception e) { 
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the Billing.\"}";
			 System.err.println(e.getMessage()); 
		} 
		return output;
	}
	
	public String readBillings() {
		String output = "";
		try {
			Connection con = connect();
			if(con == null) {
				return "Error while connecting to the database";
			}
			
			output = "<table border='2' class='table table-bordered'>"
					+ "<tr>"
					+ "<th>Customer Name</th>"
					+ "<th>AccountNo</th>"
					+ "<th>Billing Date</th>"
					+ "<th>No of Uninits</th>"
					+ "<th>Unit Price</th>"
					+ "<th>Update</th>"
					+ "<th>Delete</th>"
					+ "</tr>";
			
			String query = "select * from Billingt";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String cID = Integer.toString(rs.getInt("cID"));
				String cName = rs.getString("cName");
				String cAccNo = Integer.toString(rs.getInt("cAccNo"));
				String billingDate = rs.getString("billingDate");
				String noOfUnitis = Integer.toString(rs.getInt("noOfUnitis"));
				String unitPrice = rs.getString("unitPrice");
					
				//add a row into html table
				output += "<tr>";
				output += "<td>" + cName  + "</td>";
				output += "<td>" + cAccNo  + "</td>";
				output += "<td>" + billingDate + "</td>";
				output += "<td>" + noOfUnitis  + "</td>";
				output += "<td>" + unitPrice  + "</td>";
				
				//buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-success' data-cid='" + cID + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-cid='" + cID + "'></td></tr>";
				
			}
			
			con.close();
			output += "</table>";
			
		}catch(Exception e) {
			output = "Error while reading";
			System.out.println(e.getMessage());
		}
		return output;
	}

	
	public String updateBilling(String cID, String cName, String cAccNo, String billingDate, String noOfUnitis, String unitPrice) {
		String output = ""; 
		try { 
			Connection con = connect(); 
			if (con == null) 
			{ 
				return "Error while connecting to the database"; 
			} 
			// create a prepared statement
			String query = "UPDATE Billingt SET cName=?,cAccNo=?,billingDate=?,noOfUnitis=?,unitPrice=? WHERE cID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query); 

			// binding values
			preparedStmt.setString(1, cName);
			preparedStmt.setString(2, cAccNo);
			preparedStmt.setString(3, billingDate);
			preparedStmt.setString(4, noOfUnitis);
			preparedStmt.setString(5, unitPrice);
			preparedStmt.setInt(6, Integer.parseInt(cID));
			
			// execute the statement
			 preparedStmt.execute();
			 con.close();

			 String Billings = readBillings();
			 output = "{\"status\":\"success\", \"data\": \"" + Billings + "\"}";
		}catch (Exception e) { 
			output = "{\"status\":\"error\", \"data\": \"Error while updating the Billing.\"}"; 
			 System.err.println(e.getMessage()); 
		} 
		return output;
	}
	
	
	public String deleteBilling(String cID)
	{ 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
				 return "Error while connecting to the database for deleting."; 
			 } 
			 // create a prepared statement
			 String query = "delete from Billingt where cID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(cID));
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String Billings = readBillings();
		     output = "{\"status\":\"success\", \"data\": \"" + Billings + "\"}"; 
		} 
		catch (Exception e) 
		{ 
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the Billing.\"}"; 
			System.err.println(e.getMessage()); 
		} 
		return output;
	}

	
}
