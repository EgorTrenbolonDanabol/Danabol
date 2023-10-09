package functions;

public class MockTabulatedFunction extends AbstractTabulatedFunction {
    private final double x0, x1, y0, y1;

    public MockTabulatedFunction(double x0, double x1, double y0, double y1) {
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
        this.count = 2; // У нас всегда две точки в данном мок-объекте
    }

    protected int floorIndexOfX(double x) {
        if (x < x0) return 0;
        if (x >= x1) return 1;
        return 0;
    }

    protected double extrapolateLeft(double x) {
        return interpolate(x, x0, x1, y0, y1);
    }

    protected double extrapolateRight(double x) {
        return interpolate(x, x0, x1, y0, y1);
    }

    protected double interpolate(double x, int floorIndex) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    public int getCount() {
        return 0;
    }

    public double getX(int index) {
        if (index == 0) return x0;
        if (index == 1) return x1;
        throw new IllegalArgumentException("Index out of bounds: " + index);
    }

    public double getY(int index) {
        if (index == 0) return y0;
        if (index == 1) return y1;
        throw new IllegalArgumentException("Index out of bounds: " + index);
    }

    @Override
    public void setY(int index, double value) {

    }

    public int indexOfX(double x) {
        if (x == x0) return 0;
        if (x == x1) return 1;
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        return 0;
    }

    public double leftBound() {
        return x0;
    }

    public double rightBound() {
        return x1;
    }
}