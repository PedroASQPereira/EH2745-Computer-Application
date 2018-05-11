
public class PowerTransformer {

	String rdf_ID;
	String name;
	String equipmentContainer_ID;
	
	public PowerTransformer(String name1,String name2,String name3)
	{
		rdf_ID=name1;
		name=name2;
		equipmentContainer_ID=name3;

	}
	
	public void show_data()
	{
		System.out.println("PowerTransformer\n" +" rdf:ID " + rdf_ID +"\n name " + name + "\n EquipmentContainer "+ equipmentContainer_ID );
	}
	
}
