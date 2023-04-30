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
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            
            // Connect to the database file "films.db"
            conn = DriverManager.getConnection("jdbc:sqlite:films.db");
            
            System.out.println("Connected to SQLite database.");
        } catch (ClassNotFoundException | SQLException e) {
        	System.out.println("Nepovedlo se najit databazi filmu.");
        }
    }
    
    public static void disconnect() {
        try {
            // Close the database connection
            conn.close();
            
            System.out.println("Disconnected from SQLite database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void createTable() {
        try {
            // Create the film table
            String sql = "CREATE TABLE IF NOT EXISTS film (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "nazev TEXT NOT NULL," +
                         "reziser TEXT NOT NULL," +
                         "rok INTEGER NOT NULL," +
                         "doporuceny_vek_divaka INTEGER,"+
                         "lidi JSON)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            
            System.out.println("Film table created.");
            
            sql = "CREATE TABLE IF NOT EXISTS hodnoceni("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "id_filmu INT NOT NULL,"
                    + "skore FLOAT,"
                    + "slovni TEXT,"
                    + "FOREIGN KEY (id_filmu) REFERENCES film(id));";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

            System.out.println("Review table created.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void insertFilm(Film film) {
        try {
            // Insert a new row into the film table with data from the Film object
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
            
            sql="SELECT COUNT(*) FROM film";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            int idFilmu=rs.getInt(1);
            
            sql = "INSERT INTO hodnoceni (id_filmu, skore, slovni)"
            		+ "VALUES (?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idFilmu);
            for (Hodnoceni hodnoceni : film.getHodnoceni()) {
            	stmt.setFloat(2, hodnoceni.getSkore());
            	stmt.setString(3, hodnoceni.getSlovni());
            }
            stmt.executeUpdate();
            
            
            System.out.println("Film data inserted into database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Film> loadFilmy 	() throws SQLException {
    	List<Film> novaDatabaze = new ArrayList<>();
    	String sql = "SELECT nazev, reziser, rok, doporuceny_vek_divaka, lidi FROM film";
    	PreparedStatement stmt=conn.prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nazev = rs.getString("nazev");
                Reziser reziser = new Reziser(rs.getString("reziser"));
                int rok = rs.getInt("rok");
                int vekDivaka = rs.getInt("doporuceny_vek_divaka");
                String stringLidi = rs.getString("lidi");
                System.out.println(stringLidi);
                if (vekDivaka==0) {
                	List<Herec> herci = parseHerci(stringLidi);
                	Hrany film = new Hrany(nazev, reziser, rok);
                	for (Herec herec : herci) {
                		film.addHerec(herec);
                	}
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
                ResultSet rs2 = stmt.executeQuery();
                Hodnoceni hodnoceni = new Hodnoceni();
                while (rs.next()) {
                	hodnoceni.setSkore(rs.getFloat("hodnoceni.skore"));
                	hodnoceni.setSlovni(rs.getString("hodnoceni.slovni"));
                	film.addHodnoceni(hodnoceni);
                }
                rs2.close();
                
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

