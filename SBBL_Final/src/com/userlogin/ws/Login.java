package com.userlogin.ws;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sun.rmi.runtime.Log;

public class Login {
	public String authentication(String userName, String password) {
		File f = new File("C:\\Users\\siam\\workspaceJ2EE\\SBBL_Final\\balance.txt");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = "u " + userName + " " + password;
		pw.write(s);

		String status = statusData(userName, password);
		pw.write(status);
		pw.close();
		return status;
		// return "Shophag";

	}

	public String statusData(String userName, String password) {
		String retrievedUserName = "";
		String retrievedPassword = "";
		String accountNumber="";
		String status = "check";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = null;

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sbbl", "root", "");

			PreparedStatement statement = con
					.prepareStatement("SELECT * FROM login WHERE username = '"
							+ userName + "'");
			
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				retrievedUserName = result.getString("username");
				retrievedPassword = result.getString("password");
				accountNumber=result.getString("accountNumber");
				System.out.println(retrievedUserName + " " + retrievedPassword);
			}

			if (retrievedUserName.equals(userName)
					&& retrievedPassword.equals(password)) {
				status = userName+","+accountNumber;
			}

			else {
				status = "false";
			}

		} catch (Exception e) {
			status = "error";
			// System.out.println();
			e.printStackTrace();
		}
		return status;
	}

}
