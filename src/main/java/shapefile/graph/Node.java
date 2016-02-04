package shapefile.graph;


public class Node {
	private String ID;
	private String Cross_flag;
	private String Cross_LID;
	private String mainNodeID;
	private String Adjoin_NID;
	private String Node_LID;
	
	public Node(){
		
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCross_flag() {
		return Cross_flag;
	}

	public void setCross_flag(String cross_flag) {
		Cross_flag = cross_flag;
	}

	public String getCross_LID() {
		return Cross_LID;
	}

	public void setCross_LID(String cross_LID) {
		Cross_LID = cross_LID;
	}

	public String getMainNodeID() {
		return mainNodeID;
	}

	public void setMainNodeID(String mainNodeID) {
		this.mainNodeID = mainNodeID;
	}

	public String getAdjoin_NID() {
		return Adjoin_NID;
	}

	public void setAdjoin_NID(String adjoin_NID) {
		Adjoin_NID = adjoin_NID;
	}

	public String getNode_LID() {
		return Node_LID;
	}

	public void setNode_LID(String node_LID) {
		Node_LID = node_LID;
	}
}
