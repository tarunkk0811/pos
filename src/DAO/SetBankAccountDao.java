package DAO;

import application.controllers.SessionController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SetBankAccountDao {
    DBConnection dao = new DBConnection();
    Connection con = dao.connect();

    public void addBankAccount(String account_number, String ifsc_code, String account_balance,
                               Integer account_id, Integer bank_id) throws SQLException
    {
        Integer cid = SessionController.cid;
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

    public void updateBankDetails(String account_number, String ifsc_code, String account_balance,
                                  Integer account_id, Integer bank_id) throws SQLException
    {
        Integer cid = SessionController.cid;
        Integer bid = SessionController.editbid;
        String query = "UPDATE `pos`.`bank_account`\n" +
                "SET\n" +
                "`AID` = ?,\n" +
                "`CID` = ?,\n" +
                "`BANK_ID` = ? ,\n" +
                "`ACCOUNT_NUMBER` = ? ,\n" +
                "`IFSC` = ?,\n" +
                "`BALANCE` = ? ,\n" +
                "`DATE_UPDATED` = CURRENT_TIMESTAMP\n" +
                "WHERE `BID` = ? ;";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1,account_id);
        stmt.setInt(2,cid);
        stmt.setInt(3,bank_id);
        stmt.setString(4,account_number);
        stmt.setString(5,ifsc_code);
        stmt.setString(6,account_balance);
        stmt.setInt(7,bid);

        stmt.execute();
        SessionController.editbid = 0;
    }

    public void deleteBank(int bid) throws SQLException
    {
        String query = "delete from bank_account where bid = "+bid;
        Statement stmt = con.createStatement();
        stmt.executeUpdate(query);
    }
}
