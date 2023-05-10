package Ejercicio5;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class Pagina{
    List<ItemPagina> lista = new ArrayList<>();

    public void addImg(Imagen img) {
       lista.add(img);
    }
    public void removeImg(Imagen img) {
        lista.remove(img);
    }
    public void addDoc(Documento doc){
        lista.add(doc);
    }
    public void removeDoc(Documento doc){
        lista.remove(doc);
    }
     @Override
    public String toString() {
        String resultado = "";
        for(int i=0; i<lista.size(); i++){
            resultado += lista.get(i).toString();
            if(i < lista.size() -1){
                resultado += "\n\n";
            }
        }
        return resultado;
    }
    
    public void changeTitulo(Sustituidor elem){
        for(var item : lista){
            if(item instanceof Documento doc){
                elem.modificar(doc); 
            }
        }
    }
@FunctionalInterface
public interface Sustituidor{
    String modificar(Documento doc);
}
}


