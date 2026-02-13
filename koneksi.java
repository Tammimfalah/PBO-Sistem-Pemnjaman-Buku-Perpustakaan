package config;
import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {
    private static Connection conn;

    public static Connection getConnection() {
        try {
            String url = "jdbc:mariadb://localhost:3306/siperpus";
            String user = "root";
            String pass = "";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
        return conn;
    }
}
