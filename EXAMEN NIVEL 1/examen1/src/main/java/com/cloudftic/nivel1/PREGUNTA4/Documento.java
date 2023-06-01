package com.cloudftic.nivel1.PREGUNTA4;

import java.util.ArrayList;

public class Documento {
    private ArrayList<Parrafo> parrafos = new ArrayList<>();
    private String titulo;
    private int numeroParrafos;
    private TipoDocumento tipo;

    public Documento(TipoDocumento tipo) {
        parrafos = new ArrayList<>();
        this.tipo = tipo;
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
    public TipoDocumento getTipo() {
        return tipo;
    }
    public void setTipo(TipoDocumento tipo) {
        this.tipo = tipo;
    }
    public String getTipoMay() {
        return tipo.toString().toUpperCase();
    }
}

//Por defecto los campos del enum se ponen en MAYUSCULAS aunque en ejercicio se solicita de esta forma
enum TipoDocumento {
    libro, novela, ensayo
}
