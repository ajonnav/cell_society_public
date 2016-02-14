package simulations;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import slot.*;
import cells.*;
import automaton.*;

public class GameOfLife extends CA{
	private double perAlive; 
	
	public GameOfLife(XMLArgs xmlArgs, AutomatonDisplay autoDisp) {
		super(xmlArgs, autoDisp);
		perAlive = xmlArgs.getAsDouble("perAlive");
	}
	
	@Override
	public void initializeScreen() {		
		int numCell = 0;
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
				list.get(getIndexFromRowCol(col, row)).addOccupants(cell);
				numCell++;
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
	
	@Override
	public void drawCells() {
		getGraphicsContext().clearRect(0,0,getSimWidth(), getSimHeight());
		
		for(Slot slot: getAllSlots()) {
			Color c = slot.getOccupants().get(0).getCellColor();
			slot.draw(getGraphicsContext(), c);
		}
		int x=0;
		int y=x+1;
	}
}
