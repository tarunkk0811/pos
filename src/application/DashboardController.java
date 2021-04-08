package application;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class DashboardController {
	
    @FXML
    void showComapanies(ActionEvent event) {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
    		Scene scene = new Scene(root);
    		Main.changeSceneTo(scene);
    	}catch(Exception e){
    		
    	}
    	
    }
    
    @FXML	
    void createCompanyWindow(ActionEvent event) {
    	new ApplicationController().createCompanyWindow(event);
    }
    
    
}
