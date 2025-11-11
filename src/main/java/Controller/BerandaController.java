/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

// File: src/controller/RestoranController.java

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import Model.Pesanan; // Import model

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BerandaController implements Initializable {
    
    // --- FXML Components (sesuaikan ID) ---
    @FXML private DatePicker dpTanggal;
    @FXML private TextField txtPemesan;
    @FXML private TextField txtMakanan;
    @FXML private TextField txtHarga;
    @FXML private ChoiceBox<String> cbPembayaran; // Komponen Eksperimen

    @FXML private TableView<Pesanan> tabelPesanan;
    @FXML private TableColumn<Pesanan, String> colPemesan;
    @FXML private TableColumn<Pesanan, String> colMakanan;
    @FXML private TableColumn<Pesanan, Double> colHarga;
    @FXML private TableColumn<Pesanan, LocalDate> colTanggal;

    // --- Data List ---
    private ObservableList<Pesanan> dataPesanan;
    @FXML
    private Button btnTambah;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnHapus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 1. Inisialisasi ObservableList dan data dummy
        dataPesanan = FXCollections.observableArrayList(
            new Pesanan("Andi", "Nasi Goreng", 25000.0, LocalDate.now()),
            new Pesanan("Budi", "Mie Ayam", 18000.0, LocalDate.of(2025, 11, 10))
        );

        // 2. Setup Kolom TableView (Read)
        colPemesan.setCellValueFactory(cellData -> cellData.getValue().namaPemesanProperty());
        colMakanan.setCellValueFactory(cellData -> cellData.getValue().namaMakananProperty());
        // Perhatikan tipe data Double/Number untuk Harga
        colHarga.setCellValueFactory(cellData -> cellData.getValue().hargaProperty().asObject());
        colTanggal.setCellValueFactory(cellData -> cellData.getValue().tanggalProperty());

        // Hubungkan data ke TableView
        tabelPesanan.setItems(dataPesanan);

        // 3. Setup ChoiceBox (Komponen Eksperimen)
        cbPembayaran.getItems().addAll("Tunai", "Debit", "QRIS");
        cbPembayaran.setValue("Tunai"); 

        // 4. Setup Event Listener untuk Seleksi TableView (Update/Delete)
        tabelPesanan.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> tampilkanDetailPesanan(newValue));
    }

    private void tampilkanDetailPesanan(Pesanan pesanan) {
        if (pesanan != null) {
            dpTanggal.setValue(pesanan.getTanggal());
            txtPemesan.setText(pesanan.getNamaPemesan());
            txtMakanan.setText(pesanan.getNamaMakanan());
            txtHarga.setText(String.valueOf(pesanan.getHarga()));
            // Catatan: Anda perlu logika tambahan untuk mengisi ChoiceBox jika Anda ingin menyimpannya
        } else {
            // Kosongkan field jika tidak ada yang dipilih
            kosongkanField();
        }
    }
    
    private void kosongkanField() {
        dpTanggal.setValue(null);
        txtPemesan.setText("");
        txtMakanan.setText("");
        txtHarga.setText("");
        cbPembayaran.setValue("Tunai");
        tabelPesanan.getSelectionModel().clearSelection();
    }
    // ... lanjutan dari RestoranController.java

    // --- Create (Tambah Data) ---
    @FXML
    private void handleTambahPesanan() {
        try {
            String pemesan = txtPemesan.getText();
            String makanan = txtMakanan.getText();
            double harga = Double.parseDouble(txtHarga.getText());
            LocalDate tanggal = dpTanggal.getValue();
            
            if (pemesan.isEmpty() || makanan.isEmpty() || tanggal == null) {
                // Tampilkan warning
                return; 
            }

            Pesanan newPesanan = new Pesanan(pemesan, makanan, harga, tanggal);
            dataPesanan.add(newPesanan); // Otomatis refresh TableView

            kosongkanField();
        } catch (NumberFormatException e) {
            // Tangani jika input harga tidak valid
        }
    }

    // --- Update (Ubah Data) ---
    @FXML
    private void handleUpdatePesanan() {
        Pesanan selectedPesanan = tabelPesanan.getSelectionModel().getSelectedItem();
        if (selectedPesanan != null) {
            try {
                // Set data baru ke objek yang sudah ada
                selectedPesanan.setNamaPemesan(txtPemesan.getText());
                selectedPesanan.setNamaMakanan(txtMakanan.getText());
                // Karena kita pakai Property, hanya set yang perlu diubah
                selectedPesanan.hargaProperty().set(Double.parseDouble(txtHarga.getText()));
                selectedPesanan.tanggalProperty().set(dpTanggal.getValue());

                // Memaksa refresh TableView (meskipun pakai Property, ini adalah praktik yang baik)
                tabelPesanan.getColumns().get(0).setVisible(false);
                tabelPesanan.getColumns().get(0).setVisible(true);
                
                kosongkanField();
            } catch (NumberFormatException e) {
                // Tangani jika input harga tidak valid
            }
        }
    }

    // --- Delete (Hapus Data) ---
    @FXML
    private void handleHapusPesanan() {
        Pesanan selectedPesanan = tabelPesanan.getSelectionModel().getSelectedItem();
        if (selectedPesanan != null) {
            dataPesanan.remove(selectedPesanan); // Otomatis refresh TableView
            kosongkanField();
        }
    }
}