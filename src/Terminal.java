
public class Terminal {

	String rdf_ID;
	String connectivityNode;
	String conductingEquipment;
	
	Boolean visited;
	
	public Terminal(String rdf_ID, String connectivityNode,String conductingEquipment) {
		this.rdf_ID = rdf_ID;
		this.connectivityNode =connectivityNode;
		this.conductingEquipment=conductingEquipment;
		visited = false;
	}
	
	public void visitTerminal() {
		
		this.visited = true;
	}
	
	
	
	
}
