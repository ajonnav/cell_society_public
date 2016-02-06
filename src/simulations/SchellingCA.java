package simulations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

//import javafx.scene.paint.Color;
import cells.*;
import automaton.AutomatonDisplay;
//import grid.SquareGrid;

;
public class SchellingCA extends CA {
	private double tPercentage;
	private double typeOneP;
	private double vacantP;
	private SegregationSquareCell[] allCells;

	public SchellingCA(Map<String, String> mapArgs, AutomatonDisplay a) {
		super(mapArgs, a);
		tPercentage = Double.parseDouble(mapArgs.get("tPercentage"));
		typeOneP = Double.parseDouble(mapArgs.get("typeOneP"));
		vacantP = Double.parseDouble(mapArgs.get("vacantP"));
		allCells = new SegregationSquareCell[getNumCell()];

	}

	@Override
	public void initializeScreen() {
		// TODO Auto-generated method stub

		setInitialStates();
		initializeSimulationLoop();
		calculateAdjacencyMatrixAndSetNeighbor();
		drawCells();

	}

	public void setInitialStates() {
		int numCell = 0; 
		for (int i = 0; i < getNumRow(); i++) {
			for (int j = 0; j < getNumCol(); j++) {
				double typeOne = (1.0 - vacantP)*typeOneP;
				//double typeTwo = (1.0 - vacantP)*(1 - typeOneP);
				double rand = Math.random();
				if(rand < vacantP){
					getAllCells()[numCell] = new VacantSegregationSquareCell(i*getCellWidth(), j*getCellHeight(), getCellWidth(), getCellHeight());
				}else if(rand > vacantP && rand < vacantP + typeOne){
					getAllCells()[numCell] = new TypeOneSegregationSquareCell(i*getCellWidth(), j*getCellHeight(), getCellWidth(), getCellHeight(), tPercentage);
				}else {
					getAllCells()[numCell] = new TypeTwoSegregationSquareCell(i*getCellWidth(), j*getCellHeight(), getCellWidth(), getCellHeight(), tPercentage);
				}
			numCell++;
			}
		}
	}

	@Override
	public void updateCells() {
		SegregationSquareCell[] tempList = new SegregationSquareCell[getNumCell()];
		SegregationSquareCell[] gridThisRound = Arrays.copyOf(getAllCells(), getNumCell());
		for (int i = 0; i < getNumCell(); i++) {
			tempList[i] = getAllCells()[i].update(getAllCells(), gridThisRound);
		}
		if(gridThisRound == getAllCells()) this.setSimOver(true);
		setAllCells(tempList);
	}

	protected void calculateAdjacencyMatrixAndSetNeighbor() {
		for (int i = 0; i < getNumCell(); i++) {
			Cell cell = getAllCells()[i];
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int j = 0; j < getNumCell(); j++) {
				Cell temp = getAllCells()[j];
				if (Math.abs(cell.getX() - temp.getX()) <= getCellWidth()
						&& Math.abs(cell.getY() - temp.getY()) <= getCellHeight()
						&& i != j) {
					getAdjacency()[i][j] = 1;
					list.add(j);
				}
			}
			cell.setNeighbor(list);
		}
	}

	public SegregationSquareCell[] getAllCells() {
		return allCells;
	}

	public void setAllCells(SegregationSquareCell[] newList) {
		allCells = Arrays.copyOf(newList, newList.length);
	}

	public void drawCells() {
		getGraphicsContext().clearRect(0, 0, getSimWidth(), getSimHeight());
		for (SquareCell cell : getAllCells()) {
			cell.draw(getGraphicsContext());
		}

	}

}
