package slot;
import java.util.List;
import cells.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface Slot {
	/**
	 * The Slot Interface will serve as the basis for which the "slots" rather than cells implement.
	 * Slots serve as the place holder of the cell object-- cells can move around, but the "egg carton" underneath
	 * remains in place -- avoiding saying static.
	 * 
	 * Slots will control their shape, X & Y coordinates, neighbors and draw
	 */
	
	 /**
	  * Will be used when grid is setting up graph.  
	  * Undecided if I squash this method and just set the neighbors when I instantiate the object
	  * I could use some input from y'all about that.
	  * @param neighbors
	  */
	public void setNeighbors(List<Slot> newNeighbors);
	
	
	/**
	 * Again, might use this while building graph.  This method will be useful too for infinite grids to add neighbors
	 * as the grid "grows"
	 * @param newSlot
	 */
	public void addNeighbor(Slot newSlot);
	
	/*
	 * Slot's "serial number" which is its index in the grids getSlots() collection, useful for moving cell objects
	 */
	public int index();
	
	/**
	 * Returns the neighboring slots to this slot.
	 * @return
	 */
	public List<Slot> getNeighbors();
	/**
	 * Thinking about moving the draw to the slot itself. Useful for simulations that have multiple cells in one slot
	 *   
	 * @param gc
	 */
	public void draw(GraphicsContext gc, Color c);
	/**
	 * Returns a collection of all the cells in this slot.
	 * @return
	 */
	public List<Cell> getOccupants();
	/**
	 * Sets the occupants of this slot
	 * @param cells
	 */
	public void setOccupants(List<Cell> cells);
	
	public void addOccupant(Cell cell);
	
}
