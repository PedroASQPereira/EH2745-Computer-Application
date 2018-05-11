import java.util.ArrayList;
import java.util.Objects;

public class Breaker {

	String rdf_ID;
	String name;
	String state;
	String equipmentContainer_ID;
	String baseVoltage_rdf_ID;
	
	public Breaker(String name1,String name2,String name3,String name4)
	{
		rdf_ID=name1;
		name=name2;
		state=name3;
		equipmentContainer_ID=name4;
	}
	
	public void findBaseVoltage(ArrayList<VoltageLevel> ListVoltageLevel) {
		
		for(VoltageLevel s : ListVoltageLevel) {
			if( Objects.equals( equipmentContainer_ID.substring(1), s.rdf_ID) ){
				this.baseVoltage_rdf_ID= s.baseVoltage_rdf_ID;
			}
		}
	}
	
	
	public void show_data()
	{
		System.out.println("Breaker\n" +" rdf:ID " + rdf_ID +"\n name " + name + "\n State "+ state + "\n baseVoltage:rdf:ID " + baseVoltage_rdf_ID);
	}
			
	
	
}
