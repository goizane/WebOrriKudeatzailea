package praktika1;

public class GakoHitz {
	
	// ATRIBUTUAK
	private String hitza;
	
	// ERAIKITZAILEA
	public GakoHitz(String hitza) {
		this.hitza = hitza;
	}
	
	// METODOAK
	public String getHitza(){
		//aurre:
		//post: gako hitzaren izena bueltatuko du
		return this.hitza;
	}
}