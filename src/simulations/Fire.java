package simulations;

import java.util.ArrayList;
import java.util.List;
import slot.Slot;
import cells.*;
import cells.Cell;
import automaton.AutomatonDisplay;
import automaton.XMLArgs;

/*
 * Author: Christine Zhou, Ani
 */
public class Fire extends CA {
	private double probCatch;
	private List<Integer> burning_x;
	private List<Integer> burning_y;
	private List<Integer> empty_x;
	private List<Integer> empty_y;
	
	public Fire (XMLArgs xmlArgs, AutomatonDisplay a) {
		super(xmlArgs, a);
		// TODO Auto-generated constructor stub
		probCatch = xmlArgs.getAsDouble("probCatch");
		burning_x = xmlArgs.getAsListOfInteger("burning_x");
		burning_y = xmlArgs.getAsListOfInteger("burning_y");
		empty_x = xmlArgs.getAsListOfInteger("empty_x");
		empty_y = xmlArgs.getAsListOfInteger("empty_y");
	}

	@Override
	public void initializeScreen() {
		List<Slot> list = getAllSlots();
		for(Slot slot:list) {
			slot.getOccupants().clear();
		}
		for (int col = 0; col < getNumCol(); col++) {
			for (int row = 0; row < getNumRow(); row++) {
				Cell cell;
				if (burning_x.contains(col) && burning_y.contains(row)) {
					cell = new BurningFireCell();
				} else if (empty_x.contains(col) && empty_y.contains(row)) {
					cell = new EmptyFireCell();
				} else {
					cell = new TreeFireCell(probCatch);
				}
				list.get(getIndexFromRowCol(col, row)).addOccupant(cell);
			}
		}
		initializeSimulationLoop();
		drawCells();
	}

	@Override
	public void updateCells() {
		List<Cell> newCellList = new ArrayList<Cell>();
		List<Slot> slotList = getAllSlots();
		for(Slot slot : slotList) {
			Cell newCell=slot.getOccupants().get(0).update(slot.getNeighbors());
			newCellList.add(newCell);
		}
		for(int i=0; i < slotList.size(); i++) {
			List<Cell> list = new ArrayList<Cell>();
			list.add(newCellList.get(i));
			slotList.get(i).setOccupants(list);
		}
	}
}
