package automaton;


import java.util.Map;

import simulations.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class AutomatonDisplay {
	private static final String DEFAULT_RESOURCE_PACKAGE = "uicss/";
	private static final String STYLESHEET = "default.css";
	private double canvasY = 0;//10;
	private double canvasX = 0;//17.5;
	private double canvasHeight; //350;
	private double canvasWidth;
	private int windowWidth;
	private int windowHeight;
	
	private final int BUTTON_PANE_HEIGHT = 100;
	
	private Stage window;
	private Scene myDisplay;
	private Group root;
	private Canvas canvas;
	private CA ca;
	private Map<String, String> map;
	
	public AutomatonDisplay(Map<String, String> map) {
		this.map = map;
		root = new Group();
		window = new Stage();
		canvasHeight = Integer.parseInt(map.get("simHeight"));
		canvasWidth = Integer.parseInt(map.get("simWidth"));
		windowWidth = Integer.parseInt(map.get("simWidth"));
		windowHeight = Integer.parseInt(map.get("simHeight"))+ BUTTON_PANE_HEIGHT;
		myDisplay = new Scene(root, windowWidth, windowHeight);
		myDisplay.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
		canvas = new Canvas(canvasWidth, canvasHeight);
		String simName = map.get("name");
		if(simName.equals("GOL")) {
			ca = new GameOfLife(map, this);
		} else if(simName.equals("Fire")) {
			ca = new Fire(map, this);
		} else if(simName.equals("Segregation")) {
			ca = new SchellingCA(map, this);
		}
		else if(simName.equals("Wator")) {
			ca = new Wator(map, this);
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
		AutomatonDisplayButtons controls = new AutomatonDisplayButtons(root, "English");
		controls.setDisplayControllers(ca);
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
}
