package ForgingAntCells;

import java.util.Collection;

import javafx.scene.paint.Color;

public class NE_Ant extends AntCell {

	public NE_Ant(double x, double y, Color color, double w, double h) {
		super(x, y, color, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(AntCell[] ants, GroundCell[] groundcells) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<GroundCell> getForwardNeighbors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<GroundCell> getNonForwardNeighbors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AntCell moveToNextLocation(GroundCell cell) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getFoodStatus() {
		// TODO Auto-generated method stub
		return false;
	}

}
