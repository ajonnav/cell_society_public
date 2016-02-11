package simulations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import FireCells.FireBURNING;
import FireCells.FireCell;
import FireCells.FireEMPTY;
import FireCells.FireTREE;
import automaton.AutomatonDisplay;

public class Fire extends CA {
	private double probCatch;
	private FireCell[] allCells;
	private ArrayList<Integer> burning_x;
	private ArrayList<Integer> burning_y;
	private ArrayList<Integer> empty_x;
	private ArrayList<Integer> empty_y;
	
	public Fire (Map<String, String> mapArgs, AutomatonDisplay a) {
		super(mapArgs, a);
		// TODO Auto-generated constructor stub
		probCatch = Double.parseDouble(mapArgs.get("probCatch"));
		allCells = new FireCell[getNumCell()];
		getListsfromXMLMap(mapArgs);
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
	
	private void getListsfromXMLMap(Map<String, String> mapArgs) {
		String [] burn_x = mapArgs.get("BurningCells_x").split("\\s+");
		String [] burn_y = mapArgs.get("BurningCells_y").split("\\s+");
		String [] emp_x = mapArgs.get("Empty_x").split("\\s+");
		String [] emp_y = mapArgs.get("Empty_y").split("\\s+");
		burning_x = new ArrayList<Integer>();
		burning_y = new ArrayList<Integer>();
		empty_x = new ArrayList<Integer>();
		empty_y = new ArrayList<Integer>();
		addBurning(burn_x, burn_y);
		for (int i = 0; i < emp_x.length; i++) {
			empty_x.add(Integer.parseInt(emp_x[i]));
			empty_y.add(Integer.parseInt(emp_y[i]));
		}
	}

	private void addBurning(String[] burn_x, String[] burn_y) {
		for (int i = 0; i < burn_x.length; i++) {
			burning_x.add(Integer.parseInt(burn_x[i]));
			burning_y.add(Integer.parseInt(burn_y[i]));
		}
	}

}
