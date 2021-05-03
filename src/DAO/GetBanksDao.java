package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetBanksDao {

    Connection con = new DBConnection().connect();

    public ResultSet getAllBanks() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet bank_names_rs = stmt.executeQuery("select BANK_ID, BANK_NAME from bank");
        return bank_names_rs;
    }
}
