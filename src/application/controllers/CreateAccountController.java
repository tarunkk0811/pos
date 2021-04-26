package application.controllers;

import java.util.Collections;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

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
    private ComboBox<String> country,state,city,atype,btype;

    
    @FXML
    private Button next,submit;
    
    ObservableList<String> countries = FXCollections.observableArrayList();
	 
    ObservableList<String> states = FXCollections.observableArrayList("Andhra Pradesh",
            "Arunachal Pradesh",
            "Assam",
            "Bihar",
            "Chhattisgarh",
            "Goa",
            "Gujarat",
            "Haryana",
            "Himachal Pradesh",
            "Jammu and Kashmir",
            "Jharkhand",
            "Karnataka",
            "Kerala",
            "Madhya Pradesh",
            "Maharashtra",
            "Manipur",
            "Meghalaya",
            "Mizoram",
            "Nagaland",
            "Odisha",
            "Punjab",
            "Rajasthan",
            "Sikkim",
            "Tamil Nadu",
            "Telangana",
            "Tripura",
            "Uttarakhand",
            "Uttar Pradesh",
            "West Bengal",
            "Andaman and Nicobar Islands",
            "Chandigarh",
            "Dadra and Nagar Haveli",
            "Daman and Diu",
            "Delhi",
            "Lakshadweep",
            "Puducherry");
   
    @FXML
    public void initialize() {
    	
         Collections.sort(states);
        
         String[] locales = Locale.getISOCountries();
         for (String countryCode : locales) {
             Locale obj = new Locale("", countryCode);
            countries.add(obj.getDisplayCountry());
         }
        
        country.setEditable(true);
        state.setEditable(true);
        city.setEditable(true);
        country.getItems().addAll(countries);
        country.getSelectionModel().select("India");
        state.getItems().addAll(states);
        
        state.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
        	ObservableList<String> statescopy = FXCollections.observableArrayList(states);
        	String key = state.getEditor().getText();
        	int ascii=0;
        	try {
        	ascii= event.getText().charAt(0);
        	}
        	catch(Exception e){	
        		System.out.print("Cant convert to ascii due to index of input event in create account states");
        	}
       
        	if(ascii!=99 && key=="") {
        		state.getItems().clear();
        		state.getItems().addAll(statescopy);
        	}
        	else if(ascii>=65 && ascii<=122) {	
        	state= new ApplicationController().searchComboBox(event,state,statescopy);
            state.show();
        	}
        });   
    }


    @FXML
    void createAccount(ActionEvent event) {
    String Aname=name.getText();
    String Aphone=phone.getText();
    String Aadhaar=adhaar.getText();
    String Aemail=email.getText();
    String Acdays = cdays.getText();
    String Agstin = gstin.getText();
    String Aaddress= address.getText();
    

    }
    @FXML
    void goToGsttab(ActionEvent event) {
    	tabpane.getSelectionModel().select(gsttab);
    }
    	
	
}
