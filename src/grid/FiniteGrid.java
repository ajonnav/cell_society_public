package grid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import slot.*;


public class FiniteGrid implements AnyGrid {
	private List<Slot> slotList;
	private int rows, cols, slotLength;
	//private final Slot SLOT_TYPE;
	SquareSlot sq;
	Direction[] directions;
	Map<String, Slot> rcMap;
	public FiniteGrid(int r, int c, int slotType, int length, Direction [] neighborsToCheck) {
		// TODO Auto-generated constructor stub
		rows = r;
		cols = c;
		slotList = new ArrayList<Slot>();
		slotLength = length;
		directions = neighborsToCheck;
	}
	/**
	 * goes through the rows and cols and builds a slot for each spot.  how the slot coordinates are made depend on the slot type.
	 * assigns an index in a linear fashion. 
	 */
	@Override
	public void initializeGrid() {
		// TODO Auto-generated method stub
		int index = 0;
		rcMap = new HashMap<String,Slot>();
		for(int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				Slot s = new SquareSlot(i, j, slotLength, slotLength, index);
				slotList.add(s); // will change when I figure out how to choose which slot type to build
				String ij = buildKey(i, j);
				rcMap.put(ij, s);
				index++;
			}
		}
		setNeighbors();
	}
	private String buildKey(int i, int j) {
		StringBuilder st = new StringBuilder();
		st.append(Integer.toString(i));
		st.append(Integer.toString(j));
		String ij=  st.toString();
		return ij;
	}
	
	private void setNeighbors(){
		for(Slot s : slotList){
			for(Direction d : directions){
				int[] rcDelta = new int[2];
				rcDelta[0] = s.getRowCol()[0] + d.getVec()[0];
				rcDelta[1] = s.getRowCol()[1] + d.getVec()[1];
				if(inBounds(rcDelta)){
					s.addNeighbor(rcMap.get(buildKey(rcDelta[0], rcDelta[1])));
				}
			}
		}
	}
	
	private boolean inBounds(int[] rcDelta){
		return (0 <= rcDelta[0] && rcDelta[0] < rows && 0 <= rcDelta[1] && rcDelta[1] < cols && rcDelta[0] != rcDelta[1]);
	}

	@Override
	public Collection<Slot> getSlots() {
		// TODO Auto-generated method stub
		return slotList;
	}

}
