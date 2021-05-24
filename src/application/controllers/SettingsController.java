package application.controllers;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
        private VBox vbox1;

        @FXML
        private VBox vbox2;

        @FXML
        private VBox vbox3;

        @FXML
        private Button new_field;

        JSONObject fields;

        JSONObject settings ;
        JSONObject hiddenfield,unhiddenfield;

        @FXML
        public void initialize() throws IOException, ParseException {

                JSONParser parser = new JSONParser();
                settings = (JSONObject) parser.parse(new FileReader("src/settings/settings.json"));
                JSONObject pv = (JSONObject) settings.get("purchasevoucher");
                fields = (JSONObject) pv.get("fields"); //unhidden,hidden,newfield
                hiddenfield = (JSONObject) fields.get("hiddenfields");
                unhiddenfield = (JSONObject) fields.get("unhiddenfields");
                hiddenUnhiddenFields("unhiddenfields",true);
                hiddenUnhiddenFields("hiddenfields",false);

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

        public void removeField(String key,String field){

        }

        @FXML
        void newField(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("/application/views/NewField.fxml"));
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(root, 600, 400);
                stage.setScene(scene);
                stage.setTitle("Add New Field");
                stage.setResizable(false);
                stage.showAndWait();
        }
}


