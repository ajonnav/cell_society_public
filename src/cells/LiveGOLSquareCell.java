package cells;

import javafx.scene.paint.Color;

public class LiveGOLSquareCell extends GOLSquareCell{

	public final boolean ALIVE_STATUS = true;
	
	public LiveGOLSquareCell(double x, double y, double w, double h) {
		super(x, y, Color.BLUE, w, h);
		setAlive(ALIVE_STATUS);
	}

	@Override
	public GOLSquareCell update(GOLSquareCell[] cells) {
		int numAlive = 0;
		for(int i: getNeighbor()) {
			if(cells[i].isAlive())
				numAlive++;
		}
		if(numAlive<2 || numAlive>3) {
			DeadGOLSquareCell returnCell = new DeadGOLSquareCell(getX(), getY(), getWidth(), getHeight());
			returnCell.setNeighbor(getNeighbor());
			return returnCell;
		}
		return this;
	}

}
