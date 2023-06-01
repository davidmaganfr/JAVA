package com.cloudftic.nivel1.PREGUNTA1;

import java.util.ArrayList;

public class Documento {
    private ArrayList<String> parrafos = new ArrayList<>();
    private String titulo;
    private int numeroParrafos;

    public Documento() {
        parrafos = new ArrayList<>();
    }
    //Metodo get que actualiza devuelve el numero de parrafos actualizados de un campo de solo lectura
    public int getNumeroParrafos() {
        numeroParrafos = parrafos.size();
        return numeroParrafos;
    }
}