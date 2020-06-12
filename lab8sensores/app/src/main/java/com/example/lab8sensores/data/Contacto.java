package com.example.lab8sensores.data;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Contacto extends Serializable {
    String nombre;
    String number;
    Bitmap image;

    public Contacto(String nombre, String number, Bitmap image) {
        this.nombre = nombre;
        this.number = number;
        this.image = image;
    }

    public Contacto(String nombre, String number) {
        this.nombre = nombre;
        this.number = number;
        this.image=null;
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
