package DAO;

import application.controllers.ApplicationController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SetPurchaseDao {
	DBConnection dao = new DBConnection();
	Connection con = dao.connect();
	public boolean addNewField(String field_name, String type, String default_value) throws SQLException {
		Statement stmt = con.createStatement();
		String query = "ALTER TABLE PURCHASE_VOUCHER ADD " + field_name;

		if(type == "Number"){
			query += " DECIMAL(10,2) DEFAULT ";
			query += (default_value.isEmpty()) ? default_value : String.valueOf(0);
		}
		else{
			query += " VARCHAR(1000) DEFAULT ";
			query += (default_value.isEmpty()) ? "\'NULL\'" : ("\'"+ default_value + "\'");
		}

		try {
			stmt.execute(query);
			return true;
		}catch (Exception e){
			new ApplicationController().errorDialog("Error: "+ e.getMessage(),null);
			return false;
		}
	}

    public boolean addNewColumn(String name, String default_value, String add_to) throws SQLException {
		Statement stmt = con.createStatement();
		String query = "ALTER TABLE PURCHASE_ITEM ADD " + name;

		if(!add_to.equalsIgnoreCase("None")){
			query += " DECIMAL(10,2) DEFAULT ";
			query += (default_value.isEmpty()) ? "0" : default_value;
		}
		else{
			query += " VARCHAR(1000) DEFAULT ";
			query += (default_value.isEmpty()) ? "\'NULL\'" : ("\'"+ default_value + "\'");
		}
		System.out.println(query);
		try {
			stmt.execute(query);
			return true;
		}catch (Exception e){
			new ApplicationController().errorDialog("Error: "+ e.getMessage(),null);
			return false;
		}
    }
}
