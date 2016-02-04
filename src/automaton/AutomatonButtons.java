package automaton;


import simulations.CA;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;

public class AutomatonButtons {
	
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
	public void setAutomatonButtons(AutomatonDisplay a, CA ca) {
		reset = createButton("Reset", a.getCanvasX() + 25, a.getCanvasY() + a.getCanvasHeight() + 10);
		start = createButton("Start", a.getCanvasX() + 50 + 25, a.getCanvasY() + a.getCanvasHeight() + 10);
		pause = createButton("Pause", a.getCanvasX() + 100 + 25, a.getCanvasY() + a.getCanvasHeight() + 10);
		step = createButton("Step", a.getCanvasX() + 150 + 25, a.getCanvasY() + a.getCanvasHeight() + 10);
		setButtonActions(ca);
	}
	
	/**
	 * Sets the actions of the buttons
	 */
	private void setButtonActions(CA ca) {
		start.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(ca.getTimeline().getStatus() == Animation.Status.PAUSED) {
		    		ca.getTimeline().play();
		    	}
		    }
		});
		pause.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(ca.getTimeline().getStatus() == Animation.Status.RUNNING) {
		    		ca.getTimeline().pause();
		    	}
		    }
		});
	}
}
