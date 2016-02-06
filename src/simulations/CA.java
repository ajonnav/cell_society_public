package simulations;
import automaton.AutomatonDisplay;

import java.util.*;

import cells.*;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public abstract class CA {

	private final int DEFAULT_FRAMES_PER_SECOND = 30;
	private int simWidth;
	private int simHeight;
	private int numRow;
	private int numCol;
	private String name;
	private String title;
	private String author;
	private int[][] adjacency;
	private boolean simOver;
	private Timeline timeline;
	private GraphicsContext graphicsContext;
	private double cellWidth;
	private double cellHeight;
	private int numCell;

	/**
	 * Default Constructor that I am going to use until we figure out XML file
	 * format
	 * 
	 * @param width
	 * @param height
	 * @param states
	 */

	public CA(Map<String, String> argsMap, AutomatonDisplay a) {
		setSimWidth(Integer.parseInt(argsMap.get("simWidth")));
		setSimHeight(Integer.parseInt(argsMap.get("simHeight")));
		setNumRow(Integer.parseInt(argsMap.get("numRow")));
		setNumCol(Integer.parseInt(argsMap.get("numCol")));
		setName(argsMap.get("name"));
		setTitle(argsMap.get("title"));
		setAuthor(argsMap.get("author"));
		setTimeline(new Timeline());
		setGraphicsContext(a.getCanvas().getGraphicsContext2D());
		simOver = false;
		setCellWidth((double)getSimWidth()/getNumCol());
		setCellHeight((double)getSimHeight()/getNumRow());
		setNumCell(getNumRow()*getNumCol());
		adjacency = new int[getNumCell()][getNumCell()];
	}
	/**
	 * Initializes each cell to its starting state, varies from simulation to simulation
	 */
	public abstract void initializeScreen();
	
	/**
	 * Creates Timeline object that will animate simulations
	 */
	protected void initializeSimulationLoop() {
		getTimeline().setCycleCount(Animation.INDEFINITE);
		KeyFrame simulate = new KeyFrame(Duration.millis(1000/getFramesPerSecond()),
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						runSimulation();
					}
				});
		getTimeline().getKeyFrames().add(simulate);
		//getTimeline().play();
	}

	/**
	 * How cells are updated also vary depending on CA. This will only be used
	 * by simulation loop. No need for public visibility
	 */
	
	protected abstract void calculateAdjacencyMatrixAndSetNeighbor();
	
	public abstract void updateCells();

	public abstract void drawCells();

	protected void runSimulation() {
		updateCells();
		drawCells();
		if(simOver){ getTimeline().stop();
		graphicsContext.clearRect(0, 0, simWidth, simHeight);
		graphicsContext.setFill(Color.GOLD);
		graphicsContext.fill();
		}
	}
	
	public int getSimWidth() {
		return simWidth;
	}
	public void setSimWidth(int simWidth) {
		this.simWidth = simWidth;
	}
	public int getSimHeight() {
		return simHeight;
	}
	public void setSimHeight(int simHeight) {
		this.simHeight = simHeight;
	}
	public int[][] getAdjacency() {
		return adjacency;
	}
	public void setAdjacency(int[][] adjacency) {
		this.adjacency = adjacency;
	}
	
	public boolean getSimOver() {
		return simOver;
	}
	public void setSimOver(boolean opt) {
		simOver = opt;
	}
	
	public int getNumRow() {
		return numRow;
	}
	public void setNumRow(int numRow) {
		this.numRow = numRow;
	}
	public int getNumCol() {
		return numCol;
	}
	public void setNumCol(int numCol) {
		this.numCol = numCol;
	}
	public Timeline getTimeline() {
		return timeline;
	}
	private void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getCellWidth() {
		return cellWidth;
	}
	public void setCellWidth(double d) {
		this.cellWidth = d;
	}
	public double getCellHeight() {
		return cellHeight;
	}
	public void setCellHeight(double d) {
		this.cellHeight = d;
	}
	public GraphicsContext getGraphicsContext() {
		return graphicsContext;
	}
	private void setGraphicsContext(GraphicsContext graphicsContext) {
		this.graphicsContext = graphicsContext;
	}
	public int getNumCell() {
		return numCell;
	}
	public void setNumCell(int numCell) {
		this.numCell = numCell;
	}
	
	private int getFramesPerSecond() {
		return DEFAULT_FRAMES_PER_SECOND;
	}

}
