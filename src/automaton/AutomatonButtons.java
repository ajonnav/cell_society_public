package automaton;


import javafx.scene.Group;
import javafx.scene.control.Button;

public class AutomatonButtons {
	private static final int CANVAS_Y = 10;
	private static final double CANVAS_X = 17.5;
	private static final int CANVAS_SIZE = 350;
	
	private Button step;
	private Button pause;
	private Button start;
	private Button reset;
	private Group root;
	
	/**
	 * Constructor takes Group of Automaton window as a parameter 
	 */
	public AutomatonButtons(Group root) {
		this.root = root;
	}
	
	/**
	 * Creates a button with a certain layout and text
	 */
	private Button createButton(String text, double x, double y) {
		Button b = new Button(text);
		b.setLayoutX(x);
		b.setLayoutY(y);
		root.getChildren().add(b);
		return b;
	}
	
	/**
	 * Sets the buttons for the automaton display
	 */
	public void setAutomatonButtons(AutomatonDisplay a) {
		reset = createButton("Reset", CANVAS_X + 25, CANVAS_Y + CANVAS_SIZE + 10);
		start = createButton("Start", CANVAS_X + 50 + 25, CANVAS_Y + CANVAS_SIZE + 10);
		pause = createButton("Pause", CANVAS_X + 100 + 25, CANVAS_Y + CANVAS_SIZE + 10);
		step = createButton("Step", CANVAS_X + 150 + 25, CANVAS_Y + CANVAS_SIZE + 10);
	}
	
	/**
	 * Sets the actions of the buttons
	 */
	private void setButtonActions() {
		
	}
}
