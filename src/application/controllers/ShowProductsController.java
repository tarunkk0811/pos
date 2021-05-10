package application.controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.GetAccountsDao;
import DAO.GetProductsDao;
import DAO.SetAccountsDao;
import DAO.SetProductsDao;
import application.custom_properties.CustomTreeItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShowProductsController {


    @FXML
    private AnchorPane AnchroPane;
    
	@FXML
	private TreeView<String> showproductstv;

	@FXML
	private Button newbtn, editbtn, deletebtn, searchbtn;

	@FXML
	public void initialize() throws SQLException {
		CustomTreeItem root = new CustomTreeItem(String.format("%-40s%-25s%-6s","Product Name","Quantity","Selling Cost"));
		ResultSet rs = new GetProductsDao().getProducts(SessionController.cid);
		while (rs.next()) {
			int pid = rs.getInt(1);
			String name = "";

			name = String.format("%-40s%-25s%-6s", rs.getString(2), rs.getString(9), rs.getString(11));
			
			CustomTreeItem product = new CustomTreeItem(name);
			product.setId(pid);
			root.getChildren().add(product);
		}
		root.setExpanded(true);
		showproductstv.setRoot(root);
		//showproductstv.setShowRoot(false);

	}

	@FXML
	void createProduct(ActionEvent event) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/CreateProduct.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Scene scene = new Scene(root, 820, 500);
			stage.setScene(scene);
			stage.setTitle("Create new Product");
			stage.setResizable(false);
			stage.showAndWait();
		} catch (IOException e) {
			System.out.printf("Error occured: %s", e);
		}
		Parent root2 = FXMLLoader.load(getClass().getResource("/application/views/ShowProducts.fxml"));
		Scene scene = new Scene(root2);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		DashboardController.changeSceneTo(scene);
	}

	@FXML
	void deleteProduct(ActionEvent event) throws IOException, SQLException {
		CustomTreeItem selectedProd = (CustomTreeItem) showproductstv.getSelectionModel().getSelectedItem();
		if (selectedProd != null) {
			if (new ApplicationController().confirmationDialog("Are you sure, you want to delete ?", null))
				new SetProductsDao().deleteProduct(selectedProd.getId());
		}
		
		Parent root2 = FXMLLoader.load(getClass().getResource("/application/views/ShowProducts.fxml"));
		Scene scene = new Scene(root2);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		DashboardController.changeSceneTo(scene);
	}

	@FXML
	void editProduct(ActionEvent event) throws IOException {
		CustomTreeItem item = (CustomTreeItem) showproductstv.getSelectionModel().getSelectedItem();
		int pid = item.getId();
		SessionController.editpid = pid;
		Parent root = FXMLLoader.load(getClass().getResource("/application/views/CreateProduct.fxml"));
		Stage ccstage = new Stage();
		ccstage.initModality(Modality.APPLICATION_MODAL);
		Scene ccscene = new Scene(root, 820, 500);
		ccstage.setScene(ccscene);
		ccstage.setTitle("Edit Product");
		ccstage.setResizable(false);
		ccstage.showAndWait();
		SessionController.editpid = 0;
		Parent root2 = FXMLLoader.load(getClass().getResource("/application/views/ShowProducts.fxml"));
		Scene scene = new Scene(root2);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		DashboardController.changeSceneTo(scene);
	}

}
