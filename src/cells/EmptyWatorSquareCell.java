package cells;

import java.util.HashMap;

import javafx.scene.paint.Color;

public class EmptyWatorSquareCell extends WatorSquareCell{

	private static final Color CELL_COLOR = Color.BLUE;
	private final boolean IS_EMPTY= true;
	private final boolean IS_SHARK= false;
	
	public EmptyWatorSquareCell(double x, double y, double w, double h) {
		super(x, y, CELL_COLOR, w, h);
		setEmpty(IS_EMPTY);
		setShark(IS_SHARK);
	}

	@Override
	public WatorSquareCell update(WatorSquareCell[] cells) {
		return null;
	}

	@Override
	public WatorSquareCell updateWator(WatorSquareCell[] cells, HashMap<Integer, Integer> map, int position) {
		if(map.containsKey(position)) {
			WatorSquareCell returnCell = cells[map.get(position)].copy();
			returnCell.setNeighbor(this.getNeighbor());
			returnCell.setX(getX());
			returnCell.setY(getY());
			return returnCell;
		}
		return this;
	}

	@Override
	public WatorSquareCell copy() {
		EmptyWatorSquareCell returnCell = new EmptyWatorSquareCell(getX(), getY(), getWidth(), getHeight());
		returnCell.setNeighbor(getNeighbor());
		return returnCell;
	}

}
