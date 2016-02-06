package FireCells;

import javafx.scene.paint.Color;

public class FireTREE extends FireCell {

	private static final boolean isBurning = false;
	private static final boolean isEmpty = false;
	private static double probCatch;

	public FireTREE(double x, double y, double w, double h, double probCatch) {
		super(x, y, Color.GREEN, w, h, probCatch);
		// TODO Auto-generated constructor stub
		setEmpty(isEmpty);
		setBurning(isBurning);
		this.probCatch = probCatch;
	}

	@Override
	public FireCell update(FireCell[] cells) {
		// TODO Auto-generated method stub
		FireBURNING newFireCell = new FireBURNING(getX(), getY(), getWidth(), getHeight());
		newFireCell.setNeighbor(getNeighbor());
		return hasBurningNeighbor(cells) && (probCatch <= Math.random()) 
				? newFireCell : this;
	}
	
	private boolean hasBurningNeighbor(FireCell[] cells) {
		for (int i : getNeighbor()) {
			if (cells[i].isBurning()) {
				return true;
			}
		}
		return false;
	}
}
