package com.userlogin.ws;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sun.rmi.runtime.Log;

public class checkBalance {
	public String getBalance(String accNum) {
		String accountInfo = "";

		// / File ////////////////
		File f = new File("C:\\Users\\siam\\workspaceJ2EE\\SBBL_Final\\balance.txt");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = "u ";
		// //////////////////////////////

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sbbl", "root", "");
			// Find customer information where the customer ID is maximum
			PreparedStatement statement = con
					.prepareStatement("SELECT * FROM account WHERE accountNumber = '"
							+ accNum + "'");
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				accountInfo = accountInfo + result.getString("accountName")
						+ "," + result.getString("accountType") + ","
						+ result.getString("balance");
				// Here "&"s are added to the return string. This is help to
				// split the string in Android application
			}
		}

		catch (Exception exc) {
			System.out.println(exc.getMessage());
			s += "error";
		}

		s += accountInfo;
		pw.write(s);
		pw.close();

		return accountInfo;
	}
}
