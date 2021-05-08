package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SetCompanyDao {
	DBConnection dao = new DBConnection();
	Connection con = dao.connect();

	public void setCompany(String name, String address, String gstin, String phone, String email, String password,
			String cpassword) throws SQLException {
		String cc_query = "INSERT INTO `pos`.`company`\r\n" + "(`NAME`,\r\n" + "`ADDRESS`,\r\n" + "`GSTIN`,\r\n"
				+ "`PHONE_NUMBER`,\r\n" + "`EMAIL`,\r\n" + "`COMPANY_PASSWORD`,\r\n" + "`DATE_UPDATED`)\r\n"
				+ "VALUES(?,?,?,?,?,?,CURRENT_TIMESTAMP)";

		PreparedStatement stmt = con.prepareStatement(cc_query);

		stmt.setString(1, name);
		stmt.setString(2, address);
		stmt.setString(3, gstin);
		stmt.setString(4, phone);
		stmt.setString(5, email);
		stmt.setString(6, password);
		stmt.execute();

	}

	public ResultSet getLastid() throws SQLException {
		String get_id_query = "SELECT cid from pos.company order by cid desc limit 1";
		Statement getid = con.createStatement();
		ResultSet rs = getid.executeQuery(get_id_query);
		return rs;
	}

	public void setFinYear(int cid, java.sql.Date fromdt, java.sql.Date todt) throws SQLException

	{

		String fin_year_query = "INSERT INTO `pos`.`financial_year`\r\n" + "(`CID`,\r\n" + "`FROM_YEAR`,\r\n"
				+ "`TO_YEAR`,\r\n" + "`DATE_UPDATED`)\r\n" + "VALUES(?,?,?,CURRENT_TIMESTAMP)";

		PreparedStatement stmt2 = con.prepareStatement(fin_year_query);
		stmt2.setInt(1, cid);
		stmt2.setDate(2, fromdt);
		stmt2.setDate(3, todt);
		
		System.out.println(stmt2);
		
		stmt2.execute();
		con.close();
	}
}
