package shapefile.graph;

public class Road {
	
	private String LID;
	private String SnodeID;
	private String EnodeID;
	private String Length;
	private String Direction;
	
	public Road(){
		
	}

	public String getLID() {
		return LID;
	}

	public void setLID(String lID) {
		LID = lID;
	}

	public String getSnodeID() {
		return SnodeID;
	}

	public void setSnodeID(String snodeID) {
		SnodeID = snodeID;
	}

	public String getEnodeID() {
		return EnodeID;
	}

	public void setEnodeID(String enodeID) {
		EnodeID = enodeID;
	}

	public String getLength() {
		return Length;
	}

	public void setLength(String length) {
		Length = length;
	}

	public String getDirection() {
		return Direction;
	}

	public void setDirection(String direction) {
		Direction = direction;
	}
}
