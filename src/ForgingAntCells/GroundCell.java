package ForgingAntCells;

import java.util.Comparator;

import cells.Cell;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GroundCell extends Cell {
	private double homeHormone;
	private double foodHormone;
	private double hormoneLimit;
	
	public GroundCell(double x, double y, Color color, double w, double h) {
		super(x, y, color, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}
	
	public void sethomeHormone(double addHormone) {
		if (homeHormone < hormoneLimit) {
			homeHormone += addHormone;
		}
	}
	
	public void setfoodHormone(double addHormone) {
		if (foodHormone < hormoneLimit) {
			foodHormone += addHormone;
		}
	}

	public double gethomeHormone() {
		return homeHormone;
	}
	
	public double getfoodHormone() {
		return foodHormone;
	}
	
	public void update(GroundCell[] cells) {
		//evaporation, decrease hormone levels by certain amount
		//check for diffusion? based on neighbors, increase by certain amount?
	}
	
	//use comparable or comparator to sort by either food hormones or home hormones?
	public class foodCompare implements Comparator<GroundCell> {

		@Override
		public int compare(GroundCell c1, GroundCell c2) {
			if (c1.getfoodHormone() != c2.getfoodHormone()) {
				return c1.getfoodHormone() > c2.getfoodHormone() ? 1 : -1;
			}
			return 0;
		}

		
	}
	
	public class homeCompare implements Comparator<GroundCell> {

		@Override
		public int compare(GroundCell c1, GroundCell c2) {
			if (c1.gethomeHormone() != c2.gethomeHormone()) {
				return c1.gethomeHormone() > c2.gethomeHormone() ? 1 : -1;
			}
			return 0;
		}

	}

}
