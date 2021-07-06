package solid;

import java.util.List;

public class VolumeSumCalculator extends AreaSumCalculator {
    public VolumeSumCalculator(List<ShapeInterface> shapes) {
        super(shapes);
    }

    @Override
    public double sum() {
        for (ShapeInterface shape : getShapes()) {
            shape.calculate();
        }

        return getSum();
    }
}
