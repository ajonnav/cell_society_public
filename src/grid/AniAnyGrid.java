package grid;
import java.util.Collection;
import java.util.List;
import automaton.*;
import slot.*;

public abstract class AniAnyGrid {
	
	private boolean toroidal;
	private int cellWidth;
	private int cellHeight;
	private int numRow;
	private int numCol;
	private List<AniSlot> slotList;
	private int neighborDepth;
	private String hoodType;
	private String gridShape;
	
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
	
	public AniAnyGrid(XMLArgs xmlArgs, AutomatonDisplay autoDisp) {
		
	}
	
	public void initializeGrid() {
		
	}
	/**
	 * Any Grid will be able to return a Collection of all the slots in the grid.  It will be an adjustable data
	 * structure to accommodate for infinitely large grids. 
	 */
	public Collection<Slot> getSlots() {
		return null;
		
	}
	
	public void setUpSlots() {
		if(gridShape == "Square") {
			int numCell = 0;
			for(int row = 0; row < numRow; row ++) {
				for(int col = 0; col < numCol; col ++) {
					AniSlot slot = new AniSquareSlot(col*cellWidth, row*cellHeight, cellWidth, cellHeight, numCell);
					slotList.add(slot);
					numCell++;
				}
			}
		}
		if(gridShape == "Hexagonal") {
			int numCell = 0;
			for(int row=0;row<numRow;row++) {
				for(int col=0; col<numCol;col++) {
					AniSlot slot;
					if(row%2==0) {
						slot = new AniHexagonalSlot(cellWidth/2 + cellWidth*col, 
								cellWidth/2*Math.tan(Math.toRadians(30)) + (cellHeight+cellWidth/(2*Math.cos(Math.toRadians(30))))*Math.floor(row/2),
								cellWidth, cellHeight, numCell);
					}
					else {
						slot = new AniHexagonalSlot(cellWidth*col, cellHeight + (cellHeight+cellWidth/(2*Math.cos(Math.toRadians(30))))*Math.floor(row/2),
								cellWidth, cellHeight, numCell);
					}
					numCell++;
					slotList.add(slot);
				}
			}
		}
	}
	
	public void calculateNeighbors() {
		for(AniSlot slot: slotList) {
			slot.calculateNeighbors(numRow, numCol, neighborDepth, toroidal, hoodType, slotList);
		}
	}
}
