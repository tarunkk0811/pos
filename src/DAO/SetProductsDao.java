package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SetProductsDao {
	Connection con = new DBConnection().connect();
	public void setProduct(int cid,int fid, String name, String alias, String hsn, String desc, int qty, float buying_cost,
			float selling_cost,String gtype,float gper,String units,int opening_stock,
			float disc,boolean incl_gst,int reorder,String ptype) throws SQLException {
			
			String query = "INSERT INTO `pos`.`inventory`"
					+ "(`AID`,`CID`,`FID`,`NAME`,`ALIAS`,`HSN`,`DESCRIPTION`,`QUANTITY`,"
					+ "`BUYING_COST`,`SELLING_COST`,`GST_TYPE`,`GST_PER`,`UNITS`,"
					+ "`OPENING_STOCK`,`DISCOUNT`,`INCLUSIVE_GST`,`REORDER_LEVEL`,"
					+ "`PRODUCT_TYPE`,`DATE_UPDATED`)"
					+ "VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP);";

		PreparedStatement stmt = con.prepareStatement(query);

		stmt.setInt(1, cid);
		stmt.setInt(2, fid);
		stmt.setString(3, name);
		stmt.setString(4, alias);
		stmt.setString(5, hsn);
		stmt.setString(6, desc);
		stmt.setInt(7, qty);
		stmt.setFloat(8, buying_cost);
		stmt.setFloat(9, selling_cost);
		stmt.setString(10, gtype);
		stmt.setFloat(11, gper);
		stmt.setString(12, units);
		stmt.setInt(13, opening_stock);
		stmt.setFloat(14, disc);
		stmt.setBoolean(15, incl_gst);
		stmt.setInt(16, reorder);
		stmt.setString(17, ptype);
		
		
		stmt.execute();

	}

}
