package shapefile.graph;


public class Node {
	private char ID;
	private char Cross_flag;
	private char Cross_LID;
	private char mainNodeID;
	private char Adjoin_NID;
	private char Node_LID;
	
	public Node(){
		
	}

	public char getID() {
		return ID;
	}

	public void setID(char iD) {
		ID = iD;
	}

	public char getCross_flag() {
		return Cross_flag;
	}

	public void setCross_flag(char cross_flag) {
		Cross_flag = cross_flag;
	}

	public char getCross_LID() {
		return Cross_LID;
	}

	public void setCross_LID(char cross_LID) {
		Cross_LID = cross_LID;
	}

	public char getMainNodeID() {
		return mainNodeID;
	}

	public void setMainNodeID(char mainNodeID) {
		this.mainNodeID = mainNodeID;
	}

	public char getAdjoin_NID() {
		return Adjoin_NID;
	}

	public void setAdjoin_NID(char adjoin_NID) {
		Adjoin_NID = adjoin_NID;
	}

	public char getNode_LID() {
		return Node_LID;
	}

	public void setNode_LID(char node_LID) {
		Node_LID = node_LID;
	}
}
