package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetVoucherDao {
    DBConnection dao = new DBConnection();
    Connection con = dao.connect();

    public int getVno() throws SQLException {
        int vno = 0;
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery("select count(purchase_voucher_id) from purchase_voucher");
        if(res.next())
            vno=res.getInt(1);
        return vno;
    }


}
