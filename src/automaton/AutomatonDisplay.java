package automaton;

import java.util.Map;
import java.util.ResourceBundle;
import simexception.ConfigFileException;
import simulations.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class AutomatonDisplay {
	private static final String DEFAULT_RESOURCE_PACKAGE = "ResourceBundle/Errors";
	private double canvasY = 0;
	private double canvasX = 0;
	private double canvasHeight;
	private double canvasWidth;
	private int windowWidth;
	private int windowHeight;
	
	private final int BUTTON_PANE_HEIGHT = 100;
	
	private Stage window;
	private Scene myDisplay;
	private Group root;
	private Canvas canvas;
	private Button step;
	private Button pause;
	private Button start;
	private Button reset;
	private CA ca;
	private ResourceBundle myResources;
	
	public AutomatonDisplay(XMLArgs xmlArgs) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		root = new Group();
		window = new Stage();
		canvasHeight = xmlArgs.getAsDouble("simHeight");
		canvasWidth = xmlArgs.getAsDouble("simWidth");
		windowWidth = (int) canvasWidth;
		windowHeight = (int) canvasHeight + BUTTON_PANE_HEIGHT;
		myDisplay = new Scene(root, windowWidth, windowHeight);
		canvas = new Canvas(canvasWidth, canvasHeight);
		
		String simName = xmlArgs.getAsString("name");
		if(simName.equals("GOL")) {
			ca = new GameOfLife(xmlArgs, this);
		} else if(simName.equals("Fire")) {
			ca = new Fire(xmlArgs, this);
		} else if(simName.equals("Segregation")) {
			ca = new SchellingCA(xmlArgs, this);
		}
		else if(simName.equals("Wator")) {
			ca = new Wator(xmlArgs, this);
		}
		else {
			throw new ConfigFileException(myResources.getString("NotValid"), "name");
		}
	}
	
	public Stage getwindow() {
		return window;
	}
	
	/**
	 * Loads the automaton
	 * eventually will have a param for the object from XMLargs
	 */

	public void loadAutomaton() {
		openDisplay();
		//make new CA and do stuff
		ca.initializeScreen();
	}
	
	/**
	 * Creates new window and a group for the window, also adds the buttons for the window
	 */
	private void openDisplay() {
		window.setWidth(windowWidth);
		window.setHeight(windowHeight);
		setDisplayScene();
		setCanvas();
		AutomatonButtons controls = new AutomatonButtons(root, "English");
		controls.setAutomatonButtons(this, ca);
		window.show();
	}
	
	/**
	 * Sets scene and background for the window
	 */
	private Scene setDisplayScene() {
		window.setScene(myDisplay);
		return myDisplay;
	}
	
	/**
	 * Creates a canvas for the window
	 */
	private void setCanvas() {
		canvas.setLayoutX(canvasX);
		canvas.setLayoutY(canvasY);
		root.getChildren().add(canvas);
	}
	
	/**
	 * Returns the canvas of the window
	 */
	public Canvas getCanvas() {
		return canvas;
	}
	
	/**
	 * Returns the group associated with the window
	 */
	public Group getRoot() {
		return root;
	}
	
	public double getCanvasX() {
		return canvasX;
	}
	public double getCanvasY() {
		return canvasY;
	}
	public double getCanvasHeight() {
		return canvasHeight;
	}
	public double getCanvasWidth() {
		return canvasWidth;
	}
	
	public boolean isDouble(String s) {
		try {
			double num = Double.parseDouble(s);
		}
		catch(NumberFormatException e) { 
	        return false; 
		}
		return true;
	}
}