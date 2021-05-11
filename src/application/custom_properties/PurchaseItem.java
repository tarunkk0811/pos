package application.custom_properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PurchaseItem {
	private String sno;
	private ComboBox<String> items, type_of_purchase;
	private TextField quantity, rate, gross, discount, cgst, sgst, igst, other_charges, cess, taxable_value;
	static double total_qty,total_gross,total_rate,total_discount,total_cgst,total_sgst,total_igst,total_oc,total_cess,total_taxable,total_net_amount;

	public static double getTotal_qty() {
		return total_qty;
	}

	public static void setTotal_qty(double total_qty) {
		PurchaseItem.total_qty = total_qty;
	}

	public static double getTotal_gross() {
		return total_gross;
	}

	public static void setTotal_gross(double total_gross) {
		PurchaseItem.total_gross = total_gross;
	}

	public static double getTotal_rate() {
		return total_rate;
	}

	public static void setTotal_rate(double total_rate) {
		PurchaseItem.total_rate = total_rate;
	}

	public static double getTotal_discount() {
		return total_discount;
	}

	public static void setTotal_discount(double total_discount) {
		PurchaseItem.total_discount = total_discount;
	}

	public static double getTotal_cgst() {
		return total_cgst;
	}

	public static void setTotal_cgst(double total_cgst) {
		PurchaseItem.total_cgst = total_cgst;
	}

	public static double getTotal_sgst() {
		return total_sgst;
	}

	public static void setTotal_sgst(double total_sgst) {
		PurchaseItem.total_sgst = total_sgst;
	}

	public static double getTotal_igst() {
		return total_igst;
	}

	public static void setTotal_igst(double total_igst) {
		PurchaseItem.total_igst = total_igst;
	}

	public static double getTotal_oc() {
		return total_oc;
	}

	public static void setTotal_oc(double total_oc) {
		PurchaseItem.total_oc = total_oc;
	}

	public static double getTotal_cess() {
		return total_cess;
	}

	public static void setTotal_cess(double total_cess) {
		PurchaseItem.total_cess = total_cess;
	}

	public static double getTotal_taxable() {
		return total_taxable;
	}

	public static void setTotal_taxable(double total_taxable) {
		PurchaseItem.total_taxable = total_taxable;
	}

	public static double getTotal_net_amount() {
		return total_net_amount;
	}

	public static void setTotal_net_amount(double total_net_amount) {
		PurchaseItem.total_net_amount = total_net_amount;
	}

	public PurchaseItem(int sno, ObservableList items, ObservableList type_of_purchase, String quantity,
						String rate, String gross, String discount, String cgst, String sgst, String igst, String other_charges,
						String cess, String taxable_value) {
		super();
		this.sno = String.valueOf(sno);
		this.items = new ComboBox<String>(items);
		this.type_of_purchase = new ComboBox(type_of_purchase);

		this.quantity = new TextField(quantity);
		this.rate = new TextField(rate);
		this.gross = new TextField(gross);
		this.discount = new TextField(discount);
		this.cgst = new TextField(cgst);
		this.sgst = new TextField(sgst);
		this.igst = new TextField(igst);
		this.other_charges = new TextField(other_charges);
		this.cess = new TextField(cess);
		this.taxable_value = new TextField(taxable_value);
		this.items.setEditable(true);
		
		// focus
		this.items.focusedProperty().addListener(e -> {
			this.items.show();
		});
		
		this.type_of_purchase.focusedProperty().addListener(e -> {
			this.type_of_purchase.show();
		});
		this.type_of_purchase.getSelectionModel().select(0);
		
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public ComboBox<String> getItems() {
		return items;
	}

	public void setItems(ComboBox<String> items) {
		this.items = items;
	}

	public ComboBox<String> getType_of_purchase() {
		return type_of_purchase;
	}

	public void setType_of_purchase(ComboBox<String> type_of_purchase) {
		this.type_of_purchase = type_of_purchase;
	}

	public TextField getQuantity() {
		return quantity;
	}

	public void setQuantity(TextField quantity) {
		this.quantity = quantity;
	}

	public TextField getRate() {
		return rate;
	}

	public void setRate(TextField rate) {
		this.rate = rate;
	}

	public TextField getGross() {
		return gross;
	}

	public void setGross(TextField gross) {
		this.gross = gross;
	}

	public TextField getDiscount() {
		return discount;
	}

	public void setDiscount(TextField discount) {
		this.discount = discount;
	}

	public TextField getCgst() {
		return cgst;
	}

	public void setCgst(TextField cgst) {
		this.cgst = cgst;
	}

	public TextField getSgst() {
		return sgst;
	}

	public void setSgst(TextField sgst) {
		this.sgst = sgst;
	}

	public TextField getIgst() {
		return igst;
	}

	public void setIgst(TextField igst) {
		this.igst = igst;
	}

	public TextField getOther_charges() {
		return other_charges;
	}

	public void setOther_charges(TextField other_charges) {
		this.other_charges = other_charges;
	}

	public TextField getCess() {
		return cess;
	}

	public void setCess(TextField cess) {
		this.cess = cess;
	}

	public TextField getTaxable_value() {
		return taxable_value;
	}

	public void setTaxable_value(TextField taxable_value) {
		this.taxable_value = taxable_value;
	}

	
	
	/*
	 * public String getSno() { return sno; }
	 * 
	 * public void setSno(int sno) { this.sno = String.valueOf(sno); }
	 * 
	 * public ObservableList getItems() { return items.getItems(); }
	 * 
	 * public void setItems(ObservableList items) {
	 * this.items.getItems().addAll(items); }
	 * 
	 * public ObservableList getType_of_purchase() { return
	 * type_of_purchase.getItems(); }
	 * 
	 * public void setType_of_purchase(ObservableList type_of_purchase) {
	 * this.type_of_purchase.getItems().addAll(type_of_purchase); }
	 * 
	 * public String getQuantity() { return quantity.getText(); }
	 * 
	 * public void setQuantity(String quantity) { this.quantity.setText(quantity); }
	 * 
	 * public String getRate() { return rate.getText(); }
	 * 
	 * public void setRate(String rate) { this.rate.setText(rate); }
	 * 
	 * public String getGross() { return gross.getText(); }
	 * 
	 * public void setGross(String gross) { this.gross.setText(gross); }
	 * 
	 * public String getDiscount() { return discount.getText(); }
	 * 
	 * public void setDiscount(String discount) { this.discount.setText(discount); }
	 * 
	 * public String getCgst() { return cgst.getText(); }
	 * 
	 * public void setCgst(String cgst) { this.cgst.setText(cgst); }
	 * 
	 * public String getSgst() { return sgst.getText(); }
	 * 
	 * public void setSgst(String sgst) { this.sgst.setText(sgst); }
	 * 
	 * public String getIgst() { return igst.getText(); }
	 * 
	 * public void setIgst(String igst) { this.igst.setText(igst); }
	 * 
	 * public String getOther_charges() { return other_charges.getText(); }
	 * 
	 * public void setOther_charges(String other_charges) {
	 * this.other_charges.setText(other_charges); }
	 * 
	 * public String getCess() { return cess.getText(); }
	 * 
	 * public void setCess(String cess) { this.cess.setText(cess); }
	 * 
	 * public String getTaxable_value() { return taxable_value.getText(); }
	 * 
	 * public void setTaxable_value(String taxable_value) {
	 * this.taxable_value.setText(taxable_value); }
	 */
}
