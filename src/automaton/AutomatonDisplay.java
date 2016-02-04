package automaton;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AutomatonDisplay {
	private static final int CANVAS_Y = 0;//10;
	private static final double CANVAS_X = 0;//17.5;
	private static final int CANVAS_SIZE = 400; //350;
	private static final int WIDTH = 400;
	private static final int HEIGHT = 500;
	
	private Stage window;
	private Scene myDisplay;
	private Group root;
	private Canvas canvas;
	private Button step;
	private Button pause;
	private Button start;
	private Button reset;
	
	public Stage getwindow() {
		return window;
	}
	
	/**
	 * Loads the automaton
	 * eventually will have a param for the object from XMLargs
	 */
	public void loadAutomaton(AutomatonDisplay a) {
		openDisplay(a);

		//make new CA and do stuff
	}
	
	/**
	 * Creates new window and a group for the window, also adds the buttons for the window
	 */
	private void openDisplay(AutomatonDisplay a) {

		window = new Stage();
		root = new Group();
		setDisplayScene();
		setCanvas();
		AutomatonButtons controls = new AutomatonButtons(root);
		controls.setAutomatonButtons(this);
		window.show();
	}
	
	/**
	 * Sets scene and background for the window
	 */
	private Scene setDisplayScene() {
		myDisplay = new Scene(root, WIDTH, HEIGHT);
		Image background = new Image(getClass().getClassLoader().getResourceAsStream("DisplayCA.jpg"));
		ImageView display = new ImageView(background);
		root.getChildren().add(display);
		window.setScene(myDisplay);
		return myDisplay;
	}
	
	/**
	 * Creates a canvas for the window
	 */
	private void setCanvas() {
		canvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE);
		canvas.setLayoutX(CANVAS_X);
		canvas.setLayoutY(CANVAS_Y);
//		GraphicsContext gc = canvas.getGraphicsContext2D();
//		gc.setFill(Color.BLUE);
//		gc.fillRect(0,0,400,400);
//		root.getChildren().add(canvas);
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
	
}
