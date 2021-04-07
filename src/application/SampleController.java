package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SampleController {
	 
	@FXML
    private Button create_cmpy_btn;
	@FXML
	 private Button sel;
	
	@FXML
	void print(ActionEvent event) throws IOException {
		
		
	}
	
    @FXML
    void showCreateCmpy(ActionEvent event) {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("CreateCompany.fxml"));
			Stage ccstage =new Stage();
			ccstage.initModality(Modality.APPLICATION_MODAL);
			Scene ccscene = new Scene(root,600,600);
			ccstage.setScene(ccscene);
			ccstage.setTitle("Create Company");
			ccstage.setResizable(false);
			ccstage.showAndWait();
		} catch (IOException e) {
			System.out.printf("Error occured: %s",e);
		}
    }
}
