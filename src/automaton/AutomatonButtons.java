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
	private Button speedUp;
	private Button slowDown;
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
		start = createButton("Start", a.getCanvasX() + 75 + 25, a.getCanvasY() + a.getCanvasHeight() + 10);
		pause = createButton("Pause", a.getCanvasX() + 125 + 25, a.getCanvasY() + a.getCanvasHeight() + 10);
		step = createButton("Step", a.getCanvasX() + 175 + 25, a.getCanvasY() + a.getCanvasHeight() + 10);
		speedUp = createButton("Speed Up", a.getCanvasX() + 225 + 25, a.getCanvasY() + a.getCanvasHeight() + 10);
		slowDown = createButton("Slow Down", a.getCanvasX() + 300 + 25, a.getCanvasY() + a.getCanvasHeight() + 10);
		setButtonActions(ca);
	}
	
	/**
	 * Sets the actions of the buttons
	 */
	private void setButtonActions(CA ca) {
		start.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	ca.getTimeline().play();
		    }
		});
		pause.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent ee) {
		    	ca.getTimeline().pause();
		    }
		});
		step.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent eee) {
		    	ca.updateCells();
		    	ca.drawCells();
		    }
		});
		reset.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent eee) {
		    	ca.getTimeline().stop();
		    	ca.initializeScreen();
		    }
		});
		speedUp.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent eee) {
		    	if(ca.getTimeline().getRate()<=8)
		    		ca.getTimeline().setRate(ca.getTimeline().getRate()*2);
		    }
		});
		slowDown.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent eee) {
		    	ca.getTimeline().setRate(ca.getTimeline().getRate()/2);
		    }
		});
	}
}
