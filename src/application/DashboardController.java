package application;

import application.Main;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;

public class DashboardController {
	@FXML
    private MenuItem open_cmpy_btn;

    @FXML
    void showComapanies(ActionEvent event) {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
    		Scene scene = new Scene(root);
    		Main.changeSceneTo(scene);
    	}catch(Exception e){
    		
    	}
    	
    }
    
}
