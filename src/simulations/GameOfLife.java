package simulations;
import java.util.ArrayList;
import java.util.List;
import slot.*;
import cells.*;
import automaton.*;

/**
 * Class for Game of Life Simulation
 * @author aj168
 *
 */
public class GameOfLife extends CA{
	private double perAlive; 
	
	/**
	 * Constructor
	 * @param xmlArgs XML Arguments read in
	 * @param autoDisp AutomatonDisplay to display the simulation
	 */
	public GameOfLife(XMLArgs xmlArgs, AutomatonDisplay autoDisp) {
		super(xmlArgs, autoDisp);
		perAlive = xmlArgs.getAsDouble("perAlive");
	}
	
	/**
	 * Initializes the simulation.screen
	 */
	@Override
	public void initializeScreen() {
		List<Slot> list = getAllSlots();
		for(Slot slot:list) {
			slot.getOccupants().clear();
		}
		for(int col = 0; col<getNumCol(); col++) {
			for(int row = 0; row<getNumRow(); row++) {
				Cell cell;
				if(Math.random()<perAlive) {
					cell = new LiveGOLCell();
				}
				else {
					cell = new DeadGOLCell();
				}
				list.get(getIndexFromRowCol(col, row)).addOccupant(cell);
			}
		}
		initializeSimulationLoop();
		drawCells();
	}
	
	/**
	 * Updates the cells
	 */
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
