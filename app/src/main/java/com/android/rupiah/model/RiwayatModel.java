package com.android.rupiah.model;


import java.io.Serializable;
import java.sql.Timestamp;

public class RiwayatModel implements Serializable {

    private int saldo;
    private String keterangan;
    private String tanggal;
    private String tipe;
    public RiwayatModel(int saldo, String keterangan, String tanggal, String tipe) {
        this.saldo = saldo;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
        this.tipe = tipe;
    }



    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public RiwayatModel() {
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public String toString(){
        return " " + saldo + "\n" +
               " " + keterangan + "\n" +
               " " + tanggal + "\n" +
               " " + tipe + "\n" ;
    }
}
