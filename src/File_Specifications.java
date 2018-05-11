import java.util.ArrayList;

public class File_Specifications {

	ArrayList<String> ListSpecs_EQ;
	ArrayList<String> ListSpecs_SSH;
	
	public File_Specifications() {
		this.ListSpecs_EQ = new ArrayList<String>();
		this.ListSpecs_SSH = new ArrayList<String>();
		
		ListSpecs_EQ.add("cim:BaseVoltage");
		ListSpecs_EQ.add("cim:Substation");
		ListSpecs_EQ.add("cim:VoltageLevel");
		ListSpecs_EQ.add("cim:GeneratingUnit");
		ListSpecs_EQ.add("cim:SynchronousMachine");
		ListSpecs_EQ.add("cim:RegulatingControl");
		ListSpecs_EQ.add("cim:PowerTransformer");
		ListSpecs_EQ.add("cim:EnergyConsumer");
		ListSpecs_EQ.add("cim:PowerTransformerEnd");
		ListSpecs_EQ.add("cim:Breaker");
		ListSpecs_EQ.add("cim:RatioTapChanger");
		ListSpecs_EQ.add("cim:Terminal");
		ListSpecs_EQ.add("cim:BusbarSection");
		ListSpecs_EQ.add("cim:ConnectivityNode");
		ListSpecs_EQ.add("cim:ACLineSegment");
		ListSpecs_EQ.add("cim:LinearShuntCompensator");
		
		
		ListSpecs_SSH.add("cim:RegulatingControl");
		ListSpecs_SSH.add("cim:EnergyConsumer");
		ListSpecs_SSH.add("cim:SynchronousMachine");
		ListSpecs_SSH.add("cim:RatioTapChanger");
		
	}
	
}

