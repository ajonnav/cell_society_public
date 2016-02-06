package cells;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;

public class FishWatorSquareCell extends WatorSquareCell{

	private static final Color CELL_COLOR = Color.WHITE;
	private final int BREED;
	private int breedCount;
	private boolean IS_EMPTY = false;
	private final boolean IS_SHARK= false;
	
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
	
	public void decrementBreedCount() {
		setBreedCount(getBreedCount() - 1);
	}
	
	public void resetBreedCount() {
		setBreedCount(BREED);
	}

	@Override
	public void updateWator(WatorSquareCell[] cells, WatorSquareCell[] list, int position) {
		if(!list[position].isEmpty())
			return;
		decrementBreedCount();
		ArrayList<Integer> emptyCells = new ArrayList<Integer>();
		for(int i =0; i<getNeighbor().size();i++) {
			if(list[getNeighbor().get(i)].isEmpty() && cells[getNeighbor().get(i)].isEmpty())
				emptyCells.add(getNeighbor().get(i));
		}
		if(emptyCells.size() == 0) {
			list[position] = this;
			return;
		}
		
		Random rnd = new Random();
		int newIndex = emptyCells.get(rnd.nextInt(emptyCells.size()));
		ArrayList<Integer> tempNeighbor = new ArrayList<Integer>(list[newIndex].getNeighbor());
		
		if(breedCount <= 0) {
			list[position] = new FishWatorSquareCell(list[newIndex].getX(), list[newIndex].getY(),getWidth(), getHeight(), BREED);
			list[position].setNeighbor(new ArrayList<Integer>(this.getNeighbor()));
			resetBreedCount();
		}
		
		list[newIndex] = new FishWatorSquareCell(list[newIndex].getX(), list[newIndex].getY(),getWidth(), getHeight(), BREED);
		list[newIndex].setNeighbor(tempNeighbor);
		((FishWatorSquareCell)list[newIndex]).setBreedCount(this.getBreedCount());
		
		return;
	}

	public int getBreedCount() {
		return breedCount;
	}

	public void setBreedCount(int breedCount) {
		this.breedCount = breedCount;
	}
}
