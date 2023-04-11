package main;

import lombok.AllArgsConstructor;
import lombok.Data;

//EJERCICIO 2

@Data
public class Vector {
    private Punto puntoExtremo;

    public Vector(Punto puntoExtremo) {
        this.puntoExtremo = puntoExtremo;
    }
    public int altura(){
        return this.puntoExtremo.getY();
    }
    public int ancho(){
        return this.puntoExtremo.getX();
    }
    public double modulo(){
        double v1 = Math.pow(puntoExtremo.getX(), 2);
        double v2 = Math.pow(puntoExtremo.getY(), 2);

        return Math.sqrt((v1 + v2));
    }
    public Punto suma (Vector otrovector){
        return new Punto(this.puntoExtremo.getX() + otrovector.getPuntoExtremo().getX(), 
                        this.puntoExtremo.getY() + otrovector.getPuntoExtremo().getY());
    }
    @Override
    public String toString() {
        return "Vector (" + puntoExtremo.getX() + "," + puntoExtremo.getY() + ")";
    }
}

@Data
@AllArgsConstructor
class Punto{
    private int x, y;
}
