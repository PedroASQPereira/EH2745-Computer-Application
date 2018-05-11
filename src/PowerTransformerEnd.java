
public class PowerTransformerEnd {

	String rdf_ID;
	String name;
	double Transformer_r;
	double Transformer_x;
	double Transformer_g;
	double Transformer_b;
	String Transformer_ID;
	String baseVoltage_rdf_ID;
	
	public PowerTransformerEnd(String name1,String name2,double name3,double name4,double name5, double name6, String name7, String name8)
	{
		rdf_ID=name1;
		name=name2;
		Transformer_r=name3;
		Transformer_x=name4;
		Transformer_g = name5;
		Transformer_b =name6;
		Transformer_ID=name7;
		baseVoltage_rdf_ID=name8;
	}
	
	public void show_data()
	{
		System.out.println("PowerTransformerEnd\n" +" rdf:ID " + rdf_ID +"\n name " + name + "\n Resistance "+ Transformer_r + "\n Reactance "+ Transformer_x + "\n Transformer_ID " + Transformer_ID + "\n baseVoltage:rdf:ID " + baseVoltage_rdf_ID);
	}
			
	
	
	
}
