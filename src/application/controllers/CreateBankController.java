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
    private TextField accountNumber, ifscCode, balance;

    @FXML
    private Button createBank;

    ObservableList<String> accounts = FXCollections.observableArrayList();

    ObservableList<String> banks = FXCollections.observableArrayList();

    HashMap<String, Integer> accountDetails = new HashMap<String, Integer>();
    HashMap<String, Integer> bankDetails = new HashMap<String, Integer>();
    @FXML
    public void initialize() throws SQLException {


        ResultSet rs = new GetAccountsDao().getAllAccounts();
        while (rs.next()) {
            int accountId = rs.getInt(1);
            String accountName = rs.getString(2);
            accountDetails.put(accountName, accountId);
            accounts.add(accountName);
        }
        Collections.sort(accounts);
        account.setEditable(true);

        account.getItems().addAll(accounts);

        account.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            ObservableList<String> accountCopy = FXCollections.observableArrayList(accounts);
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
                account.getItems().addAll(accountCopy);
            } else if (ascii >= 65 && ascii <= 122) {
                account = new ApplicationController().searchComboBox(event, account, accountCopy);
                account.show();
            }
        });


        ResultSet res = new GetBanksDao().getAllBanks();
        while (res.next()) {
            int bankId = res.getInt(1);
            String bankName = res.getString(2);
            bankDetails.put(bankName, bankId);
            banks.add(bankName);
        }
        Collections.sort(banks);
        bank.setEditable(true);

        bank.getItems().addAll(banks);

        bank.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            ObservableList<String> bankCopy = FXCollections.observableArrayList(banks);
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
                bank.getItems().addAll(bankCopy);
            } else if (ascii >= 65 && ascii <= 122) {
                bank = new ApplicationController().searchComboBox(event, bank, bankCopy);
                bank.show();
            }
        });
    }

    @FXML
    void createBank(ActionEvent event) throws SQLException{
        String accNumber = accountNumber.getText();
        String accIfsc = ifscCode.getText();
        String accBalance = balance.getText();
        Integer accountID = accountDetails.get(account.getSelectionModel().getSelectedItem());
        Integer bankId = bankDetails.get(bank.getSelectionModel().getSelectedItem());
        new SetBankAccountDao().addBankAccount(accNumber, accIfsc, accBalance, accountID, bankId);

        new ApplicationController().informationDialog("Operation Success !", null);
        Stage window = (Stage) ap.getScene().getWindow();
        window.close();
    }

}
