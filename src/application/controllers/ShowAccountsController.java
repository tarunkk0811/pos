package application.controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.GetAccountsDao;
import DAO.GetCompaniesDao;
import DAO.SetAccountsDao;
import application.Main;
import application.custom_properties.CustomTreeItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShowAccountsController {
	
	
	@FXML
	private TreeView<String> showaccountstv;

	@FXML
	private Button newbtn, editbtn, deletebtn, searchbtn;

	@FXML
	public void initialize() throws SQLException {
		CustomTreeItem root = new CustomTreeItem("Accounts");
		ResultSet rs = new GetAccountsDao().getAccounts(SessionController.cid);
		CustomTreeItem customers = new CustomTreeItem("Customers");
		CustomTreeItem vendors = new CustomTreeItem("Vendors");

		while (rs.next()) {
			int aid = rs.getInt(1);
			String name = "";

			name = String.format("%-30s%-25s%-6s", rs.getString(3), rs.getString(13), rs.getString(14));

			CustomTreeItem account = new CustomTreeItem(name);
			account.setId(aid);
			if (rs.getString(9).equals("Customer")) {
				customers.getChildren().add(account);
				// System.out.print("in cus");
			} else {
				vendors.getChildren().add(account);
			}
		}
		root.getChildren().addAll(vendors, customers);
		customers.setExpanded(true);
		vendors.setExpanded(true);
		root.setExpanded(true);
		showaccountstv.setRoot(root);
		showaccountstv.setShowRoot(false);
	}

	@FXML
	public void newAccount(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/views/CreateAccount.fxml"));
		Stage ccstage = new Stage();
		ccstage.initModality(Modality.APPLICATION_MODAL);
		Scene ccscene = new Scene(root, 820, 500);
		ccstage.setScene(ccscene);
		ccstage.setTitle("Create New Account");
		ccstage.setResizable(false);
		ccstage.showAndWait();
		//event.getScene().getWindow().setWidth(event.getScene().getWidth() + 0.001);
		//newbtn.getScene().getWindow().setWidth(newbtn.getScene().getWidth()+0.001);
		
		Parent root2 = FXMLLoader.load(getClass().getResource("/application/views/ShowAccounts.fxml"));
		Scene scene = new Scene(root2);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		DashboardController.changeSceneTo(scene);
	}

	@FXML
	public void editAccount(ActionEvent event) throws IOException {
		CustomTreeItem item = (CustomTreeItem) showaccountstv.getSelectionModel().getSelectedItem();
		int aid = item.getId();
		SessionController.editaid = aid;
		Parent root = FXMLLoader.load(getClass().getResource("/application/views/CreateAccount.fxml"));
		Stage ccstage = new Stage();
		ccstage.initModality(Modality.APPLICATION_MODAL);
		Scene ccscene = new Scene(root, 820, 500);
		ccstage.setScene(ccscene);
		ccstage.setTitle("Edit Account");
		ccstage.setResizable(false);
		ccstage.showAndWait();
		//editbtn.getScene().getWindow().setWidth(editbtn.getScene().getWidth()+0.001);
		SessionController.editaid = 0;
		Parent root2 = FXMLLoader.load(getClass().getResource("/application/views/ShowAccounts.fxml"));
		Scene scene = new Scene(root2);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		DashboardController.changeSceneTo(scene);
	}
	
	@FXML
	public void deleteAccount(ActionEvent event) throws SQLException, IOException{
		CustomTreeItem itemtodel = (CustomTreeItem)showaccountstv.getSelectionModel().getSelectedItem();
		if(itemtodel!=null) {
			if(new ApplicationController().confirmationDialog("Are you sure, you want to delete ?", null))
				new SetAccountsDao().deleteAccount(itemtodel.getId());
		}
		Parent root2 = FXMLLoader.load(getClass().getResource("/application/views/ShowAccounts.fxml"));
		Scene scene = new Scene(root2);
		
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		DashboardController.changeSceneTo(scene);
	}

}
