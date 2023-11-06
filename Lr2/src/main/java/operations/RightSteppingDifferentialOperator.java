package operations;
import functions.*;
public class RightSteppingDifferentialOperator extends SteppingDifferentialOperator{
    RightSteppingDifferentialOperator(double step) {
        super(step);
    }
    public MathFunction derive(MathFunction function) {
        return ((x)->(function.apply(x+step)-function.apply(x))/step);
    }
}