package application.controllers;

import DAO.SetPurchaseDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Locale;

public class NewField extends  ApplicationMainController{
    @FXML
    private TextField field_name;

    @FXML
    private ComboBox<String> field_type;

    @FXML
    private TextField combobox_list;

    @FXML
    private TextField field_default;

    @FXML
    private Button save_field;

    @FXML
    private HBox add_to_hbox;

    @FXML
    private HBox combolist_hbox;

    @FXML
    private RadioButton rbtn_1;

    @FXML
    private RadioButton rbtn_2;

    @FXML
    private RadioButton rbtn_3;


    @FXML
    private RadioButton tv_rbtn;

    @FXML
    private RadioButton nv_rbtn;


    String window_name;

    JSONObject window;
    JSONObject fields;
    JSONObject hiddenfields;
    JSONObject unhiddenfields;
    String row_num;
    ToggleGroup group;
    ToggleGroup group2;
    ObservableList<String> field_types = FXCollections.observableArrayList("Text","Number","Dropdown");
    @FXML
    private void initialize(){
        group = new ToggleGroup();
        group2 = new ToggleGroup();

        field_type.getItems().addAll(field_types);
        field_type.getSelectionModel().select(0);

        rbtn_1.setToggleGroup(group);
        rbtn_2.setToggleGroup(group);
        rbtn_2.setSelected(true);
        rbtn_2.setSelected(true);
        rbtn_3.setToggleGroup(group);

        tv_rbtn.setToggleGroup(group2);
        tv_rbtn.setSelected(true);
        nv_rbtn.setToggleGroup(group2);

        field_type.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String oldValue, String newValue) {
                if(newValue == "Number")
                    add_to_hbox.setVisible(true);

                else
                    add_to_hbox.setVisible(false);

                if(newValue == "Dropdown")
                    combolist_hbox.setVisible(true);

                else
                    combolist_hbox.setVisible(false);
            }
        });



    }

    @FXML
    void saveField(ActionEvent event) throws JSONException, IOException, SQLException, ParseException {
        RadioButton result = (RadioButton) group.getSelectedToggle();
        row_num = result.getText();
        String add_to = "";
        String name = field_name.getText();
        String type = field_types.get(field_type.getSelectionModel().getSelectedIndex());
        if(type=="Number") {
            RadioButton res = (RadioButton) group2.getSelectedToggle();
            add_to = res.getText();
        }
        String list = combobox_list.getText();
        String default_value = field_default.getText();
       // window_name = SettingsController.getNewFieldStage().getTitle();
        if(fieldNotExists(name)) {
            //Create json object
            JSONObject newfield = new JSONObject();
            newfield.put("rno", row_num);
            newfield.put("name", capitalize(name));
            newfield.put("type", type);
            newfield.put("default", default_value);
            newfield.put("add_to", add_to);
            newfield.put("combobox_list", list);
            //save to jsonobject
            JSONObject settings = getJson();
            JSONObject window = (JSONObject) settings.get(window_name); // pv or si or quot
            JSONObject fields = (JSONObject) window.get("fields");
            JSONObject unhidden = (JSONObject) fields.get("unhiddenfields");

            String key = name.split(" ")[0].toLowerCase() + "hbox";
            unhidden.put(key, capitalize(name));

            JSONArray newfield_array = (JSONArray) fields.get("newfield");
            newfield_array.add(newfield);
            // save to json file
            if (new SetPurchaseDao().addNewField(name, type, default_value)){
                writeToJson(settings);
                new ApplicationController().informationDialog(name + " Added successfully!",null);
            }
        }
        else{
            new ApplicationController().informationDialog("Field name Already Exists !",null);
        }


        //RUN QUERY Based on window;

        //Information Dialog
        Stage temp = (Stage)save_field.getScene().getWindow();
        temp.close();
    }

    private boolean fieldNotExists(String name) throws JSONException, IOException, ParseException {

        window_name = SettingsController.new_field_stage.getTitle();
        JSONObject settings = getJson();
        window = (JSONObject) settings.get(window_name);
        fields = (JSONObject) window.get("fields");
        hiddenfields = (JSONObject) fields.get("hiddenfields");
        unhiddenfields = (JSONObject) fields.get("unhiddenfields");
        Iterator hkeys = hiddenfields.keySet().iterator();
        Iterator uhkeys = unhiddenfields.keySet().iterator();
        while(hkeys.hasNext())
            if(hiddenfields.get((String) hkeys.next()).equals(capitalize(field_name.getText())))
                return false;
        while (uhkeys.hasNext())
            if (unhiddenfields.get((String) uhkeys.next()).equals(capitalize(field_name.getText())))
                return false;

        return true;
    }

}
