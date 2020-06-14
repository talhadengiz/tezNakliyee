package com.example.talha.teznakliyee.models;

/**
 * Created by Talha on 22.5.2017.
 */

public class User {

    private String name;
    private String email;
    private String unique_id;
    private String password;
    private String old_password;
    private String new_password;
    private String en;
    private String boy;
    private String yukseklik;
    private String agirlik;
    private String tarih;
    private String saat;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }


    public void setEn(String en) {
        this.en = en;
    }

    public void setBoy(String boy) {
        this.boy = boy;
    }

    public void setYukseklik(String yukseklik) {
        this.yukseklik = yukseklik;
    }

    public void setAgirlik(String agirlik) {
        this.agirlik = agirlik;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }
}