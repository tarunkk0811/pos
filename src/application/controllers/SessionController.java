package application.controllers;

public class SessionController {
	public static int cid;
	public static int fid;
	
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
	
}
