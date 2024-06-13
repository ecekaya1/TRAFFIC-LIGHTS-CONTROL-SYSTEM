package com.example.proje;

public class Arac {
    String plaka;
    public Arac(String plaka) {
        this.plaka = plaka;
    }
    public String getPlaka() {
        return plaka;
    }

    @Override
    public String toString() {
        return getPlaka();
    }
}
