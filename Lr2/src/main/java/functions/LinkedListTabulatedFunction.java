package functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    class Node {
        public double x;
        public double y;
        public Node next;
        public Node prev;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
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
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
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
        return getNode(index).x;
    }

    public double getY(int index) {
        return getNode(index).y;
    }

    public void setY(int index, double value) {
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
}