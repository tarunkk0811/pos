package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos", "root", "root");
			return con;
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public String capitalize(String word)
	{
		String nameCapitalized = "";
		String s1 = word.substring(0, 1).toUpperCase();
		nameCapitalized = s1 + word.substring(1).toLowerCase();
		return nameCapitalized;
	}
}
