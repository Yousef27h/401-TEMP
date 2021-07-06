package solid;
//
//public class Square {
//
//    private final double length;
//
//    public Square(double length) {
//        this.length = length;
//    }
//
//    public double getLength() {
//        return length;
//    }
//
//    public double area() {
//        return Math.pow(length, 2);
//    }
//}

public class Square implements AreaInterface, ShapeInterface {

    private final double length;

    public Square(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    @Override
    public double area() {
        return Math.pow(length, 2);
    }

    @Override
    public double calculate() {
        return this.area();
    }
}