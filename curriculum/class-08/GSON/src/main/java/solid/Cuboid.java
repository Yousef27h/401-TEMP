package solid;

public class Cuboid implements AreaInterface, VolumeInterface, ShapeInterface {
    private double length;
    private double width;
    private double height;

    public Cuboid(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return 30; // implement on  your own
    }

    @Override
    public double volume() {
        return length * width * height;
    }

    @Override
    public double calculate() {
        return this.area();
    }
}
