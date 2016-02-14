package simulations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import FireCells.FireBURNING;
import FireCells.FireCell;
import FireCells.FireEMPTY;
import FireCells.FireTREE;
import automaton.AutomatonDisplay;
import automaton.XMLArgs;

/*
 * Author: Christine Zhou
 */
public class Fire extends CA {
	private double probCatch;
	private FireCell[] allCells;
	private List<Integer> burning_x;
	private List<Integer> burning_y;
	private List<Integer> empty_x;
	private List<Integer> empty_y;
	
	public Fire (XMLArgs xmlArgs, AutomatonDisplay a) {
		super(xmlArgs, a);
		// TODO Auto-generated constructor stub
		probCatch = xmlArgs.getAsDouble("probCatch");
		allCells = new FireCell[getNumCell()];
		burning_x = xmlArgs.getAsListOfInteger("burning_x");
		burning_y = xmlArgs.getAsListOfInteger("burning_y");
		empty_x = xmlArgs.getAsListOfInteger("empty_x");
		empty_y = xmlArgs.getAsListOfInteger("empty_y");
	}

	@Override
	public void initializeScreen() {
		// TODO Auto-generated method stub
		int numCell = 0;
		for (int i = 0; i < getNumCol(); i++) {
			for (int j = 0; j < getNumRow(); j++) {
				if (burning_x.contains(i) && burning_y.contains(j)) {
					getAllCells()[numCell] = new FireBURNING(i*getCellWidth(), j*getCellHeight(), getCellWidth(), getCellHeight());
				} else if (empty_x.contains(i) && empty_y.contains(j)) {
					getAllCells()[numCell] = new FireEMPTY(i*getCellWidth(), j*getCellHeight(), getCellWidth(), getCellHeight());
				} else {
					getAllCells()[numCell] = new FireTREE(i*getCellWidth(), j*getCellHeight(), getCellWidth(), getCellHeight(), probCatch);
				}
				numCell++;
			}
		}
		initializeSimulationLoop();
		calculateAdjacencyMatrixAndSetNeighbor();
		drawCells();
	}

	@Override
	public void updateCells() {
		// TODO Auto-generated method stub
		FireCell[] newStates = new FireCell[getNumCell()];
		for (int i = 0; i < getNumCell(); i++) {
			newStates[i] = getAllCells()[i].update(getAllCells());
		}
		setAllCells(newStates);
		
	}

	@Override
	protected void calculateAdjacencyMatrixAndSetNeighbor() {
		// TODO Auto-generated method stub
		for (int i = 0; i < getNumCell(); i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int j = 0; j < getNumCell(); j++) {
				if ((Math.abs(getAllCells()[i].getX() - getAllCells()[j].getX()) <= getCellWidth())
						&& getAllCells()[i].getY() == getAllCells()[j].getY()) {
					getAdjacency()[i][j] = 1;
					list.add(j);
				} else if ((Math.abs(getAllCells()[i].getY() - getAllCells()[j].getY()) <= getCellHeight()) 
						&& getAllCells()[i].getX() == getAllCells()[j].getX()) {
					getAdjacency()[i][j] = 1;
					list.add(j);
				}
			}
			getAllCells()[i].setNeighbor(list);
		}
	}

	@Override
	public void drawCells() {
		// TODO Auto-generated method stub
		for (FireCell i : getAllCells()) {
			i.draw(getGraphicsContext());
		}
	}
	
	private FireCell[] getAllCells() {
		return allCells;
	}
	
	private void setAllCells(FireCell[] newStates) {
		allCells = Arrays.copyOf(newStates, newStates.length);
	}
}
