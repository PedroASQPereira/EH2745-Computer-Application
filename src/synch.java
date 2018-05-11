import java.util.ArrayList;
import java.util.Objects;

public class synch {
	
	String rdfID;
	String name;
	String ratedS;
	double P;
	double Q;
	String genUnit_rdf;
	String regControl_rdf;
	String equipmentcontainer_rdf;
	String baseVoltage_rdf;
	
	public synch (String name2,String rdf,String gen_rdf,String regCont_rdf, String equipmentcont_rdf, String rated, double p, double q){
		name=name2;
		rdfID=rdf;
		genUnit_rdf=gen_rdf;
		regControl_rdf=regCont_rdf;
		equipmentcontainer_rdf=equipmentcont_rdf;
		ratedS=rated;
		P=p;
		Q=q;

	}
	
	public void findBaseVoltage(ArrayList<VoltageLevel> ListVoltageLevel) {
		
		for(VoltageLevel s : ListVoltageLevel) {
			if( Objects.equals( equipmentcontainer_rdf.substring(1), s.rdf_ID) ){
				this.baseVoltage_rdf= s.baseVoltage_rdf_ID;
			}
		}
		
	}
		public void show_data(){
			System.out.println("SynchronousMachine" + "\n rdf_ID" + rdfID + "\n" +name + "\n" +genUnit_rdf + "\n" +regControl_rdf + "\n" +equipmentcontainer_rdf + "\n" +baseVoltage_rdf + "\n" +P +"\n" + Q );
		}

}
