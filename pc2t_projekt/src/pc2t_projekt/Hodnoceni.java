package pc2t_projekt;

import java.io.Serializable;

public class Hodnoceni implements Comparable<Hodnoceni> , Serializable{
	Hodnoceni(float num, String slovniHodnoceni){
		this.setSkore(num);
		this.setSlovni(slovniHodnoceni);
	}
	
	public Hodnoceni() {
	}

	public float getSkore() {
		return skore;
	}

	public void setSkore(float skore) {
		this.skore = skore;
	}

	public String getSlovni() {
		return slovni;
	}

	public void setSlovni(String slovni) {
		this.slovni = slovni;
	}

	private String slovni;
	private float skore;
	
	@Override
	public int compareTo(Hodnoceni obj) {
		if(this.skore>obj.skore) {
			return -1;
		}
		if (this.skore<obj.skore) {
			return 1;
		}
		return 0;
	}
}
