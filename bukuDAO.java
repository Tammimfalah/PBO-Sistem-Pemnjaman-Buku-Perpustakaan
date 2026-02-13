package dao;
import config.Koneksi;
import java.sql.*;

public class BukuDAO {

    public void tambahBuku(String judul, String pengarang,
                           String penerbit, int tahun, int stok) {

        String sql = "INSERT INTO buku VALUES (NULL,?,?,?,?,?)";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, judul);
            pst.setString(2, pengarang);
            pst.setString(3, penerbit);
            pst.setInt(4, tahun);
            pst.setInt(5, stok);
            pst.executeUpdate();

            System.out.println("Data buku berhasil ditambah");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void tampilBuku() {
        String sql = "SELECT * FROM buku";

        try (Connection conn = Koneksi.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id_buku") + " - " +
                    rs.getString("judul")
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
