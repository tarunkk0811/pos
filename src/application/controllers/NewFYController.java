package application.controllers;

import java.io.IOException;
import java.sql.SQLException;

import DAO.GetCompaniesDao;
import DAO.SetCompanyDao;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

public class NewFYController {
	
	@FXML
	private DatePicker newfromdt, newtodt;
	@FXML
	private Button submitfy;
	
	private static MainController main;
	
	
	public static void InjectMainController(MainController m) {
		main = m;
	}
	
	@FXML
	void addNewFY(ActionEvent event) throws SQLException, IOException {
		Object object = null;
		
		java.sql.Date fromdt = java.sql.Date.valueOf(newfromdt.getValue());
		java.sql.Date todt = java.sql.Date.valueOf(newtodt.getValue());
		int cid = main.cid;
		new SetCompanyDao().setFinYear(cid, fromdt, todt);
		Stage fin = (Stage) submitfy.getScene().getWindow();
		fin.close();

		Parent root = FXMLLoader.load(getClass().getResource("/application/views/Main.fxml"));
		Scene scene = new Scene(root);
		Main.changeSceneTo(scene);

	}

}
