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

public class SettingsController {

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
        private Button new_field;

        JSONObject fields;

        public static Stage new_field_stage;


        static JSONObject settings ;
        JSONObject hiddenfield,unhiddenfield;
        String current_selected = "purchasevoucher";

        @FXML
        public void initialize() throws IOException, ParseException {
                displayField();
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
                                        FileOutputStream outputStream = new FileOutputStream("src/settings/settings.json");
                                        byte[] strToBytes = settings.toString().getBytes();
                                        outputStream.write(strToBytes);
                                        outputStream.close();

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

        public static Stage getNewFieldStage(){
                return new_field_stage;
        }

        @FXML
        void selectPurchaseVoucher(ActionEvent event) throws IOException, ParseException {
                current_selected = purchasevoucher.getId();
                displayField();;
        }

        @FXML
        void selectQuotation(ActionEvent event) throws IOException {
                current_selected = quotation.getId();
        }

        @FXML
        void selectSalesInvoice(ActionEvent event) throws IOException, ParseException {
                current_selected = salesinvoice.getId();
                displayField();
        }

        public void displayField() throws IOException, ParseException {
                vbox1.getChildren().clear();
                vbox2.getChildren().clear();
                vbox3.getChildren().clear();
                JSONParser parser = new JSONParser();
                settings = (JSONObject) parser.parse(new FileReader("src/settings/settings.json"));
                JSONObject pv = (JSONObject) settings.get(current_selected);
                if(pv != null) {
                        fields = (JSONObject) pv.get("fields"); //unhidden,hidden,newfield
                        hiddenfield = (JSONObject) fields.get("hiddenfields");
                        unhiddenfield = (JSONObject) fields.get("unhiddenfields");
                        hiddenUnhiddenFields("unhiddenfields", true);
                        hiddenUnhiddenFields("hiddenfields", false);
                }
        }
}


