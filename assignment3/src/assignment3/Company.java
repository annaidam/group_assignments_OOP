package assignment3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Company {
    private ArrayList<Employee> listOfEmployees;
    private HashMap<String, Integer> degreeMap = new HashMap<>();
    final String END_OF_LINE = System.lineSeparator();
    boolean foundEmployee = false;

    public Company() {
        this.listOfEmployees = new ArrayList<>();
    }

    public String createEmployee(String id, String name, double grossSalary) throws Exception {
        Employee employee = new Employee(id, name, grossSalary);

        //most of the try catch is in the tests, we need to figure out where to throw and where to catch
        // ////////////
        for (Employee currentEmployee : listOfEmployees) {
            if (id.equals(currentEmployee.getID())) {
                throw new InvalidCompanyException("Cannot register. The ID " + id + " is already registered.");
            }
        }

        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }

    public String createEmployee(String id, String name, double grossSalary, String DEGREE_TYPES) throws Exception {
        Employee employee = new Manager(id, name, grossSalary, DEGREE_TYPES);

        ////////////throw from update, throw from setter
        for (Employee currentEmployee : listOfEmployees) {
            if (id.equals(currentEmployee.getID())) {
                throw new InvalidCompanyException("Cannot register. The ID " + id + " is already registered.");
            }
        }
        boolean containsBSc = DEGREE_TYPES.contains("BSc"); //equlas
        boolean containsMSc = DEGREE_TYPES.contains("MSc");
        boolean containsPhD = DEGREE_TYPES.contains("PhD");

        if (!containsBSc || !containsMSc || !containsPhD) {
            throw new InvalidEmployeeException("Degree must be one of the options: BSc, MSc or PhD.");
        }

        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }

    public String createEmployee(String id, String name, double grossSalary, String DEGREE_TYPES, String department) throws Exception {
        Employee employee = new Director(id, name, grossSalary, DEGREE_TYPES, department);

        //check exceptions
        for (Employee currentEmployee : listOfEmployees) {
            if (id.equals(currentEmployee.getID())) {
                throw new InvalidCompanyException("Cannot register. The ID " + id + " is already registered.");
            }
        }

        boolean containsBSc = DEGREE_TYPES.contains("BSc");
        boolean containsMSc = DEGREE_TYPES.contains("MSc");
        boolean containsPhD = DEGREE_TYPES.contains("PhD");

        if (!containsBSc || !containsMSc || !containsPhD) {
            throw new InvalidEmployeeException("Degree must be one of the options: BSc, MSc or PhD.");
        }

        boolean containsBusiness = department.contains("Business");
        boolean containsHumanResources = department.contains("Human Resources");
        boolean containsTechnical = department.contains("Technical");

        if (!containsBusiness || !containsHumanResources || !containsTechnical) {
            throw new InvalidEmployeeException("DDepartment must be one of the options: Business, Human Resources or Technical.");
        }
        //finish checking exceptions

        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }

    public String createEmployee(String id, String name, double grossSalary, int GPA) throws Exception {
        Employee employee = new Intern(id, name, grossSalary, GPA);

        for (Employee currentEmployee : listOfEmployees) {
            if (id.equals(currentEmployee.getID())) {
                throw new InvalidCompanyException("Cannot register. The ID " + id + " is already registered.");
            }
        }
        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }

    /* private Employee findEmployee(String ID) {
        for (int i = 0; i < employeeList.size(); i++) {
            Employee currentEmployee = employeeList.get(i);
            if (currentEmployee.getId().equals(ID)) {
                return currentEmployee;
            }
        }
        return null;
    }

     */

    /*public Employee findEmployee(String id) throws Exception {
     for (Employee currentEmployee : listOfEmployees) {
         if (!id.equals(currentEmployee.getID())) {
             throw new InvalidCompanyException("Employee " + id + " was not registered yet.");
         } else {
             return currentEmployee;
         }
     }
 }

  */
    public Employee findEmployee(String id) throws Exception {
        Employee currentEmployee = null;
        for (int i = 0; i < listOfEmployees.size(); i++) {
            currentEmployee = listOfEmployees.get(i);
            if (currentEmployee.getID().equals(id)) {
                foundEmployee = true;
            }
        }
        if (!foundEmployee) {
            throw new InvalidCompanyException("Employee " + id + " was not registered yet.");
        } else {
            return currentEmployee;
        }
    }

    public String removeEmployee(String empID) throws Exception {
        for (Employee currentEmployee : listOfEmployees) {
            if (!empID.equals(currentEmployee.getID())) {
                throw new InvalidCompanyException("Employee " + empID + " was not registered yet.");
            }
        }
        this.listOfEmployees.remove(findEmployee(empID));
        return "Employee " + empID + " was successfully removed.";
    }

    public String printEmployee(String employeeID) throws Exception {
        for (Employee currentEmployee : listOfEmployees) {
            if (!employeeID.equals(currentEmployee.getID())) {
                throw new InvalidCompanyException("Employee " + employeeID + " was not registered yet.");
            }
        }
        return findEmployee(employeeID).toString();
    }

    public String printAllEmployees() throws Exception {
        String allEmployees = "";

        if (listOfEmployees.isEmpty()) {
            throw new InvalidCompanyException("No employee has been registered yet.");
        }

        for (Employee employee : listOfEmployees) {
            allEmployees = allEmployees + employee.toString() + END_OF_LINE;
        }
        return "All registered employees:" + END_OF_LINE + allEmployees;
    }

    public double getNetSalary(String employeeID) throws Exception {
        for (Employee currentEmployee : listOfEmployees) {
            if (!employeeID.equals(currentEmployee.getID())) {
                throw new InvalidCompanyException("Employee " + employeeID + " was not registered yet.");
            }
        }
        return findEmployee(employeeID).calculateNetSalary();
    }

    public double getTotalNetSalary() throws Exception {
        if (listOfEmployees.isEmpty()) {
            throw new InvalidCompanyException("No employee has been registered yet.");
        }
        double expenses = 0.0;
        if (!this.listOfEmployees.isEmpty()) {
            for (Employee currentEmployee : listOfEmployees) {
                expenses = currentEmployee.calculateNetSalary() + expenses;
            }
            double temporary1 = expenses * 100;
            double temporary2 = (int) temporary1;
            expenses = temporary2 / 100.0;
        }
        return expenses;
    }

    /*
    public interface Comparable<E> {
        public double compareTo(E o);
    }
     */

    /*
    The compareTo() method determines the order of one object (object1) with the specified
    object o (object2) and returns the following:
    1. Returns 1 if object1 > object2;
    2. Returns 0 if object1 == object2;
    3. Returns -1 if object1 < object2;
     */

    //Sort again using stuff we will learn tomorrow
    public String printSortedEmployees() throws Exception {
        if (listOfEmployees.isEmpty()) {
            throw new InvalidCompanyException("No employee has been registered yet.");
        }
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

    public String updateEmployeeName(String id, String newName) throws Exception {
        for (int i = 0; i < listOfEmployees.size(); i++) {
            Employee currentEmployee = listOfEmployees.get(i);
            if (id.equals(currentEmployee.getID())) {
                currentEmployee.setName(newName);
                return "Employee " + id + " was updated successfully";
            }
        }
        return null;
    }

    public String updateGrossSalary(String id, double newGrossSalary) throws Exception {
        findEmployee(id).setGrossSalary(newGrossSalary);
        return "Employee " + id + " was updated successfully";
    }

    public String updateManagerDegree(String id, String newDegree) throws Exception {
        if (findEmployee(id) instanceof Manager) {
            ((Manager) findEmployee(id)).setDEGREE_TYPES(newDegree);
            return "Employee " + id + " was updated successfully";
        } else {
            return null;
        }
    }

    public String updateDirectorDegree(String id, String newDegree) throws Exception {
        if (findEmployee(id) instanceof Director) {
            ((Director) findEmployee(id)).setDEGREE_TYPES(newDegree);
            return "Employee " + id + " was updated successfully";
        }
        return null;
    }

    public String updateDirectorDept(String id, String newDept) throws Exception {
        if (findEmployee(id) instanceof Director) {
            ((Director) findEmployee(id)).setDepartment(newDept);
            return "Employee " + id + " was updated successfully";
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

    /*PROMOTIONS:
        1. Retrieve the Employee;
        2. Save all of his/her information (name and raw gross salary);
        3. Create a “new employee” with the corresponding new type and specify all of their
        new required information (when applicable), along with the information saved in step
        2;
        4. Remove the “old employee” from the database;
        5. Add the new Employee.*/

    public void promoteToManager(String id, String degree) throws Exception {
        String originalName = findEmployee(id).getName();
        double originalSalary = findEmployee(id).getGrossSalary();
        Employee promotedEmployee = new Manager(id, originalName, originalSalary, degree);
        removeEmployee(id);
        listOfEmployees.add(promotedEmployee);
    }

    public void promoteToDirector(String id, String degree, String department) throws Exception {
        String originalName = findEmployee(id).getName();
        double originalSalary = findEmployee(id).getGrossSalary();
        Employee promotedEmployee = new Director(id, originalName, originalSalary, degree, department);
        removeEmployee(id);
        listOfEmployees.add(promotedEmployee);
    }

    public void promoteToIntern(String id, int GPA) throws Exception {
        String originalName = findEmployee(id).getName();
        double originalSalary = findEmployee(id).getGrossSalary();
        Employee promotedEmployee = new Intern(id, originalName, originalSalary, GPA);
        removeEmployee(id);
        listOfEmployees.add(promotedEmployee);
    }

    //"Academic background of employees:" + END_OF_LINE;
    //"BSc: => " + counterBSc + END_OF_LINE;
    //"MSc: => " + counterMSc + END_OF_LINE;
    //"PhD: => " + counterPhD + END_OF_LINE;

    /*
    public void checkDegrees(){
        // Go through each post
        for (Employee current : listOfEmployees) {
            //Retrieve the hashtags in that post. Then, for each Hashtag:
            //Check if the hashtag was mentioned before (it would exist as a key in the map).
            //If yes, then retrieve the number of times mentioned, increment and put back in map.
            //If not, then add the hashtag to the map as a key with value 1 (mentioned once).
            HashSet<String> degrees = current.getDegrees();
            for (String degree : degrees){
                if (mapEachDegree.containsKey(degree) ){
                    int numOfDegrees = mapEachDegree.get(degree);
                    numOfDegrees = numOfDegrees + 1;
                    mapEachDegree.put(degree, numOfDegrees);
                } else {
                    mapEachDegree.put(degree, 1);
                }
            }
        }
    }

    public HashMap<String, Integer> getDegrees(){
        return this.mapEachDegree;
    }
     */

    public Map mapEachDegree() {
        int counterBSc = 0, counterMSc = 0, counterPhD = 0;
        for (Employee current : listOfEmployees) {
            if (current instanceof Manager || current instanceof Director) {
                if (((Manager) current).getDEGREE_TYPES().equals("BSc")) {
                    counterBSc = counterBSc + 1;
                } else if (((Manager) current).getDEGREE_TYPES().equals("MSc")) {
                    counterMSc = counterMSc + 1;
                } else if (((Manager) current).getDEGREE_TYPES().equals("PhD")) {
                    counterPhD = counterPhD + 1;
                }
            }
        }
        String BSc = "";
        String MSc = "";
        String PhD = "";
        if (counterBSc > 0) {
            BSc = "BSc: => " + counterBSc + END_OF_LINE;
        } else if (counterMSc > 0) {
            MSc = "MSc: => " + counterMSc + END_OF_LINE;
        } else if (counterPhD > 0) {
            PhD = "PhD: => " + counterPhD + END_OF_LINE;
        }
        System.out.println("Academic background of employees:" + END_OF_LINE + BSc + MSc + PhD);
        return mapEachDegree();
        //return "Academic background of employees:" + END_OF_LINE + BSc + MSc + PhD;
    }
}

   /* public Map<String, Integer> mapEachDegree ()
    {
        for (Employee employee : listOfEmployees) {
            if (employee instanceof Manager) {
                if (((Manager) employee).getDEGREE_TYPES().equals("BSc")) {
                    degreeMap.put("BSc", 1);
                }
            }
        }

       String result = "Size of map is:- "+ degreeMap.size();
        if (degreeMap.containsKey("Bsc")) {

            // Mapping
            Integer a = degreeMap.get("BSc");

            // Printing value for the corresponding key
            System.out.println("value for key"
                    + " \"BSc\" is:- " + a);
    }*/

/* Isabela-draft of task 10
    public Map<String, Integer> mapEachDegree() {
        for (Employee employee : listOfEmployees) {
            if (employee instanceof Manager || employee instanceof Director) {
                if (((Manager) employee).getDEGREE_TYPES().equals("BSc")) {
                    degreeMap.put("BSc", 1);
                } else if (((Manager) employee).getDEGREE_TYPES().equals("MSc")) {
                    degreeMap.put("MSc", 1);
                } else if (((Manager) employee).getDEGREE_TYPES().equals("PhD")) {
                    degreeMap.put("PhD", 1);
                }
            }

        }return null;
    }
    public String numberOfDegrees(){
        String numberOfBSc = "";
        String numberOfMSc = "";
        String numberOfPhD = "";

        for (Employee employee : listOfEmployees) {
            String numberOfBSc = numberOfBSc + "BSc: =>" + degreeMap.get("BSc") + END_OF_LINE;
            String numberOfMSc = numberOfMSc + "MSc: =>" + degreeMap.get("MSc") + END_OF_LINE;
            String numberOfPhD = numberOfPhD + "PhD: =>" + degreeMap.get("PhD") + END_OF_LINE;

        return "Academic background of employees: " + END_OF_LINE + numberOfBSc + numberOfMSc + numberOfPhD;
    }
}

 */

