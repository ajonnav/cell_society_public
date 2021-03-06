package grid;

public class Direction {

	private int[] vec;
	private Direction(int v, int h) {
		// TODO Auto-generated constructor stub
		vec = new int[2];
		vec[0] = v;
		vec[1] = h;
	}
	
	public static final Direction NORTH = new Direction(-1, 0);
	public static final Direction SOUTH = new Direction(1, 0);
	public static final Direction EAST = new Direction(0, 1);
	public static final Direction WEST = new Direction(0, -1);
	public static final Direction NORTHWEST = new Direction(-1, -1);
	public static final Direction NORTHEAST = new Direction(-1, 1);
	public static final Direction SOUTHWEST = new Direction(1, -1);
	public static final Direction SOUTHEAST = new Direction(1, 1);
	
	public static final Direction[] ALL_DIRECTIONS_SQUARE = 
		{NORTH, SOUTH, EAST, WEST, NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST};
	public static final Direction[] CARDINAL_DIRECTIONS = 
		{NORTH, SOUTH, EAST, WEST};
	public static final Direction[] ALL_DIRECTIONS_HEXAGON = 
		{NORTH, SOUTH, EAST, WEST, NORTHWEST, SOUTHWEST};
	public int[] getVec(){ return vec; }
}
