package grid;
import java.util.Collection;
import slot.*;
public interface AnyGrid {
	/**
	 * The Constructor for each implementation of AnyGrid will get the necessary parameters for each type of grid
	 * Simulation can initialize the grid by calling this  method.
	 * This will build a Collection (most likely an ArrayList) of Slot objects
	 * How the slots and list is ordered will depend on the type of grid
	 * Finite Grids will use the same ordering that we have used (i, j) 
	 * Toroidal Grids have a finite number of slots, therefore they will have the same ordering as finite grids
	 * Infinite Grids are a special case - they will be initialized to a certain r x c, however they will be ordered
	 * using a dovetailing technique.
	 */
	public void initializeGrid();
	/**
	 * Any Grid will be able to return a Collection of all the slots in the grid.  It will be an adjustable data
	 * structure to accommodate for infinitely large grids. 
	 */
	public Collection<Slot> getSlots();
	
	
	
}
