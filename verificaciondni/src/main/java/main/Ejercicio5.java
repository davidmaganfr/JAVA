package main;

public class Ejercicio5 {
    static final String abcedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    public static void main(String[] args) { 
        rombo(7);
    }
    /* static void programaRombo(int num){
        String[] lista = {"A", "ABA", "ABCBA", "ABCDCBA", "ABCDEDCBA", "ABCDEFEDCBA", "ABCDEFGFEDCBA"};
        for(int i=0; i<num; i++){
            String espacios = " ";
            int numEspacios = lista[num-1].length() - lista[i].length() / 2;
            System.out.println(espacios.repeat(numEspacios) + lista[i]);
        }
        for(int n=num-2; n>=0; n--){
            String espacios = " ";
            int numEspacios = lista[num-1].length() - lista[n].length() / 2;
            System.out.println(espacios.repeat(numEspacios) + lista[n]);
        }
    }  */

    static void rombo(int num){
        lineaSup(num);
        lineaIntermedio(num);
        lineaInferiores(num);
    }
    static void lineaSup(int num){
        for(int i=0; i<num-1; i++){
            String blancos = " ".repeat(num - i - 1);
            String ascendente = abcedario.substring(0, i);
            String letraintermedio = abcedario.substring(i, i + 1);
            String descendente = new StringBuilder(ascendente).reverse().toString(); 
            System.out.println(blancos + ascendente + letraintermedio + descendente);
        }
    }
    static void lineaIntermedio(int num){
        String ascendente = abcedario.substring(0, num - 1);
        String letraintermedio = abcedario.substring(num -1, num);
        String descendente = new StringBuilder(ascendente).reverse().toString(); //invierte el orden del String
        System.out.println(ascendente + letraintermedio + descendente);
    }
    static void lineaInferiores(int num){
        for(int i=num-1; i>=0; i--){
            String blancos = " ".repeat(num - i - 1);
            String ascendente = abcedario.substring(0, i);
            String letraintermedio = abcedario.substring(i, i + 1);
            String descendente = new StringBuilder(ascendente).reverse().toString(); 
            System.out.println(blancos + ascendente + letraintermedio + descendente);
        }
    }
} 
