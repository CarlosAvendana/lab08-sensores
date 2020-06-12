package com.example.lab8sensores.data;

import java.util.ArrayList;
import java.util.List;

public class Contenedor {

    private static Contenedor instancia = null;

    public List<Contacto> lista;

    private Contenedor(){
        lista= new ArrayList<>();

    }
    private void addContactos(){
        instancia.lista.add(new Contacto("Luis Felipe Piedra","+50689807793"));
        instancia.lista.add(new Contacto("Carlos Obando Avendano", "+50687106583"));
    }

    public static Contenedor getInstance(){
        if(instancia==null){
            instancia= new Contenedor();
            instancia.addContactos();
        }
        return instancia;
    }


}
