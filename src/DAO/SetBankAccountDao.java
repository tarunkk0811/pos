package DAO;

import application.controllers.SessionController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SetBankAccountDao {
    Connection con = new DBConnection().connect();

    public void addBankAccount(String account_number, String ifsc_code, String account_balance,
                               Integer account_id, Integer bank_id) throws SQLException
    {
        Integer cid = SessionController.cid;
        System.out.println(cid);
        System.out.println(SessionController.fid);
        String query = "INSERT INTO `pos`.`bank_account`\n" +
                "(`AID`,\n" +
                "`CID`,\n" +
                "`BANK_ID`,\n" +
                "`ACCOUNT_NUMBER`,\n" +
                "`IFSC`,\n" +
                "`BALANCE`,\n" +
                "`DATE_UPDATED`)" +
                "VALUES\n" + "(?,?,?,?,?,?,CURRENT_TIMESTAMP);";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1,account_id);
        stmt.setInt(2,cid);
        stmt.setInt(3,bank_id);
        stmt.setString(4,account_number);
        stmt.setString(5,ifsc_code);
        stmt.setString(6,account_balance);

        stmt.execute();
    }
}
