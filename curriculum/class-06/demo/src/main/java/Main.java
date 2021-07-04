import inheritance.ASACStudent;
import inheritance.EngineeringStudent;
import inheritance.HospitalityStudent;
import inheritance.Student;
import structures.BinarySearchTree;
import structures.LinkedList;

public class Main {
    private static final LinkedList<String> list = new LinkedList<>();
    private static final BinarySearchTree<Integer> tree = new BinarySearchTree<>();

    public static void main(String[] args) {

//        Student student = new Student("Mariam Odat", "+9627775678900");
//        student.setAddress("Nowhere Texas");
//        student.setAddress("Amman Jordan");
//
//        ASACStudent asacStudent = new ASACStudent("Motasim Al-azzam", "+9627898875665", true);
//        HospitalityStudent hospitalityStudent = new HospitalityStudent("George Odell", "+96276474646");
//
//        Student student1 = new ASACStudent("Jason Jones", "12345", true, "Harmony Lodge");

        // figure this out why does this not compile
        Student testStudent = new Student("Omar Zain", "555-5555");
        ASACStudent asacStudent1 = (ASACStudent) testStudent;
        System.out.println("Name: " + asacStudent1.getName());
        System.out.println("Number: " + asacStudent1.getNumber());
        System.out.println("Address: " + asacStudent1.getAddress());
        System.out.println("PC?: " + asacStudent1.isHasPC());
        System.out.println("The Class: " + asacStudent1.getClass());

        /*
        // student object
        System.out.println("My name is: " + student.getName());
        System.out.println("My number is: " + student.getNumber());
        System.out.println("My address is: " + student.getAddress());

        System.out.println("");
        System.out.println("");

        // asac student
        System.out.println("My name is: " + asacStudent.getName());
        System.out.println("My number is: " + asacStudent.getNumber());
        System.out.println("Do i have a PC: " + asacStudent.isHasPC());
        System.out.println("My address is: " + asacStudent.getAddress());

        System.out.println("");
        System.out.println("");

        System.out.println("My name is: " + student1.getName());
        System.out.println("My number is: " + student1.getNumber());
        System.out.println("My address is: " + student1.getAddress());

        System.out.println("");
        System.out.println("");

        // hospitality student
        System.out.println("My name is: " + hospitalityStudent.getName());
        System.out.println("My number is: " + hospitalityStudent.getNumber());

         */
    }
}
