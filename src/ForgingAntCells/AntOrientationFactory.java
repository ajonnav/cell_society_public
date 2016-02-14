package ForgingAntCells;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import slot.Slot;

/*
 * Author: Christine Zhou
 */
public class AntOrientationFactory {
	private Collection<Slot> forwardNeighbors;
	private Collection<Slot> backwardNeighbors;
	private Collection<Slot> Neighbors;
	
	private int tcol;
	
	public AntOrientationFactory() {	
	}
	
	/*
	* Factory will return the list of neighbors both forward and backward
	*/
	public AntOrientationFactory(CardinalDirection d, Slot s, int tcol) {
		Neighbors = s.getNeighbors();
		forwardNeighbors = new ArrayList<Slot>();
		backwardNeighbors = new ArrayList<Slot>();
		this.tcol = tcol;
		findNeighborDirection(d, s);
	}
	
	/*
	* Returns forward neighbors
	*/
	public Collection<Slot> getForwardNeighbors() {
		return forwardNeighbors;
	}
	
	/*
	* Returns backward neighbors
	*/
	public Collection<Slot> getBackwardNeighbors() {
		return backwardNeighbors;
	}
	
	private void getSlotsfromArray(Slot s, int[] dx, int[] dy) {
		int index = s.index();
		int myX = index / tcol;
		int myY = index % tcol;
		List<Integer> f_indexes = new ArrayList<Integer>();
		for (int k = 0; k < dx.length; k++) {
			f_indexes.add((myX + dx[k])*(tcol) + (myY + dy[k]));
		}
		for (Slot slot : Neighbors) {
			if (f_indexes.contains(slot.index())) {
				forwardNeighbors.add(slot);
			} else {
				backwardNeighbors.add(slot);
			}
		}
	}
	
	private void findNeighborDirection(CardinalDirection d, Slot s) {
		int[] dx = null; 
		int[] dy = null;
		if (d == null) {
			dx = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
			dy = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
		} else {
			switch (d) {
				case N:
					dx = new int[]{-1, -1, -1};
					dy = new int[]{-1, 0, 1};
					break;
				case NE: 
					dx = new int[]{-1, -1, 0};
					dy = new int[]{0, 1, 1};
					break;
				case E:
					dx = new int[]{-1, 0, 1};
					dy = new int[]{-1, 1, 1};
					break;
				case SE: 
					dx = new int[]{0, 1, 1};
					dy = new int[]{1, 1, 0};
					break;
				case S:
					dx = new int[]{1, 1, 1};
					dy = new int[]{1, 0, 1};
					break;
				case SW:
					dx = new int[]{1, 1, 0};
					dy = new int[]{0, -1, -1};
					break;
				case W:
					dx = new int[]{1, 0, -1};
					dy = new int[]{-1, -1, -1};
					break;
				case NW:
					dx = new int[]{0, -1, -1};
					dy = new int[]{-1, -1, 0};
					break;
				default:
					dx = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
					dy = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
					break;
			}
		}
		getSlotsfromArray(s, dx, dy);
	}

}
