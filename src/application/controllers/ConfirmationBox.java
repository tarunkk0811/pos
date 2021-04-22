package application.controllers;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ConfirmationBox {
	
	boolean response;
	
	public ConfirmationBox(String text) {
		this.textid.setText(text);
	}

	@FXML
	private Label textid;

	@FXML
	private Button yes_btn, no_btn;

	@FXML
	void onResponse(ActionEvent event) {
		System.out.println(event.getSource());
		if(event.getSource() == yes_btn) {
			System.out.println("Yes");
			response = true;
		}else {
			System.out.println("No");
			response = false;
		}
	}

	public boolean getResponse() {
		return response;
	}

	
	
}
