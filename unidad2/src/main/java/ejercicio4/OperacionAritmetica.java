package ejercicio4;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
abstract class OperacionAritmetica implements IExpresion {
    private final String operador;
    private final Numero x, y;

    @Override
    public String toString() {
        return "(" + x.getValor() + this.operador + y.getValor() + ")";
    }
}
