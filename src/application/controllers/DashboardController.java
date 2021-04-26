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

	@FXML
	private Border BorderPane;

	@FXML
	private MenuItem open_cmpy_btn, createcompanytb;

	@FXML
	private MenuItem products;

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

}
