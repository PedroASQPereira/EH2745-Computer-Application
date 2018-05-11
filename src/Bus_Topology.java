

public class Bus_Topology {
	
	BusBar Bus;
	EnergyConsumer Load;
	LinearShunt Shunt;
	synch SynchronousMachine;
	VoltageLevel Voltage;
	
	double bus_admittance;
	
	public Bus_Topology(BusBar Bus) {
		
		this.Bus=Bus;	
	}
	
	public void insertLoad(EnergyConsumer Load) {
		
		this.Load=Load;
	}
	
	public void insertShunt(LinearShunt Shunt) {
		
		this.Shunt=Shunt;
	}
	
	public void insertSyncMachine(synch SynchronousMachine) {
		
		this.SynchronousMachine=SynchronousMachine;
	}
	
	public void insertVoltage(VoltageLevel Voltage) {
		
		this.Voltage=Voltage;
	}
	

	
	
}
