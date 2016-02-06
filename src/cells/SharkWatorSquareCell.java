package cells;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.paint.Color;

/**
 * Class for shark cells in Wator
 * @author aj168 - Anirudh Jonnavithula
 *
 */
public class SharkWatorSquareCell extends WatorSquareCell{

	private static final Color CELL_COLOR = Color.RED;
	private final int BREED;
	private final int STARVE;
	private int starveCount;
	private int breedCount;
	private final boolean IS_EMPTY = false;
	private final boolean IS_SHARK = true;
	
	/**
	 * Constructor
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param w Width
	 * @param h Height
	 * @param breed Number of rounds until cell can breed
	 * @param starve Number of rounds until cell dies (starves)
	 */
	public SharkWatorSquareCell(double x, double y, double w, double h, int breed, int starve) {
		super(x, y, CELL_COLOR, w, h);
		setEmpty(IS_EMPTY);
		setShark(IS_SHARK);
		BREED = breed;
		breedCount = BREED;
		STARVE = starve;
		starveCount = STARVE;
	}

	@Override
	public WatorSquareCell update(WatorSquareCell[] cells) {
			return null;
	}

	/**
	 * Updates the cell
	 * @param cells List of cells of current state
	 * @param map Maps new cell positions to old ones
	 * @param position Position of current cell
	 */
	@Override
	public WatorSquareCell updateWator(WatorSquareCell[] cells, HashMap<Integer, Integer> map, int position) {
		Random rnd = new Random();
		decrementBreedCount();
		decrementStarveCount();
		
		if(getStarveCount() == 0) {
			EmptyWatorSquareCell returnCell = new EmptyWatorSquareCell(getX(), getY(), getWidth(), getHeight());
			returnCell.setNeighbor(getNeighbor());
			return returnCell;
		}
		
		ArrayList<Integer> fishCells = new ArrayList<Integer>();
		for(int i = 0; i < getNeighbor().size(); i++) {
			if(!cells[getNeighbor().get(i)].isShark() 
					&& !cells[getNeighbor().get(i)].isEmpty() 
					&& !map.containsKey(this.getNeighbor().get(i)))
				fishCells.add(getNeighbor().get(i));
		}
		
		ArrayList<Integer> emptyCells = new ArrayList<Integer>();
		for(int i = 0; i < getNeighbor().size(); i++) {
			if(cells[getNeighbor().get(i)].isEmpty() && !map.containsKey(this.getNeighbor().get(i)))
				emptyCells.add(getNeighbor().get(i));
		}
		
		if(!fishCells.isEmpty()) {
			int nextIndex = fishCells.get(rnd.nextInt(fishCells.size()));
			map.put(nextIndex, position);
			resetStarveCount();
			
			if(breedCount <= 0) {
				SharkWatorSquareCell returnCell = new SharkWatorSquareCell(getX(), getY(), getWidth(), getHeight(), BREED, STARVE);
				returnCell.setNeighbor(getNeighbor());
				resetBreedCount();
				return returnCell;
			}
			else {
				EmptyWatorSquareCell returnCell = new EmptyWatorSquareCell(getX(), getY(), getWidth(), getHeight());
				returnCell.setNeighbor(getNeighbor());
				return returnCell;
			}
		}
		
		if(!emptyCells.isEmpty()) {
			int nextIndex = emptyCells.get(rnd.nextInt(emptyCells.size()));
			map.put(nextIndex,  position);
			if(breedCount <= 0) {
				SharkWatorSquareCell returnCell = new SharkWatorSquareCell(getX(), getY(), getWidth(), getHeight(), BREED, STARVE);
				returnCell.setNeighbor(getNeighbor());
				resetBreedCount();
				return returnCell;
			}
			else {
				EmptyWatorSquareCell returnCell = new EmptyWatorSquareCell(getX(), getY(), getWidth(), getHeight());
				returnCell.setNeighbor(getNeighbor());
				return returnCell;
			}
		}
		return this;
	}

	/**
	 * Decrements the breed count
	 */
	public void decrementBreedCount() {
		setBreedCount(getBreedCount() - 1);
	}
	
	/**
	 * Decrements the starve count
	 */
	public void decrementStarveCount() {
		setStarveCount(getStarveCount() - 1);
	}
	
	/**
	 * Resets the breed count
	 */
	public void resetBreedCount() {
		setBreedCount(BREED);
	}
	
	/**
	 * Resets the starve count
	 */
	public void resetStarveCount() {
		setStarveCount(STARVE);
	}
	
	/**
	 * Gets the new current count
	 * @return The current breed count
	 */
	public int getBreedCount() {
		return breedCount;
	}

	/**
	 * Sets the breed count
	 * @param breedCount New breed count
	 */
	public void setBreedCount(int breedCount) {
		this.breedCount = breedCount;
	}

	/**
	 * Gets the starve count
	 * @return The current starve count
	 */
	private int getStarveCount() {
		return starveCount;
	}

	/**
	 * Sets the starve count
	 * @param starveCount New starve count
	 */
	private void setStarveCount(int starveCount) {
		this.starveCount = starveCount;
	}

	/**
	 * Returns a copy of this cell
	 */
	@Override
	public WatorSquareCell copy() {
		SharkWatorSquareCell returnCell = new SharkWatorSquareCell(getX(), getY(), getWidth(), getHeight(), BREED, STARVE);
		returnCell.setNeighbor(getNeighbor());
		returnCell.setBreedCount(getBreedCount());
		returnCell.setStarveCount(getStarveCount());
		return returnCell;
	}

}
