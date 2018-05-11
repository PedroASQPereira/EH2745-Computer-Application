import java.util.ArrayList;


public class ConnectivityNode {
	
	String rdf_ID;
	String resource;
	ArrayList<Terminal> terminals; 
	
	
	public ConnectivityNode(String name1,String name2)
	{
		rdf_ID=name1;
		resource=name2;
		this.terminals= new ArrayList<Terminal>();
		
	}
	
	public void addTerminal(Terminal t) {
		
		(this.terminals).add(t);
		
	}
	
	public void showTerminal() {
		System.out.print("[");
		for(Terminal s : this.terminals) {
			System.out.print(s.rdf_ID +"  ");
		}
		System.out.print("]\n");
	}
	

}