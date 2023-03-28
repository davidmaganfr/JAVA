package main;

public class Ejercicio7 {
    public static void main(String[] args) {
        String contrasena = args.length > 0 ? args[0] : "12343";
        for(int i = 3; i >= 0; i--) {
            if(i == 0){
                System.out.println("Has agotado los intentos");
                break;
            }
            String validacion = Consola.readText("Introduce una contraseña: ");
            if(!validacion.equals(contrasena)){
                System.out.println("La contraseña no es correcta. Tienes " + (i - 1)+ " intentos: ");
            }else{
                break;
            }
        }
    }
}
