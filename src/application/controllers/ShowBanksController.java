package application.controllers;

import DAO.GetBanksDao;
import DAO.SetAccountsDao;
import DAO.SetBankAccountDao;
import application.custom_properties.CustomTreeItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowBanksController {

    @FXML
    private TreeView<String> showbankstv;

    @FXML
    private Button newbtn, editbtn, deletebtn, searchbtn;

    @FXML
    public void initialize() throws SQLException {
    	
        CustomTreeItem root = new CustomTreeItem(String.format("%-40s%-25s%-6s","Bank Name","Customer/Vendor","Balance"));
        ResultSet rs =  new GetBanksDao().getCreatedBanks(SessionController.cid);
        while(rs.next())
        {
            int bid = rs.getInt(2);
            String name = "";
            name = String.format("%-45s%-25s%-6s", rs.getString(1), rs.getString(5), rs.getString(4));
            CustomTreeItem account = new CustomTreeItem(name);
            account.setId(bid);
            root.getChildren().add(account);
        }
        root.setExpanded(true);
        showbankstv.setRoot(root);
    }

    @FXML
    void deleteBank(ActionEvent event) throws SQLException, IOException{
        CustomTreeItem itemtodel = (CustomTreeItem)showbankstv.getSelectionModel().getSelectedItem();
        if(itemtodel!=null) {
            new SetBankAccountDao().deleteBank(itemtodel.getId());
        }
        Parent root2 = FXMLLoader.load(getClass().getResource("/application/views/ShowBanks.fxml"));
        Scene scene = new Scene(root2);
        scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        DashboardController.changeSceneTo(scene);
    }

    @FXML
    void editBank(ActionEvent event) throws IOException{
        CustomTreeItem item = (CustomTreeItem) showbankstv.getSelectionModel().getSelectedItem();
        int bid = item.getId();
        SessionController.editbid = bid;
        Parent root = FXMLLoader.load(getClass().getResource("/application/views/CreateBank.fxml"));
        Stage ccstage = new Stage();
        ccstage.initModality(Modality.APPLICATION_MODAL);
        Scene ccscene = new Scene(root, 820, 500);
        ccscene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        ccstage.setScene(ccscene);
        ccstage.setTitle("Edit Account");
        ccstage.setResizable(false);
        ccstage.showAndWait();
        
        SessionController.editbid = 0;

        Parent root2 = FXMLLoader.load(getClass().getResource("/application/views/ShowBanks.fxml"));
        Scene scene = new Scene(root2);
        scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        DashboardController.changeSceneTo(scene);
    }

    @FXML
    void newBank(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/application/views/CreateBank.fxml"));
        Stage ccstage = new Stage();
        ccstage.initModality(Modality.APPLICATION_MODAL);
        Scene ccscene = new Scene(root, 820, 500);
        ccstage.setScene(ccscene);
        ccstage.setTitle("Create New Bank");
        ccstage.setResizable(false);
        ccstage.showAndWait();
        //event.getScene().getWindow().setWidth(event.getScene().getWidth() + 0.001);
        //newbtn.getScene().getWindow().setWidth(newbtn.getScene().getWidth()+0.001);

        Parent root2 = FXMLLoader.load(getClass().getResource("/application/views/ShowBanks.fxml"));
        Scene scene = new Scene(root2);
        scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        DashboardController.changeSceneTo(scene);
    }

}
