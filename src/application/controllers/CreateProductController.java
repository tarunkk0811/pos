package application.controllers;

import java.sql.SQLException;

import DAO.SetProductsDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateProductController {
	
	ObservableList<String> product_types = FXCollections.observableArrayList("Finished Goods","Intermediate Product","Raw Material","Service");
	ObservableList<String> gst_types = FXCollections.observableArrayList("Capital goods","Exempted","Nil","Non-GST","Normal","Reverse Charge");
	ObservableList<String> units_types = FXCollections.observableArrayList("Kgs","Li","Ton","Bun");

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab products_tab, GST_tab, others_tab;
	
	@FXML
	private TextField name, alias, reorder, qunatity, buying_price, selling_price, hsn, gst_per, opening_stock,discount;
	
	@FXML
	private TextArea desc;

	@FXML
	private Button next_to_gst, next_to_others, create_product;

	@FXML
	private ComboBox<String> product_type, gst_type, units,account;

	@FXML
	private CheckBox inclusive_gst;

	
	@FXML
	public void initialize() {
		product_type.getItems().addAll(product_types);
		gst_type.getItems().addAll(gst_types);
		units.getItems().addAll(units_types);
		account.getItems().addAll(product_types);
		
		// setting default values
		discount.setText("0");
		opening_stock.setText("0");
		
		// listeners
		product_type.focusedProperty().addListener(e->{product_type.show();});
		gst_type.focusedProperty().addListener(e->{gst_type.show();});
		units.focusedProperty().addListener(e->{units.show();});
		account.focusedProperty().addListener(e->{account.show();});
		
	}
	
	@FXML
	void createProduct(ActionEvent event) throws SQLException {
		String p_name = name.getText();
		String p_alias = alias.getText();
		int p_reorder = Integer.parseInt(reorder.getText());
		String p_products_type = product_types.get(product_type.getSelectionModel().getSelectedIndex());
		String p_desc = desc.getText();
		
		String p_hsn = hsn.getText();
		String p_gst_type = gst_types.get(gst_type.getSelectionModel().getSelectedIndex());
		
		String p_units = units_types.get(units.getSelectionModel().getSelectedIndex());
		
		
		boolean inclusive = inclusive_gst.isSelected();
		
		
		int p_quantity = Integer.parseInt(qunatity.getText());
		float p_buying_price = Float.parseFloat(buying_price.getText());
		float p_selling_price = Float.parseFloat(selling_price.getText());
		
		float p_gst_per = Float.parseFloat(gst_per.getText());
		float p_disc = Float.parseFloat(discount.getText()); 
		
		int p_opening_stock = Integer.parseInt(opening_stock.getText());
		
		new SetProductsDao().setProduct(SessionController.cid,SessionController.fid,p_name, p_alias, p_hsn, p_desc, p_quantity, p_buying_price, p_selling_price,p_gst_type,p_gst_per, p_units, p_opening_stock, p_disc, inclusive,p_reorder,p_products_type);

		new ApplicationController().informationDialog("Product Added Successfully !", null);
		Stage curr = (Stage)anchorPane.getScene().getWindow();
		curr.close();
	}

	@FXML
	void goToGSTtab(ActionEvent event) {
		tabPane.getSelectionModel().select(GST_tab);
	}

	@FXML
	void goToOtherstab(ActionEvent event) {
		tabPane.getSelectionModel().select(others_tab);
	}

	@FXML
    void moveToNext(KeyEvent event) {
		if(event.getCode() == KeyCode.TAB) {
			desc.setText(desc.getText().replace("\t", ""));
			next_to_gst.requestFocus();
		}
    }
	
	@FXML
    void showItems(KeyEvent event) {
	  ComboBox<String> cbox = (ComboBox<String>)event.getSource();
	  new ApplicationController().showComboBoxItems(cbox, event);
    }
	

}
