package simulations;
import java.util.List;
<<<<<<< HEAD
=======

>>>>>>> master
import slot.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.util.Duration;
import automaton.*;
import factory.*;
import grid.*;

public class CA {
	
	private final int DEFAULT_FRAMES_PER_SECOND = 30;
	private Grid grid;
	private Timeline timeline;
	private GraphicsContext graphicsContext;
	private Stage window;
	private double simWidth;
	private double simHeight;
	
	public CA (XMLArgs xmlArgs, AutomatonDisplay autoDisp) {
		grid = (Grid) GridFactory.create(xmlArgs, autoDisp);
		grid.initializeGrid();
		timeline= new Timeline();
		simWidth = xmlArgs.getAsDouble("simWidth");
		simHeight = xmlArgs.getAsDouble("simHeight");
		graphicsContext = autoDisp.getCanvas().getGraphicsContext2D();
		window = autoDisp.getwindow();
	}
	
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
	
	protected void runSimulation() {
		updateCells();
		drawCells();
<<<<<<< HEAD
	}
	
	public void updateCells() {
	}
	
	public void drawCells() {
	}
	
	private int getFramesPerSecond() {
		return DEFAULT_FRAMES_PER_SECOND;
	}
	
	public int getNumCol() {
		return grid.getNumCol();
	}
	
	public int getNumRow() {
		return grid.getNumRow();
	}
	
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

	
=======
	}
	
	public void updateCells() {
	}
	
	public void drawCells() {
		getGraphicsContext().clearRect(0,0,getSimWidth(), getSimHeight());
		for(Slot slot: getAllSlots()) {
			Color c = slot.getOccupants().get(0).getCellColor();
			slot.draw(getGraphicsContext(), c);
		}
	}
	
	private int getFramesPerSecond() {
		return DEFAULT_FRAMES_PER_SECOND;
	}
	
	public int getNumCol() {
		return grid.getNumCol();
	}
	
	public int getNumRow() {
		return grid.getNumRow();
	}
	
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

	
>>>>>>> master
	public void initializeScreen() {		
	}

	public Timeline getTimeline() {
		return timeline;
	}

	private void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}
}
