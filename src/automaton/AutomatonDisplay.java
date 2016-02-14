package automaton;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import factory.SimulationFactory;
import simexception.ConfigFileException;
import simulations.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

/*
 * @ Author: Christine Zhou
 */

public class AutomatonDisplay {
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
	private CA ca;
	
	
	public AutomatonDisplay(XMLArgs xmlArgs) {
		
		root = new Group();
		window = new Stage();
		canvasHeight = xmlArgs.getAsDouble("simHeight");
		canvasWidth = xmlArgs.getAsDouble("simWidth");
		windowWidth = (int) canvasWidth;
		windowHeight = (int) canvasHeight + BUTTON_PANE_HEIGHT;
		myDisplay = new Scene(root, windowWidth, windowHeight);
		canvas = new Canvas(canvasWidth, canvasHeight);
		String simName = xmlArgs.getAsString("name");
		ca=SimulationFactory.create(simName, xmlArgs, this);
	}
	
	public Stage getwindow() {
		return window;
	}
	
	/**
	 * Loads the automaton
	 * eventually will have a param for the object from XMLargs
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */

	public void loadAutomaton() throws IOException, ParserConfigurationException, SAXException {
		openDisplay();
		//make new CA and do stuff
		ca.initializeScreen();
	}
	
	/**
	 * Creates new window and a group for the window, also adds the buttons for the window
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	private void openDisplay() throws IOException, ParserConfigurationException, SAXException {
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