package DAO;

import java.sql.Connection;

public class SetAccountsDao {
	
	Connection con = new DBConnection().connect();
	public void setAccount(String Aname,String Aphone,String Aadhaar,String Aemail ,String Acdays,String Agstin,String Aaddress,String Acountry,String Astate,String Acity ,String Aatype,String Abtype) {
		
		String query= "INSERT INTO `pos`.`account`\r\n"
				+ "(`CID`,\r\n"
				+ "`NAME`,\r\n"
				+ "`PHONE`,\r\n"
				+ "`ADDRESS`,\r\n"
				+ "`CITY`,\r\n"
				+ "`STATE`,\r\n"
				+ "`COUNTRY`,\r\n"
				+ "`ACCOUNT_TYPE`,\r\n"
				+ "`CREDIT_DAYS`,\r\n"
				+ "`ADHAAR`,\r\n"
				+ "`EMAIL`,\r\n"
				+ "`GSTIN`,\r\n"
				+ "`BUSINESS_TYPE`)\r\n"
				+ "VALUES\r\n"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)";
		
	}
}
