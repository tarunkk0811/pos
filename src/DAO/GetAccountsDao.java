package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetAccountsDao {

	DBConnection dao = new DBConnection();
	Connection con = dao.connect();

	public ResultSet getStates() throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet get_states = stmt.executeQuery("select distinct city_state from cities");
		return get_states;
	}

	public ResultSet getCities(String state) throws SQLException {

		String query = "select distinct city_name from cities where city_state=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, state);
		ResultSet get_cities=stmt.executeQuery();
		return get_cities;
	}

	public ResultSet getAccounts(int cid) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet get_accounts=stmt.executeQuery("select *from account where cid="+cid);
		return get_accounts;
		}

	public ResultSet getAllAccounts() throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet get_all_accounts = stmt.executeQuery("select AID, NAME from account");
		return get_all_accounts;
	}

	public ResultSet getAccountDetails(int aid) throws SQLException {
		ResultSet rs = null;
		Statement stmt = con.createStatement();
		System.out.println(aid);
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
	
	public ResultSet getVendors(int cid) throws SQLException {
		ResultSet rs = null;
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery("select aid,name from account where ACCOUNT_TYPE='Vendor' AND cid =" + cid);
		return rs;
	}
	
	public ResultSet getCustomers(int cid) throws SQLException {
		ResultSet rs = null;
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery("select aid,name from account where ACCOUNT_TYPE='Customer' AND cid =" + cid);
		return rs;
	}
}