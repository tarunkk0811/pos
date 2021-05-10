package application.controllers;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import DAO.GetVoucherDao;
import com.mysql.cj.conf.StringProperty;

import DAO.GetAccountsDao;
import DAO.GetProductsDao;
import application.custom_properties.PurchaseItem;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;



public class PurchaseVoucherController {

	@FXML
	private TableView purchasetv;

	@FXML
	private Text qty_total,gross_total,rate_total,discount_total,cgst_total, sgst_total, igst_total, oc_total,cess_total, taxable_total,net_amount,vno;

	static double total_qty,total_gross,total_rate,total_discount,total_cgst,total_sgst,total_igst,total_oc,total_cess,total_taxable,total_net_amount;
	@FXML
	private TableColumn<PurchaseItem, String> sno_col;

	@FXML
	private TableColumn<PurchaseItem, String> qty_col, rate_col, gross_col, disc_col, cgst_col, sgst_col, igst_col, ocharges_col, cess_col, taxable_value_col;

	@FXML
	private TableColumn<PurchaseItem, ComboBox> item_col, type_of_purchase_col;

	@FXML
	private ChoiceBox<String> purchase_account;

	@FXML
	private DatePicker purchasedt, billdt;

	@FXML
	private ComboBox<String> place, vendor;

	@FXML
	private TextField billno;

	@FXML
	private CheckBox isigst, show_customers;

	@FXML
	private Button add_rows;



	ObservableList<PurchaseItem> itemlist = FXCollections.observableArrayList();


	ObservableList<String> type_of_purchase = FXCollections.observableArrayList();
	ObservableList<String> purchase_account_list = FXCollections.observableArrayList("PURCHASE-SGST", "PURCHASE-IGST");
	ObservableList<String> accounts = FXCollections.observableArrayList();
	ObservableList<String> states = FXCollections.observableArrayList();


	HashMap<String, Integer> items_with_ids = new HashMap<String, Integer>();
	HashMap<String, Integer> Accounts_with_ids = new HashMap<String, Integer>();
	final ObservableList<String> temp_items = getAllItems();

	GetAccountsDao get_acc_dao = new GetAccountsDao();
	GetProductsDao get_products_dao = new GetProductsDao();
	ApplicationController app_controller = new ApplicationController();
	GetVoucherDao get_voucher_dao = new GetVoucherDao();

	public PurchaseVoucherController() throws SQLException {
	}
	 int sno;

	@FXML
	public void initialize() throws Exception {

		ResultSet vendors_rs = get_acc_dao.getVendors(SessionController.cid);
		while (vendors_rs.next()) {
			Accounts_with_ids.put(vendors_rs.getString(2), vendors_rs.getInt(1));
			accounts.add(vendors_rs.getString(2));
		}
		Collections.sort(accounts);
		vendor.setEditable(true);
		vendor.getItems().addAll(accounts);

		// adding place of supply
		ResultSet rs = get_acc_dao.getStates();

		while (rs.next()) {
			String statename = rs.getString(1);
			states.add(statename);
		}
		Collections.sort(states);
		place.setEditable(true);
		place.getItems().addAll(states);

		//get vno
		int vnumber = get_voucher_dao.getVno();
		vno.setText(String.valueOf(vnumber+1));
		place.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
			app_controller.filter(place, states, event);
		});

		vendor.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
			app_controller.filter(vendor, accounts, event);
		});

		purchase_account.getItems().addAll(purchase_account_list);

		// geting products and adding to observable list

		type_of_purchase.add("Taxable Supply");
		type_of_purchase.add("CC Exempt supply");
		type_of_purchase.add("Exemp Supply");
		type_of_purchase.add("CC Non Business");
		type_of_purchase.add("Non Business");
		type_of_purchase.add("Ineligible Credit Section");
		type_of_purchase.add("CR Not available");


		for (sno= 1; sno <= 50;sno++) {
			ObservableList<String> items = FXCollections.observableArrayList(temp_items);
			itemlist.add(new PurchaseItem(sno, items, type_of_purchase, "", "", "", "", "", "", "", "", "", ""));
		}
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

		linkEventListeners(itemlist);
		// appyling focus property
		vendor.focusedProperty().addListener(e -> {
			vendor.show();
		});

		place.focusedProperty().addListener(e -> {
			place.show();
		});

		/*purchase_account.focusedProperty().addListener(e -> {
			purchase_account.show();
		});*/

		//purchase_account.getSelectionModel().select(0);

		purchasedt.setValue(LocalDate.now());



	}

	private void linkEventListeners(ObservableList<PurchaseItem> itemlist) {

		for (PurchaseItem item : itemlist) {
			ObservableList<String> items = FXCollections.observableArrayList(temp_items);
			item.getItems().getEditor().focusedProperty().addListener((event,wasFocused, isNowFocused) -> {
				if (! isNowFocused) {
					if (item.getItems().getEditor().getText() != "") {
						try {
							ResultSet res = get_products_dao.getProductDetails(items_with_ids.get(item.getItems().getSelectionModel().getSelectedItem()));
							if(res.next()){
								item.getRate().setText(String.valueOf(res.getFloat(1)));
								int gst = res.getInt(2);
								item.getDiscount().setText(String.valueOf(res.getFloat(3)));
								if(isigst.isSelected()) {
									item.getIgst().setText(String.valueOf(gst));
									item.getCgst().setText("");
									item.getSgst().setText("");
								}
								else{
									item.getCgst().setText(String.valueOf(gst/2));
									item.getSgst().setText(String.valueOf(gst/2));
									item.getIgst().setText("");
								}
							}

						} catch (SQLException throwables) {
							throwables.printStackTrace();
						}
						//item.getItems().setValue(item.getItems().getEditor().getText());
					}
				}
			});
			item.getItems().addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
				app_controller.filter(item.getItems(), items, event);
			});


			item.getQuantity().focusedProperty().addListener((event,wasFocused, isNowFocused) -> {
				if (! isNowFocused && item.getQuantity().getText()!="") {
					double q = Double.parseDouble(item.getQuantity().getText());
					total_qty+=q;
					qty_total.setText(String.valueOf(total_qty));

					item.getGross().setText(String.valueOf(Double.parseDouble(item.getQuantity().getText())*
							Double.parseDouble(item.getRate().getText())));
					//float taxable_value = Float.parseFloat(item.getGross().getText())+(Float.parseFloat(item.getGross().getText())
					//*Float.parseFloat(item.getDiscount().getText()))/100;
					item.getTaxable_value().setText(String.valueOf(Double.parseDouble(item.getGross().getText())+(Float.parseFloat(item.getGross().getText())
							*Double.parseDouble(item.getDiscount().getText()))/100));
				}
				else if(isNowFocused && item.getQuantity().getText()!="") {
					total_qty = total_qty - Double.parseDouble(item.getQuantity().getText());
					qty_total.setText(String.valueOf(total_qty));
				}
			});

			item.getRate().focusedProperty().addListener((event,wasFocused, isNowFocused) -> {
//				if (! isNowFocused && item.getRate().getText()!="") {
//					double q = Double.parseDouble(item.getRate().getText());
//					total_rate+=q;
//					rate_total.setText(String.valueOf(total_rate));
//				}
//				else
				if(!isNowFocused && item.getRate().getText()!="") {
					total_rate = total_rate + Double.parseDouble(item.getRate().getText());
					rate_total.setText(String.valueOf(total_rate));
				}
			});

				}

	}

	private ObservableList<String> getAllItems() throws SQLException {
		ObservableList<String> items = FXCollections.observableArrayList();
		ResultSet res =new GetProductsDao().getProducts(SessionController.cid);
		while (res.next()) {
			items_with_ids.put(res.getString(2), res.getInt(1));
			items.add(res.getString(2));
		}
		Collections.sort(items);
		return items;
	}



	public void addRows(javafx.event.ActionEvent actionEvent) {
		int temp;
		ObservableList<PurchaseItem> extrarowslist = FXCollections.observableArrayList();

		for(temp=sno;temp<sno+10;temp++) {
			ObservableList<String> items = FXCollections.observableArrayList(temp_items);
			extrarowslist.add(new PurchaseItem(temp, items, type_of_purchase, "", "", "", "", "", "", "", "", "", ""));
		}
		purchasetv.getItems().addAll(extrarowslist);
		linkEventListeners(extrarowslist);
		purchasetv.refresh();
		sno=temp;
	}
}
