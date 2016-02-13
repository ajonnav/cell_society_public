package grid;

import slot.Slot;

public class ToroidalGrid extends FiniteGrid {

	public ToroidalGrid(int r, int c, int w, int h, String s, Direction[] neighborsToCheck) {
		super(r, c, w, h, s, neighborsToCheck);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Technically, a ToroidalGrid IS a Finite Grid, the only difference between the two is the connection between cells at the edge of the grid
	 * therefore, the only override required is with neighbor assignment.  
	 */
	@Override
	public void addIfInBounds(Slot s, Direction d) {
		int[] rcDelta = new int[2];
		int row = s.index()/getNumCol();
		int col = s.index()%getNumCol();
		rcDelta[0] = (row + d.getVec()[0])%getNumRow();
		rcDelta[1] = (col + d.getVec()[1])%getNumCol();
		s.addNeighbor(slotList.get(getIndexFromRowCol(rcDelta[0], rcDelta[1])));
	}
}
