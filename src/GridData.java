import java.util.ArrayList;

public class GridData {

	ArrayList<BaseVoltage> ListBaseVoltage ;
	ArrayList<Substation> ListSubstation ;
	ArrayList<VoltageLevel> ListVoltageLevel ;
	
	ArrayList<BusBar> ListBusBar;
	ArrayList<GeneratingUnit> ListGeneratingUnit ;		
	ArrayList<synch> ListSynchronous ;
	ArrayList<regControl> ListRegControl ;
	ArrayList<EnergyConsumer> ListLoad;
	ArrayList<LinearShunt> ListShunt ;
	ArrayList<Breaker> ListBreaker ;
	
	ArrayList<PowerTransformer> ListPowerTransformer ;
	ArrayList<PowerTransformerEnd> ListPowerTransformerEnd ;
	ArrayList<RatioTapChanger> ListRatioTapChanger ;
	ArrayList<ACLine> ListACLine;
	
	
	ArrayList<Terminal> ListTerminal ;
	ArrayList<ConnectivityNode> ListConNode ;
	
	
	
	public GridData() {
		this.ListBaseVoltage = new ArrayList<BaseVoltage>();
		this.ListSubstation = new ArrayList<Substation>();
		this.ListVoltageLevel = new ArrayList<VoltageLevel>();
		this.ListGeneratingUnit = new ArrayList<GeneratingUnit>();		
		this.ListSynchronous = new ArrayList<synch>();
		this.ListRegControl = new ArrayList<regControl>();
		this.ListPowerTransformer = new ArrayList<PowerTransformer>();
		this.ListLoad = new ArrayList<EnergyConsumer>();
		this.ListPowerTransformerEnd = new ArrayList<PowerTransformerEnd>();
		this.ListBreaker = new ArrayList<Breaker>();
		this.ListRatioTapChanger = new ArrayList<RatioTapChanger>();
		
		this.ListBusBar = new ArrayList<BusBar>();
		this.ListTerminal = new ArrayList<Terminal>();
		this.ListConNode = new ArrayList<ConnectivityNode>();
		this.ListACLine = new ArrayList<ACLine>();
		this.ListShunt = new ArrayList<LinearShunt>();
		
	}
	
	
}
