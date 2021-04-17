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
}
