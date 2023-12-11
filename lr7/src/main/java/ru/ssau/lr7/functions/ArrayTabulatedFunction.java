package ru.ssau.lr7.functions;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import ru.ssau.lr7.exceptions.InterpolationException;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Serializable {
    private static final long serialVersionUID = -3585087032471720766L;
    private double[] xValues;
    private double[] yValues;
    private int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        checkLengthIsTheSame(xValues, yValues);
        //checkSorted(xValues);
        if (xValues.length != yValues.length) {
            throw new IllegalArgumentException("xValues and yValues must have the same length");
        }

        if (xValues.length < 2) throw new IllegalArgumentException("Length is less than the minimum");
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        this.count = xValues.length;
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) throw new IllegalArgumentException("Length is less than the minimum");

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
        if (floorIndex < 0 || floorIndex >= count - 1) {
            throw new InterpolationException("Interpolation index is out of bounds.");
        }

        double leftX = xValues[floorIndex];
        double rightX = xValues[floorIndex + 1];

        if (x < leftX || x > rightX) {
            throw new InterpolationException("Interpolation point is outside the interpolation interval.");
        }

        double leftY = yValues[floorIndex];
        double rightY = yValues[floorIndex + 1];

        return leftY + (rightY - leftY) / (rightX - leftX) * (x - leftX);
    }

/*
    @Override
    public String toString() {
        String ans = "(" + this.xValues[0] + "; " + this.yValues[0] + ")";
        for (int i = 1; i < this.count; i++) {
            ans += " (" + this.xValues[i] + "; " + this.yValues[i] + ")";
        }
        return ans;
    }
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTabulatedFunction that = (ArrayTabulatedFunction) o;
        return count == that.count && Arrays.equals(xValues, that.xValues) && Arrays.equals(yValues, that.yValues);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(xValues);
        result = 31 * result + Arrays.hashCode(yValues);
        result = 31 * result + count;
        return result;
    }

    @Override
    protected Object clone() {
        return new ArrayTabulatedFunction(this.xValues, this.yValues);
    }

    @Override
    public Iterator<Point> iterator() {
        Iterator<Point> iterator = new Iterator<Point>() {
            int i;

            @Override
            public boolean hasNext() {
                return (i < count);
            }

            @Override
            public Point next() {
                if (hasNext())
                    return new Point(xValues[i], yValues[i++]);
                else
                    throw new NoSuchElementException();
            }
        };
        return iterator;
    }
}