import java.util.ArrayList;
import java.util.Objects;

public class Branch_Topology {

	ArrayList<Breaker> breakers;
	ArrayList<Bus_Topology> busbars;
	PowerTransformer power_transformer ;
	ACLine line;
	
	
	public Branch_Topology() {
		
		this.line =null;
		this.power_transformer =null;
		this.breakers = new ArrayList<Breaker>();	
		this.busbars=new ArrayList<Bus_Topology>();
		
	}
	
	
	public void addBreaker(Breaker breaker) {
		
		this.breakers.add(breaker);
	}
	
	public void addBus_Topology(Bus_Topology busbar) {
		
		this.busbars.add(busbar);
	}
		
	public void addBusToBranch(ArrayList<Bus_Topology> bus_top, BusBar busbar) {
		
		for(Bus_Topology bus : bus_top) {
			if(Objects.equals(busbar.rdf_ID,bus.Bus.rdf_ID)) {
				this.busbars.add(bus);
			}
		}
	}
		
	public void addPT(PowerTransformer transformer) {
		
		this.power_transformer =transformer;
	}
		
	public void addLine(ACLine line) {
		
		this.line =line;
	}
	
	public void addConnectors(GridData sys,Terminal t, int init) {
		Breaker breaker = null;
		Terminal terminal = null;
		PowerTransformer transformer = null;
		ACLine ac_line = null;
		ConnectivityNode node = null;
		
		//visit this terminal
		visitTerminal(sys,t);
		
		//in case there is a bus in this node, finish branch except the initial terminal
		if(BusAtTerminalNode(sys,t) == true && init!=0) {return;}
		
		//looks to see if this terminal is connected to a breaker if yes then add to branch
		for(Breaker b : sys.ListBreaker) {
			if(Objects.equals(t.conductingEquipment.substring(1),b.rdf_ID)) {
				//Found Breaker
				breaker = b;
				terminal = getOtherTerminal_Breaker(sys,t,breaker);
				if(checkIfVisited(terminal,sys) == false) {
					this.breakers.add(b);
					addConnectors(sys,terminal,1);
					return;
				}
			}
		}
		
		//looks to see if this terminal is connected to a power transformer if yes then add to branch
		for(PowerTransformer pt : sys.ListPowerTransformer) {
			if(Objects.equals(t.conductingEquipment.substring(1),pt.rdf_ID) ) {
				//Found Breaker
				transformer = pt;
				terminal = getOtherTerminal_PowerTransformer(sys,t,transformer);
				if(checkIfVisited(terminal,sys) == false) { 
					this.addPT(pt);;
					addConnectors(sys,terminal,1);
					return;
				}
			}
		}
		
		//looks to see if this terminal is connected to a line if yes then add to branch
		for(ACLine line : sys.ListACLine) {
			if(Objects.equals(t.conductingEquipment.substring(1),line.rdf_ID) ) {
				//Found Breaker
				ac_line = line;
				terminal = getOtherTerminal_Line(sys,t,ac_line);
				if(checkIfVisited(terminal,sys) == false) {
					this.addLine(ac_line);
					addConnectors(sys,terminal,1);
					return;
				}
			}
		}
		
		//look up the terminal node
		for(ConnectivityNode con : sys.ListConNode) {
			if(Objects.equals(con.rdf_ID, t.connectivityNode.substring(1))) {
				node =con;
			}
		}
		
		//iterate other possible terminals and recalls the function
		for(Terminal term : node.terminals) {
			if(checkIfVisited(term,sys)==false) {
				addConnectors(sys,term,1);
				return;
			}
		}
		
		return;
	}
	
	public Terminal getOtherTerminal_Breaker(GridData sys, Terminal t, Breaker breaker) {
		
		for(Terminal terminal : sys.ListTerminal) {
			if(Objects.equals(terminal.conductingEquipment.substring(1),breaker.rdf_ID)) {
				if(Objects.equals(terminal.rdf_ID, t.rdf_ID) != true){
					return terminal;
				}
			}
		}
		return null;
	}
	
	public Terminal getOtherTerminal_PowerTransformer(GridData sys, Terminal t, PowerTransformer PT) {
		
		for(Terminal terminal : sys.ListTerminal) {
			if(Objects.equals(terminal.conductingEquipment.substring(1),PT.rdf_ID)) {
				if(Objects.equals(terminal.rdf_ID, t.rdf_ID) != true){
					return terminal;
				}
			}
		}
		return null;
	}
	
	public Terminal getOtherTerminal_Line(GridData sys, Terminal t, ACLine line) {
		
		for(Terminal terminal : sys.ListTerminal) {
			if(Objects.equals(terminal.conductingEquipment.substring(1),line.rdf_ID)) {
				if(Objects.equals(terminal.rdf_ID, t.rdf_ID) != true){
					return terminal;
				}
			}
		}
		return null;
	}
	
	public Boolean BusAtTerminalNode(GridData sys,Terminal t) {
		ConnectivityNode con_node = null;
		
		//obtain node of the terminal
		for(ConnectivityNode node : sys.ListConNode) {
			if(Objects.equals(node.rdf_ID, t.connectivityNode.substring(1))) {
				con_node = node;
				break;
			}
		}
		
		//obtain all terminals from the node and check if there is a bus bar
		for(Terminal terminal : con_node.terminals) {
			for(BusBar busbar : sys.ListBusBar) {
				if(Objects.equals(busbar.rdf_ID,terminal.conductingEquipment.substring(1))) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void visitTerminal(GridData sys, Terminal t) {
		ConnectivityNode node =null;
		int index1 = 0,index2 = 0;
		
		
		for(int i = 0; i < sys.ListConNode.size(); i++) {
			
			if(Objects.equals(sys.ListConNode.get(i).rdf_ID,t.connectivityNode.substring(1) )) {
				index1 = i;
				node = sys.ListConNode.get(i);
				break;
			}
		}
		
		for(int i = 0; i < node.terminals.size(); i++) {
			if(Objects.equals(node.terminals.get(i).rdf_ID, t.rdf_ID)) {
				index2 = i;
			}
		}
		sys.ListConNode.get(index1).terminals.get(index2).visited = true;
	}
	
	public Boolean checkIfVisited(Terminal t, GridData sys) {
		
		ConnectivityNode node =null;
		int index1 = 0,index2 = 0;
		
		for(int i = 0; i < sys.ListConNode.size(); i++) {
			if(Objects.equals(sys.ListConNode.get(i).rdf_ID,t.connectivityNode.substring(1) )) {
				index1 = i;
				node = sys.ListConNode.get(i);
			}
		}
		
		for(int i = 0; i < node.terminals.size(); i++) {
			if(Objects.equals(node.terminals.get(i).rdf_ID, t.rdf_ID)) {
				index2 = i;
			}
		}
		return sys.ListConNode.get(index1).terminals.get(index2).visited;
		
		
	}
	
	
}
