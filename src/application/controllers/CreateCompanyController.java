package application.controllers;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import application.*;

public class CreateCompanyController {
	
	    @FXML
	    private AnchorPane ap;
	   
	    @FXML
	    private Button ccbtn;
	    
	    @FXML
	    void createCompany(ActionEvent event) throws IOException {
		   Stage cur_stage = (Stage)ccbtn.getScene().getWindow();
		   
		   Parent root = FXMLLoader.load(getClass().getResource("/application/views/Main.fxml"));
   		   Scene scene = new Scene(root);
   		   Main.changeSceneTo(scene);
   		   cur_stage.close();
	    }

}
