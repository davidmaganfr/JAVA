package com.cloudftic.nivel1.PREGUNTA2;

import java.util.ArrayList;

public class Documento {
    private ArrayList<String> parrafos = new ArrayList<>();
    private String titulo;
    private int numeroParrafos;

    public Documento() {
        parrafos = new ArrayList<>();
    }
    public int getNumeroParrafos() {
        numeroParrafos = parrafos.size();
        return numeroParrafos;
    }
    public String getParrafo(int numParrafo) {
        if (numParrafo <= 0 || numParrafo > this.parrafos.size()) {
            return null;
        }
        return this.parrafos.get(numParrafo - 1);
    }
    public void setParrafo(int numParrafo, String texto) {
        if (numParrafo <= 0 || numParrafo > this.parrafos.size()) {
            this.parrafos.add(texto);
        }
        this.parrafos.set(numParrafo - 1, texto);
    }
}
