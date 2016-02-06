package cells;

import java.util.ArrayList;
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

	@Override
	public void updateWator(WatorSquareCell[] cells, WatorSquareCell[] list, int position) {
		decrementBreedCount();
		decrementStarveCount();
		
		if(getStarveCount() == 0) {
			return;
		}
		
		ArrayList<Integer> emptyCells = new ArrayList<Integer>();
		for(int i = 0; i < getNeighbor().size(); i++) {
			if(list[getNeighbor().get(i)].isEmpty() && !cells[getNeighbor().get(i)].isShark())
				emptyCells.add(getNeighbor().get(i));
		}
		
		if(emptyCells.isEmpty()) {
			return;
		}
		
		Random rnd = new Random();
		int newIndex = emptyCells.get(rnd.nextInt(emptyCells.size()));
		
		if(!cells[newIndex].isShark()) {
			resetStarveCount();
		}
		
		ArrayList<Integer> tempNeighbor = new ArrayList<Integer>(list[newIndex].getNeighbor());
		
		if(breedCount <= 0) {
			list[position] = new SharkWatorSquareCell(list[newIndex].getX(), list[newIndex].getY(),getWidth(), getHeight(), BREED, STARVE);
			list[position].setNeighbor(new ArrayList<Integer>(this.getNeighbor()));
			resetBreedCount();
		}
		
		list[newIndex] = new SharkWatorSquareCell(list[newIndex].getX(), list[newIndex].getY(),getWidth(), getHeight(), BREED, STARVE);
		list[newIndex].setNeighbor(tempNeighbor);
		((SharkWatorSquareCell)list[newIndex]).setBreedCount(this.getBreedCount());
		((SharkWatorSquareCell)list[newIndex]).setStarveCount(this.getStarveCount());
		return;
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

}
