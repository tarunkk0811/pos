package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetAccountsDao {
	
	Connection con = new DBConnection().connect();
	
	public ResultSet getStates() throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select distinct city_state from cities");
		return rs;
	}

	public ResultSet getCities(String state) throws SQLException {
		
		String query="select distinct city_name from cities where city_state=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, state);
		ResultSet rs=stmt.executeQuery();
		return rs;
	}

}
