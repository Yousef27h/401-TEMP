package solid;

public class Sphere implements AreaInterface, VolumeInterface, ShapeInterface {

    private static final Double CONSTANT = 1.3;
    private double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public double volume() {
        return CONSTANT * Math.PI * Math.pow(radius, 3);
    }

    @Override
    public double area() {
        return 10; // implement on  your own
    }

    @Override
    public double calculate() {
        return this.area();
    }
}
