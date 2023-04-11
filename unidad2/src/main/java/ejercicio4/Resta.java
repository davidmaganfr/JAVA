package ejercicio4;

public class Resta extends OperacionAritmetica {
    public Resta(Numero x, Numero y) {
        super(" - ", x, y);
    }
    
    @Override
    public double getValor() {
        return this.getX().getValor() - this.getY().getValor();
    }
}
