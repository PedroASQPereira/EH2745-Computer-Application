import java.util.ArrayList;
import java.util.Objects;

public class EnergyConsumer {

	String rdf_ID;
	String name;
	double P;
	double Q;
	String equipmentContainer_ID;
	String baseVoltage_rdf_ID;
	
	public EnergyConsumer(String name1,String name2,double name3,double name4,String name5)
	{
		rdf_ID=name1;
		name=name2;
		P=name3;
		Q=name4;
		equipmentContainer_ID=name5;
		
	}
	
	public void findBaseVoltage(ArrayList<VoltageLevel> ListVoltageLevel) {
		
		for(VoltageLevel s : ListVoltageLevel) {
			if( Objects.equals( equipmentContainer_ID.substring(1), s.rdf_ID) ){
				this.baseVoltage_rdf_ID= s.baseVoltage_rdf_ID;
			}
		}
	}
	
//	public double getAdmittance(ArrayList<VoltageLevel> ListVoltageLevel) {
//		
//		double n=0;
//		double y=0;
//		double x= Math.sqrt( Math.pow((this.P*Math.pow(10,6)),2) + Math.pow((this.Q*Math.pow(10,6)), 2) );
//		
//		for(VoltageLevel s : ListVoltageLevel) {
//			if( Objects.equals( equipmentContainer_ID.substring(1), s.rdf_ID) ){
//				n= s.name*Math.pow(10,3);
//			}
//		}
//		
//		y= 1/(Math.pow(n,2)/x);
//		
//		return y;
//	}
	
	public void show_data()
	{
		System.out.println("Energy Consumer\n" +" rdf:ID " + rdf_ID +"\n name " + name + "\n Active Power "+ P + "\n Reactive Power "+ Q + "\n baseVoltage:rdf:ID " + baseVoltage_rdf_ID);
	}
			
	
	
}
