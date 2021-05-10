package application.controllers;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import application.*;
import DAO.SetCompanyDao;

public class CreateCompanyController {

	@FXML
	private TextField cname, ccpassword, caddress, cgstin, cphone, cemail, cpassword;
	@FXML
	private DatePicker fromdate, todate;
	@FXML
	private Button ccbtn;

	int cid;

	SetCompanyDao set_company_dao = new SetCompanyDao();

	@FXML
	void createCompany(ActionEvent event) throws IOException, SQLException {
		Stage cur_stage = (Stage) ccbtn.getScene().getWindow();
		String name = cname.getText();
		String address = caddress.getText();
		String gstin = cgstin.getText();
		String phone = cphone.getText();
		String email = cemail.getText();
		String password = cpassword.getText();
		String cpassword = ccpassword.getText();
		java.sql.Date fromdt = java.sql.Date.valueOf(fromdate.getValue());
		java.sql.Date todt = java.sql.Date.valueOf(todate.getValue());

		set_company_dao.setCompany(name, address, gstin, phone, email, password, cpassword);
		
		ResultSet rs = set_company_dao.getLastid();

		while (rs.next()) {
			cid = rs.getInt(1);
		}

		set_company_dao.setFinYear(cid, fromdt, todt);

		cur_stage.close();

		Parent root = FXMLLoader.load(getClass().getResource("/application/views/Main.fxml"));
		Scene scene = new Scene(root);
		Main.changeSceneTo(scene);

	}

}
