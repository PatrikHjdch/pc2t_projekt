package pc2t_projekt;

abstract public class Film {
	
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

	
	protected String nazev;
	protected Reziser reziser;
	protected int rok;
}
