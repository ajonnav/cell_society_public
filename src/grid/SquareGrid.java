package grid;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import simulations.*;
import cells.*;

public class SquareGrid {
	private int numCells;
	private int cellWidth;
	private int cellHeight;
	private double gridWidth;
	private double gridHeight;
	private int[] states;
	private ArrayList<Cell> cells;
	private int[][] adjMatrix;
	private static final Color VACANT_COLOR = Color.GRAY;
	private CA simulation;
	public SquareGrid(CA ca, int cellNum){
		numCells = cellNum;
		//cellWidth = ca.getCellWidth();
		//cellHeight = ca.getCellHeight();
		//gridWidth = ca.getCanvas().getWidth();
		//gridHeight = ca.getCanvas().getHeight();
		//states = ca.getStates();
		simulation = ca;
		adjMatrix = new int [cellNum][cellNum];
		makeCellGrid();
	}
	
	//make 2dArray of cells
	private void makeCellGrid(){
		cells = new ArrayList<Cell>();
		double xPixel = 0.0;
		double yPixel = 0.0;
		for(int i = 0; i < numCells; i++){
			for(int j = 0; j < numCells; j++){
			//	cells.add(new SquareCell(simulation.getStates()[0], xPixel, yPixel,VACANT_COLOR, cellWidth, cellHeight, i, j));
				xPixel += cellWidth;
			}
			xPixel = 0.0;
			yPixel += cellHeight;
		}		
		setAdjacencyList();
	}
	
	private void setAdjacencyList(){		
		for (int i = 0; i < numCells; i++){
			for (int j = 0; j < numCells; j++){
				if(i == j) adjMatrix[i][j] = 0;
				checkAround(i, j);
			}
		}
	}
	
	private void checkAround(int i, int j){
		int [] boundaries  = {-1, 0, 1};
		for(int a = 0; a < boundaries.length; a++){
			for(int b = 0; b < boundaries.length; b++){
				if (!(a == 0 && b == 0)){
					if(inBounds(i + a, j + b)){
						adjMatrix[i + a][j + b] = 1;
					}
				}
			}
		}
	}

	private boolean inBounds (int r, int c){
		 return (0 <= r && r < numCells && 0 <= c && c < numCells && r != c);
	}
	
	public ArrayList<Cell> getCells(){
		return cells;
	}
	
	public int[][] getAdjacencyList(){
		return adjMatrix;
	}
}
