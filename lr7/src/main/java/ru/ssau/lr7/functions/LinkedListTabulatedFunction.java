package ru.ssau.lr7.functions;
import java.util.Objects;
import java.util.Iterator;
import java.util.NoSuchElementException;
import ru.ssau.lr7.exceptions.InterpolationException;
import java.io.Serializable;
public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Serializable{
    private static final long serialVersionUID = -2135922596401784690L;

    static class Node implements Cloneable, Serializable{
        public double x;
        public double y;
        public Node next;
        public Node prev;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
        public String toString() {
            return "(" + x + "; " + y + ")";
        }
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return Double.compare(node.x, x) == 0 &&
                    Double.compare(node.y, y) == 0;
        }
        public int hashCode(){
            return Objects.hash(x,y);
        }
        public Object clone() throws CloneNotSupportedException {
            Node clone = (Node) super.clone();
            clone.next = null;
            clone.prev = null;
            return clone;
        }

    }
    private Node head = null;
    private int count = 0;

    protected void  addNode(double x, double y) {
        Node node = new Node(x, y);
        if (head == null) {
            head = node;
            head.next = head;
            head.prev = head;
        } else {
            Node last = head.prev;
            last.next = node;
            node.prev = last;
            node.next = head;
            head.prev = node;
        }
        count++;
    }
    protected Node getNode(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        //checkSorted(xValues);
        checkLengthIsTheSame(xValues, yValues);
        if (xValues.length < 2)
        {throw new IllegalArgumentException("Array length < 2");}

        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2)
        {throw new IllegalArgumentException("Count must be at least 2");}

        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }

        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            double x = xFrom + i * step;
            addNode(x, source.apply(x));
        }
    }

    public int getCount() {
        return this.count;
    }

    // Метод, получающий значение аргумента x по номеру индекса
    public double getX(int index) {
        if (index < 0 || index >= count){
            throw new IllegalArgumentException("the index goes beyond the acceptable values");}

        return getNode(index).x;
    }

    public double getY(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("the index goes beyond the acceptable values");
        }

        return getNode(index).y;
    }

    public void setY(int index, double value) {
        if (index < 0 || index >= count){
            throw new IllegalArgumentException("the index goes beyond the acceptable values");
        }
        getNode(index).y = value;
    }

    public int indexOfX(double x) {
        Node node = this.head;
        for (int i = 0; i < this.count; i++) {
            if (node.x == x)
                return i;
            node = node.next;
        }
        return -1;
    }

    public int indexOfY(double y) {
        Node node = this.head;
        for (int i = 0; i < this.count; i++) {
            if (node.y == y)
                return i;
            node = node.next;
        }
        return -1;
    }

    public double leftBound() {
        return head.x;
    }

    public double rightBound() {
        return head.prev.x;
    }

    protected int floorIndexOfX(double x) {
        if (head.x > x) return 0;
        else {
            Node elem = head;
            int i = 1;
            do {
                elem = elem.next;
                if (elem.x > x) return (i - 1);
                else if (elem.x == x) return i;
                i++;
            } while (elem != head.prev);
        }
        return count;
    }
    protected double interpolate(double x, int floorIndex) {
        if (head.next == head) {
            return head.y;
        }
        else {
            double leftX = getX(floorIndex - 1);
            double leftY = getY(floorIndex - 1);
            double rightX = getX(floorIndex);
            double rightY = getY(floorIndex);
            if (x < leftX || x > rightX) {
                throw new InterpolationException("Interpolation point is outside the interpolation interval.");
            }
            return interpolate(x, leftX, rightX, leftY, rightY);
        }

    }

    protected double extrapolateLeft(double x) {
        return (head.y + (((head.next.y - head.y) / (head.next.x - head.x)) * (x - head.x)));
    }

    protected double extrapolateRight(double x) {
        return (head.prev.prev.y + (((head.prev.y - head.prev.prev.y) / (head.prev.x - head.prev.prev.x)) * (x - head.prev.prev.x)));
    }

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return (leftY + (((rightY - leftY) / (rightX - leftX)) * (x - leftX)));
    }

    //X* задание 2
    private Node floorNodeOfX(double x){
        Node node = this.head;
        for (int i = 0; i < this.count; i++) {
            if (node.x == x)
                return node;
            else if (node.x > x) {
                return (i == 0) ? head : node.prev;
            }
            node = node.next;
        }
        return head;
    }
    public double apply(double x) {
        if(x < leftBound()){
            return extrapolateLeft(x);
        }
        if (x > rightBound()) {
            return extrapolateRight(x);
        }
        int idxx = indexOfX(x);
        if (idxx != -1)
            return getY(idxx);

        Node node = floorNodeOfX(x);
        double leftX = node.prev.x;
        double leftY = node.prev.y;
        double rightX = node.x;
        double rightY = node.y;
        return leftY + ((rightY - leftY) / (rightX - leftX)) * (x - leftX);

    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedListTabulatedFunction that = (LinkedListTabulatedFunction) o;
        if (this.count != that.count) return false;
        // Проверьте точки для эквивалентности
        for (int i = 0; i < this.count; i++) {
            if (this.getX(i) != that.getX(i) || this.getY(i) != that.getY(i)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode(){
        int result = 0;
        Node elem = head;
        do {
            result = 31 * result + elem.hashCode();
            elem = elem.next;
        }while(elem != head);
        return result;
    }

    public Object clone() {
        double[] xValuesClone = new double[count];
        double[] yValuesClone = new double[count];
        Node elem = head;
        for (int i = 0; i < count; i++) {
            xValuesClone[i] = elem.x;
            yValuesClone[i] = elem.y;
            elem = elem.next;
        }
        LinkedListTabulatedFunction clone = new LinkedListTabulatedFunction(xValuesClone, yValuesClone);
        return clone;
    }
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private Node currentNode = head;
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < count;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                double x = currentNode.x;
                double y = currentNode.y;
                currentNode = currentNode.next;
                currentIndex++;

                return new Point(x, y);
            }
        };
    }
}