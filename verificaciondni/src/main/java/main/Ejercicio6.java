package main;

public class Ejercicio6 {
    public static void main(String[] args) {
        int numero = Consola.readInteger("Escribe un numero: ");

        String cifras = contadorCifras(numero);
        System.out.println(cifras);

        String numeroInvertido = invertirNumero(numero);
        System.out.println(numeroInvertido);

        System.out.println("¿Es par el numero? " + esPar(numero));

        System.out.println("Es primo el numero? " + esPrimo(numero));
        
    }

    static String contadorCifras(int numero){
        int contador = 0;
        if(numero < 10){
            contador = 1;
        } else{
            for(int i = numero; i > 0; i/=10){
            contador ++; 
                if(i <= 0){
                    break;
                }
            }
        }
        return "La cantidad de cifras del numero es: " + contador;
    }
    static String invertirNumero(int numero){
        String convertirString = String.valueOf(numero);
        String invertido = "";
        for(int i = convertirString.length() - 1; i >= 0; i--){
            invertido += convertirString.charAt(i);
        }
        return numero + " -> " + invertido;
    }
    static boolean esPar(int numero){
        if(numero % 2 == 0){
            return true;
        }
        return false;
    }
    static boolean esPrimo(int numero){
        if(numero > 2){
            for(int i=2; i<numero; i++){
                if(numero % i == 0){
                    return false;
                }
            }
        }
        return true;
    }
}