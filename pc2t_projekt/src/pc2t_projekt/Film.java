package pc2t_projekt;
import java.util.*;

abstract public class Film {
	
	public final float maxHodnoceni=-1;
	
	public String getNazev() {
		return nazev;
	}
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public Reziser getReziser() {
		return reziser;
	}

	public void setReziser(Reziser reziser) {
		this.reziser = reziser;
	}

	public int getRok() {
		return rok;
	}

	public void setRok(int rok) {
		this.rok = rok;
	}
	
	public List<Hodnoceni> getHodnoceni() {
			return hodnoceni;
		}
	
	public void addHodnoceni(Hodnoceni hodnoceni) {
		this.hodnoceni.add(hodnoceni);
	}
	
	protected List<Hodnoceni> hodnoceni;
	protected String nazev;
	protected Reziser reziser;
	protected int rok;
}
