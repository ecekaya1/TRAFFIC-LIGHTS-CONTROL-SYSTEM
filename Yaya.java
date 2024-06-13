package com.example.proje;

public class Yaya{

    private int yas;

    public Yaya(String yas) {

        this.yas = Integer.parseInt(yas);
    }

    public int getYas() {
        return yas;
    }

    @Override
    public String toString() {
        return String.valueOf(yas);
    }
}
