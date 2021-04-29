package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import application.controllers.SessionController;

public class SetAccountsDao {

	Connection con = new DBConnection().connect();

	public void setAccount(String Aname, String Aphone, String Aadhaar, String Aemail, String Acdays, String Agstin,
			String Aaddress, String Acountry, String Astate, String Acity, String Aatype, String Abtype)
			throws SQLException {

		String query = "INSERT INTO `pos`.`account`\r\n" + "(`CID`,\r\n" + "`NAME`,\r\n" + "`PHONE`,\r\n"
				+ "`ADDRESS`,\r\n" + "`CITY`,\r\n" + "`STATE`,\r\n" + "`COUNTRY`,\r\n" + "`ACCOUNT_TYPE`,\r\n"
				+ "`CREDIT_DAYS`,\r\n" + "`ADHAAR`,\r\n" + "`EMAIL`,\r\n" + "`GSTIN`,\r\n" + "`BUSINESS_TYPE`,\r\n"
				+ "`DATE_UPDATED`)" + "VALUES\r\n" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)";

		PreparedStatement stmt = con.prepareStatement(query);
		int cid = SessionController.cid;
		System.out.println(cid);
		stmt.setInt(1, cid);
		stmt.setString(2, Aname);
		stmt.setString(3, Aphone);
		stmt.setString(4, Aaddress);
		stmt.setString(5, Acity);
		stmt.setString(6, Astate);
		stmt.setString(7, Acountry);
		stmt.setString(8, Aatype);
		stmt.setString(9, Acdays);
		stmt.setString(10, Aadhaar);
		stmt.setString(11, Aemail);
		stmt.setString(12, Agstin);
		stmt.setString(13, Abtype);

		stmt.execute();

	}

	public void updateAccount(String Aname, String Aphone, String Aadhaar, String Aemail, String Acdays, String Agstin,
			String Aaddress, String Acountry, String Astate, String Acity, String Aatype, String Abtype)
			throws SQLException {
		String query = "UPDATE `pos`.`account`\r\n" + "SET\r\n" + "`NAME` =?,\r\n" + "`PHONE` = ?,\r\n"
				+ "`ADDRESS` = ?,\r\n" + "`CITY` = ?,\r\n" + "`STATE` = ?,\r\n" + "`COUNTRY` = ?,\r\n"
				+ "`ACCOUNT_TYPE` =?,\r\n" + "`CREDIT_DAYS` = ?,\r\n" + "`ADHAAR` = ?,\r\n" + "`EMAIL` = ?,\r\n"
				+ "`GSTIN` = ?,\r\n" + "`BUSINESS_TYPE` = ?,\r\n" + "`DATE_UPDATED` = CURRENT_TIMESTAMP \r\n"
				+ "WHERE `AID` = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, Aname);
		stmt.setString(2, Aphone);
		stmt.setString(3, Aaddress);
		stmt.setString(4, Acity);
		stmt.setString(5, Astate);
		stmt.setString(6, Acountry);
		stmt.setString(7, Aatype);
		stmt.setString(8, Acdays);
		stmt.setString(9, Aadhaar);
		stmt.setString(10, Aemail);
		stmt.setString(11, Agstin);
		stmt.setString(12, Abtype);
		stmt.setInt(13, SessionController.editaid);

		stmt.executeUpdate();
		SessionController.editaid = 0;
	}
	public void deleteAccount(int aid) throws SQLException{
		String query="DELETE FROM `pos`.`account`\r\n"
				+ "WHERE aid="+aid;
		Statement stmt = con.createStatement();
		stmt.executeUpdate(query);
	}
}
