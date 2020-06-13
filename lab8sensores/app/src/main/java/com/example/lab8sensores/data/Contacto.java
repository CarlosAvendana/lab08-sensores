package com.example.lab8sensores.data;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Contacto implements Serializable {
    String nombre;
    String number;
    String rol;

    public Contacto(String nombre, String number, String rol) {
        this.nombre = nombre;
        this.number = number;
        this.rol = rol;
    }

    public Contacto(String nombre, String number) {
        this.nombre = nombre;
        this.number = number;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", number='" + number + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }

}
