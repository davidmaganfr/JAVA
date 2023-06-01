package com.david;

import java.util.List;
import com.example.App;

// EJERCICIO 1
/*Crea un proyecto modular "applib" y en su clase principal define dos  métodos estáticos. 
Usaremos este proyecto como una librería ("applib-1.0.jar") para otros proyectos.
Cresa un proyecto modular "app1", el cual debe hacer uso de los método estáticos del proyecto "applib".

Configura Maven para crear el fichero ejecutable "app1-1.0.jar", y que haga uso de la libería 
"applib.jar" ubicada en un carpeta  "lib/" dentro de la misma carpeta que "app1-1.0.jar".
 */

public class Principal<T extends CharSequence>  {
    public static void main( String[] args ){
        App.saludo();
        App.despedida();
        System.out.println(procesar(2, List.of("ei", "del", "of", "fisiologia"))); 
        StringBuilder a = new StringBuilder();
        StringBuffer a1 = "assds";
    }
    //EJERCICIO 2 UNIDAD 4
// Crea un método estático llamado "procesar" con dos argumentos:

// Hay que garantizar que el primer argumento sea un número de cualquier tipo válido.
// Hay que garantizar que el segundo argumento sea una lista que contenga secuencias de texto.
// El método debe retornar la primera secuencia de texto, del mismo tipo que los elementos de la lista, cuya longitud coincida con el valor del primer argumento.

// Usa genéricos para cumplir con los requerimientos.

    public static <T extends CharSequence, A extends Number> T procesar(A numero, List<T> texto){
        for(T t : texto){
            if(t.length() == numero.intValue()){
                return t;
            }
        }
        return null;
    }
}
