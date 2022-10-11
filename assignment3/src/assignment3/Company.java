package assignment3;
import java.util.ArrayList;
public class Company {
    private ArrayList<Employee> listOfEmployees;
    final String END_OF_LINE = System.lineSeparator();

    public Company() {
        this.listOfEmployees = new ArrayList<>();
    }

    public String createEmployee(String id, String name, double grossSalary) {
        Employee employee = new Employee(id, name, grossSalary);
        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }
    public String createEmployee(String id, String name, double grossSalary, String DEGREE_TYPES) {
        Employee employee = new Manager(id, name, grossSalary, DEGREE_TYPES);
        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }
    public String createEmployee(String id,String name, double grossSalary,String DEGREE_TYPES, String department) {
        Employee employee = new Director(id, name, grossSalary, DEGREE_TYPES, department);
        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }
    public String createEmployee(String id, String name, double grossSalary, int GPA) {
        Employee employee = new Intern(id, name, grossSalary, GPA);
        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }

    public Employee findEmployee(String id) {
        for (Employee currentEmployee : listOfEmployees) {
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
        return Math.floor(Math.pow(10,2) * expenses / Math.pow(10,2));
    }

    //Your system should print a list of employees in ascending order based on their gross
    //salary. Note that this should be sorted based on the value of the gross salary after any
    //bonuses.
    public String retrieveSortedEmployees() {
        for (int i = 0; i < listOfEmployees.size(); i++) {
            for (int j = listOfEmployees.size() - 1; j < i; j--) {
                //how to access the gross salary AFTER any bonuses?
                if (listOfEmployees.get(i).getGrossSalary() > listOfEmployees.get(j).getGrossSalary()) {
                    Employee temp = listOfEmployees.get(i);
                    listOfEmployees.set(i, listOfEmployees.get(i));
                    listOfEmployees.set(j, temp);
                }
            }
        }
        String allEmployeesSorted = "";
        for (Employee employee : listOfEmployees) {
            allEmployeesSorted = allEmployeesSorted + employee.toString() + END_OF_LINE;
        }
        return "Employees sorted by gross salary (ascending order):" + END_OF_LINE + allEmployeesSorted;
    }

    public String updateEmployeeName(String id, String newName) {
        for (int i = 0; i < listOfEmployees.size(); i++) {
            Employee currentEmployee = listOfEmployees.get(i);
            if (id.equals(currentEmployee.getID())) {
                currentEmployee.setName(newName);
                return "Employee " + id + " was updated successfully";
            }
        }
        return null;
    }

    public String updateInternGPA(String id, int newGPA) {
        for (int i = 0; i < listOfEmployees.size(); i++) {
            Employee currentEmployee = listOfEmployees.get(i);
            if (id.equals(currentEmployee.getID())) {
                if (currentEmployee instanceof Intern) {
                    ((Intern) currentEmployee).setGpa(newGPA);
                    return "Employee " + id + " was updated successfully";
                }
            }
        }
        return null;
    }

   /* public String updateManagerDegree(String id, String newDegree)
    {
        for (int i=0; i<listOfEmployees.size();i++)
        {
            Employee currentEmployee = listOfEmployees.get(i);
            if(id.equals(currentEmployee.getID()))
            {
                if(currentEmployee instanceof Manager)
                {
                    ((Manager) currentEmployee).setDEGREE_TYPES(newDegree);
                    return "Employee "+ id + "was updated successfully";
                }
            }
        }
        return null;
    }

    */
    public String updateManagerDegree(String id, String newDegree) {
        if(findEmployee(id) instanceof Manager) {
            ((Manager) findEmployee(id)).setDEGREE_TYPES(newDegree);
            return "Employee "+ id + "was updated successfully";
        }
        else {
            return null;
        }
    }

    /*
    + removeEmployee(String id) : String –IM
    + retrieveEmployee() : String –IM : printEmployee and printAllEmployees
    + updateGrossSalary(String id, double newGrossSalary) –IM
    + updateDirectorDegree(String id, String newDegree) –IM
    + updateDirectorDept(String id, String newDept) –IM
     */
    public String removeEmployee(String empID) {
        this.listOfEmployees.remove(findEmployee(empID));
        return "Employee " + empID + " was successfully removed.";
    }

    public String printEmployee(String employeeID) {
        return findEmployee(employeeID).toString();
    }

    public String printAllEmployees() {

        String allEmployees = "";

        for (Employee employee : listOfEmployees) {
            allEmployees = allEmployees + employee.toString() + END_OF_LINE;
            //or instance of director, manager, intern
        }
        return allEmployees;
    }

    //we need a method get net salary; we search employeeID in the list and return net salary
    public double getNetSalary(String employeeID) {
        return findEmployee(employeeID).calculateNetSalary();
    }

   public String updateGrossSalary(String id, double newGrossSalary){
       findEmployee(id).setGrossSalary(newGrossSalary);
       return "Employee " + id + " was updated successfully";
   }

   public String updateDirectorDegree(String id, String newDegree) {
       if (findEmployee(id) instanceof Director) {
           ((Director) findEmployee(id)).setDEGREE_TYPES(newDegree);
           return "Employee " + id + " was updated successfully";
       }
       return null;
   }

   public String updateDirectorDept(String id, String newDept){
       if (findEmployee(id) instanceof Director) {
           ((Director)findEmployee(id)).setDepartment(newDept);
           return "Employee " + id + " was updated successfully";
       }
       return null;
   }
}