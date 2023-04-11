package Ejercicio5;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import lombok.Data;

@Data
public class Album {
    List<Pagina> listadoPaginas = new ArrayList<>();

    public void addPag(Pagina pagina) {
        listadoPaginas.add(pagina);
    }
    public void removePag(Pagina pagina) {
        listadoPaginas.remove(pagina);
    }

    @Override
    public String toString() {
        String resultado = "";
        for (int i=0; i<listadoPaginas.size(); i++) {
            resultado += "PAGINA " + i + ":\n" + listadoPaginas.get(i).toString() + "\n-------------------------------\n\n";
        }
        return resultado;
    }
    public List<Pagina> selectPaginas(Predicate<Imagen> filtro){
        List<Pagina> nuevaLista = new ArrayList<Pagina>();
        for(Pagina p : listadoPaginas){
            for(var item : p.lista){
                 if(item instanceof Imagen img && filtro.test(img) && !nuevaLista.contains(p)){
                    nuevaLista.add(p);
                 }
            }
        }
        return nuevaLista;
    }
}