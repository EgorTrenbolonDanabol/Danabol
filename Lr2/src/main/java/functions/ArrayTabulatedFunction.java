package functions;
import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private double[] xValues;
    private double[] yValues;
    private int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new IllegalArgumentException("xValues and yValues must have the same length");
        }
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        this.count = xValues.length;
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        this.count = count;
        this.xValues = new double[count];
        this.yValues = new double[count];

        if (xFrom == xTo) {
            Arrays.fill(xValues, xFrom);
            for (int i = 0; i < count; i++) {
                yValues[i] = source.apply(xFrom);
            }
        } else {
            double step = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; i++) {
                xValues[i] = xFrom + i * step;
                yValues[i] = source.apply(xValues[i]);
            }
        }
    }

    public int getCount() {
        return count;
    }

    public double getX(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        return xValues[index];
    }

    public double getY(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        return yValues[index];
    }

    public void setY(int index, double value) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        yValues[index] = value;
    }
    public double leftBound() {
        return xValues[0];
    }

    public double rightBound() {
        return xValues[count - 1];
    }

    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    public int floorIndexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] > x) {
                return i - 1;
            }
        }
        return count - 1;
    }

    protected double extrapolateLeft(double x) {
        return 0;
    }

    protected double extrapolateRight(double x) {
        return 0;
    }

    protected double interpolate(double x, int floorIndex) {
        return 0;
    }
}