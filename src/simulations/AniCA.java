package simulations;
import java.util.List;
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

public class AniCA {
	
	private final int DEFAULT_FRAMES_PER_SECOND = 30;
	private Grid grid;
	private Timeline timeline;
	private GraphicsContext graphicsContext;
	private Stage window;
	private double simWidth;
	private double simHeight;
	
	public AniCA (XMLArgs xmlArgs, AutomatonDisplay autoDisp) {
		grid = (Grid) GridFactory.create(xmlArgs, autoDisp);
		grid.initializeGrid();
	}
	
	protected void initializeSimulationLoop() {
		timeline.setCycleCount(Animation.INDEFINITE);
		KeyFrame simulate = new KeyFrame(Duration.millis(1000/getFramesPerSecond()),
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						runSimulation();
					}
				});
		timeline.getKeyFrames().add(simulate);
		//getTimeline().play();
		window.setOnCloseRequest(e -> timeline.stop());
	}
	
	protected void runSimulation() {
		updateCells();
		drawCells();
	}
	
	protected void updateCells() {
	}
	
	protected void drawCells() {
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
}
