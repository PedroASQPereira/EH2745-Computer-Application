
public class BaseVoltage {

		String rdf_ID;
		String name;

		
		public BaseVoltage(String name1,String name2)
		{
			rdf_ID=name1;
			name=name2;
		}
		
		public void show_data()
		{
			System.out.println("Base Voltage\n" +" rdf:ID " + rdf_ID +"\n name " + name);
		}
				
	
}