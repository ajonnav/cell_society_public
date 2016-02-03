package automaton;


import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

public class Main extends Application{
	
	private static final String TITLE = "Cell Society";
	
	/**
	 * Creates a new instance of the splash screen, sets the title of the window and displays the splash screen
	 */
	public void start (Stage s) {
		SplashScreen n = new SplashScreen();
		s.setTitle(TITLE);
		Scene scene = n.initScreen();
        s.setScene(scene);
        s.show();
	}
	
	/**
	 * Launches the animation
	 */
	public static void main (String[] args) {
        launch(args);
    } 
}