package assignment3;
import java.util.ArrayList;
public class Company
{
    private ArrayList<Employee> listOfEmployees;

    public Company ()
    {
        this.listOfEmployees= new ArrayList<>();
    }

    public void registerEmployee (String id, String name, double grossSalary)
    {
        Employee employee = new Employee (id, name, grossSalary);
        this.listOfEmployees.add(employee);
    }

    public Employee findEmployee (String id)
    {
        for (int i=0; i<listOfEmployees.size(); i++)
        {
            Employee currentEmployee = listOfEmployees.get(i);
            if(id.equals(currentEmployee.getID()))
            {
                return currentEmployee;
            }
        }
        return null;
    }

    /*
    //Your system should print the sum of all net salaries for all employees. Similarly to all
    //salaries, the system should truncate the result of this operation in two decimal values.
    public double retrieveExpenses() {
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

    public double updateEmployeeName(String id, String newName) {
    }

    public double updateInternGPA() {
    }

    public String toString() {
    }
     */
}
/*
+ removeEmployee(String id) : String –IM
+ retrieveEmployee() : String –IM : printEmployee and printAllEmployees
+ updateGrossSalary(String id, double newGrossSalary) –IM
+ updateDirectorDegree(String id, String newDegree) –IM
+ updateDirectorDept(String id, String newDept) –IM
 */
public removeEmployee(String empID){

}