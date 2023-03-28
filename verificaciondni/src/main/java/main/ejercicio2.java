package main;
public class ejercicio2 {
    public static void main( String[] args ){
        String[] arrayNombres = new String[10];
        int[] arrayEdades = new int[10];

        solicitarNombreYEdad(arrayNombres, arrayEdades);
        String personaMayor = mayor(arrayNombres, arrayEdades);
        String mediaEdad = media(arrayEdades);
        
        System.out.println(personaMayor);
        System.out.println(mediaEdad);
    }
    static void solicitarNombreYEdad(String[] nombres, int[]edades){
        for(int i=0; i<10; i++){
            nombres[i] = Consola.readText("Ecribe tu nombre: ");
            edades[i] = Consola.readInteger("Escribe la edad: ");
        }
    }
    static String mayor(String[] nombres, int[]edades){
        int valorMax = Integer.MIN_VALUE;
        int indice = 0;
        for (int valor = 0; valor < edades.length; valor++){
            if(edades[valor] > valorMax){
                valorMax = edades[valor];
                indice = valor; 
            }
        }
        return "La persona con mas edad es: " + nombres[indice];
    }
    static String media(int[]edades){
        int sumatorio = 0;
        for (int i : edades){
            sumatorio += i;
        }
        return "La media de edad es: " + sumatorio/edades.length;
    }
}
