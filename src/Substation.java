
public class Substation {

	String rdf_ID;
	String name;
	String region_rdf_ID;
	
	public Substation(String name1,String name2,String name3)
	{
		rdf_ID=name1;
		name=name2;
		region_rdf_ID=name3;

	}
	
	public void show_data()
	{
		System.out.println("Substation\n" +" rdf:ID " + rdf_ID +"\n name " + name + "\n region:rdf:ID "+ region_rdf_ID );
	}
		
	
}
