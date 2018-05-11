import java.util.Objects;

public class Complex {

	double re;
	double im;
	
	public Complex(double re, double im) {
		
		this.re =re;
		this.im=im;
	}
	
	public void add(Complex a){
		
		this.re=a.re+this.re;
		this.im=a.im+this.im;
		
	}
	
	public Complex conjugate() {
		
		Complex a = new Complex(this.re,-(this.im));
		return a;
	}
	
	public Complex divide(Complex b) {
		
		Complex a = this;
		double x = (a.re*b.re+b.im*a.im)/(Math.pow(b.re,2)+Math.pow(b.im, 2));
		double y = ( a.im*b.re-a.re*b.im  )/(Math.pow(b.re,2)+Math.pow(b.im, 2));
		
		Complex z=new Complex(x,y);
		
		return z;
	}
	
	public void calcLoadAdmittance(EnergyConsumer Load, double Voltage) {
		
		Complex s_conj = new Complex(Load.P,-Load.Q);
		Complex volt = new Complex(Math.pow(Voltage,2),0);
		
		Complex y = s_conj.divide(volt);
		
		this.re =y.re;
		this.im =y.im;
		
	}
	
	public void calcShuntAdmittance(LinearShunt shunt) {
		
		Complex y = new Complex(shunt.g*shunt.n_section,shunt.b*shunt.n_section);
		
		this.re = this.re +y.re;
		this.im = this.re +y.im;
		
	}
	
	public void calcLineAdmittance(ACLine line) {
		
		Complex z = new Complex(line.line_r,line.line_x);
		Complex y = new Complex(0,0);
		
		y.add(new Complex(1,0).divide(z));
		
		this.re = y.re;
		this.im = y.im;
		
	}
	
	
	public void calcTransAdmittance(PowerTransformer transformer, GridData sys) {
		
		Complex z = new Complex(0,0);
		
		for(PowerTransformerEnd pte : sys.ListPowerTransformerEnd) {
			if(Objects.equals(pte.Transformer_ID.substring(1), transformer.rdf_ID)) {
				z.add(new Complex(pte.Transformer_r,pte.Transformer_x));
			}
		}
		
		Complex y = new Complex(1,0).divide(z);
		
		this.re = y.re;
		this.im = y.im;
		
	}
	
	public void calcBranchAdmittance(Branch_Topology branch, GridData sys) {
		
		Complex y = new Complex(0,0);
		
		//Check if is has a power transformer
		if(branch.power_transformer != null) {
			//find power transformer end in sys
			//calculate the admittance
			y.calcTransAdmittance(branch.power_transformer,sys);
			
		}
		
		//Check if it has a line
		if(branch.line != null){
			//simply calculate the admittance
			y.calcLineAdmittance(branch.line);
		}
		
		//returns negative value due to being non-diagonal components of the matrix
		this.re = this.re -y.re;
		this.im = this.im -y.im;
		
	}
	
	public void addLineShunts_Exception(Branch_Topology branch, GridData sys) {
		
		Complex y = new Complex(0,0);
		//Check if it has a line
		if(branch.line != null){
			//simply calculate the admittance
			y.add(new Complex (branch.line.line_g,branch.line.line_b));
		}
		
		this.re = this.re -y.re;
		this.im = this.im -y.im;
		
	}
	
	
	public void addTrans_Exception(Branch_Topology branch, GridData sys) {
		
		Complex y = new Complex(0,0);
		//Check if it has a line
		if(branch.power_transformer != null){
			//simply calculate the admittance
			
			for(PowerTransformerEnd pte : sys.ListPowerTransformerEnd) {
				if(Objects.equals(pte.Transformer_ID.substring(1), branch.power_transformer.rdf_ID)) {
					y.add(new Complex(pte.Transformer_g,pte.Transformer_b));
				}
			}
			
		}
		
		this.re = this.re -y.re;
		this.im = this.im -y.im;
		
	}
	
	

}
