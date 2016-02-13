package simulations;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import cells.*;
import slot.Slot;
import automaton.AutomatonDisplay;
import automaton.XMLArgs;
import grid.*;

public class SegregationGrid extends CA {
	private double tPercentage;
	private double typeOneP;
	private double vacantP;
	private AnyGrid simGrid;

	public SegregationGrid(XMLArgs xmlArgs, AutomatonDisplay a) {
		super(xmlArgs, a);
		// TODO Auto-generated constructor stub
		tPercentage = xmlArgs.getAsDouble("tPercentage");
		typeOneP = xmlArgs.getAsDouble("typeOneP");
		vacantP = xmlArgs.getAsDouble("vacantP");
		simGrid = new FiniteGrid(getNumRow(), getNumCol(), 1,
				(int) getCellWidth(), Direction.ALL_DIRECTIONS);
	}

	@Override
	public void initializeScreen() {
		// TODO Auto-generated method stub
		simGrid.initializeGrid();
		for (Slot s : simGrid.getSlots()) {
			double typeOne = (1.0 - vacantP) * typeOneP;
			double rand = Math.random();
			if (rand < vacantP) {
				SegregationSlotCell cell = new SegregationSlotCell(Color.GRAY,
						tPercentage, 0,0);
				s.addOccupant(cell);
			} else if (rand > vacantP && rand < vacantP + typeOne) {
				SegregationSlotCell cell = new SegregationSlotCell(Color.RED,
						tPercentage, 1,2);
				s.addOccupant(cell);
			} else {
				SegregationSlotCell cell = new SegregationSlotCell(Color.BLUE,
						tPercentage, 2,1);
				s.addOccupant(cell);
			}

		}
	}

	@Override
	protected void calculateAdjacencyMatrixAndSetNeighbor() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCells() {
		// TODO Auto-generated method stub
		for (Slot s : simGrid.getSlots()) {
			ArrayList<Cell> occupant = new ArrayList<Cell>(s.getOccupants());
			SegregationSlotCell resident = (SegregationSlotCell) occupant
					.get(0);
			ArrayList<Integer> neighborStates = getNeighborStates(s);
			if(resident.isUnsatisfied(neighborStates)){
				
			}
			
		}

	}

	private ArrayList<Integer> getNeighborStates(Slot s) {
		ArrayList<Integer> states = new ArrayList<Integer>();
		ArrayList<Slot> neighbors = new ArrayList<Slot>(s.getNeighbors());
		for (Slot n : neighbors) {
			SegregationSlotCell neigh = (SegregationSlotCell) new ArrayList<Cell>(
					n.getOccupants()).get(0);
			states.add(neigh.getState());
		}
		return states;
	}

	@Override
	public void drawCells() {
		// TODO Auto-generated method stub

	}

}
