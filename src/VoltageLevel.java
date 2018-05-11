
public class VoltageLevel {

		String rdf_ID;
		double name;
		String substation_rdf_ID;
		String baseVoltage_rdf_ID;
		
		public VoltageLevel(String name1,double name2,String name3,String name4)
		{
			rdf_ID=name1;
			name=name2;
			substation_rdf_ID=name3;
			baseVoltage_rdf_ID=name4;
		}
		
		public void show_data()
		{
			System.out.println("Voltage Level\n" +" rdf:ID " + rdf_ID +"\n name " + name + "\n substation:rdf:ID "+ substation_rdf_ID + "\n baseVoltage:rdf:ID " + baseVoltage_rdf_ID);
		}
				
	
}
