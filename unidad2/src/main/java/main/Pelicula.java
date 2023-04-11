package main;

import lombok.Data;

//EJERCICIO 1

@Data
public class Pelicula {
    private final int maxLen = 15;
    private final int codigo;
    private String titulo;
    private String duracion;

    public Pelicula( String titulo, String duracion) {
        this.codigo = (int) (Math.random() * System.currentTimeMillis());
        this.titulo = titulo.length() > maxLen? titulo.substring(0, maxLen) + "..." : titulo;
        this.duracion = duracion;
    }

    


}
