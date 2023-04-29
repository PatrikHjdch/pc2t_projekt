package pc2t_projekt;

public class Hrany extends Film{
	public Hrany(String a, String b, int c, float d) {
		this.nazev=a;
		this.reziser=b;
		this.rok=c;
		this.hodnoceni=d;
	}
	
	public int getHranyHodnoceni() {
		return hranyHodnoceni;
	}

	public void setHranyHodnoceni(int hranyHodnoceni) {
		this.hranyHodnoceni = hranyHodnoceni;
	}

	private int hranyHodnoceni;
}
