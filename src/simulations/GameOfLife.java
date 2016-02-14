package simulations;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;

import cells.SquareCell;
import automaton.AutomatonDisplay;
import automaton.XMLArgs;

/**
 * Class for Game of Life Simulation
 * @author aj168 - Anirudh Jonnavithula
 *
 */
public class GameOfLife extends CA{
	
	private static final String RESOURCE_PACKAGE = "ResourceBundle/GameOfLife";
	private double perAlive;
	private GOLSquareCell[] allCells;
	private ResourceBundle myResources;
	
	/**
	 * Constructor
	 * @param mapArgs Map with arguments read in from XML file
	 * @param a AutomatonDisplay on which simulation is drawn
	 */
	public GameOfLife(XMLArgs xmlArgs, AutomatonDisplay a) {
		super(xmlArgs, a);
		myResources = ResourceBundle.getBundle(RESOURCE_PACKAGE);
		perAlive = xmlArgs.getAsDouble(myResources.getString("perAlive"));
		allCells = new GOLSquareCell[getNumCell()];
	}

	/**
	 * Initilaizes the screen and simulation
	 */
	@Override
	public void initializeScreen() {		
		int numCell = 0;
		for(int i = 0; i<getNumCol(); i++) {
			for(int j = 0; j<getNumRow(); j++) {
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

	/**
	 * Updates the cells
	 */
	@Override
	public void updateCells() {
		GOLSquareCell[] list = new GOLSquareCell[getNumCell()];
		for(int i=0; i<getNumCell(); i++) {
			list[i] = getAllCells()[i].update(getAllCells());
		}
		setAllCells(list);
	}

	/**
	 * Calculates the adjacency matrix/ neighbors
	 */
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
	
	/**
	 * Returns all the cells in the current state
	 * @return Cells in their current state
	 */
	public GOLSquareCell[] getAllCells() {
		return allCells;
	}
	
	/**
	 * Sets the current states of all the cells
	 * @param list List of new cells
	 */
	public void setAllCells(GOLSquareCell[] list) {
		allCells = Arrays.copyOf(list, list.length);
	}

	/**
	 * Draws the cells
	 */
	@Override
	public void drawCells() {
		getGraphicsContext().clearRect(0,0,getSimWidth(), getSimHeight());
		for(SquareCell cell: getAllCells()) {
			cell.draw(getGraphicsContext());
		}
	}
}
