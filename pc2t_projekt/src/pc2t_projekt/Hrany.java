package pc2t_projekt;

import java.util.*;

public class Hrany extends Film{
	public Hrany(String a, Reziser b, int c) {
		this.nazev=a;
		this.reziser=b;
		this.rok=c;
		seznamHercu = new ArrayList<>();
	}
	
	public int getHranyHodnoceni() {
		return hranyHodnoceni;
	}

	public void setHranyHodnoceni(int hranyHodnoceni) {
		this.hranyHodnoceni = hranyHodnoceni;
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

	private int hranyHodnoceni;
	private List<Herec> seznamHercu;
}
