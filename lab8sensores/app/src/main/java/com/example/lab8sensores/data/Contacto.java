package com.example.lab8sensores.data;

import android.graphics.Bitmap;

public class Contacto {
    String nombre;
    String number;
    Bitmap image;

    public Contacto(String nombre, String number, Bitmap image) {
        this.nombre = nombre;
        this.number = number;
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
