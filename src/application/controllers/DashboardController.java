package application.controllers;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import application.*;


public class DashboardController {
	@FXML
    private MenuItem accounts;

    @FXML
    void createAccount(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/views/ShowAccounts.fxml"));
			Stage ccstage = new Stage();
			ccstage.initModality(Modality.APPLICATION_MODAL);
			Scene ccscene = new Scene(root, 820, 500);
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
    	}catch(Exception e){
    		
    	}
    	
    }
    
    @FXML	
    void createCompanyWindow(ActionEvent event) {
    	new ApplicationController().createCompanyWindow(event);
    }
    
    
}
