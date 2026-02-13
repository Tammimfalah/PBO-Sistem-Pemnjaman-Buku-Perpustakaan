private void btnKembalikanActionPerformed(java.awt.event.ActionEvent evt) {

    try {
        Connection conn = config.Koneksi.getConnection();

        String sql = "SELECT tanggal_kembali FROM peminjaman WHERE id_pinjam=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, 1); // contoh ID

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            LocalDate kembali = rs.getDate("tanggal_kembali").toLocalDate();
            LocalDate sekarang = LocalDate.now();

            long selisih = ChronoUnit.DAYS.between(kembali, sekarang);

            double denda = 0;
            if (selisih > 0) {
                denda = selisih * 1000;
            }

            String update =
            "UPDATE peminjaman SET tanggal_dikembalikan=?, denda=?, status='Kembali' WHERE id_pinjam=1";

            PreparedStatement pst2 = conn.prepareStatement(update);
            pst2.setDate(1, Date.valueOf(sekarang));
            pst2.setDouble(2, denda);
            pst2.executeUpdate();

            JOptionPane.showMessageDialog(null, "Denda: " + denda);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
