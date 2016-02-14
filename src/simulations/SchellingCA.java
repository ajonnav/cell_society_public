package simulations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;



//import javafx.scene.paint.Color;
import cells.*;
import graphing.GraphData;
import graphing.cellLineGraph;
import automaton.AutomatonDisplay;
import automaton.XMLArgs;

//import grid.SquareGrid;

;
public class SchellingCA extends CA {
	private double tPercentage;
	private double typeOneP;
	private double vacantP;
	private SegregationSquareCell[] allCells;
	private GraphData data;
	private cellLineGraph graph;

	public SchellingCA(XMLArgs xmlArgs, AutomatonDisplay a) {
		super(xmlArgs, a);
		tPercentage = xmlArgs.getAsDouble("tPercentage");
		typeOneP = xmlArgs.getAsDouble("typeOneP");
		vacantP = xmlArgs.getAsDouble("vacantP");
		allCells = new SegregationSquareCell[getNumCell()];
	}

	@Override
	public void initializeScreen() {
		// TODO Auto-generated method stub
		setInitialStates();
		initializeSimulationLoop();
		calculateAdjacencyMatrixAndSetNeighbor();
		drawCells();
		data = new GraphData();
		graph = new cellLineGraph();
	}
/**
 * Random number is used to determine which cell is built where.  If random is less than Vacant percentage, vacant cell built
 * if random is between vacant probability and typeOne probability, then typeOne is built.  Any number larger creates a typeTwo
 */
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
	/**
	 * Checks satisfaction and/or vacancy of each cell.  If cell is unsatisfied, or vacant, it is added to its respective
	 * ArrayList for use in moving unsatisfied agents.  The two objects are shuffled to give more realistic movement
	 */
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
		setAllCells(mergeList(swappedCells, gridThisRound));
		data.setGraphData(allCells);
		graph.setCellSeries(data.getMap(), data.getCycle());
	}
/**
 * Determines the neighbors of each Cell.  Adjacency list is also built for future abstractions.
 */
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
	/**
	 * Compares the list of newly moved unsatisfied cells against the master list. Anything on the Swapped list overwrites
	 * the master list to reflect changes.  
	 * @param swapped
	 * @param master
	 * @return
	 */
	private SegregationSquareCell[] mergeList(SegregationSquareCell[] swapped, SegregationSquareCell[] master){
		for (int i = 0; i < getNumCell(); i++){
			if(swapped[i] != null){
				master[i] = swapped[i];
			}
		}
		
		return master;
	}
	/**
	 * Takes in lists of vacant and unsatisfied cells.  Loops through until all unsatisfied cells are moved.  Once unsatisfied
	 * cell is moved, its former spot is added to the vacancy list.
	 * @param vacant
	 * @param unsatisfied
	 * @return
	 */
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
	
/**
 * Returns all the Cells in the simulation
 * @return allCells
 */
	public SegregationSquareCell[] getAllCells() {
		return allCells;
	}
/**
 * Sets all the Cells to a new list
 * @param newList
 */
	public void setAllCells(SegregationSquareCell[] newList) {
		allCells = Arrays.copyOf(newList, newList.length);
	}
/**
 * Renders the cells for the next round of the simulation.
 */
	public void drawCells() {
		getGraphicsContext().clearRect(0, 0, getSimWidth(), getSimHeight());
		for (SquareCell cell : getAllCells()) {
			cell.draw(getGraphicsContext());
		}

	}

}
