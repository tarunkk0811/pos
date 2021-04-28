package application.controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.GetAccountsDao;
import DAO.GetCompaniesDao;
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
	public void newAccount() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/views/CreateAccount.fxml"));
		Stage ccstage = new Stage();
		ccstage.initModality(Modality.APPLICATION_MODAL);
		Scene ccscene = new Scene(root, 820, 500);
		ccstage.setScene(ccscene);
		ccstage.setTitle("Create New Account");
		ccstage.setResizable(false);
		ccstage.showAndWait();
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
	}

}