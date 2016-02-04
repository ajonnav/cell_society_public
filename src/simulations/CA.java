package simulations;
import automaton.AutomatonDisplay;

import java.util.*;

import cells.*;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public abstract class CA {
	protected int cellWidth;
	protected int cellHeight;
	protected int[][] neighbors;
	protected final int[] stateCodes;;
	protected boolean simOver;
	protected ArrayList<Cell> allCells;
	protected Timeline t;
	protected Canvas canvas;

	/**
	 * Default Constructor that I am going to use until we figure out XML file
	 * format
	 * 
	 * @param width
	 * @param height
	 * @param states
	 */
	public CA(int width, int height, int[] states, AutomatonDisplay a) {
		cellWidth = width;
		cellHeight = height;
		stateCodes = states;
		t = new Timeline();
		canvas = a.getCanvas();
		simOver = false;
	}
	/**
	 * Initializes each cell to its starting state, varies from simulation to simulation
	 */
	public abstract void initializeScreen();
	
	/**
	 * Creates Timeline object that will animate simulations
	 */
	protected void initializeSimulationLoop() {
		t.setCycleCount(Animation.INDEFINITE);
		KeyFrame simulate = new KeyFrame(Duration.INDEFINITE,
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						runSimulation();
					}
				});
		t.getKeyFrames().add(simulate);
		t.play();
	}

	/**
	 * How cells are updated also vary depending on CA. This will only be used
	 * by simulation loop. No need for public visibility
	 */
	protected abstract void updateCells();

	protected void drawCells() {
		for (Cell c : allCells) {
			c.draw(canvas.getGraphicsContext2D());
		}
	}

	protected void runSimulation() {
		updateCells();
		drawCells();
		if(!simOver) t.stop();
	}
	
	public int getCellWidth(){
		return cellWidth;
	}
	
	public int getCellHeight(){
		return cellHeight;
	}
	
	public Canvas getCanvas(){
		return canvas;
	}
	
	public int[] getStates(){
		return stateCodes;
	}
	
	public int[][] getNeighbors(){
		return neighbors;
	}
	
	public void setNeighbors(int[][] newList){
		neighbors = newList;
	}

}
