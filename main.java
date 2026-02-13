import dao.BukuDAO;
import dao.PeminjamanDAO;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        BukuDAO buku = new BukuDAO();
        buku.tambahBuku("Java Programming",
                "Andi", "Informatika", 2023, 5);

        buku.tampilBuku();

        PeminjamanDAO pinjam = new PeminjamanDAO();

        pinjam.pinjamBuku(1, 1,
                LocalDate.now(),
                LocalDate.now().plusDays(7));
    }
}
