import java.util.Objects;

public class YMatrix {

	Complex[][] Y;
	
	public YMatrix(BusToBranch_Topology BusBranch) {
		
		int dim = BusBranch.bus.size();
		this.Y =  new Complex[dim][dim];
		
		//Initialize Matrix
		for(int i = 0; i < dim; i++) {
			for(int j = 0; j < dim ; j++) {
				this.Y[i][j] = new Complex(0,0);
			}
		}
		
	}
	
	public void BuildYMatrix(BusToBranch_Topology BusBranch, GridData sys) {
		
		checkBreakers(BusBranch);
		this.fillBusAdmittances(BusBranch);
		this.fillBranchAdmittances(BusBranch,sys);
			
	}
	
	public void checkBreakers(BusToBranch_Topology BusBranch) {
		//Search for open breaker and then remove branch in case there is one open
		for(int i = 0; i < BusBranch.branch.size(); i++) {
			for(Breaker breaker : BusBranch.branch.get(i).breakers){
				if(Objects.equals(breaker.state, "true")) {
					//Then the breaker is open and the branch must be removed
					System.out.println("REMOVING A BRANCH BECAUSE THE BREAKER IS OPEN");
					BusBranch.branch.remove(i);
				}
				
			}
		}
		
		
	}
	
	public void fillBusAdmittances(BusToBranch_Topology BusBranch) {
		
		System.out.println("\nCalculating Admittance at Buses: ");
		for(int i = 0 ; i < BusBranch.bus.size() ; i++) {
			Complex y = new Complex(0,0);
			Complex y1 = new Complex(0,0);
			System.out.print("  "+ BusBranch.bus.get(i).Bus.name+ " -");
			//Search for Load
			if(BusBranch.bus.get(i).Load != null) {
				y.calcLoadAdmittance(BusBranch.bus.get(i).Load, BusBranch.bus.get(i).Voltage.name);
				System.out.print(" load "+ BusBranch.bus.get(i).Load.name + " : " + y.re+"+j"+y.im+ "(S)");
			}
			//Search for Linear Shunt
			if(BusBranch.bus.get(i).Shunt != null) {
				y1.calcShuntAdmittance(BusBranch.bus.get(i).Shunt);
				System.out.print(" load(and/or)shunt "+ BusBranch.bus.get(i).Shunt.name + " : " + y.re+"+j"+y.im+ "(S)");
				y.add(y1);
			}
			
			this.Y[i][i] = y ;
			System.out.println("");
		}
		System.out.println("");
		
	}
	
	public void fillBranchAdmittances(BusToBranch_Topology BusBranch,GridData sys) {
		
		//This complex number is going to be negative due to being non-diagonal elements of the Ymatrix
		int[] indexes = {0 , 0};
		
		System.out.println("\nCalculating Admittance at Branchs: ");
		for(Branch_Topology branch :BusBranch.branch) {
			Complex y = new Complex(0,0);
			indexes = indexOfBusesConnectedToBranch(BusBranch, branch);
			y.calcBranchAdmittance(branch,sys);
			this.Y[indexes[0]][indexes[1]] = y;
			this.Y[indexes[1]][indexes[0]] = y;
			System.out.println(" w/ admittance valued "+y.re+"+j"+y.im);
			//Add to diagonal elements also
			y.addLineShunts_Exception(branch,sys);
			y.addTrans_Exception(branch,sys);
			this.Y[indexes[0]][indexes[0]].add(new Complex(-y.re,-y.im));
			this.Y[indexes[1]][indexes[1]].add(new Complex(-y.re,-y.im));
			
		}
		System.out.println("");
	}
	
	public int[] indexOfBusesConnectedToBranch(BusToBranch_Topology BusBranch, Branch_Topology branch) {
		int[] buses_index = new int[2] ;
		
		for(int j = 0 ; j < branch.busbars.size() ; j++) {
			for(int i = 0 ; i < BusBranch.bus.size() ; i++) {
				if(Objects.equals(branch.busbars.get(j).Bus.name, BusBranch.bus.get(i).Bus.name)) {
					buses_index[j] = i;
				}
			}
		}
		System.out.print("  Branch connecting Buses "+buses_index[0]+" and "+buses_index[1]);
		return buses_index;
	}
	
}
