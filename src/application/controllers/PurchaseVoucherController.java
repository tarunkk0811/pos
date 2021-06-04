package application.controllers;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.*;


import DAO.GetVoucherDao;


import DAO.GetAccountsDao;
import DAO.GetProductsDao;
import application.custom_properties.PurchaseItem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


public class PurchaseVoucherController extends ApplicationMainController {
	@FXML
	private AnchorPane ap;

	@FXML
	private HBox row1hbox;

	@FXML
	private HBox vnohbox;

	@FXML
	private HBox row2hbox;

	@FXML
	private HBox pahbox;


	@FXML
	private HBox datehbox;


	@FXML
	private HBox vendorhbox;


	@FXML
	private HBox row3hbox;

	@FXML
	private HBox billnohbox;


	@FXML
	private HBox billdatehbox;


	@FXML
	private HBox isigsthbox;
	;

	@FXML
	private HBox placehbox;

	@FXML
	private ScrollPane spane;

	@FXML
	private VBox topvbox;









	@FXML
	private TableView purchasetv;

	@FXML
	private Text qty_total,gross_total,rate_total,discount_total,cgst_total, sgst_total, igst_total, oc_total,cess_total, taxable_total,net_amount,vno;


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

	HashMap<Integer,Double> tot_quantity = new HashMap<>(),tot_rate = new HashMap<>(),tot_gross = new HashMap<>(),
						tot_taxable_value = new HashMap<>() ,tot_discount = new HashMap<>(),tot_sgst = new HashMap<>(),
						tot_cgst = new HashMap<>(),tot_igst = new HashMap<>(),tot_other_charges = new HashMap<>(),
						tot_cess = new HashMap<>(),tot_new_col1 = new HashMap<>(),tot_new_col2 = new HashMap<>(),
						tot_new_col3 = new HashMap<>(),tot_new_col4 = new HashMap<>(),tot_new_col5 = new HashMap<>(),
						tot_net_value = new HashMap<>();

	HashMap<Integer,TextField> add_to_taxable_value = new HashMap<>();
	HashMap<Integer,TextField> add_to_net_value = new HashMap<>();



	static double total_qty,total_gross,total_rate,total_discount,total_cgst,total_sgst,total_igst,total_oc,total_cess,total_taxable,total_net_amount,total_col1,total_col2,total_col3,total_col4,total_col5;



	ObservableList<PurchaseItem> itemlist = FXCollections.observableArrayList();


	ObservableList<String> type_of_purchase = FXCollections.observableArrayList();
	ObservableList<String> purchase_account_list = FXCollections.observableArrayList("PURCHASE-SGST", "PURCHASE-IGST");
	ObservableList<String> accounts = FXCollections.observableArrayList();
	ObservableList<String> states = FXCollections.observableArrayList();


	HashMap<String, Integer> items_with_ids = new HashMap<String, Integer>();
	HashMap<String, Integer> Accounts_with_ids = new HashMap<String, Integer>();
	final ObservableList<String> temp_items = getAllItems();
	double prev,nv,ov ;
	String previtemname;
	GetAccountsDao get_acc_dao = new GetAccountsDao();
	GetProductsDao get_products_dao = new GetProductsDao();
	ApplicationController app_controller = new ApplicationController();
	GetVoucherDao get_voucher_dao = new GetVoucherDao();

	public PurchaseVoucherController() throws SQLException {
	}
	 int sno=1;
	String var1="",var2="",var3="",var4="",var5="";

	@FXML
	public void initialize() throws Exception {

		addNewFields();
		hideOrUnhideFields();

		prev = 0.0;
		 nv=0;ov=0;
		total_qty=0;total_gross=0;total_rate=0;total_discount=0;total_cgst=0;total_sgst=0;total_igst=0;total_oc=0;total_cess=0;total_taxable=0;total_net_amount=0;
		total_col1=0;total_col2=0;total_col3=0;total_col4=0;total_col5=0;
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


		/*for (sno= 1; sno <= 13;sno++) {
			ObservableList<String> items = FXCollections.observableArrayList(temp_items);
			itemlist.add(new PurchaseItem(sno, items, type_of_purchase, "", "", "", "", "", "", "", "", "", "","somtxt","col2"));
		}*/


		//purchasetv.setMaxWidth(purchasetv.getMaxWidth()+temp2.getMaxWidth());

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


		addNewColumns();

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

		purchasetv.refresh(); // is this really needed?




		purchasedt.setValue(LocalDate.now());

		addRows(13);

		/*itemlist.get(itemlist.size()-1).getQuantity().focusedProperty().addListener((observableValue, aBoolean, t1) -> {
			addRows(1);
		});*/



		purchasetv.applyCss();

		/*Stage temp = (Stage) ap.getScene().getWindow();
		temp.setOnShown((event)->{
			System.out.println("completed");

		});*/
	}

	private void addNewColumns() throws IOException, ParseException {
		int i=1;
		JSONObject settings = getJson();
		JSONObject pv = (JSONObject) settings.get("purchasevoucher");
		JSONObject columns = (JSONObject) pv.get("columns");
		JSONArray newcols = (JSONArray) columns.get("newcolumn");
		Iterator objs = newcols.iterator();
		while(objs.hasNext()){
			JSONObject column = (JSONObject) objs.next();
			String name = (String) column.get("name");
			String add_to = (String) column.get("add_to");
			String default_value = (String) column.get("default");
			//	columns adding to  table
				if(i<5) {
					TableColumn<PurchaseItem, String> temp2 = new TableColumn<PurchaseItem, String>(name);
					temp2.setSortable(false);
					temp2.setMaxWidth(100);
					temp2.setCellValueFactory(new PropertyValueFactory<PurchaseItem, String>("newcol" + i));
					String fname = name.split("\\s")[0].toLowerCase(Locale.ROOT)+"_col";
					temp2.setId(fname);
					purchasetv.getColumns().add(temp2);

					if(i==1)
						var1 = add_to;
					else if(i==2)
						var2 = add_to;
					else if(i==3)
						var3 = add_to;
					else if(i==4)
						var4 = add_to;
					else if(i==5)
						var5 = add_to;

					i++;
				}

		}

		}



	private void hideOrUnhideFields() throws IOException, ParseException {
		JSONObject settings = getJson();
		JSONObject hidden =   getJsonObject("hiddenfields", getJsonObject("fields",getJsonObject("purchasevoucher",settings)))  ;

		Iterator keys = hidden.keySet().iterator();


		topvbox.getChildren().remove((HBox) ap.lookup("#billnohbox"));

		while(keys.hasNext()) {
			String key = (String) keys.next();
			Node n;
			if((n=row1hbox.lookup( "#"+key)) !=null)
				row1hbox.getChildren().remove(n);

			else if((n=row2hbox.lookup( "#"+key)) !=null)
				row2hbox.getChildren().remove(n);

			else if((n=row3hbox.lookup( "#"+key)) !=null)
				row3hbox.getChildren().remove(n);

		}

	}

	@FXML
	public void printData(){


	}

	private void addNewFields() throws IOException, ParseException {
		JSONObject settings = getJson();
		//System.out.println(settings.toJSONString());
		JSONObject fields = getJsonObject("fields",getJsonObject("purchasevoucher",settings));
		JSONArray newfields= (JSONArray) fields.get("newfield");

		Iterator obj = newfields.iterator();
		while (obj.hasNext()){
			JSONObject newf = (JSONObject) obj.next();
			int rno = Integer.parseInt((String) newf.get("rno"));
			String type = (String) newf.get("type");
			String name = (String) newf.get("name");
			String default_value = (String) newf.get("default");
			String list = (String) newf.get("combobox_list");

			addToHBox(rno,name,type,list,default_value);


		}
	}

	private void addToHBox(int rno, String name, String type, String list, String default_value) {
		String hboxid = name.split(" ")[0].toLowerCase() + "hbox";
		HBox newhbox = new HBox();
		newhbox.setId(hboxid);
		System.out.println(type);
		if(type.equalsIgnoreCase("Text")){
		TextField tf = new TextField();
		tf.setPromptText(name);
		if(!default_value.isEmpty())
			tf.setText(default_value);
		newhbox.getChildren().add(tf);
		}
		else if(type.equalsIgnoreCase("Dropdown")){
			ObservableList<String> dropdownlist = FXCollections.observableArrayList();
			String[] arr = list.split(",");
			for(String s:arr){
				dropdownlist.add(s);
			}
			ComboBox<String> ddcbox = new ComboBox<String>(dropdownlist);

			newhbox.getChildren().add(ddcbox);
		}
		if(rno==1)
			row1hbox.getChildren().add(newhbox);

		else if(rno==2)
			row2hbox.getChildren().add(newhbox);

		else
			row3hbox.getChildren().add(newhbox);
	}

	private void linkEventListeners(ObservableList<PurchaseItem> itemlist) {

		for (PurchaseItem item : itemlist) {
			ObservableList<String> items = FXCollections.observableArrayList(temp_items);

			item.getItems().getEditor().focusedProperty().addListener((event,wasFocused, isNowFocused) -> {
				if (! isNowFocused) {
					if ((!item.getItems().getEditor().getText().isEmpty()) && (items_with_ids.containsKey(capitalize(item.getItems().getEditor().getText()))) && (!previtemname.equals(item.getItemName()))) {

						try {
							ResultSet res = get_products_dao.getProductDetails(items_with_ids.get(item.getItems().getSelectionModel().getSelectedItem()));
							if(res.next()) {
								// if rate field in empty then set the rate field
								// then updates the total_rate

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
				else{
					previtemname= item.getItemName();

				}
			});

			item.getItems().addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
				app_controller.filter(item.getItems(), items, event);
			});
			/********************************************************************/

			item.getQuantity().focusedProperty().addListener((observableValue, wasFocussed, isNowFocussed) -> {
				if(!isNowFocussed){
					if(Integer.parseInt(item.getSno()) == sno -2)
						addRows(1);
					if(!item.getItemName().isEmpty())
						calculate(item);
				}
			});

			item.getRate().focusedProperty().addListener((observableValue, wasFocussed, isNowFocussed) -> {
				if(!isNowFocussed){
					if(isDiff(tot_rate,item.getRateValue(),parseToInt(item.getSno())))
						calculate(item);
				}
			});

			item.getGross().focusedProperty().addListener((observableValue, wasFocussed, isNowFocussed) -> {
				if(!isNowFocussed){
					if(isDiff(tot_gross,item.getGrossValue(),parseToInt(item.getSno()))){
						item.getRate().setText(doubleToStringF(item.getGrossValue()/item.getQuantityValue()));
						calculate(item);
					}
				}
			});

			item.getDiscount().focusedProperty().addListener((observableValue, wasFocussed, isNowFocussed) -> {
				if(!isNowFocussed){
					if(isDiff(tot_discount,item.getDiscountValue(),parseToInt(item.getSno())))
						calculate(item);
				}
			});

			item.getSgst().focusedProperty().addListener((observableValue, wasFocussed, isNowFocussed) -> {
				if(!isNowFocussed){
					if(isDiff(tot_sgst,item.getSgstValue(),parseToInt(item.getSno())))
						calculate(item);
				}
			});

			item.getCgst().focusedProperty().addListener((observableValue, wasFocussed, isNowFocussed) -> {
				if(!isNowFocussed){
					if(isDiff(tot_cgst,item.getCgstValue(),parseToInt(item.getSno())))
						calculate(item);
				}
			});

			item.getIgst().focusedProperty().addListener((observableValue, wasFocussed, isNowFocussed) -> {
				if(!isNowFocussed){
					if(isDiff(tot_igst,item.getIgstValue(),parseToInt(item.getSno())))
						calculate(item);
				}
			});

			item.getOther_charges().focusedProperty().addListener((observableValue, wasFocussed, isNowFocussed) -> {
				if(!isNowFocussed){
					if(isDiff(tot_other_charges,parseToDouble(item.getOther_charges().getText()),parseToInt(item.getSno())))
						calculate(item);
				}
			});

			item.getCess().focusedProperty().addListener((observableValue, wasFocussed, isNowFocussed) -> {
				if(!isNowFocussed){
					if(isDiff(tot_cess,parseToDouble(item.getCess().getText()),parseToInt(item.getSno())))
						calculate(item);
				}
			});









			/******************************************************************************/
			//For input string: "5         0" should be handled
//
//			item.getQuantity().textProperty().addListener((observable, oldValue, newValue) -> {
//				total_qty=calculateTotal(oldValue,newValue,total_qty);
//				qty_total.setText(String.valueOf(total_qty));
//				if(!item.getQuantity().getText().isEmpty()) {
//					item.getGross().setText(doubleToStringF(item.getQuantityValue() * item.getRateValue()));
//					calculateTaxable(item);
//				}
//
//			});
//			item.getRate().textProperty().addListener((observable,oldValue,newValue)->{
//				total_rate=calculateTotal(oldValue,newValue,total_rate);
//				rate_total.setText(doubleToStringF(total_rate));
//
//				if(!item.getRate().getText().isEmpty() && !item.getQuantity().getText().isEmpty()) {
//					item.getGross().setText(doubleToStringF(item.getQuantityValue() * item.getRateValue()));
////					double taxable = item.getGrossValue()-(item.getGrossValue()*item.getDiscountValue())/100;
////					item.getTaxable_value().setText(doubleToStringF(taxable));
//
//				}
//			});
//
//			item.getGross().textProperty().addListener((observable,oldValue,newValue)->{
//				total_gross=calculateTotal(oldValue,newValue,total_gross);
//				gross_total.setText(doubleToStringF(total_gross));
//				// if(!item.getQuantity().getText().isEmpty() )
//
//				total_discount=calculatePercentageGst(oldValue,newValue,total_discount,(int)item.getDiscountValue());
//				discount_total.setText(doubleToStringF(total_discount));
//
//				calculateTaxable(item);
//
//			});
//			item.getGross().focusedProperty().addListener((event,wasFocused,isNowFocused)->{
//				if(!isNowFocused && item.getQuantityValue()!=0){
//					item.getRate().setText(doubleToStringF(item.getGrossValue()/item.getQuantityValue()));
//				}
//			});
//
//			item.getDiscount().textProperty().addListener(((observableValue, oldValue, newValue) -> {
//				total_discount=calculatePercentageTotal(oldValue,newValue,total_discount,item.getGrossValue());
//				discount_total.setText(doubleToStringF(total_discount));
//				calculateTaxable(item);
//			}));
//
//			item.getTaxable_value().textProperty().addListener(((observableValue, oldValue, newValue) ->{
//				System.out.println("old: " + oldValue + "   New:" + newValue);
//				total_taxable=calculateTotal(oldValue,newValue,total_taxable);
//				taxable_total.setText(doubleToStringF(total_taxable));
//
//				total_cgst = calculatePercentageGst(oldValue,newValue,total_cgst,item.getCgstValue());
//				cgst_total.setText(doubleToStringF(total_cgst));
//
//				total_sgst = calculatePercentageGst(oldValue,newValue,total_sgst,item.getSgstValue());
//				sgst_total.setText(doubleToStringF(total_sgst));
//
//				total_igst = calculatePercentageGst(oldValue,newValue,total_igst,item.getIgstValue());
//				igst_total.setText(doubleToStringF(total_igst));
//
//				total_net_amount=total_cess+total_oc+total_cgst+total_sgst+total_taxable;
//				if (var1.equalsIgnoreCase("net value"))
//					total_net_amount += total_col1;
//				if (var2.equalsIgnoreCase("net value"))
//					total_net_amount += total_col2;
//				if (var3.equalsIgnoreCase("net value"))
//					total_net_amount += total_col3;
//				if (var4.equalsIgnoreCase("net value"))
//					total_net_amount += total_col4;
//				if (var5.equalsIgnoreCase("net value"))
//					total_net_amount += total_col5;
//
//				net_amount.setText(doubleToStringF(total_net_amount));
//			}));
//
//			item.getCgst().textProperty().addListener(((observableValue, oldValue, newValue) ->{
//				total_cgst = calculatePercentageTotal(oldValue,newValue,total_cgst,item.getTaxableValue());
//				cgst_total.setText(doubleToStringF(total_cgst));
//			}));
//			item.getSgst().textProperty().addListener(((observableValue, oldValue, newValue) ->{
//				total_sgst = calculatePercentageTotal(oldValue,newValue,total_sgst,Double.parseDouble(item.getTaxable_value().getText()));
//				sgst_total.setText(doubleToStringF(total_sgst));
//			}));
//
//			if(isigst.isSelected()){
//				item.getIgst().textProperty().addListener(((observableValue, oldValue, newValue) ->{
//					total_igst = calculatePercentageTotal(oldValue,newValue,total_igst,item.getTaxableValue());
//					igst_total.setText(doubleToStringF(total_igst));
//				}));
//			}
//
//			item.getOther_charges().textProperty().addListener(((observableValue, oldValue, newValue) ->{
//				total_oc=calculateTotal(oldValue,newValue,total_oc);
//				oc_total.setText(doubleToStringF(total_oc));
//
//				total_net_amount=total_cess+total_oc+total_cgst+total_sgst+total_taxable+total_col1+total_col2+total_col3+total_col4+total_col5;
//				net_amount.setText(doubleToStringF(total_net_amount));
//			}));
//
//			item.getCess().textProperty().addListener((observableValue, oldValue, newValue) -> {
//				total_cess=calculateTotal(oldValue,newValue,total_cess);
//				cess_total.setText(doubleToStringF(total_cess));
//				total_net_amount=total_cess+total_oc+total_cgst+total_sgst+total_taxable+total_col1+total_col2+total_col3+total_col4+total_col5;
//				net_amount.setText(doubleToStringF(total_net_amount));
//			});
//
//
//
//			////////////////////////////////////////////////////////////////////
//			item.getQuantity().focusedProperty().addListener((event,wasFocussed,isNowFocussed)->{
//				if(Integer.parseInt(item.getSno()) == sno -2){
//					addRows(1);
//				}
//			});
//
//
//			if(!var1.isEmpty()) {
//				item.getNewcol1().textProperty().addListener((observableValue, oldValue, newValue) -> {
//					String old_total_col = String.valueOf(total_col1);
//					total_col1 = calculateTotal(oldValue,newValue,total_col1);
//					addNewColListeners(var1,old_total_col, total_col1, oldValue, newValue, item);
//				});
//				if (!var2.isEmpty()) {
//					item.getNewcol2().textProperty().addListener((observableValue, oldValue, newValue) -> {
//						String old_total_col = String.valueOf(total_col2);
//						total_col2 = calculateTotal(oldValue,newValue,total_col2);
//						System.out.println(total_col2);
//						addNewColListeners(var2,old_total_col,total_col2, oldValue, newValue, item);
//					});
//
//					if (!var3.isEmpty()) {
//						item.getNewcol3().textProperty().addListener((observableValue, oldValue, newValue) -> {
//							String old_total_col = String.valueOf(total_col3);
//							total_col3 = calculateTotal(oldValue,newValue,total_col3);
//							addNewColListeners(var3,old_total_col,total_col3, oldValue, newValue, item);
//						});
//						if (!var4.isEmpty()) {
//							item.getNewcol4().textProperty().addListener((observableValue, oldValue, newValue) -> {
//								String old_total_col = String.valueOf(total_col1);
//								total_col4 = calculateTotal(oldValue,newValue,total_col4);
//								addNewColListeners(var4, old_total_col,total_col4, oldValue, newValue, item);
//							});
//							if (!var5.isEmpty()) {
//								item.getNewcol5().textProperty().addListener((observableValue, oldValue, newValue) -> {
//									String old_total_col = String.valueOf(total_col5);
//									total_col5 = calculateTotal(oldValue,newValue,total_col5);
//									addNewColListeners(var5,old_total_col,total_col5, oldValue, newValue, item);
//								});
//
//							}
//						}
//					}
//				}
//			}

		}

	}


	private void calculate(PurchaseItem item) {
		int item_sno = parseToInt(item.getSno());
		double item_net_value = 0;
		// rate
		tot_rate.put(item_sno,item.getRateValue());
		rate_total.setText(calculateSum(tot_rate));

		// quantity
		tot_quantity.put(item_sno,item.getQuantityValue());
		qty_total.setText(calculateSum(tot_quantity));

		//gross
		// calculate gross value of an item
		double gross = item.getRateValue()*item.getQuantityValue();
		item.getGross().setText(doubleToStringF(gross));
		tot_gross.put(item_sno,item.getGrossValue());
		gross_total.setText(calculateSum(tot_gross));

		// discount
		// calculate discount value of an item
		double discount_in_rs = (item.getDiscountValue()*gross)/100;
		tot_discount.put(item_sno,discount_in_rs);
		discount_total.setText(calculateSum(tot_discount));

		// new cols
//		if (var1.equalsIgnoreCase("taxable value")) {
//			tot_new_col1.put(item_sno, item.getNewCol1Value());
//
//		}
//		if (var2.equalsIgnoreCase("taxable value"))
//			tot_new_col2.put(item_sno,item.getNewCol2Value());
//		if (var3.equalsIgnoreCase("taxable value"))
//			tot_new_col3.put(item_sno,item.getNewCol3Value());
//		if (var4.equalsIgnoreCase("taxable value"))
//			tot_new_col4.put(item_sno,item.getNewCol4Value());
//		if (var5.equalsIgnoreCase("taxable value"))
//			tot_new_col5.put(item_sno,item.getNewCol5Value());



		// taxable
		// calculate taxable value of an item
		double taxable = gross-discount_in_rs;
		item_net_value += taxable;
		item.getTaxable_value().setText(doubleToStringF(taxable));
		tot_taxable_value.put(item_sno,item.getTaxableValue());
		taxable_total.setText(calculateSum(tot_taxable_value));


		/****************** After calculating Taxable Value *****************************/

		// tax
		// calculate tax value of an item
		double sgst = item.getTaxableValue()*item.getSgstValue()/100;
		double cgst = item.getTaxableValue()*item.getCgstValue()/100;
		double igst = item.getTaxableValue()*item.getIgstValue()/100;
		if(!isigst.isSelected()){
			// sgst
			item_net_value += sgst;
			tot_sgst.put(item_sno,sgst);
			sgst_total.setText(calculateSum(tot_sgst));

			// cgst
			item_net_value += cgst;
			tot_cgst.put(item_sno,cgst);
			cgst_total.setText(calculateSum(tot_cgst));
		}else{
			// igst
			item_net_value += igst;
			tot_igst.put(item_sno,igst);
			cgst_total.setText(calculateSum(tot_igst));
		}

		// other charges
		double other_charges = parseToDouble(item.getOther_charges().getText());
		item_net_value += other_charges;
		tot_other_charges.put(item_sno,other_charges);
		oc_total.setText(calculateSum(tot_other_charges));

		// cess
		double cess = parseToDouble(item.getCess().getText());
		item_net_value += cess;
		tot_cess.put(item_sno,cess);
		cess_total.setText(calculateSum(tot_cess));

		// total net
		tot_net_value.put(item_sno,item_net_value);
		net_amount.setText(calculateSum(tot_net_value));
	}

	private String calculateSum(HashMap<Integer, Double> tot_taxable_value) {
		double sum = 0;
		for(double value:tot_taxable_value.values())
			sum += value;
		return doubleToStringF(sum);
	}


	private boolean isDiff(HashMap<Integer, Double> tot_map, double newValue, int item_sno) {
		return (tot_map.containsKey(item_sno) && newValue != tot_map.get(item_sno));
	}

	private void calculateTaxable(PurchaseItem item) {

		double taxable = item.getGrossValue()-(item.getGrossValue()*item.getDiscountValue())/100;

		if (var1.equalsIgnoreCase("taxable value"))
			taxable += item.getNewCol1Value();
		if (var2.equalsIgnoreCase("taxable value"))
			taxable += item.getNewCol2Value();
		if (var3.equalsIgnoreCase("taxable value"))
			taxable += item.getNewCol3Value();
		if (var4.equalsIgnoreCase("taxable value"))
			taxable += item.getNewCol4Value();
		if (var5.equalsIgnoreCase("taxable value"))
			taxable += item.getNewCol5Value();

		System.out.println("Taxable: " + taxable);
		item.getTaxable_value().setText(doubleToStringF(taxable));

	}

	private void addNewColListeners(String var,String old_total_col,double total_col, String oldValue, String newValue, PurchaseItem item) {
		if (!var.equalsIgnoreCase("None")){
			if(var.equalsIgnoreCase("Net Value")){
				total_net_amount=calculateTotal(old_total_col,String.valueOf(total_col),total_net_amount);
				net_amount.setText(doubleToStringF(total_net_amount));
			}else{
				double taxable_value = item.getTaxableValue();
				taxable_value = calculateTotal(oldValue,newValue,taxable_value);
				item.getTaxable_value().setText(doubleToStringF(taxable_value));
			}
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



	public void addRows(int n) {

		purchasetv.scrollTo(sno+n);
		int temp;
		ObservableList<PurchaseItem> extrarowslist = FXCollections.observableArrayList();

		for(temp=sno;temp<sno+n;temp++) {
			ObservableList<String> items = FXCollections.observableArrayList(temp_items);
			extrarowslist.add(new PurchaseItem(temp, items, type_of_purchase, "", "", "", "", "", "", "", "", "", "","","","","",""));
		}

		purchasetv.getItems().addAll(extrarowslist);
		linkEventListeners(extrarowslist);
		itemlist.addAll(extrarowslist); //
		purchasetv.refresh();
		sno=temp;

		spane.setVvalue(0);

	}



	public double calculateTotal(String oldValue,String newValue,double total){
		if(newValue.isEmpty())
			nv=0;
		else
			nv=Double.parseDouble(newValue);
		if(oldValue.isEmpty())
			ov=0;
		else
			ov = Double.parseDouble(oldValue);

		if(!newValue.isEmpty() && oldValue.isEmpty()){
			total+=nv;
		}
		else if(!oldValue.isEmpty()&&!newValue.isEmpty()) {

			total = total- ov + nv;
		}
		else if(ov!=0 && nv==0)
			total-=ov;
		//System.out.println(total);
		return  total;
	}


	public double calculatePercentageTotal(String oldValue,String newValue,double total,double target){
		if(newValue.isEmpty())
			nv=0;
		else
			nv=Double.parseDouble(newValue);
		if(oldValue.isEmpty())
			ov=0;
		else
			ov = Double.parseDouble(oldValue);

		if(!newValue.isEmpty() && oldValue.isEmpty()){
			total+= (target*nv)/100;
		}
		else if(!oldValue.isEmpty()&&!newValue.isEmpty()) {

			total = total- (target*ov/100) + (target*nv/100);
		}
		else if(ov!=0 && nv==0)
			total-=(target*ov/100);
		//System.out.println(total);
		return  total;
	}

	public double calculatePercentageGst(String oldValue,String newValue,double total,int gst){
		if(newValue.isEmpty())
			nv=0;
		else {
			nv = Double.parseDouble(newValue);
			nv = (nv*(gst)/100);
		}
		if(oldValue.isEmpty())
			ov=0;
		else {
			ov = Double.parseDouble(oldValue);
			ov = ((ov*gst)/100);
		}

		return calculateTotal(String.valueOf(ov),String.valueOf(nv),total);
	}


	public TableView getTV() {
		return  this.purchasetv;
	}
}
