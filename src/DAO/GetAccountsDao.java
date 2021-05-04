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

		String query = "select distinct city_name from cities where city_state=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, state);
		ResultSet rs = stmt.executeQuery();
		return rs;
	}

	public ResultSet getAccounts(int cid) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery("select *from account where cid=" + cid);
		return res;
	}

	public ResultSet getAccountDetails(int aid) throws SQLException {
		ResultSet rs = null;
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery("select * from account where aid =" + aid);
		return rs;
	}
	
	public int getAccount(String acc_name) throws SQLException {
		String query = "select aid from account where name=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, acc_name);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	
}
