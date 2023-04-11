package ejercicio4;

public class Division extends OperacionAritmetica {
    public Division(Numero x, Numero y) {
        super(" / ", x, y);
    }
    
    @Override
    public double getValor() {
        return this.getX().getValor() / this.getY().getValor();
    }
}
