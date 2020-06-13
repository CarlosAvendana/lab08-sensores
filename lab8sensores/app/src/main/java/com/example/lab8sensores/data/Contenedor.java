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
        instancia.lista.add(new Contacto("Luis Felipe Piedra Walsh","+50689807793","Quality Assurance Recruiter"));
        instancia.lista.add(new Contacto("Carlos Obando Avendano", "+50687106583", "Project Manager Recruiter"));
        instancia.lista.add(new Contacto("Wesly Segura Monge", "+50684438388", "Front-End Development Recruiter"));
        instancia.lista.add(new Contacto("Juan Carlos Madrigal Vasquez", "+50683280550", "DataBase Manager Recruiter"));
    }

    public static Contenedor getInstance(){
        if(instancia==null){
            instancia= new Contenedor();
            instancia.addContactos();
        }
        return instancia;
    }


}
