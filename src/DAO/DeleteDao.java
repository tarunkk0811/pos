package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteDao {
	DBConnection dao = new DBConnection();
	Connection con = dao.connect();
	
	public boolean hideCompany(int cid) throws SQLException {
		Statement stmt = con.createStatement();
		String query = "UPDATE company set visibility=false where cid=" + cid + ";";
		// hideFinancialYears(cid);
		int rows_effected = stmt.executeUpdate(query);
		return rows_effected > 0 ? true : false;
	}
	
	public boolean hideFinancialYears(int cid) throws SQLException {
		Statement stmt = con.createStatement();
		String query = "DELETE FROM financial_year where cid=" + cid +";";
		int rows_effected = stmt.executeUpdate(query);
		return rows_effected > 0 ? true : false;
	}
	
	
	public boolean hideFinancialYear(int fid) throws SQLException {
		Statement stmt = con.createStatement();
		String query = "UPDATE financial_year set visibility=false where fid=" + fid + ";";
		int rows_effected = stmt.executeUpdate(query);
		return rows_effected > 0 ? true : false;
	}
}
