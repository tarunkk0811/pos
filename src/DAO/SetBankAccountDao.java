package DAO;

import application.controllers.SessionController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SetBankAccountDao {
    Connection con = new DBConnection().connect();

    public void addBankAccount(String accountNumber, String ifscCode, String accountBalance,
                               Integer accountId, Integer bankId) throws SQLException
    {
        Integer cid = SessionController.cid;
        System.out.println(cid);
        System.out.println(SessionController.fid);
        String query = "INSERT INTO `pos`.`bank_account`\n" +
                "(`AID`,\n" +
                "`CID`,\n" +
                "`BANK_ID`,\n" +
                "`ACCOUNT_NUMBER`,\n" +
                "`ISFC`,\n" +
                "`BALANCE`,\n" +
                "`DATE_UPDATED`)" +
                "VALUES\n" + "(?,?,?,?,?,?,CURRENT_TIMESTAMP);";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1,accountId);
        stmt.setInt(2,cid);
        stmt.setInt(3,bankId);
        stmt.setString(4,accountNumber);
        stmt.setString(5,ifscCode);
        stmt.setString(6,accountBalance);

        stmt.execute();
    }
}
