package main;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * Crea una clase Vector, que representará un vector matemático en el plano. El vector arrancará desde 
 * el punto (0, 0) y su otro extremo será un punto cualquiera del plano.

Añade los siguientes métodos:

constructor: debe recibir la posición x e y del punto extremo.
altura(): la proyección del vector sobre el eje Y (el valor v2 de la imagen de abajo).
ancho(): la proyección del vector sobre el eje X (el valor v1 de la imagen de abajo).
modulo(): debe retornar el módulo del vector. (El módulo es la longitud absoluta del vector de un extremo 
al otro. Se puede calcular con la fórmula de la imagen.)
 */

@Data
@AllArgsConstructor
public class Vector1 {
    public int x, y;

    public int altura(){
        return this.y;
    }
    public int ancho(){
        return this.x;
    }
    public double modulo(){
        double v1 = Math.pow(this.x, 2);
        double v2 = Math.pow(this.y, 2);

        return Math.sqrt((v1 + v2));
    }
    public Vector1 suma (Vector1 otrovector){
        return new Vector1(this.x + otrovector.x, this.y + otrovector.y);
    }
    @Override
    public String toString() {
        return "Vector (" + this.x + "," + this.y + ")";
    }
}
