package assignment3;
import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Company {
    private ArrayList<Employee> listOfEmployees;
    private HashMap<String, Integer> degreeMap = new HashMap<>();
    final String END_OF_LINE = System.lineSeparator();

    public Company() {
        this.listOfEmployees = new ArrayList<>();
    }

    public String createEmployee(String id, String name, double grossSalary) throws Exception {
        Employee employee = new Employee(id, name, grossSalary);

        ////////////////
        if (employee.getID().equals(findEmployee(employee.getID()))) {
            throw new EmployeeIDTakenException("Cannot register. The ID is already registered.");}
        if (id.isEmpty()){
            throw new InvalidIDException("ID cannot be blank.");}
        if (name.isEmpty()) {
            throw new InvalidNameException("Name cannot be blank.");}
        if (grossSalary <= 0){
            throw new NegativeSalaryException("Salary must be greater than zero.");}
        ////////////////

        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }

    public String createEmployee(String id, String name, double grossSalary, String DEGREE_TYPES) throws Exception {
        Employee employee = new Manager(id, name, grossSalary, DEGREE_TYPES);

        ////////////
        if (id.isEmpty()){
            throw new InvalidIDException("ID cannot be blank.");}
        if (name.isEmpty()) {
            throw new InvalidNameException("Name cannot be blank.");}
        if (grossSalary <= 0){
            throw new NegativeSalaryException("Salary must be greater than zero.");}

        boolean containsBSc = DEGREE_TYPES.contains("BSc");
        boolean containsMSc = DEGREE_TYPES.contains("MSc");
        boolean containsPhD = DEGREE_TYPES.contains("PhD");

        if(!containsBSc || !containsMSc || !containsPhD){
            throw new InvalidDegreeException("Degree must be one of the options: BSc, MSc or PhD.");}
            ///////////


        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }

    public String createEmployee(String id, String name, double grossSalary, String DEGREE_TYPES, String department) throws Exception {
        Employee employee = new Director(id, name, grossSalary, DEGREE_TYPES, department);

        //check exceptions
        if (id.isEmpty()){
            throw new InvalidIDException("ID cannot be blank.");}
        if (name.isEmpty()) {
            throw new InvalidNameException("Name cannot be blank.");}
        if (grossSalary <= 0){
            throw new NegativeSalaryException("Salary must be greater than zero.");}

        boolean containsBSc = DEGREE_TYPES.contains("BSc");
        boolean containsMSc = DEGREE_TYPES.contains("MSc");
        boolean containsPhD = DEGREE_TYPES.contains("PhD");

        if(!containsBSc || !containsMSc || !containsPhD){
            throw new InvalidDegreeException("Degree must be one of the options: BSc, MSc or PhD.");}
        //finish checking exceptions

        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }

    public String createEmployee(String id, String name, double grossSalary, int GPA) throws Exception {
        Employee employee = new Intern(id, name, grossSalary, GPA);

        //check for the exceptions
        if (id.isEmpty()){
            throw new InvalidIDException("ID cannot be blank.");}
        if (name.isEmpty()) {
            throw new InvalidNameException("Name cannot be blank.");}
        if (grossSalary <= 0){
            throw new NegativeSalaryException("Salary must be greater than zero.");}
        //finished with the exceptions

        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }

    public Employee findEmployee(String id) throws Exception {
        for (Employee currentEmployee : listOfEmployees) {
            if (id.equals(currentEmployee.getID())) {
                return currentEmployee;
            } else {
                throw new SpecificEmployeeNotFoundException("Employee " + id + " was not registered yet.");
            }
        }
        return null;
    }

    public String removeEmployee(String empID) throws Exception {
        this.listOfEmployees.remove(findEmployee(empID));
        return "Employee " + empID + " was successfully removed.";
    }

    public String printEmployee(String employeeID) throws Exception {
        return findEmployee(employeeID).toString();
    }

    public String printAllEmployees() throws Exception {
        String allEmployees = "";

        if (listOfEmployees.isEmpty()){
            throw new EmployeeNotFoundException("No employee has been registered yet.");}

        for (Employee employee : listOfEmployees) {
            allEmployees = allEmployees + employee.toString() + END_OF_LINE;
        }
        return "All registered employees:" + END_OF_LINE + allEmployees;
    }

    public double getNetSalary(String employeeID) throws Exception {
        return findEmployee(employeeID).calculateNetSalary();
    }

    public double getTotalNetSalary() throws Exception {
        if (listOfEmployees.isEmpty()){
            throw new EmployeeNotFoundException("No employee has been registered yet.");}

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

    //Sort again using stuff we will learn tomorrow
    public String printSortedEmployees() throws Exception {
        if (listOfEmployees.isEmpty()){
            throw new EmployeeNotFoundException("No employee has been registered yet.");}

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

    public String updateGrossSalary(String id, double newGrossSalary) {
        findEmployee(id).setGrossSalary(newGrossSalary);
        return "Employee " + id + " was updated successfully";
    }

    public String updateManagerDegree(String id, String newDegree) {
        if (findEmployee(id) instanceof Manager) {
            ((Manager) findEmployee(id)).setDEGREE_TYPES(newDegree);
            return "Employee " + id + " was updated successfully";
        } else {
            return null;
        }
    }

    public String updateDirectorDegree(String id, String newDegree) {
        if (findEmployee(id) instanceof Director) {
            ((Director) findEmployee(id)).setDEGREE_TYPES(newDegree);
            return "Employee " + id + " was updated successfully";
        }
        return null;
    }

    public String updateDirectorDept(String id, String newDept) {
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
}

    //Academic background of employees:
    //"BSc: => " + counterBSc + END_OF_LINE
    //"MSc: => " + counterMSc + END_OF_LINE
    //"PhD: => " + counterPhD + END_OF_LINE
    /*public String academicBackground() {
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
        if(counterBSc>0){
        return "Academic background of employees:" + counterBSc+ END_OF_LINE;
        }
        else if(counterMSc>0){
            return "Academic background of employees:" + counterMSc+ END_OF_LINE;
        }
        else if(counterPhD>0){
            return "Academic background of employees:" + counterPhD+ END_OF_LINE;
        }
        else {
            return null;
        }

     */

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

