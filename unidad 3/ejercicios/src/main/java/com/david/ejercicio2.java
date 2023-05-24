package com.david;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Data;

//Un programa usa un StringBuilder para almacenar un documento compuesto por párrafos separados con 
//un salto de línea ('\n'). Completa las instrucciones de la siguiente clase:

@Data
public class ejercicio2 {
     public static void main(String[] args) {
          ejercicio2 prueba = new ejercicio2();
          // prueba.sustituyePrimero("\\bTras\\b", "cosa");
          prueba.addParrafo(" Debe añadir un nuevo párrafo al final del documento.");
          prueba.terminarConPunto();

          System.out.println(prueba.document);
          prueba.sustituyePrimero("\\bun\\b", "EL");
     }

     public StringBuilder document = new StringBuilder();

     public void addParrafo(String texto) {
          // Debe añadir un nuevo párrafo al final del documento.
          document.append(texto + "\n");
     }

     public int getNumeroParrafos() {
          // Debe retornar cuántos párrafos tiene el documento
          return document.toString().split("\n").length;
     }

     public String getParrafo(int posicion) {
          // Debe retornar el párrafo de la posición dada contando desde 1, o null
          // si no hay ningún párrafo en esa posición.
          String[] listaParrafos = document.toString().split("\n");
          if (listaParrafos.length >= posicion) {
               return listaParrafos[posicion - 1];
          } else {
               return null;
          }
     }

     public void terminarConPunto() { // me da error de memoria
          // Garantiza que todos los párrafos terminarán con un punto.
          for (int i = 0; i < document.length(); i++) {
               if (document.charAt(i) == '\n') {
                    if (i > 0 && document.charAt(i - 1) != '.') {
                         document.insert(i, ".");
                         i++;
                    }
               }
          }
     }

     public void eliminarParrafo(int posicion) { // no se como acceder a los parrafos
          // Eliminar el párrafo de la posición dada contando desde 1, o no hace-
          // nada si no hay un párrafo en esa posición
          String[] listaParrafos = document.toString().split("\n");
          String nuevoTexto = IntStream.range(0, listaParrafos.length)
                    .filter(i -> i != posicion - 1)
                    .mapToObj(i -> listaParrafos[i])
                    .collect(Collectors.joining("\n"));
          document.replace(0, document.length(), nuevoTexto);
     }

     public void sustituyePrimero(String regex, String otro) {
          // El parámetro regex es una expresión regular con la que debe casar aquel
          // texto del documento que queremos sustituir por otro.
          var sustitucion = Pattern.compile(regex).matcher(document).replaceAll(otro);
          System.out.println(sustitucion);
     }
}
