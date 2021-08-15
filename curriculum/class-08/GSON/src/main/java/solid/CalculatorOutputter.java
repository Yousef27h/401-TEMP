package solid;

public class CalculatorOutputter {

    private AreaSumCalculator sumCalculator;

    public CalculatorOutputter(AreaSumCalculator sumCalculator) {
        this.sumCalculator = sumCalculator;
    }

    // make this actual json
    public String outputAsJSON() {
        return "The json is -> " + sumCalculator.sum();
    }

    public String outputAsHTML() {
        // implement this
         return null;
    }
}
