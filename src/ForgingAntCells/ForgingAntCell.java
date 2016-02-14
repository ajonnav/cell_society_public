package ForgingAntCells;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ForgingAntCells.GroundCell;
import cells.Cell;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import slot.Slot;

public class ForgingAntCell extends Cell {

	public ForgingAntCell(Color color) {
		super(color);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Returns a list of the ground cells of a given slot
	 */
	public List<GroundCell> getGroundCellsFromSlot(Collection<Slot> slots) {
		List<GroundCell> my_groundcells = new ArrayList<GroundCell>();
		for (Slot s : slots) {
			Collection<Cell> occupants = s.getOccupants();
			for (Cell c : occupants) {
				if(c instanceof GroundCell) {
					my_groundcells.add((GroundCell) c);
				}
			}			
		}
		return my_groundcells;
	}

}
