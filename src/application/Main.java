	package application;
	import javafx.application.Application;
	import javafx.stage.Stage;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.fxml.FXMLLoader;
	
	//error in bluestar ac rate
	public class Main extends Application {
		
		private static Stage window;

		@Override
		public void start(Stage primaryStage) {
			try {
				window = primaryStage;
				Parent root = FXMLLoader.load(getClass().getResource("/application/views/Main.fxml"));
				Scene scene = new Scene(root,1280,800);
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				window.setScene(scene);
				window.setMaximized(true);
				window.setTitle("POS");
				window.show();	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void changeSceneTo(Scene sc) {
			window.setScene(sc);
			window.setMaximized(true);
		}
		
		public static void main(String[] args) {
			launch(args);
		}
	}