import behaviours.Eat;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Simulation {
    public static void main(String[] args) {
        System.out.println("Welcome to SimuPro");

        List<Duck> ducks = new ArrayList<>();

        Duck duck = new Duck("Duck", "Black");
        Duck ducky = new MallardDuck("Donald", "Red");
        Duck ducky2 = new RedheadDuck("Donald", "Red");
//        ducky2.sing(); // produces error

        WoodenDuck woodenDuck = new WoodenDuck("Wood", "Brown");
        MallardDuck mallardDuck = new MallardDuck("Mallard", "Green");
        RedheadDuck redheadDuck = new RedheadDuck("Redhead", "Blue");
        RubberDuck rubberDucky = new RubberDuck("Rubber", "Yellow");
//        redheadDuck.sing();
//
//        duck.talk();
//        ducky.talk();
//        ducky2.talk();
//
//        System.out.println("Casting");
//        RedheadDuck ducky3 = (RedheadDuck) ducky2;
//        ducky3.sing();

        System.out.println("eat methods");
        mallardDuck.eat();
        redheadDuck.eat();

        Eat eater1 = new MallardDuck("Mally Mal", "Purple");
        Eat eater2 = new RedheadDuck("Redz", "White");

        List<Eat> eatList = new ArrayList<>();
        eatList.add(eater1);
        eatList.add(eater2);

        ducks.add(duck);
        ducks.add(ducky);
        ducks.add(ducky2);
        ducks.add(mallardDuck);
        ducks.add(redheadDuck);
        ducks.add(rubberDucky);
        ducks.add(woodenDuck);

        for (Duck duckItem:
             ducks) {
            simulate(duckItem);
            System.out.println();
        }

        for (Eat eater :
                eatList) {
            simulate(eater);
            System.out.println();
        }
    }

    public static void simulate(Duck duck) {
        System.out.println("Simulation Starting...");
        System.out.println("I am a -> " + duck.getColor() + " " + duck.getType() + " duck");
        duck.displayType();
        System.out.println("Simulation complete!");
    }

    public static void simulate(Eat eaters) {

    }
}
