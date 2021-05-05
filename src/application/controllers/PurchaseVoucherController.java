package application.controllers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.mysql.cj.conf.StringProperty;

import DAO.GetProductsDao;
import application.custom_properties.PurchaseItem;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;



public class PurchaseVoucherController {

	    @FXML
	    private TableView purchasetv;
	    
	    @FXML
	    private TableColumn<PurchaseItem, String> sno_col;

	    @FXML
	    private TableColumn<PurchaseItem, String> qty_col,rate_col,gross_col,disc_col,cgst_col,sgst_col,igst_col,ocharges_col,cess_col,taxable_value_col;

	    @FXML
	    private TableColumn<PurchaseItem, ComboBox> item_col,type_of_purchase_col;
	    
	    ObservableList<PurchaseItem> itemlist = FXCollections.observableArrayList();
	    
	    ObservableList<String> items = FXCollections.observableArrayList();
	    ObservableList<String> type_of_purchase = FXCollections.observableArrayList();
	    
	    HashMap<String,Integer> items_with_ids = new HashMap<String, Integer>();
	    HashMap<String,Integer> Accounts_with_ids = new HashMap<String, Integer>();
	    public void initialize() throws Exception {
	    	//////////////////////////////////////////////////////////////////////////////
	    	//ResultSet res = new GetProductsDao().getProducts(SessionController.cid);
	    	ResultSet res = new GetProductsDao().getProducts(2);
	    	while(res.next()) {
	    		items_with_ids.put(res.getString(2), res.getInt(1));
	    		items.add(res.getString(2));
	    	}
	    	Collections.sort(items);
	    	type_of_purchase.add("Taxable Supply");
	    	type_of_purchase.add("CC Exempt supply");
	    	type_of_purchase.add("Exemp Supply");
	    	type_of_purchase.add("CC Non Business");
	    	type_of_purchase.add("Non Business");
	    	type_of_purchase.add("Ineligible Credit Section");
	    	type_of_purchase.add("CR Not available");
	    	
	    	for(int i=0;i<500;i++)
	    	itemlist.add(new PurchaseItem(i+1,items,type_of_purchase,"","","","","","","","","",""));
	    	
	    	sno_col.setCellValueFactory(new PropertyValueFactory<PurchaseItem, String>("sno"));
	    	item_col.setCellValueFactory(new PropertyValueFactory<PurchaseItem, ComboBox>("items"));
	    	qty_col.setCellValueFactory(new PropertyValueFactory<PurchaseItem, String>("quantity"));
	    	rate_col.setCellValueFactory(new PropertyValueFactory<PurchaseItem, String>("rate"));
	    	gross_col.setCellValueFactory(new PropertyValueFactory<PurchaseItem, String>("gross"));
	    	disc_col.setCellValueFactory(new PropertyValueFactory<PurchaseItem, String>("discount"));
	    	cgst_col.setCellValueFactory(new PropertyValueFactory<PurchaseItem, String>("cgst"));
	    	sgst_col.setCellValueFactory(new PropertyValueFactory<PurchaseItem, String>("sgst"));
	    	igst_col.setCellValueFactory(new PropertyValueFactory<PurchaseItem, String>("igst"));
	    	ocharges_col.setCellValueFactory(new PropertyValueFactory<PurchaseItem, String>("other_charges"));
	    	cess_col.setCellValueFactory(new PropertyValueFactory<PurchaseItem, String>("cess"));
	    	taxable_value_col.setCellValueFactory(new PropertyValueFactory<PurchaseItem, String>("taxable_value"));
	    	type_of_purchase_col.setCellValueFactory(new PropertyValueFactory<PurchaseItem, ComboBox>("type_of_purchase"));
	    	purchasetv.setItems(itemlist);
	    }
}
