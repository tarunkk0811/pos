package application.controllers;

import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.conf.StringProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;



public class PurchaseVoucherController {

	    @FXML
	    private TableView<String> purchasetv;

	    @FXML
	    private TableColumn<String, TextField> sno, qty,rate,gross,disc,cgst,sgst,igst,ocharges,cess,taxable_value;

	    @FXML
	    private TableColumn<String, ComboBox<String>> item,type_of_purchase;
	    
	    ObservableList<String> items = FXCollections.observableArrayList();
	    
	    public void initialize() {
	    	items.add("1");
	    	items.add("xyz");
	    	purchasetv.setItems(items);
	    	
	    }
}
