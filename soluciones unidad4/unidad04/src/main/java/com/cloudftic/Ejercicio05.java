package com.cloudftic;

import java.lang.reflect.Field;

/*
Crea el siguiente método:

public static Object getValorCampo(Object objeto, String nombreCampo) {
  // Debe retornar el valor en un campo de un objeto, incluso si es un campo privado.
}

Este método debe usar técnicas de reflexión para explorar el objeto dado y retornar 
el valor en un campo que se corresponda con el nombre dado. Si el objeto es null o 
si el campo no existe debe retornar null.
 */

public class Ejercicio05 {

    public static void main(String[] args) {
        class Prueba {
            private int x = 12;
        }

        var p = new Prueba();
        System.out.println(getValorCampo(p, "x"));

    }

    public static Object getValorCampo(Object objeto, String nombreCampo) {
        try {
            Field campoRef = objeto.getClass().getDeclaredField(nombreCampo);
            campoRef.setAccessible(true);
            return campoRef.get(objeto);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }
}
