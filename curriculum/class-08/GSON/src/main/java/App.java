import com.google.gson.Gson;
import gson.asac.User;
import solid.*;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws NoSuchFieldException {
        ArrayList<ShapeInterface> shapes = new ArrayList<>();
        shapes.add(new Circle(4));
        shapes.add(new Square(4));
        shapes.add(new Triangle(10, 20));
        shapes.add(new Cuboid(10, 20, 30));
        shapes.add(new Sphere(10));
        shapes.add(new Triangle(12, 44));

        AreaSumCalculator areaSumCalculator = new AreaSumCalculator(shapes);
//        calculator.sum();
//        System.out.println(calculator.output());

        CalculatorOutputter outputter = new CalculatorOutputter(areaSumCalculator);
        System.out.println(outputter.outputAsJSON());

//        VolumeSumCalculator volumeSumCalculator = new VolumeSumCalculator(shapes);
//        CalculatorOutputter calculatorOutputter = new CalculatorOutputter(volumeSumCalculator);
//        System.out.println(calculatorOutputter.outputAsJSON());

        Gson gson = new Gson();

        User mariam = new User(
                "Mariam",
                "Odat",
                "555-555-5555",
                "mariam@mariam.com",
                "password",
                24
        );

        User motasim = new User("motasim@gmail.com");

        System.out.println("GSON output");
        String jsonOutput = gson.toJson(mariam);
        String jsonOutput1 = gson.toJson(motasim);
        System.out.println(jsonOutput);
        System.out.println(jsonOutput1);

        User userObj = gson.fromJson(jsonOutput, User.class);
        User userObj1 = gson.fromJson(jsonOutput1, User.class);
        System.out.println(userObj.getFirstName());
        System.out.println(userObj.getLastName());
        System.out.println(userObj1.getEmail());
        System.out.println(userObj1.getLastName());

        System.out.println(userObj.getClass().getSimpleName());
        System.out.println(User.class);
    }
}
