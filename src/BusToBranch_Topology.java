import java.util.ArrayList;
import java.util.Objects;

public class BusToBranch_Topology {

	ArrayList<Bus_Topology> bus;
	ArrayList<Branch_Topology> branch;
	
	public BusToBranch_Topology () {
		this.bus = new ArrayList<Bus_Topology>();
		this.branch = new ArrayList<Branch_Topology>();
	}
	
	
	public void addAllBus(ArrayList<Bus_Topology> bus, GridData sys) {
		
		//Insert in class Bus_Topology the corresponding BusBar
		for(BusBar busbar : sys.ListBusBar) {
			Bus_Topology bus_top = new Bus_Topology(busbar);
			bus.add(bus_top);
		}
		
		//Insert in class Bus_Topology the corresponding EnergyConsumer
		for(EnergyConsumer load : sys.ListLoad) {
			for(int i = 0; i < bus.size(); i++) {
				if(Objects.equals(load.equipmentContainer_ID, bus.get(i).Bus.equipContainer)) {
					bus.get(i).insertLoad(load);
				}
			}
		}
		
		//Insert in class Bus_Topology the corresponding LinearShunt
		for(LinearShunt shunt : sys.ListShunt) {
			for(int i = 0; i < bus.size(); i++) {
				if(Objects.equals(shunt.equipmentContainer, bus.get(i).Bus.equipContainer)) {
					bus.get(i).insertShunt(shunt);
				}
			}
		}
		
		//Insert in class Bus_Topology the corresponding LinearShunt
		for(synch sync : sys.ListSynchronous) {
			for(int i = 0; i < bus.size(); i++) {
				if(Objects.equals(sync.equipmentcontainer_rdf, bus.get(i).Bus.equipContainer)) {
					bus.get(i).insertSyncMachine(sync);
				}
			}
		}
		
		//Insert in class Bus_Topology the corresponding LinearShunt
		for(VoltageLevel volt : sys.ListVoltageLevel) {
			for(int i = 0; i < bus.size(); i++) {
				if(Objects.equals(volt.rdf_ID, bus.get(i).Bus.equipContainer.substring(1))) {
					bus.get(i).insertVoltage(volt);
				}
			}
		}
			
	}
	
	
	public void addAllBranch(ArrayList<Branch_Topology> branch,ArrayList<Bus_Topology> bus, GridData sys) {
			
		//ITERATE OVER ALL BUS BARS TO FIND THE EXISTING BRANCHS		
		for(BusBar busbar : sys.ListBusBar) {
			
			//FIND INDEX OF NODE CONNECTED TO THE ITERATED BUSBAR
			int index = findNodeofBus(busbar,sys);
					
			//System.out.print("BusBar " + busbar.name);
			
			//START LOOP OF TERMINALS, IGNORING LOADS SHUNTS 
			for(int i = 0; i < sys.ListConNode.get(index).terminals.size(); i++) {
				//Only if it a breaker/transformer/line will the branch be created
				//Otherwise the terminal is ignored
				if(CheckEquipOfTerminal(sys.ListConNode.get(index).terminals.get(i),sys)) {
					//Check if this breaker/line/transformer has already been visited
						//if yes
							//search for existing branch and add the bus to that branch
						//if no
							//Create branch structure
							//Input the required variables
							//Add it to the list
					//System.out.print(" is connected a breaker in terminal " + sys.ListConNode.get(index).terminals.get(i).rdf_ID +" ");
					if(sys.ListConNode.get(index).terminals.get(i).visited) {
						//get the equip of the terminal
						//cycle through the branch_topology data (breaker/line/transformer)
							//when you find a match, make a connecting between bus bar and bus topology and add it to the this branch
						addBustoExistingBranch(sys.ListConNode.get(index).terminals.get(i),branch,bus,busbar);
					}else {
						//create structure, add bus_top, breakers, line and transformer and put it in the list of bus_top
						Branch_Topology branch_top = new Branch_Topology();
						branch_top.addBusToBranch(bus, busbar);
						branch_top.addConnectors(sys,sys.ListConNode.get(index).terminals.get(i),0);
						branch.add(branch_top);
					}
				}
			}
			//WHEN REACHES A BREAKER OR LINE OR TRANSFORMER CHECK IS IT HAS BEEN VISITED
			//IF IT HAS REMEMBER TO ADD IN THE BRANCH THE BUS AS TO CONNECT ALL BRANCHS IN 
			//TWO DIRECTIONS
			//IS IT AN ALREADY EXISTING BRANCHS, IGNORE PROCEDURE
			
			
		}
		
	}
	
	public int findNodeofBus(BusBar busbar,GridData sys) {
		//This methods finds the INDEX of node to which a certain bus is connected
		int index = 0;
//		ConnectivityNode node= null;
//		for(ConnectivityNode c_node : sys.ListConNode) {
//			if(Objects.equals(busbar.t.connectivityNode.substring(1),c_node.rdf_ID)) {
//				node= c_node;
//				break;
//			}
//		}
		
		for(int i = 0; i < sys.ListConNode.size(); i++) {
			if(Objects.equals(busbar.t.connectivityNode.substring(1),sys.ListConNode.get(i).rdf_ID)) {
				index= i;
				break;
			}
		}
		
		return index;
	}
	
	
	public Boolean CheckEquipOfTerminal(Terminal t, GridData sys) {
		//This method checks the equipment of the terminals at the same node
		//In case it is a breaker line or transformer, it returns TRUE
		//Otherwise it returns FALSE
		
		for(Breaker breaker : sys.ListBreaker) {
			if(Objects.equals(t.conductingEquipment.substring(1), breaker.rdf_ID)) {
				return true;
			}
		}
		
		for(ACLine line :sys.ListACLine) {
			if(Objects.equals(t.conductingEquipment.substring(1), line.rdf_ID)) {
				return true;
			}
		}
		
		for(PowerTransformer trans :sys.ListPowerTransformer) {
			if(Objects.equals(t.conductingEquipment.substring(1), trans.rdf_ID)) {
				return true;
			}
		}
		
		
		return false;
		
	}
	
	
	public Boolean equal(String name1, String name2) {
		//Check if two strings are equal
		
		Boolean result = Objects.equals(name1, name2);
		
		return result;
		
	}
	

	public void addBustoExistingBranch(Terminal t, ArrayList<Branch_Topology> branch,ArrayList<Bus_Topology> bus, BusBar busbar) {
		
		Boolean found = false;
		int index=0;
		
		for(int i = 0; i < branch.size(); i++) {
			for(int j = 0; j < branch.get(i).breakers.size(); j++) {
				if(Objects.equals(t.conductingEquipment.substring(1), branch.get(i).breakers.get(j).rdf_ID)) {
					found = true;
					index = i;
					break;
				}
			}	
		}
		
		if(found == false) {
			for(int i = 0; i < branch.size(); i++) {
				if(branch.get(i).line !=  null) {
					if(Objects.equals(t.conductingEquipment.substring(1), branch.get(i).line.rdf_ID)) {
						found = true;
						index = i;
						break;
					}	
				}
			}
			
			for(int i = 0; i < branch.size(); i++) {
				if(branch.get(i).power_transformer !=  null) {
					if(Objects.equals(t.conductingEquipment.substring(1), branch.get(i).power_transformer.rdf_ID)) {
						found = true;
						index = i;
						break;
					}	
				}
			}
		}
		
		for(Bus_Topology b_top : bus) {
			if(Objects.equals(busbar.rdf_ID,b_top.Bus.rdf_ID)) {
				branch.get(index).addBus_Topology(b_top);
			}
		}
		
		
	}
	
		
	
	
}

