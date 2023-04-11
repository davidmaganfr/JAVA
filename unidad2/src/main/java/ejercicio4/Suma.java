package ejercicio4;

public class Suma extends OperacionAritmetica {

    public Suma(Numero x, Numero y) {
        super(" + ", x, y);
    }
    @Override
    public double getValor() {
        return this.getX().getValor() + this.getY().getValor();
    }

}
