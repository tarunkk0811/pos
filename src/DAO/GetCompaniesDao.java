package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetCompaniesDao {
	
	Connection con = new DBConnection().connect();
	
	public ResultSet getCompanies() throws SQLException {
		Statement listcompanies = con.createStatement();
		String query="select cid,name from company";
		ResultSet rs = listcompanies.executeQuery(query);
		return rs;
	}
	
	public ResultSet getFinYears(int cid) throws SQLException{
		Statement listfinyears = con.createStatement();
		String query = "select * from financial_year where cid ="+cid;
		ResultSet rs = listfinyears.executeQuery(query);
		return rs;
	}
	
	
	
}
