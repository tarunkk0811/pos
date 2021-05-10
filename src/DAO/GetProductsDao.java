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

	public boolean checkItemExists(String product) throws SQLException {
		//String query = "Select pid from inventory where name = ?";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select pid from inventory where name = '"+product+"'");
		if(rs.next())
			return true;
		return false;
	}
	public ResultSet getProductDetails(int pid) throws SQLException {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select selling_cost, gst_per, discount from inventory where pid = "+pid);
		return rs;
	}
}
