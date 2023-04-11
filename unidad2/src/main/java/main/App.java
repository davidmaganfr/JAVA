package main;

import Ejercicio5.Album;
import Ejercicio5.Documento;
import Ejercicio5.Imagen;
import Ejercicio5.Pagina;
import ejercicio4.Numero;
import ejercicio4.Suma;

public class App {
    public static void main( String[] args ){
        Pelicula pelicula1 = new Pelicula("El codigo da Vinci", "2:10:15");
        Pelicula pelicula2 = new Pelicula("Piratas del caribe: la maldicion", "1:30:00");
        
        System.out.println(pelicula1.getDuracion());
        System.out.println(pelicula2.getTitulo());
        
        Vector uno = new Vector(new Punto(1,2));
        System.out.println(uno.modulo());

        Vector1 dos = new Vector1(2,3);
        System.out.println(dos.suma(new Vector1(2,6)));

        NRomano num1 = new NRomano(1352);
        NRomano.obtenerRomano(1352);
        System.out.println(num1); 
        NRomano num2 = new NRomano(50);
        
        System.out.println(num1.add(num2)); 

        Numero n1 = new Numero(6);
        Numero n2 = new Numero(3);
        System.out.println(n2.toString());
        Suma s = new Suma(n1, n2);
        System.out.println(s.toString());
        System.out.println(s.getEcuacion());
        

        var album = new Album();
        var pagina1 = new Pagina();
        pagina1.addImg(Imagen.of("imagen1.jpg", 25));
        pagina1.addDoc(Documento.of("doc1.txt", "Documento 1"));
        pagina1.addImg(Imagen.of("img2.jpg", 35));
        album.addPag(pagina1);

        var pagina2 = new Pagina();
        pagina2.addDoc(Documento.of("doc2.txt", "Documento 2"));
        pagina2.addDoc(Documento.of("doc3.txt", "Documento 3"));
        pagina2.addImg(Imagen.of("img3.jpg", 28));
        album.addPag(pagina2);

        System.out.println(album.toString());


    }

}
