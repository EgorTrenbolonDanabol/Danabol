package functions;

public class IdentityFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return x;
    }
    public String toString() {
        return "IdentityFunction";
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }
    public int hashCode() {
        return getClass().hashCode();
    }
    public Object clone() {
        return new IdentityFunction();
    }
}