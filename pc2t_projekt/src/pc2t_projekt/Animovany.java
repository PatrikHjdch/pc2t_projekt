package pc2t_projekt;

public class Animovany extends Film {
	public Animovany(String nazev, String reziser, int rok, float hodnoceni, int vekDivaka) {
		this.nazev=nazev;
		this.reziser=reziser;
		this.rok=rok;
		this.hodnoceni=hodnoceni;
		this.setVekDivaka(vekDivaka);
	}
	
	public int getVekDivaka() {
		return vekDivaka;
	}

	public void setVekDivaka(int vekDivaka) {
		this.vekDivaka = vekDivaka;
	}

	private int vekDivaka;
}
