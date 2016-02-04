package shapefile.graph;

public class Road {
	
	private char LID;
	private char SnodeID;
	private char EnodeID;
	private char Length;
	private char Direction;
	
	public Road(){
		
	}

	public char getLID() {
		return LID;
	}

	public void setLID(char lID) {
		LID = lID;
	}

	public char getSnodeID() {
		return SnodeID;
	}

	public void setSnodeID(char snodeID) {
		SnodeID = snodeID;
	}

	public char getEnodeID() {
		return EnodeID;
	}

	public void setEnodeID(char enodeID) {
		EnodeID = enodeID;
	}

	public char getLength() {
		return Length;
	}

	public void setLength(char length) {
		Length = length;
	}

	public char getDirection() {
		return Direction;
	}

	public void setDirection(char direction) {
		Direction = direction;
	}
}
