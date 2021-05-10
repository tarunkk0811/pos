package application.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Locale;

import DAO.GetAccountsDao;
import DAO.SetAccountsDao;
import application.custom_properties.CustomTreeItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateAccountController {
	@FXML
	private AnchorPane ap;
	@FXML
	private Tab accountstab, gsttab;

	@FXML
	private TabPane tabpane;

	@FXML
	private TextField name, phone, adhaar, email, cdays, gstin;

	@FXML
	private TextArea address;

	@FXML
	private ComboBox<String> country, state, city, atype, btype;

	@FXML
	private Button next, submit;

	int flag = 1;
	ObservableList<String> countries = FXCollections.observableArrayList();

	ObservableList<String> states = FXCollections.observableArrayList();

	ObservableList<String> cities = FXCollections.observableArrayList();

	GetAccountsDao get_acc_dao = new GetAccountsDao();
	SetAccountsDao set_acc_dao = new SetAccountsDao();
	ApplicationController app_controller = new ApplicationController();

	@FXML
	public void initialize() throws SQLException {

		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			countries.add(obj.getDisplayCountry());
		}

		ResultSet rs = get_acc_dao.getStates();

		while (rs.next()) {
			String statename = rs.getString(1);
			states.add(statename);
		}
		Collections.sort(states);

		country.setEditable(true);
		state.setEditable(true);
		city.setEditable(true);

		country.getItems().addAll(countries);
		country.getSelectionModel().select("India");

		state.getItems().addAll(states);

		state.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
			ObservableList<String> statescopy = FXCollections.observableArrayList(states);
			String inp = state.getEditor().getText();
			int ascii = 0;
			try {
				ascii = event.getText().charAt(0);
			} catch (Exception e) {
				// System.out.print("Cant convert to ascii due to index of input event in create
				// account states");
			}
			if (ascii != 99 && inp == "") {
				state.getItems().clear();
				state.getItems().addAll(statescopy);
			} else if (ascii >= 65 && ascii <= 122) {
				state = app_controller.searchComboBox(event, state, statescopy);
				state.show();
			}
		});

		city.focusedProperty().addListener((event) -> {
			String statechoosen = state.getSelectionModel().getSelectedItem();
			if (statechoosen != null || statechoosen != "")
				try {
					ResultSet res = get_acc_dao.getCities(statechoosen);
					while (res.next()) {
						String city = res.getString(1);
						// System.out.println(city);
						cities.add(city);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (cities.size() != 0 && flag == 1) {
				city.getItems().clear();
				flag = 0;
				city.getItems().addAll(cities);
			}
		});

		city.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
			ObservableList<String> citiescopy = FXCollections.observableArrayList(cities);
			String inp = city.getEditor().getText();
			int ascii = 0;
			try {
				ascii = event.getText().charAt(0);
			} catch (Exception e) {
			}
			if (ascii != 99 && inp == "") {
				city.getItems().clear();
				city.getItems().addAll(citiescopy);
			} else if (ascii >= 65 && ascii <= 122) {
				city = app_controller.searchComboBox(event, city, citiescopy);
				city.show();
			}
		});

		atype.getItems().add("Customer");
		atype.getItems().add("Vendor");
		atype.focusedProperty().addListener((e) -> {
			atype.show();
		});
		btype.getItems().add("B2B");
		btype.getItems().add("B2C");
		btype.getItems().add("EXP");
		btype.getItems().add("IMP");
		btype.focusedProperty().addListener((e) -> {
			btype.show();
		});

		// edit module
		if (SessionController.editaid != 0) {
			ResultSet res = get_acc_dao.getAccountDetails(SessionController.editaid);
			while (res.next()) {
				edit(res.getString(3), res.getString(4), res.getString(11), res.getString(12), res.getInt(10),
						res.getString(13), res.getString(5), res.getString(8), res.getString(7), res.getString(6),
						res.getString(9), res.getString(14));
				System.out.print("cdays" + res.getInt(10));
			}
		}
	}

	@FXML
	void createAccount(ActionEvent event) throws SQLException {
		String Aname = name.getText();
		String Aphone = phone.getText();
		String Aadhaar = adhaar.getText();
		String Aemail = email.getText();
		String Acdays = cdays.getText();
		String Agstin = gstin.getText();
		String Aaddress = address.getText();
		String Acountry = country.getSelectionModel().getSelectedItem();
		String Astate = state.getSelectionModel().getSelectedItem();
		String Acity = city.getSelectionModel().getSelectedItem();
		String Aatype = atype.getSelectionModel().getSelectedItem();
		String Abtype = btype.getSelectionModel().getSelectedItem();
		if (SessionController.editaid == 0)
			set_acc_dao.setAccount(Aname, Aphone, Aadhaar, Aemail, Acdays, Agstin, Aaddress, Acountry, Astate,
					Acity, Aatype, Abtype);
		else {

			set_acc_dao.updateAccount(Aname, Aphone, Aadhaar, Aemail, Acdays, Agstin, Aaddress, Acountry,
					Astate, Acity, Aatype, Abtype);
		}

		app_controller.informationDialog("Operation Success !", null);
		Stage window = (Stage) ap.getScene().getWindow();
		window.close();
	}

	@FXML
	void goToGsttab(ActionEvent event) {
		tabpane.getSelectionModel().select(gsttab);
	}

	void edit(String ename, String ephone, String eadhaar, String eemail, int ecdays, String egstin, String eaddress,
			String ecountry, String estate, String ecity, String eatype, String ebtype) {
		if (ename != null || ename != "") {
			name.setText(ename);
			phone.setText(ephone);
			adhaar.setText(eadhaar);
			email.setText(eemail);
			cdays.setText("" + ecdays);
			gstin.setText(egstin);
			address.setText(eaddress);
			country.getSelectionModel().select(ecountry);
			state.getSelectionModel().select(estate);
			city.getSelectionModel().select(ecity);
			atype.getSelectionModel().select(eatype);
			btype.getSelectionModel().select(ebtype);
		}
	}
}
