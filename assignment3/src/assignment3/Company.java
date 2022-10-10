package assignment3;
import java.util.ArrayList;
public class Company {
    private ArrayList<Employee> listOfEmployees;

    public Company() {
        this.listOfEmployees = new ArrayList<>();
    }

    public void createEmployee(String id, String name, double grossSalary) { //i think we need to add GPA here too, and degree and dept
        Employee employee = new Employee(id, name, grossSalary);
        this.listOfEmployees.add(employee);
    }

    public Employee findEmployee(String id) {
        for (int i = 0; i < listOfEmployees.size(); i++) {
            Employee currentEmployee = listOfEmployees.get(i);
            if (id.equals(currentEmployee.getID())) {
                return currentEmployee;
            }
        }
        return null;
    }

    //Your system should print the sum of all net salaries for all employees. Similarly to all
    //salaries, the system should truncate the result of this operation in two decimal values.
    public double retrieveExpenses() {
        double expenses = 0.0;
        if (!this.listOfEmployees.isEmpty()) {
            for (Employee currentEmployee : listOfEmployees) {
                expenses = currentEmployee.calculateNetSalary() + expenses;
            }
        }
        return expenses;
    }

    //Your system should print a list of employees in ascending order based on their gross
    //salary. Note that this should be sorted based on the value of the gross salary after any
    //bonuses. The list should follow the template below:
    //Employees sorted by gross salary (ascending order):
    //<employee_string>
    //<employee_string>
    //<employee_string>
    //<employee_string>
    public double retrieveSortedEmployees() {
    }

    public String updateEmployeeName(String id, String newName) {
        for (int i = 0; i < listOfEmployees.size(); i++) {
            Employee currentEmployee = listOfEmployees.get(i);
            if (id.equals(currentEmployee.getID())) {
                newName = currentEmployee.getName();
                return newName;
            }
        }
        return null;
    }

    //how to get the gpa here to update it?
    public double updateInternGPA(String id, int newGPA) {
        for (int i = 0; i < listOfEmployees.size(); i++) {
            Employee currentEmployee = listOfEmployees.get(i);
            if (id.equals(currentEmployee.getID())) {
                newGPA = currentEmployee.getGpa();
            }
        }
        return newGPA;
    }

    /*
    public String toString(String id) {

    }*/

    /*
    + removeEmployee(String id) : String –IM
    + retrieveEmployee() : String –IM : printEmployee and printAllEmployees
    + updateGrossSalary(String id, double newGrossSalary) –IM
    + updateDirectorDegree(String id, String newDegree) –IM
    + updateDirectorDept(String id, String newDept) –IM
     */
    public String removeEmployee(String empID) {
        for (int i = 0; i < listOfEmployees.size(); i++) {
            Employee currentEmployee = listOfEmployees.get(i);
            if (empID.equals(currentEmployee.getID())) {
                this.listOfEmployees.remove(currentEmployee);
            }
        }
        return "Employee" + empID + " was successfully removed.";
    }

    public String printEmployee(String employeeID) {
        //To retrieve a string of an employee,
        // The customer then needs to specify the ID of the employee that it wants to get the string from.
        // The program should then retrieve the employee as specified in their respective description
        // (User Stories 1.2 and 1.3).
        for (int i = 0; i < listOfEmployees.size(); i++) {
            Employee currentEmployee = listOfEmployees.get(i);
            if (employeeID.equals(currentEmployee.getID())) {
                return currentEmployee.toString();
            }
        }
        return null;
    }
}

    /*public String toString() {
        final String END_OF_LINE = System.lineSeparator(); // "\n"
        String companyStr = "";

        for(Employee employee : employees) {
            companyStr = companyStr + employee.toString()  + END_OF_LINE;
        }

        return companyStr;
    }


    public String printAllEmployees(){

    }

    //we need a method get net salary; we search employeeID in the list and return net salary
    public double getNetSalary(String employeeID) {
    }

}

     */