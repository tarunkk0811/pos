package application.controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import DAO.GetCompaniesDao;
import DAO.SetCompanyDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import application.custom_properties.*;

public class MainController {

	// fxml instance variables
	@FXML
	private Button create_cmpy_btn, newFY;
	@FXML
	private Button sel, submitfy;
	@FXML
	public TreeView<?> tv;
	
	// variables
	public int fid;

	// fxml methods
	@FXML
	public void initialize() throws SQLException {
		CustomTreeItem root = new CustomTreeItem("Companies list");
		ResultSet rs = new GetCompaniesDao().getCompanies();

		while (rs.next()) {
			int cid = rs.getInt(1);
			String name = rs.getString(2);
			CustomTreeItem company = new CustomTreeItem(name);
			// company.setId(cid);

			ResultSet finres = new GetCompaniesDao().getFinYears(cid);

			while (finres.next()) {
				CustomTreeItem fyear = new CustomTreeItem(
						finres.getDate(3).toString() + "   To   " + finres.getDate(4).toString());
				fyear.setId(finres.getInt(2));
				company.getChildren().add(fyear);
			}
			company.setExpanded(true);
			root.getChildren().add(company);
			tv.setRoot(root);
			tv.setShowRoot(false);
		}

	}

	@FXML
	void showDashboard(ActionEvent event) {
		CustomTreeItem selecteddFinYear = (CustomTreeItem) tv.getSelectionModel().getSelectedItem();

		if (selecteddFinYear != null && selecteddFinYear.getId() != 0) {
			try {
				Stage primaryStage = (Stage) sel.getScene().getWindow();
				Parent root = FXMLLoader.load(getClass().getResource("/application/views/Dashboard.fxml"));
				SessionController.fid = selecteddFinYear.getId();
				Scene sc = new Scene(root);

				primaryStage.setScene(sc);
			} catch (Exception e) {
				System.out.printf("Error occured: %s", e);
			}
		}

	}

	@FXML
	void createCompanyWindow(ActionEvent event) {
		new ApplicationController().createCompanyWindow(event);
	}

	@FXML
	void newFinancialYear(ActionEvent event) {
		CustomTreeItem selectedItem = (CustomTreeItem) tv.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			if (selectedItem.getParent().getValue() == "Companies list") {
				selectedItem = (CustomTreeItem) selectedItem.getChildren().get(0);
			}
			
			fid = selectedItem.getId();
			NewFYController.InjectMainController(this);
			
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/application/views/NewFinYear.fxml"));
				Stage ccstage = new Stage();
				ccstage.initModality(Modality.APPLICATION_MODAL);
				Scene ccscene = new Scene(root, 500, 500);
				ccstage.setScene(ccscene);
				ccstage.setTitle("New Financial Year");
				ccstage.setResizable(false);
				ccstage.showAndWait();
			} catch (IOException e) {
				System.out.printf("Error occured: %s", e);
			}
		}
	}
	

	}
