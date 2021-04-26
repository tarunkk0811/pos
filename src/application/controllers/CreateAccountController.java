package application.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Locale;

import DAO.GetAccountsDao;
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

public class CreateAccountController {
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
	
	@FXML
	public void initialize() throws SQLException {
		
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			countries.add(obj.getDisplayCountry());
		}

		ResultSet rs = new GetAccountsDao().getStates();

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
				state = new ApplicationController().searchComboBox(event, state, statescopy);
				state.show();
			}
		});

		city.focusedProperty().addListener((event) -> {
			String statechoosen = state.getSelectionModel().getSelectedItem();
			if (statechoosen != null || statechoosen != "")
				try {
					ResultSet res = new GetAccountsDao().getCities(statechoosen);
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
				city = new ApplicationController().searchComboBox(event, city, citiescopy);
				city.show();
			}
		});	
		
		atype.getItems().add("Customer");
		atype.getItems().add("Vendor");
		atype.focusedProperty().addListener((e)->{
			atype.show();
		});
		btype.getItems().add("B2B");
		btype.getItems().add("B2C");
		btype.getItems().add("EXP");
		btype.getItems().add("IMP");
		btype.focusedProperty().addListener((e)->{
			btype.show();
		});
	}

	@FXML
	void createAccount(ActionEvent event) {
		String Aname = name.getText();
		String Aphone = phone.getText();
		String Aadhaar = adhaar.getText();
		String Aemail = email.getText();
		String Acdays = cdays.getText();
		String Agstin = gstin.getText();
		String Aaddress = address.getText();
		String Acountry=country.getSelectionModel().getSelectedItem();
		String Astate = state.getSelectionModel().getSelectedItem();
		String Acity = city.getSelectionModel().getSelectedItem();
		String Aatype= atype.getSelectionModel().getSelectedItem();
		String Abtype = btype.getSelectionModel().getSelectedItem();
		
		
	}

	@FXML
	void goToGsttab(ActionEvent event) {
		tabpane.getSelectionModel().select(gsttab);
	}

}
