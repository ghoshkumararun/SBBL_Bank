package com.userlogin.ws;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MakeTransaction {

	public String make(String toAccNum, String fromAccNum, int amount) {
		String status = "fae";
		// / File ////////////////
		File f = new File(
				"C:\\Users\\siam\\workspaceJ2EE\\SBBL_Final\\transaction.txt");
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
			// -------fromAccNum---------------
			PreparedStatement statement = con
					.prepareStatement("SELECT * FROM account WHERE accountNumber = '"
							+ fromAccNum + "'");
			ResultSet result = statement.executeQuery();
			// ---------toAccNum-------------------
			PreparedStatement statement2 = con
					.prepareStatement("SELECT * FROM account WHERE accountNumber = '"
							+ toAccNum + "'");
			ResultSet result2 = statement2.executeQuery();

			if (result.next() && result2.next()) {
				status = "f";
				int from = result.getInt("balance");
				int to = result2.getInt("balance");
				if(from<=amount)return "you do not have enough balance";
				from = from - amount;
				to = to + amount;

				PreparedStatement stmTo = con
						.prepareStatement("UPDATE account SET balance = " + to
								+ " WHERE accountNumber = '" + toAccNum + "'");
				int fg = stmTo.executeUpdate();

				PreparedStatement stmFrom = con
						.prepareStatement("UPDATE account SET balance = "
								+ from + " WHERE accountNumber = '"
								+ fromAccNum + "'");
				int fgc = stmFrom.executeUpdate();
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Calendar cal = Calendar.getInstance();
				String date = dateFormat.format(cal.getTime());
				PreparedStatement s1 = con
						.prepareStatement("INSERT INTO transaction values ("
								+ "'"+date+"', '" + toAccNum + "','deposit','"
								+ "user" + "','" + amount + "')");
				PreparedStatement s2 = con
						.prepareStatement("INSERT INTO transaction values ("
								+ "'"+date+"', '" + fromAccNum + "','withdrawal','"
								+ "user"+"','" + amount + "')");
				fg = s1.executeUpdate();
				fg = s2.executeUpdate();
				status = "true";
			} else {
				status = "fals se";
			}

		}

		catch (Exception exc) {
			System.out.println(exc.getMessage());
			status = "error";
		}

		s += status;
		pw.write(s);
		pw.close();
		return status;
	}
}
