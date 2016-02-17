
package slot;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import cells.Cell;
import java.util.ArrayList;
/**
 * Square Slot implementation.
 * @author colinduffy
 *
 */
public class SquareSlot implements Slot {
	private int index, width, height;
	private double x, y;
	private List<Cell> occupants;
	private List<Slot> neighbors;
	public SquareSlot(int x, int y, int w, int h, int n) {
		width = w;
		height = h;
		this.x = x;
		this.y = y;
		index = n;
		occupants = new ArrayList<Cell>();
		neighbors = new ArrayList<Slot>();
	}
	
	@Override
	public void setNeighbors(List<Slot> newNeighbors) {
		// TODO Auto-generated method stub
		neighbors = new ArrayList<Slot>(newNeighbors);

	}

	@Override
	public void addNeighbor(Slot newSlot) {
		// TODO Auto-generated method stub
		neighbors.add(newSlot);

	}
	
	@Override
	/**
	 * Returns the index of the slot
	 */
	public int index() {
		// TODO Auto-generated method stub
		return index;
	}
	
	@Override
	/**
	 * returns the neighbors collection
	 */
	public List<Slot> getNeighbors() {
		// TODO Auto-generated method stub
		return neighbors;
	}
	
	@Override
	/**
	 * GraphicsContext renders the square using the fillRect method
	 */
	public void draw(GraphicsContext gc, Color c) {
		// TODO Auto-generated method stub
		gc.setFill(c);
		gc.fillRect(x, y, width, height);
	}
	
	@Override
	/**
	 * Returns the Occupants
	 */
	public List<Cell> getOccupants() {
		// TODO Auto-generated method stub
		return occupants;
	}
	
	@Override
	/**
	 * Sets the Occupants of the cell
	 */
	public void setOccupants(List<Cell> cells) {
		// TODO Auto-generated method stub
		occupants = new ArrayList<Cell>(cells);

	}
	
	@Override
	/**
	 * Add an occupant to the cell
	 */
	public void addOccupant(Cell cell) {
		occupants.add(cell);
	}



}
