package simulations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
		for (int i = 0; i < getNumCol(); i++) {
			for (int j = 0; j < getNumRow(); j++) {
				double typeOne = (1.0 - vacantP) * typeOneP;
				double rand = Math.random();
				if (rand < vacantP) {
					getAllCells()[numCell] = new VacantSegregationSquareCell(i
							* getCellWidth(), j * getCellHeight(),
							getCellWidth(), getCellHeight());
				} else if (rand > vacantP && rand < vacantP + typeOne) {
					getAllCells()[numCell] = new TypeOneSegregationSquareCell(i
							* getCellWidth(), j * getCellHeight(),
							getCellWidth(), getCellHeight(), tPercentage);
				} else {
					getAllCells()[numCell] = new TypeTwoSegregationSquareCell(i
							* getCellWidth(), j * getCellHeight(),
							getCellWidth(), getCellHeight(), tPercentage);
				}
				numCell++;
			}
		}
	}
	

	@Override
	public void updateCells() {
		ArrayList<Integer> unsatisfiedList = new ArrayList<Integer>();
		ArrayList<Integer> vacantList = new ArrayList<Integer>();
		SegregationSquareCell[] gridThisRound = Arrays.copyOf(getAllCells(),
				getNumCell());
		for (int i = 0; i < getNumCell(); i++) {
			if (getAllCells()[i].cellUnsatisfied(gridThisRound))
				unsatisfiedList.add(i);
			if (getAllCells()[i].getState() == 0)
				vacantList.add(i);
		}
		Collections.shuffle(vacantList);
		Collections.shuffle(unsatisfiedList);
		SegregationSquareCell[] swappedCells = swapCells(vacantList, unsatisfiedList);
		
		//if (gridThisRound == getAllCells())
			//this.setSimOver(true);
		setAllCells(mergeList(swappedCells, gridThisRound));
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
	
	private SegregationSquareCell[] mergeList(SegregationSquareCell[] swapped, SegregationSquareCell[] master){
		for (int i = 0; i < getNumCell(); i++){
			if(swapped[i] != null){
				master[i] = swapped[i];
			}
		}
		return master;
	}
	
	private SegregationSquareCell[] swapCells(ArrayList<Integer> vacant, ArrayList<Integer> unsatisfied){
		SegregationSquareCell[] ret  = new SegregationSquareCell[getNumCell()];
		while(unsatisfied.size() > 0 && vacant.size() > 0){
			int unsatisfiedIndex = unsatisfied.get(0);
			int vacantIndex = vacant.get(0);
			SegregationSquareCell toVacate = getAllCells()[unsatisfiedIndex];
			SegregationSquareCell toFill = getAllCells()[vacantIndex];
			ret[vacantIndex] = toVacate;
			ret[unsatisfiedIndex] = toFill;
			double newX = toFill.getX();
			double newY = toFill.getY();
			ArrayList<Integer> tempN  = new ArrayList<Integer>(toFill.getNeighbor());
			double vacateX = toVacate.getX();
			double vacateY = toVacate.getY();
			toFill.setX(toVacate.getX());
			toFill.setY(toVacate.getY());
			toFill.setNeighbor(toVacate.getNeighbor());
			toVacate.setX(newX);
			toVacate.setY(newY);
			toVacate.setNeighbor(tempN);
			unsatisfied.remove(0);
			vacant.remove(0);
			vacant.add(unsatisfiedIndex);		                                            
			
		}
		return ret;
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
