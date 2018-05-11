
public class regControl {
	
	String rdfID;
	String name;
	double targetValue;
	
	public regControl (String name2,String rdf,double targVal){
		name=name2;
		rdfID=rdf;
		targetValue=targVal;
	}	
		public void show_data(){
			System.out.println(rdfID + name + targetValue);
		}
				
	

}
