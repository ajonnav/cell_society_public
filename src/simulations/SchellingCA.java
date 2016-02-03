package simulations;

import java.util.ArrayList;

import cells.Cell;
import automaton.AutomatonDisplay;

public class SchellingCA extends CA {
	private double tPercentage;
	private double typeOneP; 
	private double vacantP;
	private static final int VACANT = 0;
	private static final int STATE_ONE = 1;
	private static final int STATE_TWO = 2;
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
		makeCells(numCells);
		

	}
	
	private void makeCells(int num){
		for (int i = 0; i < num; i++){
			//Cell c = new SquareCell
		}
	}

	@Override
	protected void updateCells() {
		// TODO Auto-generated method stub
		for(int i = 0; i < allCells.size(); i++){
			checkCellforStateChange(i, neighbors[i]);			
		}

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
	
	private void checkCellforStateChange(int cellIndex, int[]cellEdges){
		ArrayList<Integer> cNeighbors = filterNeighbors(cellEdges);
		boolean satisfied = isCellSatisfied(cellIndex, cNeighbors);
		if(!satisfied){
			moveCell(allCells.get(cellIndex).getState());
			allCells.get(cellIndex).setState(VACANT);
		}
		
	}
	
	private boolean isCellSatisfied(int cellIndx, ArrayList<Integer> cNeighbors){
		double numSatisfied = numNeighborsWithThisState(allCells.get(cellIndx).getState(), cNeighbors);
		double numVacant = numNeighborsWithThisState(VACANT, cNeighbors);
		double numTotal = (double)cNeighbors.size() - numVacant;
		return ((numSatisfied / numTotal) > tPercentage);
	}
	
	private double numNeighborsWithThisState(int state, ArrayList<Integer> neighbors){
		double count = 0.0;
		for (int i : neighbors){
			if(allCells.get(i).getState() == state) count++;
		}
		
		return count;
	}
	
	private void moveCell(int state){
		boolean go = true; 
		while(go){
			Cell c = allCells.get((int)Math.random()*allCells.size());
			if (c.getState() == 0){
				c.setState(state);
				go = false;
			}
		}
	}

}
