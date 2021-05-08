package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetProductsDao {
	DBConnection dao = new DBConnection();
	Connection con = dao.connect();

	
	public ResultSet getProducts(int cid) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery("select *from inventory where cid=" +cid);
		return res;
	}
	
	public ResultSet getProduct(int pid) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery("select *from inventory where pid=" + pid);
		return res;
	}
	
	
}
