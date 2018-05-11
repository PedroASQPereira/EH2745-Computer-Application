

public class Run_File {

	public static void main(String[] args) {	
	
		//Initialize all required data
		GridData sys = new GridData();	
		File_Specifications file_specs = new File_Specifications();
		
		//Parse data to its adequate structures
		new Parsing(sys,file_specs);
		
		//Structure all information into BusBranch_Topology
		BusToBranch_Topology BusBranch = new BusToBranch_Topology();
		BusBranch.addAllBus(BusBranch.bus, sys);
		BusBranch.addAllBranch(BusBranch.branch,BusBranch.bus, sys);
		
		//Build Y Matrix using BusBranch_Topology
		YMatrix Ymatrix = new YMatrix(BusBranch);
		Ymatrix.BuildYMatrix(BusBranch,sys);
		
		
//CHECK BRANCH
		int x=0;
		System.out.println("BRANCH LIST:");
		for(Branch_Topology ch : BusBranch.branch)
		{
			x++;
			System.out.print(" Branch " + x );
			if(ch.line != null) {
				System.out.print(" has line " + ch.line.name);
			}
			if(ch.power_transformer!= null) {
				System.out.print(" and power transformer " + ch.power_transformer.name );
			}
			for(Bus_Topology bus : ch.busbars){
				System.out.print(" and bus " + bus.Bus.name );
			}	
			for(Breaker br : ch.breakers) {
				System.out.print(" and breakers " + br.name );
			}
			System.out.println("");;
		}

		
//CHECK BUS
		System.out.print("\nBUS LIST\n");
		for(Bus_Topology b : BusBranch.bus ) {
			System.out.print(" Bus " + b.Bus.name );
			if(b.Load != null) {
				System.out.print(" has load " + b.Load.name );
			}
			if(b.Shunt!= null) {
				System.out.print(" and shunt " + b.Shunt.name );
			}
			if(b.SynchronousMachine!=null) {
				System.out.print(" and synchronous " + b.SynchronousMachine.name );
			}
			System.out.print(" and voltage " + b.Voltage.name+";\n");
		
		}
		
			
//CHECK Y MATRIX
//		System.out.print("\n YMATRIX:\n ");
//		for(int i = 0; i < Ymatrix.Y.length ; i++){
//			System.out.print("| ");
//			for(int j = 0; j < Ymatrix.Y[0].length;j++ ) {
//					System.out.print("                   "+Ymatrix.Y[j][i].re+"+j"+Ymatrix.Y[j][i].im);
//				
//			}
//			System.out.print("|\n ");
//		}
//		
		
		System.out.print("\n YMATRIX:\n ");
		for(int i = 0; i < Ymatrix.Y.length ; i++){
			
			for(int j = 0; j < Ymatrix.Y[0].length;j++ ) {
					System.out.print(" Index ["+j+"]["+i+"]="+ Ymatrix.Y[j][i].re+"+j"+Ymatrix.Y[j][i].im);
				
			}
			System.out.print("\n ");
		}
		
		
		
		
	}
	
	
	
}
