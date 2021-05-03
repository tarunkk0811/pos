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
		ResultSet get_states = stmt.executeQuery("select distinct city_state from cities");
		return get_states;
	}

	public ResultSet getCities(String state) throws SQLException {
		
		String query="select distinct city_name from cities where city_state=?";
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

	public ResultSet getAccountDetails(int aid) throws SQLException{
		ResultSet get_account_details = null;
		Statement stmt = con.createStatement();
		get_account_details=stmt.executeQuery("select * from account where aid ="+aid);
		return get_account_details;
	}

}
