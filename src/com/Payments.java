package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Payments {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electro_payment", "root", "");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	
	public String insertProject(String b_id,String account_number,String c_id,String c_name,String amount,String card_number,String bank_name,String card_exp_date,String cvv,String date) {
		
		
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			// create a prepared statement
			String query = " insert into electro_payment(`b_id`, `account_number`, `c_id`, `c_name`, `amount`, `card_number`, `bank_name`, `card_exp_date`, `cvv`, `date`)"
					+ " values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, b_id);
			preparedStmt.setString(2, account_number);
			preparedStmt.setString(3, c_id);
			preparedStmt.setString(4, c_name);
			preparedStmt.setString(5, amount);
			preparedStmt.setString(6, card_number);
			preparedStmt.setString(7, bank_name);
			preparedStmt.setString(8, card_exp_date);
			preparedStmt.setString(9, cvv);
			preparedStmt.setString(10, date);

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newProject = readProject();
			output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the project.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}
	

	public String readProject() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare table to display
			
			output = "<table class='table table-striped table-dark'><tr><th style=width:100px; >Bill ID</th>"
					+ "<th style=width:100px; >Account Number</th>"
					+ "<th style=width:100px; >Customer ID</th>"
					+ "<th style=width:100px; >Customer Name</th>"
					+ "<th style=width:100px; >Total Amount</th>"
					+ "<th style=width:100px; >Card Number</th>"
					+ "<th style=width:100px; >Bank Name</th>"
					+ "<th style=width:100px; >Card Exp Date</th>"
					+ "<th style=width:100px; >CVV</th>"
					+ "<th style=width:100px; >Payment Date</th>"
					+ "<th style=width:50px; >Update</th>"
					+ "<th style=width:50px; >Remove</th></tr>";
			
			String query = "select * from electro_payment";

			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				
				String id = rs.getString("id");
				String b_id = rs.getString("b_id");
				String account_number = rs.getString("account_number");
				String c_id = rs.getString("c_id");
				String c_name = rs.getString("c_name");
				String amount = rs.getString("amount");
				String card_number = rs.getString("card_number");
				String bank_name = rs.getString("bank_name");
				String card_exp_date = rs.getString("card_exp_date");
				String cvv = rs.getString("cvv");
				String date = rs.getString("date");
				

				// Add into the table
				output += "<tr><td><input id=\'hidProjectIDUpdate\' name=\'hidProjectIDUpdate\' type=\'hidden\' value=\'"
						+ id + "'>" + b_id + "</td>";
				output += "<td>" + account_number + "</td>";
				output += "<td>" + c_id + "</td>";
				output += "<td>" + c_name + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + card_number + "</td>";
				output += "<td>" + bank_name + "</td>";
				output += "<td>" + card_exp_date + "</td>";
				output += "<td>" + cvv + "</td>";
				output += "<td>" + date + "</td>";
				

				
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-comid='"
						+ id + "'>" + "</td></tr>";
			}

			con.close();

			// Complete the table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the projects.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	public String updateProject(String id,String b_id,String account_number,String c_id,String c_name,String amount,String card_number,String bank_name,String card_exp_date,String cvv,String date) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE electro_payment SET b_id=?, account_number=?, c_id=?, c_name=?, amount=?, card_number=?, bank_name=?, card_exp_date=?, cvv=?, date=? where id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, b_id);
			preparedStmt.setString(2, account_number);
			preparedStmt.setString(3, c_id);
			preparedStmt.setString(4, c_name);
			preparedStmt.setString(5, amount);
			preparedStmt.setString(6, card_number);
			preparedStmt.setString(7, bank_name);
			preparedStmt.setString(8, card_exp_date);
			preparedStmt.setString(9, cvv);
			preparedStmt.setString(10, date);
			preparedStmt.setInt(11, Integer.parseInt(id));
		

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newProject = readProject();
			output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the project.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	
	public String deleteProject(String id) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from electro_payment where id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newProject = readProject();
			output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
		} catch (Exception e) {
			output = "Error while deleting the project.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	
	
}
