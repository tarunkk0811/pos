package application.controllers;
import java.sql.ResultSet;
import java.sql.SQLException;
import DAO.GetCompaniesDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MainController {
	 
	@FXML
    private Button create_cmpy_btn;
	@FXML
	private Button sel;
    @FXML
    private TreeView<?> tv;
    
    @FXML
    public void initialize() throws SQLException {
    	TreeItem root = new TreeItem("Companies list");
    	
    	ResultSet rs = new GetCompaniesDao().getCompanies();
    	
    	while(rs.next()) {
    		int cid = rs.getInt(1);
    		String name = rs.getString(2);
    		TreeItem company = new TreeItem(name);
    		
    		ResultSet finres = new GetCompaniesDao().getFinYears(cid);
    		
    		while(finres.next()) {
    			TreeItem fyear = new TreeItem(finres.getDate(3).toString()+"   To   "+finres.getDate(4).toString());
    			company.getChildren().add(fyear);
    		}
    		company.setExpanded(true);
    		root.getChildren().add(company);
    		tv.setRoot(root);
    		tv.setShowRoot(false);
    	}
    	
    }
	@FXML
	void showDashboard(ActionEvent event) {

		try {
			Stage primaryStage = (Stage)sel.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/application/views/Dashboard.fxml"));
			Scene sc = new Scene(root);
		
			primaryStage.setScene(sc);
		}catch(Exception e) {
			System.out.printf("Error occured: %s",e);
		}
		
	}
	
    @FXML
    void createCompanyWindow(ActionEvent event) {
    	Object object=null;
    	
    	new ApplicationController().createCompanyWindow(event);
    	
    	TreeItem ti = tv.getSelectionModel().getSelectedItem();
    	if(ti!=null) {
    	object = ti.getValue();
    	ti.setValue(null);
    	ti.setValue(object);
    	}
    }
}
