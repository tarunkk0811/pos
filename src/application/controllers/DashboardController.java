package application.controllers;

import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Border;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Iterator;

import application.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class DashboardController extends  ApplicationMainController{
	private static Stage ccstage, products_stage;
	private static Scene ccscene, products_scene;

	@FXML
	private Border BorderPane;

	@FXML
    private MenuItem accounts,products, bank;

	@FXML
	private MenuItem open_cmpy_btn, createcompanytb;


	@FXML
	void showAccounts(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/views/ShowAccounts.fxml"));
			ccstage = new Stage();
			ccstage.initModality(Modality.APPLICATION_MODAL);
			ccscene = new Scene(root, 820, 500);
			ccscene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			ccstage.setScene(ccscene);
			ccstage.setTitle("Accounts");
			ccstage.setResizable(false);
			ccstage.showAndWait();
		} catch (IOException e) {
			System.out.printf("Error occured: %s", e);
		}
	}

	@FXML
	void showComapanies(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/views/Main.fxml"));
			Scene scene = new Scene(root);
			Main.changeSceneTo(scene);
			SessionController.setFid(0);
		} catch (Exception e) {

		}

	}

	@FXML
	void createCompanyWindow(ActionEvent event) {
		new ApplicationController().createCompanyWindow(event);
	}

	public static void changeSceneTo(Scene sc) {
		ccstage.setScene(sc);
		ccstage.setResizable(false);
	}

	@FXML
	void showProducts(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/views/ShowProducts.fxml"));
			ccstage = new Stage();
			ccstage.initModality(Modality.APPLICATION_MODAL);
			ccscene = new Scene(root, 820, 500);
			ccstage.setScene(ccscene);
			ccscene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			ccstage.setTitle("Products");
			ccstage.setResizable(false);
			ccstage.showAndWait();
		} catch (IOException e) {
			System.out.printf("Error occured: %s", e);
		}
	}


	@FXML
	void showBanks(ActionEvent event) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/views/ShowBanks.fxml"));
			ccstage = new Stage();
			ccstage.initModality(Modality.APPLICATION_MODAL);
			ccscene = new Scene(root,820,500);
			ccscene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			ccstage.setScene(ccscene);
			ccstage.setTitle("Banks");
			ccstage.setResizable(false);
			ccstage.showAndWait();
		} catch (IOException e) {
			System.out.printf("Error occurred: %s", e);
		}
	}

	@FXML
	void createPurchaseVoucher(ActionEvent event) throws IOException, ParseException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/PurchaseVoucher.fxml"));
		Parent root = loader.load();
		Stage stage = new Stage();
		//stage.initModality(Modality.APPLICATION_MODAL);
		Scene scene = new Scene(root, 1280, 800);
		stage.setScene(scene);
		stage.setTitle("Purchase Voucher");
		stage.setResizable(false);
		stage.show();
		PurchaseVoucherController pv = loader.getController();
		hideTableColumns(pv);
	}

	public void hideTableColumns(PurchaseVoucherController pv) throws IOException, ParseException {
		TableView tv = pv.getTV();
		ObservableList<TableColumn> columns = tv.getColumns();
		JSONObject settings = getJson();
		JSONObject purchasev = (JSONObject) settings.get("purchasevoucher");
		JSONObject table  = (JSONObject) purchasev.get("table");
		JSONObject hiddencolumns = (JSONObject) table.get("hiddencolumns");
		Iterator keys = hiddencolumns.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			for (TableColumn e : columns) {
				if (e.getId().equalsIgnoreCase(key)) {
					e.setVisible(false);
					break;
				}
			}
		}
	}


	@FXML
	void showSettings(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/views/Settings.fxml"));
		ccstage = new Stage();
		ccstage.initModality(Modality.APPLICATION_MODAL);
		Scene scene = new Scene(root, 800, 600);
		ccstage.setScene(scene);
		ccstage.setTitle("Settings Wizard");
		ccstage.setResizable(false);
		ccstage.showAndWait();
	}




}
