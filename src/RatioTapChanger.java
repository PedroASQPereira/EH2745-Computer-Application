
public class RatioTapChanger {

	String rdf_ID;
	String name;
	double step;
	
	public RatioTapChanger(String name1,String name2,double name3)
	{
		rdf_ID=name1;
		name=name2;
		step=name3;

	}
	
	public void show_data()
	{
		System.out.println("RatioTapChanger\n" +" rdf:ID " + rdf_ID +"\n name " + name + "\n Step "+ step );
	}
			
	
	
}
