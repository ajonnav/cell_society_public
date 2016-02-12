
package slot;
import java.util.Collection;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import cells.Cell;
import java.util.ArrayList;

public class SquareSlot implements Slot {
	private int index, width, height, row, col;
	private double xCoord, yCoord;
	private List<Cell> occupants;
	private List<Slot> neighbors;
	public SquareSlot(int r, int c, int w, int h, int n) {
		// TODO Auto-generated constructor stub
		width = w;
		height = h;
		row = r;
		col = c;
		index = n;
		occupants = new ArrayList<Cell>();
		neighbors = new ArrayList<Slot>();
		setCoordinates();
		
	}

	private void setCoordinates(){
		xCoord = col * width;
		yCoord = row * height;
	}
	
	public int[] getRowCol(){
		int[] ret = {row , col};
		return ret;
	}
	@Override
	public void setNeighbors(Collection<Slot> newNeighbors) {
		// TODO Auto-generated method stub
		neighbors = new ArrayList<Slot>(newNeighbors);

	}

	@Override
	public void addNeighbor(Slot newSlot) {
		// TODO Auto-generated method stub
		neighbors.add(newSlot);

	}

	@Override
	public int index() {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public Collection<Slot> getNeighbors() {
		// TODO Auto-generated method stub
		return neighbors;
	}

	@Override
	public void draw(GraphicsContext gc, Color c) {
		// TODO Auto-generated method stub
		gc.setFill(c);
		gc.fillRect(xCoord, yCoord, width, height);

	}

	@Override
	public Collection<Cell> getOccupants() {
		// TODO Auto-generated method stub
		return occupants;
	}

	@Override
	public void setOccupants(Collection<Cell> cells) {
		// TODO Auto-generated method stub
		occupants = new ArrayList<Cell>(cells);

	}

}
