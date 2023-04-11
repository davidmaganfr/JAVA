package ejercicio4;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Numero implements IExpresion {
    private final double num;
    
    @Override
    public String toString() {
        return "\"" + num + "\"";
    }
    @Override
    public double getValor() {
        return num; 
    }

}
