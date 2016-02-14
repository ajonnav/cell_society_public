package automaton;


import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class SplashScreen {
	private static final String DEFAULT_RESOURCE_PACKAGE = "ResourceBundle/";
	private static final int WIDTH = 800;
	private static final int HEIGHT = 400;
	
	private static Group root;
	private static String chosenFile;
	private ResourceBundle myResources;
	
	/**
	 * Initializes the display of the splash screen
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 */
	public Scene initScreen(String language) throws IOException, ParserConfigurationException, SAXException {
		root = new Group();
		Scene s = new Scene(root, WIDTH, HEIGHT);
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("CellSocietyBackground.jpg"));
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		ImageView background = new ImageView(image);
		root.getChildren().add(background);
		setupFileChoose();
		//SplashScreenButtons myChoices = new SplashScreenButtons(root, "English");
		//myChoices.setDisplayControllers();
		return s;
	}
	
	/**
	 * Sets up a file chooser 
	 */
	public void setupFileChoose() {
		FileChooser f = new FileChooser();
		f.setTitle(myResources.getString("XMLButton"));
		f.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML", "*.xml"));
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
			String chosenFile = file.getName();
			XMLArgs xmlArgs = new XMLArgs();
			try {
				xmlArgs.readXML(chosenFile);
				openAutomationWindow(xmlArgs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				showError(myResources.getString("FileNotParsed"));
			} catch(Exception ee) {
				showError(ee.getMessage());
			}
		}
	}
	
	/**
	 * Creates an instance of Automaton display and opens a window for the automaton display
	 * @throws IOException 
	 */
	
	private void openAutomationWindow(XMLArgs xmlArgs) {
		AutomatonDisplay myAutomaton = null;
		try {
			myAutomaton = new AutomatonDisplay(xmlArgs);
			myAutomaton.loadAutomaton();
		}catch(Exception e) {
			showError(e.getMessage());
		}
	}
	
	/**
	 * returns the name of the chosen file
	 */
	public String getChosenFile() {
		return chosenFile;
	}
	
	/**
     * Display given message as an error in the GUI.
     */
    public void showError (String message) {
        Alert alert = new Alert(AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle(myResources.getString("ErrorTitle"));
        alert.showAndWait();
    } 
}