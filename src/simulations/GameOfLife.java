package simulations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import cells.DeadGOLSquareCell;
import cells.GOLSquareCell;
import cells.LiveGOLSquareCell;
import automaton.AutomatonDisplay;

public class GameOfLife extends CA{
	
	private double perAlive;
	private GOLSquareCell[] allCells;
	
	public GameOfLife(Map<String, String> mapArgs, AutomatonDisplay a) {
		super(mapArgs, a);
		perAlive = Double.parseDouble(mapArgs.get("perAlive"));
		allCells = new GOLSquareCell[getNumCell()];
	}

	@Override
	public void initializeScreen() {		
		int numCell = 0;
		for(int i = 0; i<getNumRow(); i++) {
			for(int j = 0; j<getNumCol(); j++) {
				if(Math.random()<perAlive) {
					getAllCells()[numCell] = new LiveGOLSquareCell(i*getCellWidth(), j*getCellHeight(), getCellWidth(), getCellHeight());
				}
				else {
					getAllCells()[numCell] = new DeadGOLSquareCell(i*getCellWidth(), j*getCellHeight(), getCellWidth(), getCellHeight());
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
		GOLSquareCell[] list = new GOLSquareCell[getNumCell()];
		for(int i=0; i<getNumCell(); i++) {
			list[i] = getAllCells()[i].update(getAllCells());
		}
		setAllCells(list);
	}

	@Override
	protected void calculateAdjacencyMatrixAndSetNeighbor() {
		for(int i=0; i<getNumCell(); i++) {
			GOLSquareCell cell = getAllCells()[i];
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int j=0; j<getNumCell(); j++) {
				GOLSquareCell temp = getAllCells()[j];
				if(Math.abs(cell.getX() - temp.getX())<=getCellWidth() && Math.abs(cell.getY()-temp.getY())<=getCellHeight() && i!=j) {
					getAdjacency()[i][j] =1;
					list.add(j);
				}
			}
			cell.setNeighbor(list);
		}
	}
	
	public GOLSquareCell[] getAllCells() {
		return allCells;
	}
	
	public void setAllCells(GOLSquareCell[] list) {
		allCells = Arrays.copyOf(list, list.length);
	}

	@Override
	public void drawCells() {
		// TODO Auto-generated method stub
		getGraphicsContext().clearRect(0,0,getSimWidth(), getSimHeight());
		for(GOLSquareCell cell: getAllCells()) {
			cell.draw(getGraphicsContext());
		}
	}
}
