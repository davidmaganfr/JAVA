package ejercicio4;

public class Producto extends OperacionAritmetica {
    public Producto(Numero x, Numero y) {
        super(" * ", x, y);
    }
    
    @Override
    public double getValor() {
        return this.getX().getValor() * this.getY().getValor();
    }
}
