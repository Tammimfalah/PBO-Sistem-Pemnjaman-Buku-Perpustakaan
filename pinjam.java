private void btnPinjamActionPerformed(java.awt.event.ActionEvent evt) {

    try {
        Connection conn = config.Koneksi.getConnection();
        String sql = "INSERT INTO peminjaman VALUES(NULL,?,?,?, ?,NULL,0,'Dipinjam')";
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, Integer.parseInt(txtIdAnggota.getText()));
        pst.setInt(2, Integer.parseInt(txtIdBuku.getText()));
        pst.setDate(3, Date.valueOf(txtTglPinjam.getText()));
        pst.setDate(4, Date.valueOf(txtTglKembali.getText()));

        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Buku Berhasil Dipinjam");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
