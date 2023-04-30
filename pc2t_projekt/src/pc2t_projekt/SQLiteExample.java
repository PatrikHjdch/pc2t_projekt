package pc2t_projekt;

import java.sql.*;

public class SQLiteExample {
    private static Connection conn;
    
    public static void main(String[] args) {
        // Connect to the SQLite database
        connect();
        
        // Create the film table
        createTable();
        
        // Insert a new Film object into the table
        Film film = new Film("Animak", new Reziser("Jiri Trnka"), 2022, 9);
        insertFilm(film);
        
        // Close the connection to the database
        disconnect();
    }
    
    public static void connect() {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            
            // Connect to the database file "films.db"
            conn = DriverManager.getConnection("jdbc:sqlite:films.db");
            
            System.out.println("Connected to SQLite database.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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
                         "doporuceny_vek_divaka INTEGER NOT NULL)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            
            System.out.println("Film table created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void insertFilm(Film film) {
        try {
            // Insert a new row into the film table with data from the Film object
            String sql = "INSERT INTO film (nazev, reziser, rok, doporuceny_vek_divaka) " +
                         "VALUES (?, ?, ?, ?)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, film.getNazev());
            stmt.setString(2, film.getReziser().toString());
            stmt.setInt(3, film.getRok());
            stmt.setInt(4, ((Animovany) film).getVekDivaka());
            stmt.executeUpdate();
            
            System.out.println("Film data inserted into database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

