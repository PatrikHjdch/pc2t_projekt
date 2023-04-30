package pc2t_projekt;

abstract public class Clovek {
	Clovek(String jmeno){
		this.jmeno=jmeno;
	}
	public Clovek() {
		
	}
	
	private String jmeno;
	
	public String getJmeno() {
		return jmeno;
	}
	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}
}
