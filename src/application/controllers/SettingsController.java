package application.controllers;
import application.Main;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;

public class SettingsController extends ApplicationMainController {

        @FXML
        private AnchorPane fields_display;

        @FXML
        private Button purchasevoucher;

        @FXML
        private Button salesinvoice;

        @FXML
        private Button quotation;

        @FXML
        private VBox vbox1;

        @FXML
        private VBox vbox2;

        @FXML
        private VBox vbox3;

        @FXML
        private HBox hbox2;

        @FXML
        private VBox vbox4;

        @FXML
        private VBox vbox5;

        @FXML
        private VBox vbox6;

        @FXML
        private Button new_column;

        @FXML
        private Button new_field;

        JSONObject fields;

        public static Stage new_field_stage;


        JSONObject settings;
        JSONObject table;
        JSONObject hiddencolumns;
        JSONObject unhiddencolumns;
        JSONObject newcolumn;
        JSONObject hiddenfield,unhiddenfield;
        String current_selected = "purchasevoucher";

        @FXML
        public void initialize() throws IOException, ParseException {
                settings = getJson();
                displayFields();
                displayColumns();
        }


        public void hiddenUnhiddenFields(String fieldstate,boolean isSelected) throws  IOException {

                JSONObject horufields = (JSONObject) fields.get(fieldstate);
                Iterator<?> keys = horufields.keySet().iterator();

                int i=0;
                while(keys.hasNext()){
                        String key = (String) keys.next();
                        String value  = (String) horufields.get(key);
                        CheckBox cb = new CheckBox(value);
                        cb.setSelected(isSelected);
                        cb.setId(key);
                        i++;
                        if(i==1)
                                vbox1.getChildren().add(cb);
                        else if(i==2)
                                vbox2.getChildren().add(cb);
                        else{
                                vbox3.getChildren().add(cb);
                                i=0;
                        }

                        cb.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) -> {
                                horufields.remove(cb.getId());
                                if(oldValue)

                                                hiddenfield.put(cb.getId(),cb.getText());

                                else
                                                unhiddenfield.put(cb.getId(),cb.getText());

                                try {
                                       writeToJson(settings);

                                } catch (IOException e) {
                                        e.printStackTrace();
                                }

                        });

                }

        }




        @FXML
        void newField(ActionEvent event) throws IOException {

                Parent root = FXMLLoader.load(getClass().getResource("/application/views/NewField.fxml"));
                new_field_stage = new Stage();
                new_field_stage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(root, 600, 400);
                new_field_stage.setScene(scene);
                new_field_stage.setTitle(current_selected);
                new_field_stage.setResizable(false);
                new_field_stage.showAndWait();
                Parent root2 =  FXMLLoader.load(getClass().getResource("/application/views/Settings.fxml"));
                Scene sc = new Scene(root2,800,600);
                DashboardController.changeSceneTo(sc);
        }


        @FXML
        void newColumn(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("/application/views/NewColumn.fxml"));
                new_field_stage = new Stage();
                new_field_stage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(root, 600, 400);
                new_field_stage.setScene(scene);
                new_field_stage.setTitle(current_selected);
                new_field_stage.setResizable(false);
                new_field_stage.showAndWait();
                Parent root2 =  FXMLLoader.load(getClass().getResource("/application/views/Settings.fxml"));
                Scene sc = new Scene(root2,800,600);
                DashboardController.changeSceneTo(sc);
        }

        public static Stage getNewFieldStage(){
                return new_field_stage;
        }

        @FXML
        void selectPurchaseVoucher(ActionEvent event) throws IOException, ParseException {
                current_selected = purchasevoucher.getId();
                displayFields();;
                displayColumns();
        }

        @FXML
        void selectQuotation(ActionEvent event) throws IOException {
                current_selected = quotation.getId();
        }

        @FXML
        void selectSalesInvoice(ActionEvent event) throws IOException, ParseException {
                current_selected = salesinvoice.getId();
                displayFields();
                displayColumns();
        }

        public void displayFields() throws IOException, ParseException {
                vbox1.getChildren().clear();
                vbox2.getChildren().clear();
                vbox3.getChildren().clear();

                JSONObject cs = (JSONObject) settings.get(current_selected);
                if(cs != null) {
                        fields = (JSONObject) cs.get("fields"); //unhidden,hidden,newfield
                        hiddenfield = (JSONObject) fields.get("hiddenfields");
                        unhiddenfield = (JSONObject) fields.get("unhiddenfields");
                        hiddenUnhiddenFields("unhiddenfields", true);
                        hiddenUnhiddenFields("hiddenfields", false);
                }
        }

        public void displayColumns() throws IOException {
                vbox4.getChildren().clear();
                vbox5.getChildren().clear();
                vbox6.getChildren().clear();
                JSONObject cs = (JSONObject) settings.get(current_selected);
                if (cs != null) {
                        table = (JSONObject) cs.get("columns");
                        hiddencolumns = (JSONObject) table.get("hiddencolumns");
                        unhiddencolumns = (JSONObject) table.get("unhiddencolumns");
                        hiddenUnhiddenColumns("unhiddencolumns",true);
                        hiddenUnhiddenColumns("hiddencolumns",false);
                }
        }

        public void hiddenUnhiddenColumns(String fieldstate,boolean isSelected) throws  IOException {

                JSONObject horuh_column = (JSONObject) table.get(fieldstate);
                Iterator<?> keys = horuh_column.keySet().iterator();

                int i=3;
                while(keys.hasNext()){
                        String key = (String) keys.next();
                        String value  = (String) horuh_column.get(key);
                        CheckBox cb = new CheckBox(value);
                        cb.setSelected(isSelected);
                        cb.setId(key);
                        i++;
                        if(i==4)
                                vbox4.getChildren().add(cb);
                        else if(i==5)
                                vbox5.getChildren().add(cb);
                        else{
                                vbox6.getChildren().add(cb);
                                i=3;
                        }

                        cb.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) -> {
                                horuh_column.remove(cb.getId());
                                if(oldValue)

                                        hiddencolumns.put(cb.getId(),cb.getText());

                                else
                                        unhiddencolumns.put(cb.getId(),cb.getText());

                                try {
                                        writeToJson(settings);

                                } catch (IOException e) {
                                        e.printStackTrace();
                                }

                        });

                }

        }


}


