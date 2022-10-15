package employeetests;

import assignment3.Company;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ComapanyMain {
    public static void main(String[] args) throws Exception {
        Company facade = new Company();
        facade.createEmployee("Emp1", "Elektra", 35000.50, "MSc", "Business");       // G: 47000.60; N: 37600.48
        facade.createEmployee("Emp2", "Blanca", 45000.00, "PhD", "Human Resources"); // G: 65750.00; N: 45450.00
        facade.createEmployee("Emp3", "Pray Tell", 25000.25, "BSc");                 // G: 27500.27; N: 24750.24
        facade.createEmployee("Emp4", "Lulu", 20000.00, 9);                          // G: 21000.00; N: 21000.00
        facade.createEmployee("Emp5", "Angel", 28500.10, 7);                         // G: 28500.10; N: 28500.10
        facade.createEmployee("Emp6", "Candy", 35000.50, 4);                         // G:     0.00; N:     0.00
        facade.createEmployee("Emp7", "Ricky", 23500.00);                            // G: 23500.00; N: 21150.00
        facade.createEmployee("Emp8", "Damon", 22100.00);                            // G: 22100.00; N: 19890.00

       /* System.out.println( );
        System.out.println( garfield.makeNoise() );
        System.out.println( scooby.makeNoise() );
*/

        //Create a list of employees; Add all employees above.
        ArrayList<String> listOfEmployees = new ArrayList<>();
        listOfEmployees.add("Emp1");
        listOfEmployees.add("Emp2");
        listOfEmployees.add("Emp3");
        listOfEmployees.add("Emp4");
        listOfEmployees.add("Emp5");
        listOfEmployees.add("Emp6");
        listOfEmployees.add("Emp7");
        listOfEmployees.add("Emp8");

        Map<String, Integer> actualMap = facade.mapEachDegree();
        actualMap.get("PhD"); //1
        actualMap.get("MSc"); //1
        actualMap.get("BSc"); //1
        System.out.println("before" + facade.mapEachDegree());
        facade.removeEmployee("Emp2"); // Remove Blanca with the PhD

        System.out.println("after" + facade.mapEachDegree());

        //Adds temporary employees with more 3 MSc and 1 BSc
        facade.createEmployee("Temp1", "John Doe", 25000.0, "MSc");
        facade.createEmployee("Temp2", "Jane Doe", 25000.0, "MSc");
        facade.createEmployee("Temp3", "Mary Doe", 25000.0, "MSc");
        facade.createEmployee("Temp4", "Mark Doe", 25000.0, "BSc");


        actualMap = facade.mapEachDegree();
        if (actualMap.containsKey("PhD") == true)
            System.out.println("contains PhD");
        else {
            System.out.println("does not contain PhD");
      //System.out.println(actualMap);//False, there should not be a PhD anymore.
            actualMap.get("MSc"); //4
            actualMap.get("BSc"); //2

        }
    }
}
