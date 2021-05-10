package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SetProductsDao {
	DBConnection dao = new DBConnection();
	Connection con = dao.connect();
	public void setProduct(int aid, int cid, int fid, String name, String alias, String hsn, String desc, float qty, float buying_cost,
                           float selling_cost, String gtype, float gper, String units, int opening_stock,
                           float disc, boolean incl_gst, int reorder, String ptype) throws SQLException {
			
			String query = "INSERT INTO `pos`.`inventory`"
					+ "(`AID`,`CID`,`FID`,`NAME`,`ALIAS`,`HSN`,`DESCRIPTION`,`QUANTITY`,"
					+ "`BUYING_COST`,`SELLING_COST`,`GST_TYPE`,`GST_PER`,`UNITS`,"
					+ "`OPENING_STOCK`,`DISCOUNT`,`INCLUSIVE_GST`,`REORDER_LEVEL`,"
					+ "`PRODUCT_TYPE`,`DATE_UPDATED`)"
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP);";

		PreparedStatement stmt = con.prepareStatement(query);

		stmt.setInt(1, aid);
		stmt.setInt(2, cid);
		stmt.setInt(3, fid);
		stmt.setString(4, dao.capitalize(name));
		stmt.setString(5, dao.capitalize(alias));
		stmt.setString(6, hsn);
		stmt.setString(7, desc);
		stmt.setFloat(8, qty);
		stmt.setFloat(9, buying_cost);
		stmt.setFloat(10, selling_cost);
		stmt.setString(11, gtype);
		stmt.setFloat(12, gper);
		stmt.setString(13, units);
		stmt.setInt(14, opening_stock);
		stmt.setFloat(15, disc);
		stmt.setBoolean(16, incl_gst);
		stmt.setInt(17, reorder);
		stmt.setString(18, ptype);
		
		
		stmt.execute();

	}
	
	public void updateProduct(int aid, int pid, String name, String alias, String hsn, String desc, float qty, float buying_cost,
							  float selling_cost, String gtype, float gper, String units, int opening_stock,
							  float disc, boolean incl_gst, int reorder, String ptype) throws SQLException {
		String query = "UPDATE `pos`.`inventory` SET `NAME` = ?,`HSN` = ?,"
				+ "`ALIAS` = ?,`DESCRIPTION` = ?,`QUANTITY` = ?,`BUYING_COST` = ?,`SELLING_COST` = ?,"
				+ "`GST_TYPE` = ?,`GST_PER` = ?,`UNITS` = ?,`OPENING_STOCK` = ?,`INCLUSIVE_GST` = ?,`DISCOUNT` = ?,"
				+ "`REORDER_LEVEL` = ?,`PRODUCT_TYPE` = ?, `AID` = ?,`DATE_UPDATED` = CURRENT_TIMESTAMP "
				+ " WHERE `PID` = ?;";

		PreparedStatement stmt = con.prepareStatement(query);

		stmt.setString(1, dao.capitalize(name));
		stmt.setString(2, hsn);
		stmt.setString(3, dao.capitalize(alias));
		stmt.setString(4, desc);
		stmt.setFloat(5, qty);
		stmt.setFloat(6, buying_cost);
		stmt.setFloat(7, selling_cost);
		stmt.setString(8, gtype);
		stmt.setFloat(9, gper);
		stmt.setString(10, units);
		stmt.setInt(11, opening_stock);
		stmt.setBoolean(12, incl_gst);
		stmt.setFloat(13, disc);
		stmt.setInt(14, reorder);
		stmt.setString(15, ptype);
		stmt.setInt(16,aid);
		stmt.setInt(17, pid);
		
		
		stmt.execute();


	}
	
	public void deleteProduct(int pid) throws SQLException{
		String query="DELETE FROM `pos`.`inventory`\r\n"
				+ "WHERE pid="+pid;
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
	}

}
