package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetBanksDao {

    DBConnection dao = new DBConnection();
    Connection con = dao.connect();

    public ResultSet getAllBanks() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet bank_names_rs = stmt.executeQuery("select BANK_ID, BANK_NAME from bank");
        return bank_names_rs;
    }
    public ResultSet getCreatedBanks(int cid) throws SQLException
    {
        Statement stmt = con.createStatement();
        ResultSet existing_banks = stmt.executeQuery("select ba.BANK_NAME, bac.BID, bac.ACCOUNT_NUMBER, bac.BALANCE,acc.name from account acc,bank_account bac left join bank ba on bac.BANK_ID = ba.BANK_ID where bac.CID = "+cid+" and acc.aid=bac.aid");
        return existing_banks;
    }

    public ResultSet getBankDetails(int bid) throws SQLException
    {
        Statement stmt = con.createStatement();
        ResultSet bank_details = stmt.executeQuery("select ac.NAME, ba.BANK_NAME, bac.ACCOUNT_NUMBER, bac.IFSC, bac.BALANCE from bank_account as bac \n" +
                "left join bank as ba on bac.BANK_ID = ba.BANK_ID\n" +
                "left join account as ac on bac.AID = ac.AID; ");
        return bank_details;
    }
   
}
