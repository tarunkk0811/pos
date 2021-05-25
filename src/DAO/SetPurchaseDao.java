package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SetPurchaseDao {
	DBConnection dao = new DBConnection();
	Connection con = dao.connect();
	public boolean addNewField(String field_name, String type, String default_value) throws SQLException {
		Statement stmt = con.createStatement();
		String query = "ALTER TABLE PURCHASE_VOUCHER ADD " + field_name;

		if(type == "Number"){
			query += " DECIMAL(10,2) ";
			query += (default_value.isEmpty()) ? default_value : String.valueOf(0);
		}
		else{
			query += " TEXT ";
			query += (default_value.isEmpty()) ? "\'NULL\'" : ("\'"+ default_value + "\'");
		}
		System.out.println(query);
		return stmt.execute(query);
	}
}
