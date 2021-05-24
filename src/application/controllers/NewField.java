package application.controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NewField {
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


    ObservableList<String> field_types = FXCollections.observableArrayList("Text","Number","Dropdown");
    @FXML
    private void initialize(){

        field_type.getItems().addAll(field_types);
    }

    @FXML
    void saveField(ActionEvent event){
        String name = field_name.getText();
        String type = field_types.get(field_type.getSelectionModel().getSelectedIndex());
        String list = combobox_list.getText();
        String default_value = field_default.getText();
        field_type.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String oldValue, String newValue) {
                System.out.println(oldValue + " .. " + newValue);
                if(newValue == "Number")
                    add_to_hbox.setVisible(true);
                else
                    add_to_hbox.setVisible(false);
            }
        });


    }
}
