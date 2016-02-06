package automaton;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class SplashScreen {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 400;
	
	private static Group root;
	private static String chosenFile;
	
	/**
	 * Initializes the display of the splash screen
	 */
	public Scene initScreen() {
		root = new Group();
		Scene s = new Scene(root, WIDTH, HEIGHT);
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("CellSocietyBackground.jpg"));
		ImageView background = new ImageView(image);
		root.getChildren().add(background);
		setupFileChoose();
		return s;
	}
	
	/**
	 * Sets up a file chooser 
	 */
	public void setupFileChoose() {
		FileChooser f = new FileChooser();
		f.setTitle("Open XML file");
		setUploadButton(f);
	}
	
	/**
	 * Creates a button to allow upload of a file
	 */
	private void setUploadButton(FileChooser f) {
		Button b = new Button("Open XML");
		b.setLayoutX(WIDTH/2.25);
		b.setLayoutY(HEIGHT*2/3);
		root.getChildren().add(b);
		b.setOnAction(e -> selectFiletoParse(f));
	}
	
	/**
	 * Gets the name of the chosen file and passes it to xmlArgs; then calls for an automation window to be opened
	 */
	private void selectFiletoParse(FileChooser f) {
		File file = f.showOpenDialog(root.getScene().getWindow());
		if (file != null) {
			chosenFile = file.getName();
			XMLArgs xmlargs = new XMLArgs();
			HashMap<String, String> argsMap = xmlargs.readXML(file.getAbsolutePath());
			//return object from parsed
			openAutomationWindow(argsMap);
		}
	}
	
	/**
	 * Creates an instance of Automaton display and opens a window for the automaton display
	 */


	private void openAutomationWindow(Map<String, String> argsMap) {
		AutomatonDisplay myAutomation = new AutomatonDisplay(argsMap);
		myAutomation.loadAutomaton();

	}
	
	/**
	 * returns the name of the chosen file
	 */
	public String getChosenFile() {
		return chosenFile;
	}
}
