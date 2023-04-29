package pc2t_projekt;
import java.util.*;

public class Main {
	public static int pouzeCelaCisla(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}
	
	public static void main(String[] args) {
		
		List<Film> databazeFilmu = new ArrayList<>();
		
		databazeFilmu.add(new Hrany("Hrany film", new Reziser("Hrany reziser"), 2023));
		((Hrany) databazeFilmu.get(0)).addHerec(new Herec("Herec1"));
		((Hrany) databazeFilmu.get(0)).addHerec(new Herec("Herec2"));
		((Hrany) databazeFilmu.get(0)).addHerec(new Herec("Herec3"));
		databazeFilmu.add(new Animovany("Animak", new Reziser("AnimoReziser"), 2022, 9));
		((Animovany) databazeFilmu.get(1)).addAnimator(new Animator("Animator1"));
		((Animovany) databazeFilmu.get(1)).addAnimator(new Animator("Animator2"));
		((Animovany) databazeFilmu.get(1)).addAnimator(new Animator("Animator3"));
		
		Scanner sc=new Scanner(System.in);
		int volba;
		boolean run = true;
		
		while(run)
		{
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1 .. Pridani noveho filmu");
			System.out.println("2 .. Upraveni filmu");
			System.out.println("3 .. Smazani filmu");
			System.out.println("4 .. Pridani hodnoceni filmu ");
			System.out.println("5 .. Vypis filmu ");
			System.out.println("6 .. Vyhledani filmu");
			System.out.println("7 .. Vypis lidi kteri se podileli na vice nez jednom filmu");
			System.out.println("8 .. Vypis vsech filmu s konkretnim hercem");
			System.out.println("9 .. Ulozeni filmu ... do souboru");
			System.out.println("10 .. Nacteni filmu ... ze souboru");
			System.out.println("11 .. Ukonceni a ulozeni");
		volba=pouzeCelaCisla(sc);
		switch(volba)
		{
			case 1:
				System.out.println("Hrany nebo animovany?\n1...Hrany\n2...Animovany");
				int typ=(pouzeCelaCisla(sc));
				sc.nextLine();
				
				System.out.println("Nazev:");
				String jmeno = sc.nextLine();
				
				System.out.println("Reziser:");
				Reziser reziser = new Reziser(sc.next());
				
				System.out.println("Rok vydani:");
				int rok =  pouzeCelaCisla(sc);
				
				switch(typ) {
					case 1:
						databazeFilmu.add(new Hrany(jmeno, reziser, rok));
						
						System.out.println("Seznam hercu (\"0\" pro dokonceni):");
						while(sc.nextInt()!=0) {
							((Hrany) databazeFilmu.get(databazeFilmu.size()-1)).addHerec(new Herec(sc.nextLine()));
						}
						break;
					case 2:
						System.out.println("Doporuceny vek divaka:");
						int vekDivaka = pouzeCelaCisla(sc);
						databazeFilmu.add(new Animovany(jmeno, reziser, rok, vekDivaka));

						System.out.println("Seznam animatoru (\"0\" pro dokonceni):");
						while(sc.nextInt()!=0) {
							((Animovany) databazeFilmu.get(databazeFilmu.size()-1)).addAnimator(new Animator(sc.nextLine()));
						}
						break;
				}
				System.out.println("Film uspesne ulozen!\n");
				break;
			case 2:
				break;
			case 3:
				
				System.out.println("Nazev filmu pro smazani:");
				sc.nextLine();
				String jmenoSmazani = sc.nextLine();
				for(int i=0; i<databazeFilmu.size();i++) {
					if (jmenoSmazani.equals(databazeFilmu.get(i).getNazev())) {
						databazeFilmu.remove(i);
						System.out.print("Film byl vymazan.\n");
					}
				}
				break;
			case 4:
				break;
			case 5:
				for(int i=0; i<databazeFilmu.size();i++) {
					System.out.println("\nNazev: "+databazeFilmu.get(i).getNazev()+"\nReziser: "+databazeFilmu.get(i).getReziser().getJmeno()+"\nRok: "+databazeFilmu.get(i).getRok());
					if(databazeFilmu.get(i).getClass().getName()=="pc2t_projekt.Animovany") {
						System.out.println("Doporuceny vek divaka: "+((Animovany) databazeFilmu.get(i)).getVekDivaka()+"\nSeznam animatoru:");
						for(int j=0; j < ((Animovany) databazeFilmu.get(i)).getSeznamAnimatoru().size();j++) {
							System.out.println(((Animovany) databazeFilmu.get(i)).getSeznamAnimatoru().get(j).getJmeno());
						}
					} else {
						System.out.println("Seznam hercu:");
						for(int j=0; j < ((Hrany) databazeFilmu.get(i)).getSeznamHercu().size();j++) {			
							System.out.println(((Hrany) databazeFilmu.get(i)).getSeznamHercu().get(j).getJmeno());
						}
					}
				}
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				break;	
			case 11:
				run = false;
				break;
				
		}		
		}
	}

}
