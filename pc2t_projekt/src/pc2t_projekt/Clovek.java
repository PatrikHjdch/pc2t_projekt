package pc2t_projekt;

import java.io.Serializable;

abstract public class Clovek implements Serializable{
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
