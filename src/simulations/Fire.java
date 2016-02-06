package simulations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import FireCells.FireBURNING;
import FireCells.FireCell;
import FireCells.FireTREE;
import automaton.AutomatonDisplay;

public class Fire extends CA {
	private double probCatch;
	private FireCell[] allCells;
	
	public Fire (Map<String, String> mapArgs, AutomatonDisplay a) {
		super(mapArgs, a);
		// TODO Auto-generated constructor stub
		probCatch = Double.parseDouble(mapArgs.get("probCatch"));
		allCells = new FireCell[getNumCell()];
	}

	@Override
	public void initializeScreen() {
		// TODO Auto-generated method stub
		int numCell = 0;
		for (int i = 0; i < getNumRow(); i++) {
			for (int j = 0; j < getNumCol(); j++) {
				if (Math.random() < 0.1) {
					getAllCells()[numCell] = new FireBURNING(i*getCellWidth(), j*getCellHeight(), getCellWidth(), getCellHeight());
				}
				else {
					getAllCells()[numCell] = new FireTREE(i*getCellWidth(), j*getCellHeight(), getCellWidth(), getCellHeight(), probCatch);
				}
				numCell++;
			}
		}
		
		//set empty and set burning
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
