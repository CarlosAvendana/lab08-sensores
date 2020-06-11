package com.example.lab8sensores.data;

import java.util.ArrayList;
import java.util.List;

public class Contenedor {

    private static Contenedor instancia = null;

    public List<Contacto> lista;

    private Contenedor(){
        lista= new ArrayList<>();
    }

    public static Contenedor getInstance(){
        if(instancia==null)
            instancia= new Contenedor();
        return instancia;
    }
}
