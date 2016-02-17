package ForgingAntCells;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import slot.Slot;

/*
 * Author: Christine Zhou
 */
//This entire file is part of my masterpiece.
//Christine Zhou
/*
 * My ant factory takes the slot and the current direction of an ant and finds its forward and backwards neighbors. It has
 * getters for the ant cell to retreive its forward and backward neighbors. 
 * I included this factory class for determining orientation because this class separated out the very difficult details
 * of getting directional neighbors as part of the ant simulation from the simulation class Forging Ants and Ant Cell class.
 * Even though the code for getting the directional neighbors is still a very long switch case, by moving it out of the rest
 * of the simulations and the cells, I made it easier in the future if I want to add directions or change how the new directional
 * neighbors are determined. I think this is a good design choice because the implementation details of getting a list of
 * forward or backward neighobrs of an ant given its current orientation is hidden from the rest of the forging ants code.
 * This way, the brute force code for finding directional neighbors never has to appear in the ant class and I don't need 8 
 * classes of an ant just because each finds a neighbor a different way based on its 8 orientations. I also assummed when
 * I refactored that the slots class can now return a collection of neighbors if a list of directional arrays is passed in.
 * I think this should have originally been implemented in our slots class because the math for getting neighbors from direction
 * arrays was in our slot class and was being used to calculate neighbors. If I calculated neighbors with directional arrays
 * again in the forging ants class or in the factory class, then I would be repeating the calculation code that should only
 * have to appear once in the slots class.
 */
public class AntOrientationFactory {
	private Collection<Slot> forwardNeighbors;
	private Collection<Slot> backwardNeighbors;
	private Collection<Slot> neighbors;
	
	public AntOrientationFactory() {
		forwardNeighbors = new ArrayList<Slot>();
		backwardNeighbors = new ArrayList<Slot>();
	}
	
	public void findNeighbors(CardinalDirection d, Slot s) {
		neighbors = s.getNeighbors();
		findNeighborDirection(d, s);
	}
	
	
	private void getSlotsfromArray(Slot s, int[] dx, int[] dy) {
		//assumes that slot has can give back a list of neighbors with those direction vectors
		//this is definitely possible since our slot neighbors were originally calculated by passing in a direction vector
		forwardNeighbors = s.findNeighbors(dx, dy);
		for (Slot slot : neighbors) {
			if (!forwardNeighbors.contains(slot)) {
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
	
	/*
	 * Returns the forward neighbors, must be called after findNeighbors 
	 */
	public Collection<Slot> getForwardNeighbors() {
		return forwardNeighbors;
	}
	
	/*
	 * Returns the backward neighbors, must be called after findNeighbors 
	 */
	public Collection<Slot> getBackwardNeighbors() {
		return backwardNeighbors;
	}
}
