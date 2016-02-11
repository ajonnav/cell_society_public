package cells;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.paint.Color;

/**
 * Class for Fish cells in Wator
 * @author aj168 - Anirudh Jonnavithula
 *
 */
public class FishWatorSquareCell extends WatorSquareCell{

	private static final Color CELL_COLOR = Color.YELLOW;
	private final int BREED;
	private int breedCount;
	private boolean IS_EMPTY = false;
	private final boolean IS_SHARK= false;
	
	/**
	 * Constructor
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param w Width of cell
	 * @param h Height of cell
	 * @param breedCount The number of rounds until cell breeds
	 */
	public FishWatorSquareCell(double x, double y, double w, double h, int breedCount) {
		super(x, y, CELL_COLOR, w, h);
		BREED = breedCount;
		setBreedCount(BREED);
		setEmpty(IS_EMPTY);
		setShark(IS_SHARK);
	}

	@Override
	public WatorSquareCell update(WatorSquareCell[] cells) {
		return null;
	}
	
	/**
	 * Decrements the breed counter
	 */
	public void decrementBreedCount() {
		setBreedCount(getBreedCount() - 1);
	}
	
	/**
	 * Resets the breed counter
	 */
	public void resetBreedCount() {
		setBreedCount(BREED);
	}

	/**
	 * Update method
	 * @param cells List of cells of current state
	 * @param map Maps new cell positions to old ones
	 * @param position Position of current cell
	 */
	@Override
	public WatorSquareCell updateWator(WatorSquareCell[] cells, HashMap<Integer, Integer> map, int position) {
		if(map.containsKey(position)) {
			WatorSquareCell returnCell = cells[map.get(position)].copy();
			returnCell.setX(getX());
			returnCell.setY(getY());
			returnCell.setNeighbor(getNeighbor());
			return returnCell;
		}
		decrementBreedCount();
		ArrayList<Integer> emptyCells = new ArrayList<Integer>();
		for(int i = 0; i < this.getNeighbor().size(); i ++) {
			if(cells[this.getNeighbor().get(i)].isEmpty() && !map.containsKey(this.getNeighbor().get(i)))
				emptyCells.add(this.getNeighbor().get(i));
		}
		if(emptyCells.isEmpty()) {
			return this;
		}
		Random rnd = new Random();
		int newIndex = emptyCells.get(rnd.nextInt(emptyCells.size()));
		map.put(newIndex, position);
		if(breedCount <= 0) {
			FishWatorSquareCell returnCell = new FishWatorSquareCell(getX(), getY(), getWidth(), getHeight(), BREED);
			returnCell.setNeighbor(this.getNeighbor());
			resetBreedCount();
			return returnCell;
		}
		else {
			EmptyWatorSquareCell returnCell = new EmptyWatorSquareCell(getX(), getY(), getWidth(), getHeight());
			returnCell.setNeighbor(this.getNeighbor());
			return returnCell;
		}
	}

	/**
	 * Gets the breed count
	 * @return breed count
	 */
	public int getBreedCount() {
		return breedCount;
	}

	/**
	 * Sets the breed count
	 * @param breedCount The new breed count
	 */
	public void setBreedCount(int breedCount) {
		this.breedCount = breedCount;
	}

	/**
	 * Returns a copy of this cell
	 */
	@Override
	public WatorSquareCell copy() {
		FishWatorSquareCell returnCell = new FishWatorSquareCell(getX(), getY(), getWidth(), getHeight(), getBreedCount());
		returnCell.setNeighbor(getNeighbor());
		return returnCell;
	}
}