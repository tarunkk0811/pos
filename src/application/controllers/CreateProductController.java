package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CreateProductController {

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab products_tab, GST_tab, others_tab;
	
	@FXML
	private TextField name, alias, reorder, qunatity, buying_price, selling_price, hsn, gst_per, opening_stock;
	
	@FXML
	private TextArea desc;

	@FXML
	private Button next_to_gst, next_to_others, create_product;

	@FXML
	private ComboBox<?> product_type, gst_type, units;

	@FXML
	private CheckBox inclusive_gst;

	@FXML
	void createProduct(ActionEvent event) {

	}

	@FXML
	void goToGSTtab(ActionEvent event) {
		tabPane.getSelectionModel().select(GST_tab);
	}

	@FXML
	void goToOtherstab(ActionEvent event) {
		tabPane.getSelectionModel().select(others_tab);
	}

}
