package application.controllers;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ApplicationController {
	
	public void createCompanyWindow(ActionEvent event) {
		try {
    		Parent root = FXMLLoader.load(getClass().getResource("/application/views/CreateCompany.fxml"));
			Stage ccstage =new Stage();
			ccstage.initModality(Modality.APPLICATION_MODAL);
			Scene ccscene = new Scene(root,800,800);
			ccstage.setScene(ccscene);
			ccstage.setTitle("Create Company");
			ccstage.setResizable(false);
			ccstage.showAndWait();
		} catch (IOException e) {
			System.out.printf("Error occured: %s",e);
		}
	}
	
	
	
	
	

}