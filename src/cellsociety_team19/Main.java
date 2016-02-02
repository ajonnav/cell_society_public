package cellsociety_team19;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

public class Main extends Application{
	
	private static final String TITLE = "Cell Society";
	
	public void start (Stage s) {
		SplashScreen n = new SplashScreen();
		s.setTitle(TITLE);
		Scene scene = n.initScreen();
        s.setScene(scene);
        s.show();
	}
	
	public static void main (String[] args) {
        launch(args);
    } 
}