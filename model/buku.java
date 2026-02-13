package model;

public class Buku {
    private int idBuku;
    private String judul;
    private String pengarang;
    private String penerbit;
    private int tahun;
    private int stok;

    public Buku(int idBuku, String judul, String pengarang,
                String penerbit, int tahun, int stok) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.tahun = tahun;
        this.stok = stok;
    }

    public String getJudul() { return judul; }
    public int getStok() { return stok; }
}
