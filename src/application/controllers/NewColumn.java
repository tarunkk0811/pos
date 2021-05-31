package application.controllers;

import DAO.SetPurchaseDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

public class NewColumn extends ApplicationMainController{
    @FXML
    private TextField column_name;

    @FXML
    private TextField column_default;

    @FXML
    private HBox add_to_hbox;

    @FXML
    private RadioButton none_rbtn;

    @FXML
    private RadioButton nv_rbtn1;

    @FXML
    private RadioButton tv_rbtn;

    @FXML
    private RadioButton nv_rbtn;

    @FXML
    private Button save_column;

    JSONObject window;
    JSONObject columns;
    JSONObject hiddencolumns;
    JSONObject unhiddencolumns;
    ToggleGroup group;
    String window_name;
    @FXML
    private void initialize(){
        group = new ToggleGroup();
        none_rbtn.setToggleGroup(group);
        none_rbtn.setSelected(true);
        tv_rbtn.setToggleGroup(group);
        nv_rbtn.setToggleGroup(group);


    }


    @FXML
    void saveColumn(ActionEvent event) throws JSONException, IOException, ParseException, SQLException {
        String add_to = "";
        String name = column_name.getText();

        RadioButton res = (RadioButton) group.getSelectedToggle();
        add_to = res.getText();

        String default_value = column_default.getText();
        // window_name = SettingsController.getNewFieldStage().getTitle();
        if(fieldNotExists(name)) {
            //Create json object
            JSONObject newfield = new JSONObject();
            newfield.put("name", capitalize(name));
            newfield.put("default", default_value);
            newfield.put("add_to", add_to);

            //save to jsonobject
            JSONObject settings = getJson();
            JSONObject window = (JSONObject) settings.get(window_name); // pv or si or quot
            JSONObject fields = (JSONObject) window.get("columns");
            JSONObject unhidden = (JSONObject) fields.get("unhiddencolumns");

            String key = name.split(" ")[0].toLowerCase() + "_col";
            unhidden.put(key, capitalize(name));

            JSONArray newfield_array = (JSONArray) fields.get("newcolumn");
            newfield_array.add(newfield);
            // save to json file
            if (new SetPurchaseDao().addNewColumn(name, default_value, add_to)){
                writeToJson(settings);
                new ApplicationController().informationDialog(name + " Added successfully!",null);
            }
        }
        else{
            new ApplicationController().informationDialog("Field name Already Exists !",null);
        }


        //RUN QUERY Based on window;

        //Information Dialog
        Stage temp = (Stage)save_column.getScene().getWindow();
        temp.close();
    }

    private boolean fieldNotExists(String name) throws JSONException, IOException, ParseException {

        window_name = SettingsController.new_field_stage.getTitle();
        JSONObject settings = getJson();
        window = (JSONObject) settings.get(window_name);
        columns = (JSONObject) window.get("columns");
        hiddencolumns = (JSONObject) columns.get("hiddencolumns");
        unhiddencolumns = (JSONObject) columns.get("unhiddencolumns");
        Iterator hkeys = hiddencolumns.keySet().iterator();
        Iterator uhkeys = unhiddencolumns.keySet().iterator();
        while(hkeys.hasNext())
            if(hiddencolumns.get((String) hkeys.next()).equals(capitalize(column_name.getText())))
                return false;
        while (uhkeys.hasNext())
            if (unhiddencolumns.get((String) uhkeys.next()).equals(capitalize(column_name.getText())))
                return false;

        return true;
    }

}
