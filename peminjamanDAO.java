package dao;
import config.Koneksi;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PeminjamanDAO {

    public void pinjamBuku(int idAnggota, int idBuku,
                           LocalDate tglPinjam,
                           LocalDate tglKembali) {

        String sql = "INSERT INTO peminjaman VALUES(NULL,?,?,?, ?,NULL,0,'Dipinjam')";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idAnggota);
            pst.setInt(2, idBuku);
            pst.setDate(3, Date.valueOf(tglPinjam));
            pst.setDate(4, Date.valueOf(tglKembali));
            pst.executeUpdate();

            System.out.println("Buku berhasil dipinjam");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void kembalikanBuku(int idPinjam, LocalDate tglDikembalikan) {

        String sql = "SELECT tanggal_kembali FROM peminjaman WHERE id_pinjam=?";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idPinjam);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                LocalDate tglKembali =
                    rs.getDate("tanggal_kembali").toLocalDate();

                long selisih =
                    ChronoUnit.DAYS.between(tglKembali, tglDikembalikan);

                double denda = 0;
                if (selisih > 0) {
                    denda = selisih * 1000;
                }

                String update =
                "UPDATE peminjaman SET tanggal_dikembalikan=?, denda=?, status='Kembali' WHERE id_pinjam=?";

                PreparedStatement pst2 =
                        conn.prepareStatement(update);

                pst2.setDate(1, Date.valueOf(tglDikembalikan));
                pst2.setDouble(2, denda);
                pst2.setInt(3, idPinjam);
                pst2.executeUpdate();

                System.out.println("Buku dikembalikan. Denda: " + denda);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
