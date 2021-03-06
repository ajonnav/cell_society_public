package simulations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javafx.scene.paint.Color;
import cells.*;
import slot.Slot;
import automaton.AutomatonDisplay;
import automaton.XMLArgs;
import factory.GridFactory;
import grid.*;
/**
 * This is the Implementation of the Segregation simulation using our new framework
 * @author colinduffy
 *
 */
public class SegregationGrid extends CA {
	private double tPercentage;
	private double typeOneP;
	private double vacantP;
	private AnyGrid simGrid;
	private Stack<Slot> slotsToChange;
	private final static Color TYPE_ONE_COLOR = Color.RED;
	private static Color TYPE_TWO_COLOR = Color.BLUE;
	private static Color TYPE_VACANT_COLOR = Color.WHITE;
	public SegregationGrid(XMLArgs xmlArgs, AutomatonDisplay a) {
		super(xmlArgs, a);
		// TODO Auto-generated constructor stub
		tPercentage = xmlArgs.getAsDouble("tPercentage");
		typeOneP = xmlArgs.getAsDouble("typeOneP");
		vacantP = xmlArgs.getAsDouble("vacantP");
		simGrid = GridFactory.create(xmlArgs, a);
		slotsToChange = new Stack<Slot>();
	}

	@Override
	/**
	 * Initializes Grid, sets initial states, begins loop and draws the cells.
	 */
	public void initializeScreen() {
		// TODO Auto-generated method stub
		simGrid.initializeGrid();
		setInitialStates();
		initializeSimulationLoop();
		drawCells();
	}

	private void setInitialStates() {
		for (Slot s : simGrid.getSlots()) {
			double typeOne = (1.0 - vacantP) * typeOneP;
			double rand = Math.random();
			if (rand < vacantP) {
				//vacant cell
				SegregationSlotCell cell = new SegregationSlotCell(TYPE_VACANT_COLOR,
						tPercentage, 0,0);
				s.addOccupant(cell);
			} else if (rand > vacantP && rand < vacantP + typeOne) {
				//type one cell
				SegregationSlotCell cell = new SegregationSlotCell(TYPE_ONE_COLOR,
						tPercentage, 1,2);
				s.addOccupant(cell);
			} else {
				//type two cell
				SegregationSlotCell cell = new SegregationSlotCell(TYPE_TWO_COLOR,
						tPercentage, 2,1);
				s.addOccupant(cell);
			}

		}
	}

	
	@Override
	/**
	 * Checks cell in each slot for its satisfaction.  If not satisfied, slot is pushed onto the Slots to Change stack
	 */
	public void updateCells() {
		for (Slot s : simGrid.getSlots()) {
			ArrayList<Cell> occupant = new ArrayList<Cell>(s.getOccupants());
			SegregationSlotCell resident = (SegregationSlotCell) occupant
					.get(0);
			ArrayList<Integer> neighborStates = getNeighborStates(s);
			if(resident.isUnsatisfied(neighborStates)){
				slotsToChange.push(s);
			}
			
		}
		//randomize slots containing unsatisfied cells
		Collections.shuffle(slotsToChange);
		moveUnsatisfiedCells();
	}
	
	private void moveUnsatisfiedCells(){
		while(!slotsToChange.isEmpty()){
			Slot s = slotsToChange.pop();			
			ArrayList<Slot> slotRound  = (ArrayList<Slot>) simGrid.getSlots();
			boolean go = true;
			while(go){
				int randIndex = (int)(Math.random()*slotRound.size());
				ArrayList<Cell> cells = new ArrayList<Cell>(slotRound.get(randIndex).getOccupants());
				SegregationSlotCell seg = (SegregationSlotCell)cells.get(0);
				if(seg.getState() == 0){
					slotRound.get(randIndex).setOccupants(s.getOccupants());
					s.setOccupants(cells);
					go = false;
				}
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
	/**
	 * Retrieves the Color of the cell and displays it for the slot. 
	 */
	public void drawCells() {
		// TODO Auto-generated method stub
		getGraphicsContext().clearRect(0, 0, getSimWidth(), getSimHeight());
		for(Slot s : simGrid.getSlots()){
			ArrayList<Cell> occupant = new ArrayList<Cell> (s.getOccupants());
			SegregationSlotCell seg = (SegregationSlotCell)occupant.get(0);
			s.draw(getGraphicsContext(), seg.getCellColor());
		}

	}

}
