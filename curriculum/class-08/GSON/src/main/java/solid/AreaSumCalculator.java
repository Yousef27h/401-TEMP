package solid;
//
//import java.util.List;
//
//public class Calculator {
//    private final List<Object> shapes;
//    private double sum;
//
//    public Calculator(List<Object> shapes) {
//        this.shapes = shapes;
//    }
//
//    public double sum() {
//        for (Object object :
//                shapes) {
//            if (object instanceof Square) {
//                sum = sum + Math.pow(((Square) object).getLength(), 2);
//            } else if (object instanceof Circle) {
//                sum = sum + Math.PI * Math.pow(((Circle) object).getRadius(), 2);
//            }
//        }
//
//        return sum;
//    }
//
//    public List<Object> getShapes() {
//        return shapes;
//    }
//
//    public double getSum() {
//        return sum;
//    }
//
//    public void setSum(double sum) {
//        this.sum = sum;
//    }
//
//    public String output() {
//        return String.format("the sum of the areas are %f", sum);
//    }
//}

import java.util.List;

public class AreaSumCalculator {
    private List<ShapeInterface> shapes;
    private double sum;

    public AreaSumCalculator(List<ShapeInterface> shapes) {
        this.shapes = shapes;
    }

    public double sum() {
        for (ShapeInterface shape : shapes) {
            shape.calculate();
        }
        return sum;
    }

    public List<ShapeInterface> getShapes() {
        return shapes;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}