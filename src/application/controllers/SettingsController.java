package application.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

        @FXML
        public void initialize() throws IOException, ParseException {

            //read json
                JSONParser parser = new JSONParser();
                JSONObject settings = (JSONObject) parser.parse(new FileReader("src/settings/settings.json"));

                JSONObject pv = (JSONObject) settings.get("puchasevoucher"); //fields,table
                JSONObject fields = (JSONObject) pv.get("fields"); //unhidden,hidden,newfield
                JSONObject unhiddenfields = (JSONObject) fields.get("unhiddenfields");
                Iterator<?> keys = unhiddenfields.keySet().iterator();
                int i=0;
                while(keys.hasNext()){
                        String key = (String) keys.next();
                        String value  = (String) unhiddenfields.get(key);
                        System.out.println(key+"  "+value);
                        CheckBox cb = new CheckBox(value);
                        cb.setSelected(true);
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

                }



        }


}


