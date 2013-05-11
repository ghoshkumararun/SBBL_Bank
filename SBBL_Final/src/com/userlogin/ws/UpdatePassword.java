package com.userlogin.ws;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdatePassword {
	public String updatePassword(String userName,String password,String oldPassword) {
		int fg = 0;

		// / File ////////////////
		File f = new File("C:\\Users\\siam\\workspaceJ2EE\\SBBL_Final\\update.txt");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// //////////////////////////////

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sbbl", "root", "");
			// Find customer information where the customer ID is maximum
			
			PreparedStatement st=con.prepareStatement("SELECT * FROM login WHERE username = '"
							+ userName + "'");
			ResultSet r=st.executeQuery();
			if(r.next()){
				if(r.getString("password").equals(oldPassword)){
						PreparedStatement st3 = con.prepareStatement("UPDATE login SET password = '" + password +"' WHERE username = '" + userName + "'");
						fg = st3.executeUpdate();
				}
			}
		}

		catch (Exception exc) {
			System.out.println(exc.getMessage());
			fg = 2;
		}


		pw.write(fg);
		pw.close();

		if(fg==0||fg==2)return "false";
		else return "true";
	}

}
