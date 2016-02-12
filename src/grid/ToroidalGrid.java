package grid;

import slot.Slot;

public class ToroidalGrid extends FiniteGrid {

	public ToroidalGrid(int r, int c, int slotType, int length,
			Direction[] neighborsToCheck) {
		super(r, c, slotType, length, neighborsToCheck);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Technically, a ToroidalGrid IS a Finite Grid, the only difference between the two is the connection between cells at the edge of the grid
	 * therefore, the only override required is with neighbor assignment.  
	 */
	private void addIfInBounds(Slot s, Direction d) {
		int[] rcDelta = new int[2];
		rcDelta[0] = s.getRowCol()[0] + d.getVec()[0];
		rcDelta[1] = s.getRowCol()[1] + d.getVec()[1];
		if(inBounds(rcDelta)){
			s.addNeighbor(rcMap.get(buildKey(rcDelta[0], rcDelta[1])));
		}else if(rcDelta[0] != rcDelta[1]){
			s.addNeighbor(rcMap.get(buildKey(wrapAround(rcDelta)[0], wrapAround(rcDelta)[1])));
		}
	}
	
	private int[] wrapAround(int[] bounds){
		if(bounds[0] < 0){
			bounds[0] = getRows() - 1; 
		}
		if(bounds[1] < 0){
			bounds[1] = getCols() - 1; 
		}
		if(bounds[0] > getRows()-1){
			bounds[0] = 0; 
		}
		if(bounds[1] > getCols()-1){
			bounds[0] = 0; 
		}
		return bounds;
	}
	

}
