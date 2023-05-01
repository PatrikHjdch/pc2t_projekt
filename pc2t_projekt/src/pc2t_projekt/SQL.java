package pc2t_projekt;

import java.io.StringReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.json.*;

public class SQL {
    private static Connection conn;
    
    
    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:films.db");
            System.out.println("Pripojovani k databazi...");
        } catch (ClassNotFoundException | SQLException e) {
        	System.out.println("Nepovedlo se najit databazi filmu.");
        }
    }
    
    public static void disconnect() {
        try {
        		conn.close();
            
            System.out.println("Odpojovani od databaze...");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
        }
    }
    
    public static void createTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS film (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "nazev TEXT NOT NULL," +
                         "reziser TEXT NOT NULL," +
                         "rok INTEGER NOT NULL," +
                         "doporuceny_vek_divaka INTEGER,"+
                         "lidi JSON)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            
            sql = "CREATE TABLE IF NOT EXISTS hodnoceni("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "id_filmu INT NOT NULL,"
                    + "skore FLOAT,"
                    + "slovni TEXT,"
                    + "FOREIGN KEY (id_filmu) REFERENCES film(id));";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void insertFilm(Film film) {
        try {
            String sql = "INSERT INTO film (nazev, reziser, rok, doporuceny_vek_divaka, lidi) " +
                         "VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, film.getNazev());
            stmt.setString(2, film.getReziser().getJmeno());
            stmt.setInt(3, film.getRok());
            if (film.getClass().getName()=="pc2t_projekt.Animovany") {
            	stmt.setInt(4, ((Animovany) film).getVekDivaka());
            }
            if (film.getClass().getName()=="pc2t_projekt.Hrany") {
            	List<String> jmenaHercu = new ArrayList<>();
            	for (Herec herec : ((Hrany) film).getSeznamHercu()) {
            		jmenaHercu.add("\""+herec.getJmeno()+"\"");
            		stmt.setString(5, jmenaHercu.toString());
            	}
            } else {
            	List<String> jmenaAnimatoru = new ArrayList<>();
            	for (Animator animator : ((Animovany) film).getSeznamAnimatoru()) {
            		jmenaAnimatoru.add("\""+animator.getJmeno()+"\"");
            		stmt.setString(5, jmenaAnimatoru.toString());
            	}
            }
            stmt.executeUpdate();
            
            sql="SELECT id FROM film WHERE nazev = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, film.getNazev());
            ResultSet rs = stmt.executeQuery();
            int idFilmu=rs.getInt(1);
            
            sql = "INSERT INTO hodnoceni (id_filmu, skore, slovni)"
            		+ "VALUES (?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idFilmu);
            for (Hodnoceni hodnoceni : film.getHodnoceni()) {
            	stmt.setFloat(2, hodnoceni.getSkore());
            	stmt.setString(3, hodnoceni.getSlovni());
            	stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Film> loadFilmy 	() throws SQLException {
    	List<Film> novaDatabaze = new ArrayList<>();
    	String sql = "SELECT nazev, reziser, rok, doporuceny_vek_divaka, lidi FROM film";
    	PreparedStatement stmt=conn.prepareStatement(sql);
    		ResultSet rsFilmy = stmt.executeQuery();
            while (rsFilmy.next()) {
                String nazev = rsFilmy.getString("nazev");
                Reziser reziser = new Reziser(rsFilmy.getString("reziser"));
                int rok = rsFilmy.getInt("rok");
                int vekDivaka = rsFilmy.getInt("doporuceny_vek_divaka");
                String stringLidi = rsFilmy.getString("lidi");
                if (vekDivaka==0) {
                	List<Herec> herci = parseHerci(stringLidi);
                	Hrany film = new Hrany(nazev, reziser, rok);
                	for (Herec herec : herci) {
                		film.addHerec(herec);
                	}
                	
                	sql = "SELECT hodnoceni.skore, hodnoceni.slovni FROM film JOIN hodnoceni ON film.id = hodnoceni.id_filmu WHERE film.nazev = ?";
                	stmt = conn.prepareStatement(sql);
                	stmt.setString(1, nazev);
                	ResultSet rsHodnoceni = stmt.executeQuery();
                	while (rsHodnoceni.next()) {
                		Hodnoceni hodnoceni = new Hodnoceni();
                		hodnoceni.setSkore(rsHodnoceni.getFloat("skore"));
                		hodnoceni.setSlovni(rsHodnoceni.getString("slovni"));
                		film.addHodnoceni(hodnoceni);
                	}
                	rsHodnoceni.close();
                    novaDatabaze.add(film);                	
                }
                else {
                	List<Animator> animatori = parseAnimatori(stringLidi);
                	Animovany film = new Animovany(nazev, reziser, rok, vekDivaka);
                	for (Animator animator : animatori) {
                		film.addAnimator(animator);
                	}
                	sql = "SELECT hodnoceni.skore, hodnoceni.slovni FROM film JOIN hodnoceni ON film.id = hodnoceni.id_filmu WHERE film.nazev = ?";
                	stmt = conn.prepareStatement(sql);
                	stmt.setString(1, nazev);
                	ResultSet rsHodnoceni = stmt.executeQuery();
                	while (rsHodnoceni.next()) {
                		Hodnoceni hodnoceni = new Hodnoceni();
                		hodnoceni.setSkore(rsHodnoceni.getFloat("skore"));
                		hodnoceni.setSlovni(rsHodnoceni.getString("slovni"));
                		film.addHodnoceni(hodnoceni);
                	}
                	rsHodnoceni.close();
                    novaDatabaze.add(film);
                }                	              
            }
            return novaDatabaze;
    }
    
    private static List<Herec> parseHerci(String lidi){
   		JsonReader reader = Json.createReader(new StringReader(lidi));
  		JsonArray array = reader.readArray();
   		List<Herec> list = new ArrayList<>();
   		for (JsonValue jmeno : array) {
    		list.add(new Herec(jmeno.toString()));
   		}
   		return list;
   	}
    	
   	private static List<Animator> parseAnimatori(String lidi){
   		JsonReader reader = Json.createReader(new StringReader(lidi));
    	JsonArray array = reader.readArray();
    	List<Animator> list = new ArrayList<>();
    	for (JsonValue jmeno : array) {
   			list.add(new Animator(jmeno.toString()));
   		}
   		return list;
   	}
}

