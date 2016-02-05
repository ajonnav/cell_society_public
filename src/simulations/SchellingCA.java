package simulations;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import cells.Cell;
import automaton.AutomatonDisplay;
import grid.SquareGrid;;
public class SchellingCA extends CA {
	private double tPercentage;
	private double typeOneP; 
	private double vacantP;
	static final int VACANT = 0;
	static final int STATE_ONE = 1;
	static final int STATE_TWO = 2;
	static final int [] STATES = {VACANT, STATE_ONE, STATE_TWO};
	static final Color [] STATE_COLORS = {Color.GRAY, Color.RED, Color.BLUE};
	private ArrayList<Cell> cellsThisRound;
	public SchellingCA(int width, int height, int[] states, AutomatonDisplay a, double t, double p, double v){
		super(width, height, states, a);
		tPercentage = t;
		typeOneP = p;
		vacantP = v;
	}
	@Override
	public void initializeScreen() {
		// TODO Auto-generated method stub
		int numCells = (int) ((canvas.getWidth()*canvas.getHeight())/(cellWidth*cellHeight));
		SquareGrid sg = new SquareGrid(this, numCells);
		neighbors = sg.getAdjacencyList();
		allCells = new ArrayList<Cell>(sg.getCells());
		setInitialStates(numCells);

	}
	
	public void setInitialStates(int numCells){
		int typeOneCells = (int)((int) numCells*typeOneP);
		int vacantCells = (int) ((int) numCells*vacantP);
		int otherCells = numCells - typeOneCells - vacantCells;
		int[] cellTypes = {vacantCells, typeOneCells, otherCells};
		for (int i = 0; i < allCells.size(); i++){
			int type = (int) (cellTypes.length*Math.random());
			if(cellTypes[type] > 0){
			allCells.get(i).setState(STATES[type]);
			//allCells.get(i).setColor(STATE_COLORS[type]));
			cellTypes[type]--;
			}
		}
		
	}
	

	@Override
	protected void updateCells() {
		// TODO Auto-generated method stub
		//cellsThisRound stores the changes happening.  Prevents cell movements at beginning of list
		//from affecting those at the end.
		cellsThisRound = new ArrayList<Cell>(allCells);
		for(int i = 0; i < allCells.size(); i++){
			checkCellforStateChange(i, neighbors[i]);			
		}
		//reset all cells for the next round;
		allCells = new ArrayList<Cell>(cellsThisRound);

	}
	/**
	 * Gets the indices of each neighbor of current cell to refer in allCells list
	 * @param list
	 * @return indexes
	 */
	private ArrayList<Integer> filterNeighbors(int[] list){
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0 ; i < list.length ; i++){
			if( list[i] == 1) indexes.add(i);
		}
		return indexes;
	}
	/**
	 * Checks if cell needs to be moved.  If not, it vacates that cell and moves it
	 * to a different vacant cell.
	 * @param cellIndex
	 * @param cellEdges
	 */
	private void checkCellforStateChange(int cellIndex, int[]cellEdges){
		ArrayList<Integer> cNeighbors = filterNeighbors(cellEdges);
		boolean satisfied = isCellSatisfied(cellIndex, cNeighbors);
		if(!satisfied){
			moveCell(allCells.get(cellIndex).getState());
			cellsThisRound.get(cellIndex).setState(VACANT);
		}
		
	}
	/**
	 * Compares similarity of neighbors.  Does not count vacant cells. 
	 * @param cellIndx
	 * @param cNeighbors
	 * @return
	 */
	private boolean isCellSatisfied(int cellIndx, ArrayList<Integer> cNeighbors){
		double numSatisfied = numNeighborsWithThisState(allCells.get(cellIndx).getState(), cNeighbors);
		double numVacant = numNeighborsWithThisState(VACANT, cNeighbors);
		double numTotal = (double)cNeighbors.size() - numVacant;
		return ((numSatisfied / numTotal) > tPercentage);
	}
	/**
	 * 
	 * @param state
	 * @param neighbors
	 * @return double value of number of neighbors with similar state
	 */
	private double numNeighborsWithThisState(int state, ArrayList<Integer> neighbors){
		double count = 0.0;
		for (int i : neighbors){
			if(allCells.get(i).getState() == state) count++;
		}
		
		return count;
	}
	/**
	 * Will randomly check a cell on the list to see if its vacant.
	 * repeats until cell is successfully moved. 
	 * @param state
	 */
	private void moveCell(int state){
		boolean go = true; 
		while(go){
			Cell c = cellsThisRound.get((int)Math.random()*cellsThisRound.size());
			if (c.getState() == 0){
				c.setState(state);
				go = false;
			}
		}
	}

}
