package cells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javafx.scene.paint.Color;

public class SharkWatorSquareCell extends WatorSquareCell{

	private static final Color CELL_COLOR = Color.RED;
	private final int BREED;
	private final int STARVE;
	private int starveCount;
	private int breedCount;
	private final boolean IS_EMPTY = false;
	private final boolean IS_SHARK = true;
	
	
	public SharkWatorSquareCell(double x, double y, double w, double h, int breed, int starve) {
		super(x, y, Color.RED, w, h);
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

	public void decrementBreedCount() {
		setBreedCount(getBreedCount() - 1);
	}
	
	public void decrementStarveCount() {
		setStarveCount(getStarveCount() - 1);
	}
	
	public void resetBreedCount() {
		setBreedCount(BREED);
	}
	public void resetStarveCount() {
		setStarveCount(STARVE);
	}
	
	public int getBreedCount() {
		return breedCount;
	}

	public void setBreedCount(int breedCount) {
		this.breedCount = breedCount;
	}

	private int getStarveCount() {
		return starveCount;
	}

	private void setStarveCount(int starveCount) {
		this.starveCount = starveCount;
	}

	@Override
	public WatorSquareCell copy() {
		SharkWatorSquareCell returnCell = new SharkWatorSquareCell(getX(), getY(), getWidth(), getHeight(), BREED, STARVE);
		returnCell.setNeighbor(getNeighbor());
		returnCell.setBreedCount(getBreedCount());
		returnCell.setStarveCount(getStarveCount());
		return returnCell;
	}

}
