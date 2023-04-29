package pc2t_projekt;
import java.util.*;

public class Animovany extends Film {
	public Animovany(String nazev, Reziser reziser, int rok, int vekDivaka) {
		this.nazev=nazev;
		this.reziser=reziser;
		this.rok=rok;
		this.setVekDivaka(vekDivaka);
		seznamAnimatoru = new ArrayList<>();
		hodnoceni = new ArrayList<>();
	}
	
	public int getVekDivaka() {
		return vekDivaka;
	}

	public void setVekDivaka(int vekDivaka) {
		this.vekDivaka = vekDivaka;
	}

	public List<Animator> getSeznamAnimatoru() {
		return seznamAnimatoru;
	}

	public void addAnimator(Animator animator) {
		seznamAnimatoru.add(animator);
	}
	
	public void removeAnimator(Animator animator) {
		seznamAnimatoru.remove(animator);
	}
	
	public final float maxHodnoceni = 10;
	private int vekDivaka;
	private List<Animator> seznamAnimatoru;
}
