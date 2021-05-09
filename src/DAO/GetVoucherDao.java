package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetVoucherDao {
    DBConnection dao = new DBConnection();
    Connection con = dao.connect();

    public ResultSet getProductDetails(int pid) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select selling_cost, gst_per, discount from inventory where pid = "+pid);
        return rs;
    }
}
