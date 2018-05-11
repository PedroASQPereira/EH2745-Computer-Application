
public class LinearShunt {
	
	String rdf_ID;
	String name;
	double n_section;
	double b;
	double g;
	String equipmentContainer;
	
	public LinearShunt(String rdf_ID,String name,double n_section,double b,double g,String equipmentContainer) {
		this.rdf_ID=rdf_ID;
		this.name =name;
		this.n_section = n_section;
		this.b =b;
		this.g=g;
		this.equipmentContainer =equipmentContainer;
	}
}
