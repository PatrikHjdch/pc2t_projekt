package pc2t_projekt;

import java.io.Serializable;
import java.util.*;

public class Hrany extends Film implements Serializable{
	public Hrany(String a, Reziser b, int c) {
		this.nazev=a;
		this.reziser=b;
		this.rok=c;
		seznamHercu = new ArrayList<>();
		hodnoceni = new ArrayList<>();
	}
	
	public List<Herec> getSeznamHercu(){
		return seznamHercu;
	}
	
	public void addHerec(Herec herec) {
		seznamHercu.add(herec);
	}
	
	public void removeHerec(Herec herec) {
		seznamHercu.remove(herec);	
	}

	public final float maxHodnoceni = 5;
	private List<Herec> seznamHercu;
}
