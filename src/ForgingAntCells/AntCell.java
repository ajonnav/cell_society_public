package ForgingAntCells;

import java.util.Collection;

import cells.Cell;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AntCell extends Cell {
	private int lifeCycle;
	private boolean hasFood;
	private double addHormone;
	private GroundCell currentground;
	
	public AntCell(double x, double y, Color color, double w, double h) {
		super(x, y, color, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Will find the next location for the cell and drop hormones 
	 */
	public void update(AntCell[] ants, GroundCell[] groundcells) {
		AntCell moveAnt = nextOrientationAnt(groundcells);
		dropHormone(currentground);
		//remove from neighbors list, get another neighbor list...etc
	}

	/*
	 * Finds the next location for the ant and creates an ant in that location
	 */
	private AntCell nextOrientationAnt(GroundCell[] cells) {
		return moveToNextLocation(findBestNeighbor(getForwardNeighbors()));
	}
	
	/*
	 * Gets the forward neighbors for the ant based on its orientation
	 */
	public abstract Collection<GroundCell> getForwardNeighbors();
	
	/*
	 * Gets all the neighbors (ground) for the ant except its forward neighbors
	 */
	public abstract Collection<GroundCell> getNonForwardNeighbors(); 
	
	/*
	 * Gets passed the forward neighbors for the ant and finds which to move to
	 * If not forward neighbors are available, gets rest of neighbors and finds the best to move to
	 */
	public GroundCell findBestNeighbor(Collection<GroundCell> cells) {
		if (getFoodStatus()) {
			//sorts list based on home hormones
		} else {
			//sorts list based on food hormones
		}
		return currentground;
		
	}
	
	/*
	 * Creates a new ant cell with the new orientation of the ant and copies over the life cycle
	 * and modifies the food status of the ant if appropriate
	 */
	public abstract AntCell moveToNextLocation(GroundCell cell);
	
	private void dropHormone(GroundCell currentground) {
		if (hasFood) {
			currentground.sethomeHormone(addHormone);
		} else {
			currentground.setfoodHormone(addHormone);
		}
	}
	
	/*
	 * Returns true if the ant has food, returns false otherwise
	 */
	public abstract boolean getFoodStatus();
	
}
