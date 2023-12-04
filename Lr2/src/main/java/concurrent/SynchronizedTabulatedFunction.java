package concurrent;

import functions.Point;
import functions.TabulatedFunction;
import operations.TabulatedFunctionOperationService;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SynchronizedTabulatedFunction implements TabulatedFunction {

    private final TabulatedFunction tabulatedFunction;
    private final Object lock;
    public SynchronizedTabulatedFunction(TabulatedFunction tabulatedFunction){
        this.tabulatedFunction = tabulatedFunction;
        this.lock = this;
    }

    @Override
    public double apply(double x) {
        synchronized (lock){ return tabulatedFunction.apply(x);}
    }

    @Override
    public int getCount() {
        synchronized (lock){ return tabulatedFunction.getCount();}
    }

    @Override
    public double getX(int index) {
        synchronized (lock){
            return tabulatedFunction.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (lock){
            return tabulatedFunction.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (lock){
            tabulatedFunction.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (lock){
            return tabulatedFunction.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (lock){
            return tabulatedFunction.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (lock){
            return tabulatedFunction.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (lock){
            return tabulatedFunction.rightBound();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        synchronized (lock){
            Point[] points = TabulatedFunctionOperationService.asPoints(tabulatedFunction);
            return new Iterator<Point>() {
                private int i = 0;

                @Override
                public boolean hasNext() {
                    return (i < points.length);
                }

                @Override
                public Point next() {
                    if (hasNext()) {
                        Point point = points[i];
                        ++i;
                        return point;
                    } else throw new NoSuchElementException();
                }
            };

        }
    }

    public interface Operation<T>{
        T apply(SynchronizedTabulatedFunction synchronizedTabulatedFunction);
    }

    public <T> T doSynchronously(Operation<T> operation){
        synchronized (lock){
            return operation.apply(this);
        }
    }

}