package application.controllers;

import DAO.GetAccountsDao;
import DAO.GetBanksDao;
import DAO.SetBankAccountDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;

public class CreateBankController {

    @FXML
    private AnchorPane ap;

    @FXML
    private ComboBox<String> account, bank;

    @FXML
    private TextField account_number, ifsc_code, balance;

    @FXML
    private Button create_bank;

    ObservableList<String> accounts = FXCollections.observableArrayList();

    ObservableList<String> banks = FXCollections.observableArrayList();

    HashMap<String, Integer> account_details = new HashMap<String, Integer>();
    HashMap<String, Integer> bank_details = new HashMap<String, Integer>();
    @FXML
    public void initialize() throws SQLException {


        ResultSet rs = new GetAccountsDao().getAllAccounts();
        while (rs.next()) {
            int account_id = rs.getInt(1);
            String account_name = rs.getString(2);
            account_details.put(account_name, account_id);
            accounts.add(account_name);
        }
        Collections.sort(accounts);
        account.setEditable(true);

        account.getItems().addAll(accounts);

        account.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            ObservableList<String> account_copy = FXCollections.observableArrayList(accounts);
            String inp = account.getEditor().getText();
            int ascii = 0;
            try {
                ascii = event.getText().charAt(0);
            } catch (Exception e) {
                // System.out.print("Cant convert to ascii due to index of input event in create
                // account states");
            }
            if (ascii != 99 && inp == "") {
                account.getItems().clear();
                account.getItems().addAll(account_copy);
            } else if (ascii >= 65 && ascii <= 122) {
                account = new ApplicationController().searchComboBox(event, account, account_copy);
                account.show();
            }
        });


        ResultSet res = new GetBanksDao().getAllBanks();
        while (res.next()) {
            int bank_id = res.getInt(1);
            String bank_name = res.getString(2);
            bank_details.put(bank_name, bank_id);
            banks.add(bank_name);
        }
        Collections.sort(banks);
        bank.setEditable(true);

        bank.getItems().addAll(banks);

        bank.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            ObservableList<String> bank_copy = FXCollections.observableArrayList(banks);
            String inp = bank.getEditor().getText();
            int ascii = 0;
            try {
                ascii = event.getText().charAt(0);
            } catch (Exception e) {
                // System.out.print("Cant convert to ascii due to index of input event in create
                // account states");
            }
            if (ascii != 99 && inp == "") {
                bank.getItems().clear();
                bank.getItems().addAll(bank_copy);
            } else if (ascii >= 65 && ascii <= 122) {
                bank = new ApplicationController().searchComboBox(event, bank, bank_copy);
                bank.show();
            }
        });
        
        account.focusedProperty().addListener((event)->{
        	account.show();
        });
        bank.focusedProperty().addListener((event)->{
        	bank.show();
        });
        
    }

    @FXML
    void createBank(ActionEvent event) throws SQLException{
        String accNumber = account_number.getText();
        String accIfsc = ifsc_code.getText();
        String accBalance = balance.getText();
        Integer accountID = account_details.get(account.getSelectionModel().getSelectedItem());
        Integer bankId = bank_details.get(bank.getSelectionModel().getSelectedItem());
        new SetBankAccountDao().addBankAccount(accNumber, accIfsc, accBalance, accountID, bankId);

        new ApplicationController().informationDialog("Operation Success !", null);
        Stage window = (Stage) ap.getScene().getWindow();
        window.close();
    }
    
    

}
