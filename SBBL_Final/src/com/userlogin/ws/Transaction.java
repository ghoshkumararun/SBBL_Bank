package com.userlogin.ws;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Transaction {
	public String getBalance(String accNum) {
		
		String transactionInfo = "";

		// / File ////////////////
		File f = new File("C:\\Users\\siam\\workspaceJ2EE\\SBBL_Final\\transaction.txt");
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
					.prepareStatement("SELECT * FROM transaction WHERE accountNumber = '"
							+ accNum + "'"+"order by date desc limit 5");
			ResultSet result = statement.executeQuery();
			transactionInfo = transactionInfo + "      Date         Type        Amount," ;

			int flag = 0;
			while (result.next()) {
				if( flag != 0){
					transactionInfo += ",";
				}
				
				
				transactionInfo = transactionInfo + result.getString("date")
						+ "   " + result.getString("type") + "   "
						 + result.getString("amount") ;
				// Here "&"s are added to the return string. This is help to
				// split the string in Android application
				flag ++;
			}
		}

		catch (Exception exc) {
			System.out.println(exc.getMessage());
			s += "error";
		}

		s += transactionInfo;
		pw.write(s);
		pw.close();

		return transactionInfo;
	}

}
