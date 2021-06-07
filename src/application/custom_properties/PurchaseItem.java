package application.custom_properties;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PurchaseItem {
	private String sno;
	private ComboBox<String> items, type_of_purchase;
	private TextField quantity;
	private TextField rate;
	private TextField gross;
	private TextField discount;
	private TextField cgst;
	private TextField sgst;
	private TextField igst;
	private TextField other_charges;
	private TextField cess;
	private TextField taxable_value;
	private TextField newcol1;
	private TextField newcol2;
	private TextField newcol3;
	private TextField newcol4;
	private TextField newcol5;
	private TextField newcol6;
	private TextField newcol7;
	private TextField newcol8;
	private TextField newcol9;

	public TextField getNewcol6() {
		return newcol6;
	}

	public void setNewcol6(TextField newcol6) {
		this.newcol6 = newcol6;
	}

	public TextField getNewcol7() {
		return newcol7;
	}

	public void setNewcol7(TextField newcol7) {
		this.newcol7 = newcol7;
	}

	public TextField getNewcol8() {
		return newcol8;
	}

	public void setNewcol8(TextField newcol8) {
		this.newcol8 = newcol8;
	}

	public TextField getNewcol9() {
		return newcol9;
	}

	public void setNewcol9(TextField newcol9) {
		this.newcol9 = newcol9;
	}

	public TextField getNewcol5() {
		return newcol5;
	}

	public void setNewcol5(TextField newcol5) {
		this.newcol5 = newcol5;
	}

	public TextField getNewcol2() {
		return newcol2;
	}

	public void setNewcol2(TextField newcol2) {
		this.newcol2 = newcol2;
	}

	static double total_qty,total_gross,total_rate,total_discount,total_cgst,total_sgst,total_igst,total_oc,total_cess,total_taxable,total_net_amount;
	DoubleProperty prop_rate,prop_goss,prop_qty;
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

	public TextField getNewcol1() {
		return newcol1;
	}

	public TextField getNewcol3() {
		return newcol3;
	}

	public void setNewcol3(TextField newcol3) {
		this.newcol3 = newcol3;
	}

	public TextField getNewcol4() {
		return newcol4;
	}

	public void setNewcol4(TextField newcol4) {
		this.newcol4 = newcol4;
	}

	public void setNewcol1(TextField newcol1) {
		this.newcol1 = newcol1;
	}

	public PurchaseItem(int sno, ObservableList items, ObservableList type_of_purchase, String quantity,
						String rate, String gross, String discount, String cgst, String sgst, String igst, String other_charges,
						String cess, String taxable_value, String ncol1,String ncol2,String ncol3,String ncol4,String ncol5,String ncol6,String ncol7,String ncol8,String ncol9) {
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
		this.prop_rate = new SimpleDoubleProperty();
		this.prop_goss = new SimpleDoubleProperty();
		this.prop_qty = new SimpleDoubleProperty();
		//newly added
		this.newcol1 = new TextField(ncol1);
		this.newcol2=new TextField(ncol2);
		this.newcol3 = new TextField(ncol3);
		this.newcol4 = new TextField(ncol4);
		this.newcol5 = new TextField(ncol5);
		this.newcol6 = new TextField(ncol6);
		this.newcol7 = new TextField(ncol7);
		this.newcol8 = new TextField(ncol8);
		this.newcol9 = new TextField(ncol9);
		// focus
		this.items.focusedProperty().addListener(e -> {
			this.items.show();
		});
		
		this.type_of_purchase.focusedProperty().addListener(e -> {
			this.type_of_purchase.show();
		});
		this.type_of_purchase.getSelectionModel().select(0);

		//this.rate.textProperty().bindBidirectional();
		
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

	public double getQuantityValue(){return this.getQuantity().getText().isEmpty()?0:Double.parseDouble(this.getQuantity().getText());}

	public double getRateValue(){return this.getRate().getText().isEmpty()?0:Double.parseDouble(this.getRate().getText());}

	public double getGrossValue(){return this.getGross().getText().isEmpty()? 0:Double.parseDouble(this.getGross().getText());}

	public double getDiscountValue(){return this.getDiscount().getText().isEmpty()?0:Double.parseDouble(this.getDiscount().getText());}

	public double getTaxableValue(){return this.getTaxable_value().getText().isEmpty()?0:Double.parseDouble(this.getTaxable_value().getText());}

	public int getCgstValue(){return  this.getCgst().getText().isEmpty() ? 0 : Integer.parseInt(this.getCgst().getText()); }

	public int getSgstValue(){return  this.getSgst().getText().isEmpty() ? 0 : Integer.parseInt(this.getSgst().getText()); }

	public String getItemName(){return this.getItems().getEditor().getText();}

	public int getIgstValue() {return  this.getIgst().getText().isEmpty() ? 0 : Integer.parseInt(this.getIgst().getText()); }

	public double getNewCol1Value(){
		return this.getNewcol1().getText().isEmpty() ? 0 : Double.parseDouble(this.getNewcol1().getText());
	}

	public double getNewCol2Value(){
		return this.getNewcol2().getText().isEmpty() ? 0 : Double.parseDouble(this.getNewcol2().getText());
	}
	public double getNewCol3Value(){
		return this.getNewcol3().getText().isEmpty() ? 0 : Double.parseDouble(this.getNewcol3().getText());
	}
	public double getNewCol4Value(){
		return this.getNewcol4().getText().isEmpty() ? 0 : Double.parseDouble(this.getNewcol4().getText());
	}
	public double getNewCol5Value(){
		return this.getNewcol5().getText().isEmpty() ? 0 : Double.parseDouble(this.getNewcol5().getText());
	}

}
