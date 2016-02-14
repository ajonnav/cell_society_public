package automaton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import automaton.SplashScreen;

public class Main extends Application{
	
	private static final String TITLE = "Cell Society";
	
	/**
	 * Creates a new instance of the splash screen, sets the title of the window and displays the splash screen
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 */
	public void start (Stage s) throws IOException, ParserConfigurationException, SAXException {
		SplashScreen n = new SplashScreen();
		s.setTitle(TITLE);
		Scene scene = n.initScreen("English");
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