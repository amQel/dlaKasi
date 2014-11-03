//Katarzyna SIcibek
//gr. 4

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


class Zapisownik{
	public Zapisownik(String ciag){
		try {
			PrintWriter out = new PrintWriter("zapis.txt");
			out.println(ciag);
			out.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}

class Pracownik {
	private static int id_Pracownik = 1;

	private static int liczbaPracownikow = 0;
	
    public static int getLiczbaPracownikow() {
		return liczbaPracownikow;
	}


	private int id;
	private String imie;
    private int pensja;
    private boolean urlop;
    
    public static Pracownik stworzPracownik(){
    	if(liczbaPracownikow<10){
    		return new Pracownik();
    	}
    	else{
    		System.out.println("Nie mozna i tyle");
    		return null;
    	}
    }
    
    public static Pracownik stworzPracownika(String nImie, int nPensja, boolean nUrlop){
    	if(liczbaPracownikow<10){
    		return new Pracownik(nImie,nPensja,nUrlop);
    	}
    	else{
    		System.out.println("Nie mozna i tyle");
    		return null;
    	}
    }
    
    Pracownik() {
    	liczbaPracownikow++;
    	this.imie = null;
    	this.pensja = 0;
    	this.urlop = false;
    	this.id = id_Pracownik++;
    }
    
    
	Pracownik(String nImie, int nPensja, boolean nUrlop)
    {
		liczbaPracownikow++;
		imie = nImie;
		pensja = nPensja;
		urlop=nUrlop;
		id = id_Pracownik++;
	}
	
	public Pracownik usunPrac(){
		liczbaPracownikow--;
		return null;
	}
	
	  public int getId() {
			return id;
		}


	public String getImie() {
		return imie;
	}


	public void setImie(String imie) {
		this.imie = imie;
	}


	public int getPensja() {
		return pensja;
	}


	public void setPensja(int pensja) {
		this.pensja = pensja;
	}


	public boolean isUrlop() {
		return urlop;
	}


	public void setUrlop(boolean urlop) {
		this.urlop = urlop;
	}
	  
}

public class Obslugak {
    
	static Pracownik[] lista = new Pracownik[100];
    static int i=0;
    

    static void wyswietlMenu(){
    	
        System.out.println("MENU");
    	System.out.println("[a] wyswietl wszystk"
    			+ "ich pracownikow");
        System.out.println("[b] dodaj nowego pracownika");
        System.out.println("[c] daj podwyzke");
        System.out.println("[d] przenies na urlop");
        System.out.println("\n[x] zakoncz program");
		System.out.print("\n");
    }
    
    
    public static void wyswietlPrac()
	{
		for(i=1; i<=Pracownik.getLiczbaPracownikow(); i++)
		{
		if(lista[i]!=null)System.out.println(lista[i].getId() + ". " + lista[i].getImie() + ": pensja = " + lista[i].getPensja() + "; urlop = " + lista[i].isUrlop());
		}
	}
    
    public static void dodajPrac()
    {
    	Scanner czytaj = new Scanner(System.in);
    	
    	String abc; int def; boolean ghi;
    	
    	System.out.println("Podaj imie i nazwisko pracownika");
    	abc = czytaj.nextLine();
    	System.out.println("Podaj pensje pracownika");
    	def = Integer.parseInt(czytaj.nextLine());
    	System.out.println("Czy pracownik jest na urlopie? (true/false)");
    	ghi = czytaj.nextBoolean();
    	lista[Pracownik.getLiczbaPracownikow() + 1] = Pracownik.stworzPracownika(abc, def, ghi);
    	
    }
    public static void usunPrac(){
    	wyswietlPrac();
    	System.out.println("Podaj id pracownika, ktorego chcesz usunac");
    	Scanner czyt  = new Scanner(System.in);
    	int idPrac = czyt.nextInt();
    	
    	lista[idPrac] = lista[idPrac].usunPrac();
    	
    	for (int i = idPrac; i < lista.length-1; i++) {
			lista[i] = lista[i+1];
		}
    }
    
    public static void podwyzka()
    {
    	int podwyzka;
    	Scanner czyt = new Scanner(System.in);
    	
    	System.out.println("O ile podwyzszyc pensje pracownikom?");
    	podwyzka = Integer.parseInt(czyt.nextLine());
    	
    	for(i=1; i<=Pracownik.getLiczbaPracownikow(); i++)
		{
    		lista[i].setPensja(lista[i].getPensja() + podwyzka);
		}	
    }
    
    public static String zapis(){
    	String wynik = "";
    	for(i=1; i<=Pracownik.getLiczbaPracownikow(); i++)
		{
    		wynik +=lista[i].getImie() + " " + lista[i].getPensja() + " " + lista[i].isUrlop() +" \n"; 
		}
    	return wynik;
    }
    
    
    public static void urlop()
    {
    	Scanner czyt = new Scanner(System.in);
    	int pozycja;
    	
    	wyswietlPrac();
    	System.out.println("Ktorego pracownika przeniesc na urlop?(Podaj nr)");
    	pozycja = czyt.nextInt();
    	lista[pozycja].setUrlop(true);
    }
    
    public static void czytaj(){
    	
    	try {
			Scanner we = new Scanner(new File("zapis.txt"));
			String imie = "";
			while(we.hasNext()){
			imie = we.next();
			imie += " " + we.next();
			int pensja = we.nextInt();
			boolean urlop = we.nextBoolean();
			
			lista[Pracownik.getLiczbaPracownikow() + 1] = Pracownik.stworzPracownika(imie, pensja, urlop);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    public static void main(String[] args) throws IOException{
    	char wybor = ' ';
    	Scanner czytnik = new Scanner(System.in);
    	
        lista[1]= Pracownik.stworzPracownika("Adam Kowalski", 1300, false);
        lista[2]= Pracownik.stworzPracownika("Marek Malinowski", 1500, false);
        lista[3]= Pracownik.stworzPracownika("Artur Nowak", 1700, false);

 	Pracownik[] dod;
        bd db = new bd(lista);
        dod = db.getLili();
System.out.println(dod[0].getImie() + lista[1].getLiczbaPracownikow());
        for (int i = 0; i < dod.length; i++) {
			if(dod[i]!=null)
			lista[Pracownik.getLiczbaPracownikow() + 1] = Pracownik.stworzPracownika(dod[i].getImie(), dod[i].getPensja(), dod[i].isUrlop());
		}
//		do{
    		
    		
			while(wybor != 'x')
			{
				wyswietlMenu();
				wybor = czytnik.next().charAt(0);
			switch(wybor){
    		case 'a' : wyswietlPrac(); 							break;
    		case 'b' : dodajPrac(); 							break;
    		case 'c' : podwyzka(); 								break;
    		case 'd' : urlop(); 								break;
    		case 'e' : usunPrac(); break;
    		case 'f' : czytaj(); break;
    		case 'h' : new Zapisownik(zapis()); break;
    		case 'x' : System.out.println("Wyjscie");			break;
			}
			}
//    		if (wybor=='a') wyswietlPrac();
//    		if (wybor=='b') dodajPrac();
//    		if (wybor=='c') podwyzka();
//    		if (wybor=='d') urlop();
//    		if(wybor=='e') usunPrac();
//    		if (wybor=='x') System.out.println("Wyjscie");
//
// 
//    	}while(wybor!='x');
		
//			new Zapisownik2(zapis());
   
    }     
}

class bd 
{
	private Pracownik lili[] = new Pracownik[100];
	
	public Pracownik[] getLili(){
		return lili;
	}
	

	public bd(Pracownik lista[]){
		
		try {
			// Zaladowanie sterownika do obslugi PostgreSQL
			Class.forName("org.postgresql.Driver");

			// Nawiazywanie polaczenia z baza
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/kscibek","kscibek", SUPERTAJNEHASLO);


			System.out.println("Logowanie do bazy powiodlo sie");

			java.sql.Statement  st = conn.createStatement();
			PreparedStatement pst;
			ResultSet rs;

			// Zapis do bazy
			for (int i=1; i<Pracownik.getLiczbaPracownikow(); i++)
			{
				
				String stm = "INSERT INTO nowiprac(id, imie, pensja, urlop) VALUES("+lista[i].getId()+", '"+ lista[i].getImie() +"', "
						+ lista[i].getPensja() +", "	+ lista[i].isUrlop() + ");";
				pst = conn.prepareStatement(stm);
				pst.executeUpdate();
			}

			// Odczyt z bazy
			pst = conn.prepareStatement("SELECT * FROM nowiprac");
			rs = pst.executeQuery();
			String imie = "";
			int pensja = 0;
			boolean urlop;
			int i=0;
			
			String baza = "";
			while(rs.next())
			{
				
				System.out.println(rs.getInt(1));		// pobranie id
				imie = rs.getString(2);
				pensja = rs.getInt(3);			// pobranie pensji
				urlop = rs.getBoolean(4);	

				
				
					lili[i] = Pracownik.stworzPracownika(imie, pensja, urlop);
				baza += imie;
			}

			conn.close();

		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			System.out.append("Brak sterownika");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.append("Blad w poleceniu PostgreSQL");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	protected static final String SUPERTAJNEHASLO = "900523";
}

