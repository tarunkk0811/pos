package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MainController {
	 
	@FXML
    private Button create_cmpy_btn;
	@FXML
	 private Button sel;
	
	@FXML
	void showDashboard(ActionEvent event) {

		try {
			Stage primaryStage = (Stage)sel.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
			Scene sc = new Scene(root);
		
			primaryStage.setScene(sc);
			
			
		}catch(Exception e) {
			System.out.printf("Error occured: %s",e);
		}
		
	}
	
    @FXML
    void createCompanyWindow(ActionEvent event) {
    	new ApplicationController().createCompanyWindow(event);
    }
}
