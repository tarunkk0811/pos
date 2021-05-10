package application.controllers;

import java.sql.SQLException;

import DAO.GetCompaniesDao;
import javafx.stage.Stage;

public class SessionController {
	public static int cid;
	public static int fid;
	public static int editbid,editaid,editpid;

	public static int getCid() {
		return cid;
	}

	public static void setCid(int cid) {
		SessionController.cid = cid;
	}

	public static int getFid() {
		return fid;
	}

	public static void setFid(int fid) {
		SessionController.fid = fid;
	}

	public static void setSession(int fid,Stage stage) throws SQLException {
		setFid(fid);
		int cid = new GetCompaniesDao().getCid(fid);
		setCid(cid);
		String comapanyName = new GetCompaniesDao().getComapanyName(cid);
		String finYear = new GetCompaniesDao().getFinancialYearString(fid);
		stage.setTitle("POS | " + comapanyName + " | " + finYear);
	}

	public static void closeSession(Stage stage) {
		setFid(0);
		setCid(0);
		stage.setTitle("POS");
	}

}
