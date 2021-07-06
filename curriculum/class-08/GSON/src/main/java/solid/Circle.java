package solid;
//
//public class Circle {
//
//    private final double radius;
//
//    public Circle(double radius) {
//        this.radius = radius;
//    }
//
//    public double getRadius() {
//        return radius;
//    }
//
////    public double area() {
////        return Math.PI * Math.pow(radius, 2);
////    }
//}

public class Circle implements AreaInterface, ShapeInterface {

    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double calculate() {
        return this.area();
    }
}