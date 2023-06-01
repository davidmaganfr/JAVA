package com.cloudftic.nivel1.PREGUNTA3;

import java.util.ArrayList;

public class Documento {
    private ArrayList<Parrafo> parrafos = new ArrayList<>();
    private String titulo;
    private int numeroParrafos;

    public Documento() {
        parrafos = new ArrayList<>();
    }

    public int getNumeroParrafos() {
        numeroParrafos = parrafos.size();
        return numeroParrafos;
    }
    public Parrafo getParrafo(int numParrafo) {
        if (numParrafo <= 0 || numParrafo > this.parrafos.size()) {
            return null;
        }
        return this.parrafos.get(numParrafo - 1);
    }
    public void setParrafo(int numParrafo, Parrafo texto) {
        if (numParrafo <= 0 || numParrafo > this.parrafos.size()) {
            this.parrafos.add(texto);
        }
        this.parrafos.set(numParrafo - 1, texto);
    }
    public void agregarParrafo(int orden, String texto) {
        Parrafo nuevoParrafo = new Parrafo(orden, texto);
        this.parrafos.add(nuevoParrafo);
    }
}
