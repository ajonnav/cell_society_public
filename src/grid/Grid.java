package grid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import slot.AniHexagonalSlot;
import slot.AniSlot;
import slot.AniSquareSlot;
import slot.HexagonalSlot;
import slot.Slot;
import slot.SquareSlot;

public abstract class Grid {

	protected List<Slot> slotList;
	private int numRow, numCol, cellWidth, cellHeight;
	private String gridShape;
	private Direction[] directions;
	
	public Grid(int r, int c, int w, int h, String s, Direction[] neighborsToCheck) {
		numRow = r;
		setNumCol(c);
		slotList = new ArrayList<Slot>();
		cellWidth = w;
		cellHeight = h;
		setDirections(neighborsToCheck);
		gridShape = s;
	}

	public void initializeGrid() {
		setUpSlots();
		setNeighbors();
	}
	
	public void setUpSlots() {
		if(gridShape.equals("Square")) {
			int numCell = 0;
			for(int row = 0; row < numRow; row ++) {
				for(int col = 0; col < getNumCol(); col ++) {
					Slot slot = new SquareSlot(col*cellWidth, row*cellHeight, cellWidth, cellHeight, numCell);
					slotList.add(slot);
					numCell++;
				}
			}
		}
		if(gridShape.equals("Hexagonal")) {
			int numCell = 0;
			for(int row=0;row<numRow;row++) {
				for(int col=0; col<getNumCol();col++) {
					Slot slot;
					if(row%2==0) {
						slot = new HexagonalSlot(cellWidth/2 + cellWidth*col, 
								cellWidth/2*Math.tan(Math.toRadians(30)) + (cellHeight+cellWidth/(2*Math.cos(Math.toRadians(30))))*Math.floor(row/2),
								cellWidth, cellHeight, numCell);
					}
					else {
						slot = new HexagonalSlot(cellWidth*col, cellHeight + (cellHeight+cellWidth/(2*Math.cos(Math.toRadians(30))))*Math.floor(row/2),
								cellWidth, cellHeight, numCell);
					}
					numCell++;
					slotList.add(slot);
				}
			}
		}
	}

	public abstract void setNeighbors();
	
	public int getIndexFromRowCol(int row, int col) {
		return row*numCol+col;
	}

	public Direction[] getDirections() {
		return directions;
	}
	
	public void setDirections(Direction[] directions) {
		this.directions = directions;
	}

	public int getNumCol() {
		return numCol;
	}

	public void setNumCol(int numCol) {
		this.numCol = numCol;
	}
	
	public int getNumRow() {
		return numRow;
	}

	public void setNumRow(int numRow) {
		this.numRow = numRow;
	}
	
	public List<Slot> getSlots() {
		return slotList;
	}
	
}
