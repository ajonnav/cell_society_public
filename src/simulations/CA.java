package simulations;
import java.util.List;

import slot.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import automaton.*;
import factory.*;
import grid.*;

/**
 * Base class for our simulations
 * @author aj168, clz4, cbd20
 *
 */
public class CA {
	
	private final int DEFAULT_FRAMES_PER_SECOND = 30;
	private Grid grid;
	private Timeline timeline;
	private GraphicsContext graphicsContext;
	private Stage window;
	private double simWidth;
	private double simHeight;
	
	/**
	 * Constructor
	 * @param xmlArgs XML arguments parsed in
	 * @param autoDisp AutonmatonDisplay to display the simulation
	 */
	public CA (XMLArgs xmlArgs, AutomatonDisplay autoDisp) {
		grid = (Grid) GridFactory.create(xmlArgs, autoDisp);
		grid.initializeGrid();
		timeline= new Timeline();
		simWidth = xmlArgs.getAsDouble("simWidth");
		simHeight = xmlArgs.getAsDouble("simHeight");
		graphicsContext = autoDisp.getCanvas().getGraphicsContext2D();
		window = autoDisp.getwindow();
	}
	
	/**
	 * Initializes simulation loop
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
		window.setOnCloseRequest(e -> getTimeline().stop());
	}
	
	/**
	 * The actual simulation loop
	 */
	protected void runSimulation() {
		updateCells();
		drawCells();
	}
	
	/**
	 * Updates cells
	 */
	public void updateCells() {
	}
	
	/**
	 * Method that draws the cells
	 */
	public void drawCells() {
		getGraphicsContext().clearRect(0,0,getSimWidth(), getSimHeight());
		for(Slot slot: getAllSlots()) {
			Color c = slot.getOccupants().get(0).getCellColor();
			slot.draw(getGraphicsContext(), c);
		}
	}
	
	/**
	 * Returns the frames per second
	 * @return Frames per second
	 */
	private int getFramesPerSecond() {
		return DEFAULT_FRAMES_PER_SECOND;
	}
	
	public int getNumCol() {
		return grid.getNumCol();
	}
	
	public int getNumRow() {
		return grid.getNumRow();
	}
	
	/**
	 * Returns the index of the slot if you know the row and column
	 * @param row Row of slot
	 * @param col Column of slot
	 * @return Index of slot
	 */
	public int getIndexFromRowCol(int row, int col) {
		return grid.getIndexFromRowCol( row, col);
	}
	
	public List<Slot> getAllSlots() {
		return grid.getSlots();
	}

	public GraphicsContext getGraphicsContext() {
		return graphicsContext;
	}

	public void setGraphicsContext(GraphicsContext graphicsContext) {
		this.graphicsContext = graphicsContext;
	}

	public double getSimWidth() {
		return simWidth;
	}

	public void setSimWidth(double simWidth) {
		this.simWidth = simWidth;
	}

	public double getSimHeight() {
		return simHeight;
	}

	public void setSimHeight(double simHeight) {
		this.simHeight = simHeight;
	}
	
	public void initializeScreen() {		
	}

	public Timeline getTimeline() {
		return timeline;
	}

	private void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}
}
