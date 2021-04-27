package application.controllers;
import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
	
	
	public boolean confirmationDialog(String contentText,String headerText) {
		Alert confirmation = new Alert(AlertType.CONFIRMATION);
		confirmation.setTitle("Confirmation Dialog");
		confirmation.setHeaderText(headerText);
		confirmation.setContentText(contentText);
		Optional<ButtonType> action = confirmation.showAndWait();
		
		return action.get() == ButtonType.OK ? true : false;
	}
	
	public void informationDialog(String contentText,String headerText) {
		Alert information = new Alert(AlertType.INFORMATION);
		information.setTitle("Information Dialog");
		information.setHeaderText(headerText);
		information.setContentText(contentText);
		information.showAndWait();
	}

	public void errorDialog(String contentText,String headerText) {
		Alert error = new Alert(AlertType.ERROR);
		error.setTitle("Error Dialog");
		error.setHeaderText(headerText);
		error.setContentText(contentText);
		error.showAndWait();
	}

	public void showComboBoxItems(ComboBox<String> cbox,KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.DOWN) {
			cbox.show();
		}
	}
	
	
}
