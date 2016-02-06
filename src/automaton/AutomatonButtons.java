package automaton;


import simulations.CA;

import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class AutomatonButtons {
	
	private static final String DEFAULT_RESOURCE_PACKAGE = "ResourceBundle/";
	private Button step;
	private Button pause;
	private Button start;
	private Button reset;
	private Button speedUp;
	private Button slowDown;
	private Group root;
	private HBox hbox;
	private ResourceBundle myResources;
	
	/**
	 * Constructor takes Group of Automaton window as a parameter 
	 */
	public AutomatonButtons(Group root, String language) {
		this.root = root;
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		hbox = new HBox(5);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		root.getChildren().add(hbox);
		hbox.setLayoutX(70);
		hbox.setLayoutY(510);
	}
	
	/**
	 * Creates a button with a certain layout and text
	 */
	private Button createButton(String text, String id) {
		Button b = new Button(text);
		b.setId(id);
		hbox.getChildren().add(b);
		return b;
	}
	
	/**
	 * Sets the buttons for the automaton display
	 */
	public void setAutomatonButtons(AutomatonDisplay a, CA ca) {
		reset = createButton(myResources.getString("ResetButton"), "resetButton");
		start = createButton(myResources.getString("StartButton"), "startButton");
		pause = createButton(myResources.getString("PauseButton"), "pauseButton");
		step = createButton(myResources.getString("StepButton"), "stepButton");
		speedUp = createButton(myResources.getString("SpeedUpButton"), "speedUpButton");
		slowDown = createButton(myResources.getString("SlowDownButton"), "slowDownButton");
		setButtonActions(ca);
	}
	
	/**
	 * Sets the actions of the buttons
	 */
	private void setButtonActions(CA ca) {
		start.setOnAction(e -> ca.getTimeline().play());
		pause.setOnAction(e -> ca.getTimeline().pause());
		step.setOnAction(e -> {
		    	ca.updateCells();
		    	ca.drawCells();
		    });
		reset.setOnAction(e -> {
		    	ca.getTimeline().stop();
		    	ca.initializeScreen();
		    });
		speedUp.setOnAction(e -> {
		    	if(ca.getTimeline().getRate()<=8)
		    		ca.getTimeline().setRate(ca.getTimeline().getRate()*2);
		    });
		slowDown.setOnAction(e -> {
		    	ca.getTimeline().setRate(ca.getTimeline().getRate()/2);
		    });
	}
}
