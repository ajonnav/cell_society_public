package cells;

import javafx.scene.paint.Color;

public abstract class WatorSquareCell extends SquareCell{

	private boolean isEmpty;
	private boolean isShark;
	
	public WatorSquareCell(double x, double y, Color c, double w, double h) {
		super(x, y, c, w, h);
	}

	public abstract WatorSquareCell update(WatorSquareCell[] cells);
	
	public abstract void updateWator(WatorSquareCell[] cells, WatorSquareCell[] list, int position);

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public boolean isShark() {
		return isShark;
	}

	public void setShark(boolean isShark) {
		this.isShark = isShark;
	}
}
