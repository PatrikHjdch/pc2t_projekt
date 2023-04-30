package pc2t_projekt;
import java.util.*;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.Serializable;

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
	
	public static float pouzeFloat(Scanner sc) 
	{
		float cislo = 0;
		try
		{
			cislo = sc.nextFloat();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim realne cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}
	
	public static int findFilm(List<Film> databaze, String nazev) {
		for(int i=0; i<databaze.size();i++) {
			if (nazev.equals(databaze.get(i).getNazev())) {
				return i;
			}
		}
		return -1;
		
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
		databazeFilmu.add(new Hrany("Nakameru", new Reziser("Rejze"),65));
		databazeFilmu.add(new Hrany("nahrany", new Reziser("Rejze Jr."),654));
		((Hrany) databazeFilmu.get(2)).addHerec(new Herec("Herec1"));
		((Hrany) databazeFilmu.get(2)).addHerec(new Herec("Herec2"));
		((Hrany) databazeFilmu.get(2)).addHerec(new Herec("Herec4"));
		((Hrany) databazeFilmu.get(3)).addHerec(new Herec("Herec2"));
		((Hrany) databazeFilmu.get(3)).addHerec(new Herec("Herec4"));
		((Hrany) databazeFilmu.get(3)).addHerec(new Herec("Herec5"));
		
		
		Scanner sc=new Scanner(System.in);
		int volba;
		boolean run = true;
		
		while(run)
		{
			System.out.println("-------------\nVyberte pozadovanou cinnost:\n");
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
				String meno;
				switch(typ) {
					case 1:
						databazeFilmu.add(new Hrany(jmeno, reziser, rok));
						System.out.println("Seznam hercu (\"0\" pro dokonceni):");
						sc.nextLine();
						meno = sc.nextLine();
						while(!meno.equals("0")) {
							((Hrany) databazeFilmu.get(databazeFilmu.size()-1)).addHerec(new Herec(meno));
							meno = sc.nextLine();
						}
						break;
					case 2:
						System.out.println("Doporuceny vek divaka:");
						int vekDivaka = pouzeCelaCisla(sc);
						databazeFilmu.add(new Animovany(jmeno, reziser, rok, vekDivaka));

						System.out.println("Seznam animatoru (\"0\" pro dokonceni):");
						sc.nextLine();
						meno = sc.nextLine();
						while(!meno.equals("0")) {
							((Animovany) databazeFilmu.get(databazeFilmu.size()-1)).addAnimator(new Animator(meno));
							meno = sc.nextLine();
						}
						break;
				}
				System.out.println("Film uspesne ulozen!\n");
				break;
			case 2:
				System.out.println("Nazev filmu pro upraveni:");
				sc.nextLine();
				String jmenoUpraveni = sc.nextLine();
				for(int i=0; i<databazeFilmu.size();i++) {
					if (jmenoUpraveni.equals(databazeFilmu.get(i).getNazev())) {
						System.out.println("1 .. Upravit nazev");
						System.out.println("2 .. Upravit rezisera");
						System.out.println("3 .. Upravit rok vydani");
						System.out.println("4 .. Upravit seznam hercu/animatoru");
						System.out.println("5 .. Upravit doporuceny vek divaka");
						int typ2 =(pouzeCelaCisla(sc));
						switch(typ2) {
						case 1:
							sc.nextLine();
							System.out.println("Novy nazev: ");
							databazeFilmu.get(i).setNazev(sc.nextLine());
							System.out.println("Nazev zmenen");
							break;
						case 2: 
							sc.nextLine();
							System.out.println("Novy reziser: ");
							databazeFilmu.get(i).setReziser(new Reziser (sc.nextLine()));
							System.out.println("Reziser zmenen");
							break;
						case 3:
							sc.nextLine();
							System.out.println("Novy nazev: ");
							databazeFilmu.get(i).setRok(pouzeCelaCisla(sc));
							System.out.println("Nazev zmenen");
							break;
						case 4:
							String pridani;
							if(databazeFilmu.get(i).getClass().getName()=="pc2t_projekt.Animovany") 
							{
								System.out.println("p .. Pridani animatoru\no .. Odebrani animatoru");
								sc.nextLine();
								pridani = sc.nextLine();
								if (pridani.equals("p"))
								{
									System.out.println("Pridavani animatoru (\"0\" pro dokonceni):");
									meno = sc.next();
									while(!meno.equals("0")) {
										((Animovany) databazeFilmu.get(i)).addAnimator(new Animator(meno));
										meno = sc.next();
									}
								}
								else {
									System.out.println("Mazani animatoru (\"0\" pro dokonceni):");
									meno = sc.nextLine();
									while(!meno.equals("0")) 
									{
										for (int j=0;j<((Animovany) databazeFilmu.get(i)).getSeznamAnimatoru().size();j++)
										if (meno.equals(((Animovany) databazeFilmu.get(i)).getSeznamAnimatoru().get(j).getJmeno())) 
										{
											System.out.print("Animator "+((Animovany) databazeFilmu.get(i)).getSeznamAnimatoru().get(j).getJmeno()+" byl vymazan.\n");
											((Animovany) databazeFilmu.get(i)).removeAnimator(((Animovany) databazeFilmu.get(i)).getSeznamAnimatoru().get(j));
										}
										meno = sc.nextLine();
									}
								}
								
								
							} 
							else 
							{
								System.out.println("p .. Pridani hercu\no .. Odebrani hercu");
								sc.nextLine();
								pridani = sc.nextLine();
								if (pridani.equals("p"))
								{
									System.out.println("Pridavani hercu (\"0\" pro dokonceni):");
									meno=sc.nextLine();
									while(!meno.equals("0")) 
									{
										((Hrany) databazeFilmu.get(i)).addHerec(new Herec(meno));
										meno = sc.nextLine();
									}
								}
								else {
									System.out.println("Mazani hercu (\"0\" pro dokonceni):");
									meno = sc.nextLine();
									while(!meno.equals("0")) 
									{
										for (int j=0;j<((Hrany) databazeFilmu.get(i)).getSeznamHercu().size();j++)
										if (meno.equals(((Hrany) databazeFilmu.get(i)).getSeznamHercu().get(j).getJmeno())) 
										{
											System.out.print("Herec "+((Hrany) databazeFilmu.get(i)).getSeznamHercu().get(j).getJmeno()+" byl vymazan.\n");
											((Hrany) databazeFilmu.get(i)).removeHerec(((Hrany) databazeFilmu.get(i)).getSeznamHercu().get(j));
										}
										meno = sc.nextLine();
									}
								}
								
							}
							break;
						case 5:
							try {
							sc.nextLine();
							System.out.println("Novy doporuceny vek divaka: ");
							((Animovany) databazeFilmu.get(i)).setVekDivaka(pouzeCelaCisla(sc));
							System.out.println("Vek zmnenen");														
							break;
							}
							catch (Exception e){
								System.out.println("Film neni animovany.");
							}
						}
					}
				}
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
				System.out.println("Nazev filmu:");
				sc.nextLine();
				String nazev = sc.nextLine();
				int ind = findFilm(databazeFilmu, nazev);
				if (ind==-1) {
					System.out.println("Film nenalezen.");
					break;
				}
				
				float maxHodnoceni = 5;
				if (databazeFilmu.get(ind).getClass().getName()=="pc2t_projekt.Animovany") {maxHodnoceni = 10;}
				System.out.println("Skore (max "+maxHodnoceni+"):");
				float skore = pouzeFloat(sc);
				while (skore>maxHodnoceni || skore<1) {
					System.out.println("Hodnoceni je mimo povoleny rozsah");
					skore=pouzeFloat(sc);
				}
				sc.nextLine();
				System.out.println("Slovni hodnoceni (nepovinne):");
				String hodnoceni = sc.nextLine();
				databazeFilmu.get(ind).addHodnoceni(new Hodnoceni(skore, hodnoceni));
				
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
				System.out.println("Nazev filmu:");
				sc.nextLine();
				String nazev1 = sc.nextLine();
				int ind1 = findFilm(databazeFilmu, nazev1);
				if (ind1==-1) {
					System.out.println("Film nenalezen.");
					break;
				}
				System.out.println("\nNazev: "+databazeFilmu.get(ind1).getNazev()+"\nReziser: "+databazeFilmu.get(ind1).getReziser().getJmeno()+"\nRok: "+databazeFilmu.get(ind1).getRok());
				if(databazeFilmu.get(ind1).getClass().getName()=="pc2t_projekt.Animovany") {
					System.out.println("Doporuceny vek divaka: "+((Animovany) databazeFilmu.get(ind1)).getVekDivaka()+"\nSeznam animatoru:");
					for(int j=0; j < ((Animovany) databazeFilmu.get(ind1)).getSeznamAnimatoru().size();j++) {
						System.out.println(((Animovany) databazeFilmu.get(ind1)).getSeznamAnimatoru().get(j).getJmeno());
					}
				} else {
					System.out.println("Seznam hercu:");
					for(int j=0; j < ((Hrany) databazeFilmu.get(ind1)).getSeznamHercu().size();j++) {			
						System.out.println(((Hrany) databazeFilmu.get(ind1)).getSeznamHercu().get(j).getJmeno());
					}
				}
				Collections.sort(databazeFilmu.get(ind1).getHodnoceni());
				System.out.println("Hodnoceni:");
				for (int i=0; i<databazeFilmu.get(ind1).getHodnoceni().size();i++) {
					System.out.println(databazeFilmu.get(ind1).getHodnoceni().get(i).getSkore()+" "+databazeFilmu.get(ind1).getHodnoceni().get(i).getSlovni());
				}
				break;
			case 7:
				Map<String, List<String>> mapaLidi = new HashMap<>();
				for (Film film : databazeFilmu) {
					if (film.getClass().getName()=="pc2t_projekt.Hrany") {		
						for (Herec herec : ((Hrany) film).getSeznamHercu()){
							List <String> seznamFilmu = mapaLidi.getOrDefault(herec.getJmeno(), new ArrayList<>());
							seznamFilmu.add(film.getNazev());
							mapaLidi.put(herec.getJmeno(), seznamFilmu);
						}
															
						}
					else {
						for (Animator animator : ((Animovany) film).getSeznamAnimatoru()) {
							List <String> seznamFilmu = mapaLidi.getOrDefault(animator.getJmeno(), new ArrayList<>());
							seznamFilmu.add(film.getNazev());
							mapaLidi.put(animator.getJmeno(), seznamFilmu);
						}
					}
				}
				for (Map.Entry<String, List<String>> clovek : mapaLidi.entrySet()) {
					if (clovek.getValue().size()>1) {
						System.out.println(clovek.getKey()+"\n"+clovek.getValue());
					}
				}
				break;
			case 8:
				System.out.println("Jmeno herce/animatora:");
				sc.nextLine();
				String herec=sc.nextLine();
				System.out.println("Filmy:");
				for (int i=0;i<databazeFilmu.size();i++) {
					if(databazeFilmu.get(i).getClass().getName()=="pc2t_projekt.Animovany") 
					{
						for (int j=0;j< ((Animovany) databazeFilmu.get(i)).getSeznamAnimatoru().size();j++) 
						{
							if (herec.equals(((Animovany) databazeFilmu.get(i)).getSeznamAnimatoru().get(j).getJmeno())) 
							{
								System.out.println(((Animovany) databazeFilmu.get(i)).getNazev());
							}
						}
					}
					else {
						for (int j=0;j<((Hrany) databazeFilmu.get(i)).getSeznamHercu().size();j++) 
						{
								if (herec.equals(((Hrany) databazeFilmu.get(i)).getSeznamHercu().get(j).getJmeno())) 
								{
									System.out.println(((Hrany) databazeFilmu.get(i)).getNazev());
								}
						}
						
						}
					
				}
				break;
			case 9:
				System.out.println("Nazev filmu:");
				sc.nextLine();
				String nazev2 = sc.nextLine();
				int ind2 = findFilm(databazeFilmu, nazev2);
				if (ind2==-1) {
					System.out.println("Film nenalezen.");
					break;
				}
				try {
				      FileOutputStream fileOut = new FileOutputStream("film.ser");
				      ObjectOutputStream out = new ObjectOutputStream(fileOut);
				      out.writeObject(databazeFilmu.get(ind2));
				      out.close();
				      fileOut.close();
				      System.out.println("Film data is saved in film.ser file");
				    } catch (Exception e) {
				      e.printStackTrace();
				      }
				break;
			case 10:
			    try {
			        FileInputStream fileIn = new FileInputStream("film.ser");
			        ObjectInputStream in = new ObjectInputStream(fileIn);
			        Film film = (Film) in.readObject();
			        databazeFilmu.add(film);
			        in.close();
			        fileIn.close();
			        
			        System.out.println("Film je nacten z film.ser souboru:");

			      } catch (Exception e) {
			        e.printStackTrace();
			        }
				
				break;	
			case 11:
				run = false;
				
				break;
				
		}
		if(run) {
			 System.out.println("\n\nZmacknete ENTER pro pokracovani");
			sc.nextLine();
			//sc.nextLine();
			}
		}
	}

}
