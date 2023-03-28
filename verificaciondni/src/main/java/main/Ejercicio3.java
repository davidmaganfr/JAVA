package main;

public class Ejercicio3 {
    public static void main(String[] args) {
        String texto = Consola.readText("Escribe un texto: ");
        invertirOrden(texto);
    }
    static void invertirOrden(String texto){
        for(int i=texto.length()- 1; i>=0; i--){
            System.out.print(texto.charAt(i));
        }
    }
}
