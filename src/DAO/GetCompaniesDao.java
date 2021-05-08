package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetCompaniesDao {

	DBConnection dao = new DBConnection();
	Connection con = dao.connect();
	
	public ResultSet getCompanies() throws SQLException {
		Statement listcompanies = con.createStatement();
		String query="select cid,name from company where visibility=true";
		ResultSet rs = listcompanies.executeQuery(query);
		return rs;
	}
	
	public ResultSet getFinYears(int cid) throws SQLException{
		Statement listfinyears = con.createStatement();
		String query = "select * from financial_year where visibility=true and cid ="+cid;
		ResultSet rs = listfinyears.executeQuery(query);
		return rs;
	}
	
	public int getCid(int fid) throws SQLException {
		Statement stmt = con.createStatement();
		String query = "select cid from financial_year where visibility=true and fid=" + fid;
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		return rs.getInt(1);
	}
	
	public String getComapanyName(int cid) throws SQLException {
		Statement stmt = con.createStatement();
		String query = "select name from company where cid=" + cid;
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		return rs.getString(1);
	}
	
	public String getFinancialYearString(int fid) throws SQLException {
		Statement stmt = con.createStatement();
		String query = "select from_year,to_year from financial_year where fid=" + fid;
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		return rs.getString(1) + " - " + rs.getString(2);
	}
	
	
	
}
