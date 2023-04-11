package Ejercicio5;

import lombok.Data;

@Data
public class Imagen extends ItemPagina{
    private int zoom;

    public Imagen(String ruta, int zoom) {
        super(ruta);
        this.zoom = zoom;
    }
    public static Imagen of(String ruta, int zoom){
        return new Imagen(ruta, zoom);
    }
    @Override
    public String toString() {
        return "\"" + "<" + this.ruta + ">" + ": " + this.zoom + "\"";
    }
  
}


