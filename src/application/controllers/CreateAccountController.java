package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateAccountController {
	@FXML
    private Tab accountstab,gsttab;
	
  @FXML
    private TabPane tabpane;


    @FXML
    private TextField name,phone,adhaar,email,cdays,gstin;
    
    @FXML
    private TextArea address;


    @FXML
    private ChoiceBox<?> country,state,city,account_type,btype;

    
    @FXML
    private Button next,submit;

    

    @FXML
    void createAccount(ActionEvent event) {
    	

    }
    @FXML
    void goToGsttab(ActionEvent event) {
    	tabpane.getSelectionModel().select(gsttab);
    }
    	
	
}
