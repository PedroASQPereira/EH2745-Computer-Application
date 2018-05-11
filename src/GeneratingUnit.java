
public class GeneratingUnit {

	String rdf_ID;
	String name;
	String max_P;
	String min_P;
	String equipmentContainer_ID;
	
	public GeneratingUnit(String name1,String name2,String name3,String name4, String name5)
	{
		rdf_ID=name1;
		name=name2;
		max_P=name3;
		min_P = name4;
		equipmentContainer_ID=name5;
	}
	
	public void show_data()
	{
		System.out.println("Generating Unit:" +" rdf:ID " + rdf_ID +"\n name " + name + "\n Max P "+ max_P + "\n Min P " + min_P + "\n EquipmentContainer " + equipmentContainer_ID);
	}
		
	
}
