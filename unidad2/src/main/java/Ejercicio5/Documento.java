package Ejercicio5;

import lombok.Data;

@Data
public class Documento extends ItemPagina {
    private String titulo;

    public Documento(String ruta, String titulo) {
        super(ruta);
        this.titulo = titulo;
    }

    public static Documento of(String ruta, String titulo){
        return new Documento(titulo, ruta);
    }
    @Override
    public String toString() {
        return this.titulo + "\n<" + this.ruta + ">";
    }

}


