package application.controllers;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Border;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

import application.*;

public class DashboardController {
	private static Stage ccstage, products_stage;
	private static Scene ccscene, products_scene;

	@FXML
	private Border BorderPane;

	@FXML
	private MenuItem open_cmpy_btn, createcompanytb;

	@FXML
	private MenuItem accounts, products;

	@FXML
	void showAccounts(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/views/ShowAccounts.fxml"));
			ccstage = new Stage();
			ccstage.initModality(Modality.APPLICATION_MODAL);
			ccscene = new Scene(root, 820, 500);
			ccscene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			ccstage.setScene(ccscene);
			ccstage.setTitle("Accounts");
			ccstage.setResizable(false);
			ccstage.showAndWait();
		} catch (IOException e) {
			System.out.printf("Error occured: %s", e);
		}
	}

	@FXML
	void showComapanies(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/views/Main.fxml"));
			Scene scene = new Scene(root);
			Main.changeSceneTo(scene);
			SessionController.setFid(0);
		} catch (Exception e) {

		}

	}

	@FXML
	void createCompanyWindow(ActionEvent event) {
		new ApplicationController().createCompanyWindow(event);
	}

	public static void changeSceneTo(Scene sc) {
		ccstage.setScene(sc);
		ccstage.setResizable(false);
	}

	@FXML
	void showProducts(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/views/ShowProducts.fxml"));
			ccstage = new Stage();
			ccstage.initModality(Modality.APPLICATION_MODAL);
			ccscene = new Scene(root, 820, 500);
			ccstage.setScene(ccscene);
			ccscene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			ccstage.setTitle("Products");
			ccstage.setResizable(false);
			ccstage.showAndWait();
		} catch (IOException e) {
			System.out.printf("Error occured: %s", e);
		}
	}

	@FXML
	void createProduct(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/views/CreateProduct.fxml"));
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Scene scene = new Scene(root, 820, 500);
			stage.setScene(scene);
			stage.setTitle("Create Account");
			stage.setResizable(false);
			stage.showAndWait();
		} catch (IOException e) {
			System.out.printf("Error occured: %s", e);
		}
	}
	
	@FXML
	void createPurchaseVoucher(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/application/views/PurchaseVoucher.fxml"));
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		Scene scene = new Scene(root, 1280, 800);
		stage.setScene(scene);
		stage.setTitle("Purchase Voucher");
		stage.setResizable(false);
		stage.showAndWait();
	}

}
