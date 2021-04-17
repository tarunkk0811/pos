package application.controllers;
import java.io.IOException;
import java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import application.*;
import DAO.DBConnection;

public class CreateCompanyController {
	
	   @FXML
	    private TextField cname,ccpassword,caddress,cgstin,cphone,cemail,cpassword;
	   @FXML
	    private DatePicker fromdate,todate;
	   @FXML
	    private Button ccbtn;
	   
	   int cid;
	

	    
	    @FXML
	    void createCompany(ActionEvent event) throws IOException, SQLException {
		   Stage cur_stage = (Stage)ccbtn.getScene().getWindow();
		   String name = cname.getText();
		   String address = caddress.getText();
		   String gstin=cgstin.getText();
		   String phone = cphone.getText();
		   String email = cemail.getText();
		   String password = cpassword.getText();
		   String cpassword = ccpassword.getText();

		 
		   java.sql.Date fromdt = java.sql.Date.valueOf(fromdate.getValue());
		   java.sql.Date todt = java.sql.Date.valueOf(todate.getValue());
		   String cc_query="INSERT INTO `pos`.`company`\r\n"
		   		+ "(`NAME`,\r\n"
		   		+ "`ADDRESS`,\r\n"
		   		+ "`GSTIN`,\r\n"
		   		+ "`PHONE_NUMBER`,\r\n"
		   		+ "`EMAIL`,\r\n"
		   		+ "`COMPANY_PASSWORD`,\r\n"
		   		+ "`DATE_UPDATED`)\r\n"
		   		+ "VALUES(?,?,?,?,?,?,CURRENT_TIMESTAMP)";
		   
		   String get_id_query="SELECT LAST_INSERT_ID()";
		   
		   String fin_year_query="INSERT INTO `pos`.`financial_year`\r\n"
		   		+ "(`CID`,\r\n"
		   		+ "`FROM_YEAR`,\r\n"
		   		+ "`TO_YEAR`,\r\n"
		   		+ "`DATE_UPDATED`)\r\n"
		   		+ "VALUES(?,?,?,CURRENT_TIMESTAMP)";
		   
		   //connect to database
		   Connection con = new DBConnection().connect();
		   PreparedStatement stmt = con.prepareStatement(cc_query);
		   PreparedStatement stmt2 = con.prepareStatement(fin_year_query);
		   stmt.setString(1,name);
		   stmt.setString(2, address);
		   stmt.setString(3,gstin);
		   stmt.setString(4, phone);
		   stmt.setString(5,email);
		   stmt.setString(6, password);
		   stmt.execute();
		   
		   Statement getid = con.createStatement();
		   ResultSet rs = getid.executeQuery(get_id_query);
		   while(rs.next()) {
			   cid =rs.getInt(1);
		   }
		   stmt2.setInt(1, cid);
		   stmt2.setDate(2,fromdt);
		   stmt2.setDate(3,todt);
		   stmt2.execute();

		   cur_stage.close();
		   
		 
		   Parent root = FXMLLoader.load(getClass().getResource("/application/views/Main.fxml"));
   		   Scene scene = new Scene(root);
   		   Main.changeSceneTo(scene);
   		  
	    }

}
