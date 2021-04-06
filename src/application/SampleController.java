package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SampleController {
	 
	@FXML
    private Button create_cmpy_btn;
	@FXML
	 private Button sel;
	
	@FXML
	void print(ActionEvent event) {
		System.out.print("Selected company");
	}
	
    @FXML
    void showCreateCmpy(ActionEvent event) {
    	
    }
}
