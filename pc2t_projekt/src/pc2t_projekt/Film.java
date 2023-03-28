package pc2t_projekt;

abstract public class Film {
	
	public String getNazev() {
		return nazev;
	}
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public String getReziser() {
		return reziser;
	}

	public void setReziser(String reziser) {
		this.reziser = reziser;
	}

	public int getRok() {
		return rok;
	}

	public void setRok(int rok) {
		this.rok = rok;
	}

	public float getHodnoceni() {
		return hodnoceni;
	}
	public void setHodnoceni(float hodnoceni) {
		this.hodnoceni = hodnoceni;
	}

	protected String nazev;
	protected String reziser;
	protected int rok;
	protected float hodnoceni;
}
