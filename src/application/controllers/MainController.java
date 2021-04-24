package application.controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.DeleteDao;
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
import application.Main;
import application.custom_properties.*;

public class MainController {

	// fxml instance variables
	@FXML
	private Button create_cmpy_btn, newFY, delete_btn;
	@FXML
	private Button sel, submitfy;
	@FXML
	public TreeView<?> tv;

	// variables
	public int fid, cid;

	// fxml methods
	@FXML
	public void initialize() throws SQLException {
		// injecting the main controller object
		NewFYController.InjectMainController(this);

		CustomTreeItem root = new CustomTreeItem("Companies list");
		ResultSet rs = new GetCompaniesDao().getCompanies();

		while (rs.next()) {
			int cid = rs.getInt(1);
			String name = rs.getString(2);
			CustomTreeItem company = new CustomTreeItem(name);
			company.setId(cid);

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
				SessionController.setSession(selecteddFinYear.getId(),primaryStage);
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
				cid = selectedItem.getId();
			} else {
				selectedItem = (CustomTreeItem) selectedItem.getParent();
				cid = selectedItem.getId();
			}
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

	@FXML
	void delete(ActionEvent event) throws SQLException, IOException {
		ApplicationController app = new ApplicationController();
		if (app.confirmationDialog("Are you sure to delete?", null)) {
			CustomTreeItem selectedItem = (CustomTreeItem) tv.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				boolean status;
				if (selectedItem.getParent().getValue() == "Companies list") {
					cid = selectedItem.getId();
					status = new DeleteDao().hideCompany(cid);
				} else {
					fid = selectedItem.getId();
					status = new DeleteDao().hideFinancialYear(fid);
				}

				Parent root = FXMLLoader.load(getClass().getResource("/application/views/Main.fxml"));
				Scene scene = new Scene(root);
				Main.changeSceneTo(scene);
				if(status)
					app.informationDialog("Deleted Successfully", null);
				else
					app.errorDialog("Deletion unsuccessfull", null);
			}
			
		}
	}

}
