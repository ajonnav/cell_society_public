package cells;

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
	public void updateWator(WatorSquareCell[] cells, WatorSquareCell[] list, int position) {
		return;
	}

}
