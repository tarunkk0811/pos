package application.controllers;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Border;
import application.*;


public class DashboardController {
	
	@FXML
    private Border BorderPane;

	 @FXML
	 private MenuItem open_cmpy_btn, createcompanytb;

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
