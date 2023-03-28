package pc2t_projekt;

public class Animovany extends Film {
	public Animovany(String a, String b, int c, float d, int e) {
		this.nazev=a;
		this.reziser=b;
		this.rok=c;
		this.hodnoceni=d;
		this.setVekDivaka(e);
	}
	
	public int getVekDivaka() {
		return vekDivaka;
	}

	public void setVekDivaka(int vekDivaka) {
		this.vekDivaka = vekDivaka;
	}

	private int vekDivaka;
}
