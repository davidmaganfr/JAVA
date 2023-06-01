package com.cloudftic;

import com.work.*;

/*
Crea un proyecto modular "applib" y en su clase principal define métodos estáticos. 
Usaremos este proyecto como una librería ("applib-1.0.jar") para otros proyectos.
Cresa un proyecto modular "app1", el cual debe hacer uso de los método estáticos del
proyecto "applib".
Configura Maven para crear el fichero ejecutable "app1-1.0.jar", y que haga uso de la
libería "applib.jar" ubicada en un carpeta  "lib/" dentro de la misma carpeta que
"app1-1.0.jar".

Adaptaremos este ejercicio para crear una libreria con el framework de ejemplo en el
manual (ver proyecto menufwork), y aquí usaremos la librería para crear un programa 
de menu (el fichero menufwork-1.0.jar en la carpeta raiz de este proyecto).
 */

@MenuOptions({"Generar informes", "Calcular balance"})
public class Ejercicio01 {

    public static void main(String[] args) throws Exception {
       FrameworkMenu.run(Ejercicio01.class);
    }

    @MenuAction(1)
    public void generarInformes() {
        System.out.println("Generando informes...");
    }
    @MenuAction(2)
    public void calcularBalance() {
        System.out.println("Calculando balance...");
    }
}
