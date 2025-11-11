/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LENOVO LOQ
 */
// File: src/model/Pesanan.java

import javafx.beans.property.*;
import java.time.LocalDate;

public class Pesanan {
    private final StringProperty namaPemesan;
    private final StringProperty namaMakanan;
    private final DoubleProperty harga;
    private final ObjectProperty<LocalDate> tanggal; // Untuk DatePicker

    public Pesanan(String namaPemesan, String namaMakanan, double harga, LocalDate tanggal) {
        this.namaPemesan = new SimpleStringProperty(namaPemesan);
        this.namaMakanan = new SimpleStringProperty(namaMakanan);
        this.harga = new SimpleDoubleProperty(harga);
        this.tanggal = new SimpleObjectProperty<>(tanggal);
    }

    public String getNamaPemesan() { 
        return namaPemesan.get(); 
    }
    public void setNamaPemesan(String namaPemesan) { 
        this.namaPemesan.set(namaPemesan); 
    }
    public StringProperty namaPemesanProperty() { 
        return namaPemesan; 
    }
    public StringProperty namaMakananProperty() { 
        return namaMakanan; 
    }
    public DoubleProperty hargaProperty() { 
        return harga; 
    }
    public ObjectProperty<LocalDate> tanggalProperty() { 
        return tanggal; 
    }

    public void setNamaMakanan(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getNamaMakanan() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getHarga() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public LocalDate getTanggal() { 
    return tanggal.get(); // Mengambil nilai dari Property
}
    

}
