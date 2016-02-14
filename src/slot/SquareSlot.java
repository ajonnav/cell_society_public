
package slot;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import cells.Cell;
import java.util.ArrayList;

public class SquareSlot implements Slot {
	private int index, width, height;
	private double x, y;
	private List<Cell> occupants;
	private List<Slot> neighbors;
	public SquareSlot(int x, int y, int w, int h, int n) {
		// TODO Auto-generated constructor stub
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
	public int index() {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public List<Slot> getNeighbors() {
		// TODO Auto-generated method stub
		return neighbors;
	}

	@Override
	public void draw(GraphicsContext gc, Color c) {
		// TODO Auto-generated method stub
		gc.setFill(c);
		gc.fillRect(x, y, width, height);
	}

	@Override
	public List<Cell> getOccupants() {
		// TODO Auto-generated method stub
		return occupants;
	}

	@Override
	public void setOccupants(List<Cell> cells) {
		// TODO Auto-generated method stub
		occupants = new ArrayList<Cell>(cells);

	}
	public void addOccupant(Cell cell){
		occupants.add(cell);
	}

	@Override
	public void addOccupants(Cell cell) {
		occupants.add(cell);
	}

}
