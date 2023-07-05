package com.david;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import lombok.Data;

/*
 * Añádele el código necesario para que los objetos Trabajador se puedan serializar en binario, 
 * excepto la propiedad ayudante, que no debe ser serializada. 
 */

public class PREGUNTA1 {
    
}

@Data
class Trabajador implements Serializable {
    private int codigo;
    private String nombre;
    private Trabajador ayudante; //puede ser null u otro trabajador

    public Trabajador(int codigo, String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public void serializaToBinary(OutputStream destino) throws IOException{
        try (var out = new ObjectOutputStream(destino)){
            out.writeObject(this);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
