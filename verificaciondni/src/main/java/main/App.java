package main;

public class App {
    public static void main( String[] args ){
        String dni = Consola.readText("Escribe tu DNI en formato '23456789-A': ");
        comprobarDni(dni);
    }
    static void comprobarDni(String dni) {
        String[] listaLetrasDni = {"T", "R", "W", "A","G","M","Y","F","P","D","X","B", "N","J","Z","S","Q","V","H","L","C","K","E"};
        String[] separarDni = dni.split("-");
        int index = Integer.parseInt(separarDni[0]) % 23;
        if(separarDni[1].equals(listaLetrasDni[index])){
            System.out.println("El DNI es válido"); 
        } else {
            System.out.println("El DNI no es valido"); 
        }

    }
}
