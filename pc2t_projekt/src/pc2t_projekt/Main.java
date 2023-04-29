package pc2t_projekt;
import java.util.Scanner;

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
				System.out.println("1 .. Pridani noveho filmu");
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
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
