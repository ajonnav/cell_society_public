package simulations;
import automaton.AutomatonDisplay;
import java.util.*;
import cells.*;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public abstract class CA {
	private int simWidth;
	private int simHeight;
	private int numRow;
	private int numCol;
	private String name;
	private String title;
	private String author;
	private int[][] neighbors;
	//private final int[] stateCodes;
	private boolean simOver;
	private List<Cell> allCells;
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
		//stateCodes = states;
		allCells = new ArrayList<Cell>();
		setTimeline(new Timeline());
		setGraphicsContext(a.getCanvas().getGraphicsContext2D());
		simOver = false;
		setCellWidth(getSimWidth()/getNumCol());
		setCellHeight(getSimHeight()/getNumRow());
		setNumCell(getNumCol()*getNumCol());
		neighbors = new int[getNumCell()][getNumCell()];
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
		KeyFrame simulate = new KeyFrame(Duration.INDEFINITE,
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
	
	protected abstract void calculateAdjacencyMatrix();
	
	protected abstract void updateCells();

	protected void drawCells() {
		for (Cell c : allCells) {
			c.draw(getGraphicsContext());
		}
	}

	protected void runSimulation() {
		updateCells();
		drawCells();
		if(simOver) getTimeline().stop();
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
	public int[][] getNeighbors() {
		return neighbors;
	}
	public void setNeighbors(int[][] neighbors) {
		this.neighbors = neighbors;
	}
	//public int[] getStateCodes() {
	//	return stateCodes;
	//}
	public boolean getSimOver() {
		return simOver;
	}
	public void setSimOver(boolean opt) {
		simOver = opt;
	}
	public List<Cell> getAllCells() {
		return allCells;
	}
	public void setAllCells(List<Cell> list) {
		Collections.copy(list, allCells);
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
	public void setCellWidth(int cellWidth) {
		this.cellWidth = cellWidth;
	}
	public double getCellHeight() {
		return cellHeight;
	}
	public void setCellHeight(int cellHeight) {
		this.cellHeight = cellHeight;
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
}
