package assignment3;

import java.util.*;

public class Company {
    private ArrayList<Employee> listOfEmployees;
    HashMap<String, Integer> degreeMap;
    final String END_OF_LINE = System.lineSeparator();
    boolean foundEmployee = false;
    int counterBSc;
    int counterMSc;
    int counterPhD;

    /* TODO Try catch exception + extras
       TODO Promotion refactoring
       TODO Hash Maps
       TODO sorting
       TODO Test locally, then upload on codeGrade
     */
    public Company() {
        this.listOfEmployees = new ArrayList<>();
        this.degreeMap = new HashMap<>();
    }

    public String createEmployee(String id, String name, double grossSalary) throws Exception {
        Employee employee = new Employee(id, name, grossSalary);

        //most of the try catch is in the tests, we need to figure out where to throw and where to catch
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

        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }

    public String createEmployee(String id, String name, double grossSalary, String DEGREE_TYPES, String department) throws Exception {
        Employee employee = new Director(id, name, grossSalary, DEGREE_TYPES, department);

        for (Employee currentEmployee : listOfEmployees) {
            if (id.equals(currentEmployee.getID())) {
                throw new InvalidCompanyException("Cannot register. The ID " + id + " is already registered.");
            }
        }

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

    public Employee findEmployee(String id) {
        for (Employee currentEmployee : listOfEmployees) {
            if (id.equals(currentEmployee.getID())) {
                return currentEmployee;
            }
        }
        return null;
    }

    /*
    for (Employee currentEmployee : listOfEmployees) {
            if (!empID.equals(currentEmployee.getID())) {
                throw new InvalidCompanyException("Employee " + empID + " was not registered yet.");
            }
        }
     */
    int counter=0;
    public String removeEmployee(String empID) throws Exception {
        //if empID is not in the arraylist listOfEmployees, then we throw
        for(int i =0; i< listOfEmployees.size(); i++) {

            Employee currentEmployee = listOfEmployees.get(i);
            if(currentEmployee.getID().equals (empID))
            {
                this.listOfEmployees.remove(findEmployee(empID));
                counter = counter+1;
            }
        }
        if(counter>0)
        {
            return "Employee " + empID + " was successfully removed.";
        }
        else {
            throw new InvalidCompanyException("Employee " + empID + " was not registered yet.");
        }
    }

    public String printEmployee(String employeeID) throws Exception {
        if (!listOfEmployees.isEmpty()) {
            return findEmployee(employeeID).toString();
        } else {
            throw new InvalidCompanyException("No employee has been registered yet.");
        }
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
        return findEmployee(employeeID).calculateNetSalary();
    }

    public double getTotalNetSalary() throws Exception {
        if (listOfEmployees.isEmpty()) {
            throw new InvalidCompanyException("No employee has been registered yet.");
        } else {
            double expenses = 0.0;
            for (Employee currentEmployee : listOfEmployees) {
                expenses = currentEmployee.calculateNetSalary() + expenses;
                double temporary1 = expenses * 100;
                double temporary2 = (int) temporary1;
                expenses = temporary2 / 100.0;
            }
            return expenses;
        }
    }

    public String printSortedEmployees() throws Exception {
        if (listOfEmployees.isEmpty()) {
            throw new InvalidCompanyException("No employee has been registered yet.");
        }

        Collections.sort(listOfEmployees, new SalaryComparator());

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

    public String updateInternGPA(String id, int newGPA) throws Exception {
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

    public String promoteToManager(String id, String degree) throws Exception {
        String originalName = findEmployee(id).getName();
        double originalSalary = findEmployee(id).getGrossSalary();
        Employee promotedEmployee = new Manager(id, originalName, originalSalary, degree);
        removeEmployee(id);
        listOfEmployees.add(promotedEmployee);
        return promotedEmployee.getID() + " promoted successfully to Manager.";
    }

    public String promoteToDirector(String id, String degree, String department) throws Exception {
        String originalName = findEmployee(id).getName();
        double originalSalary = findEmployee(id).getGrossSalary();
        Employee promotedEmployee = new Director(id, originalName, originalSalary, degree, department);
        removeEmployee(id);
        listOfEmployees.add(promotedEmployee);
        return promotedEmployee.getID() + " promoted successfully to Director.";
    }

    public String promoteToIntern(String id, int GPA) throws Exception {
        String originalName = findEmployee(id).getName();
        double originalSalary = findEmployee(id).getGrossSalary();
        Employee promotedEmployee = new Intern(id, originalName, originalSalary, GPA);
        removeEmployee(id);
        listOfEmployees.add(promotedEmployee);
        return promotedEmployee.getID() + " promoted successfully to Intern.";
    }

    /*
    public void checkDegrees(){
        for(Employee employee : this.listOfEmployees){
            HashSet<String> degrees = employee.getDEGREE_TYPES();
            for(String degree : degrees){
                if(  degreeMap.containsKey(degree) ){
                    int numOfMention = degreeMap.get(degree);
                    numOfMention = numOfMention + 1;
                    degreeMap.put(degree, numOfMention);
                } else {
                    degreeMap.put(degree, 1);
                }
            }
        }
    }

    public HashMap<String, Integer> mapEachDegree(){
        return this.degreeMap;
    }
     */

    public Map<String, Integer> mapEachDegree() throws Exception {
         counterBSc = 0;
         counterMSc = 0;
         counterPhD = 0;

        if (listOfEmployees.isEmpty()) {
            throw new InvalidCompanyException("No employees registered yet.");
        }

        for (Employee currentEmployee : listOfEmployees) {
            if (currentEmployee instanceof Manager) {
                switch (((Manager) currentEmployee).getDEGREE_TYPES()) {
                    case "BSc":
                        counterBSc = counterBSc + 1;
                        break;
                    case "MSc":
                        counterMSc = counterMSc + 1;
                        break;
                    case "PhD":
                        counterPhD = counterPhD + 1;
                        break;
                }
            }
        }
        if (counterBSc > 0) {
            degreeMap.put("BSc", counterBSc);
        }
        if (counterMSc > 0) {
            degreeMap.put("MSc", counterBSc);
        }
        if (counterPhD > 0) {
            degreeMap.put("PhD", counterBSc);
        }
       return degreeMap;
    }

    public String toString(){
        String s1;
        String s2;
        String s3;
        if (counterBSc!=0) {
            s1 = "BSc: => " + counterBSc + END_OF_LINE;
        }
        else {
            s1= "";
        }
        if (counterMSc!=0) {
            s2 = "MSc: => " + counterMSc + END_OF_LINE;
        } else {
            s2= "";
        }
        if (counterPhD!=0) {
            s3 = "PhD: => " + counterPhD + END_OF_LINE;
        } else {
            s3= "";
        }
        return "Academic background of employees: " + END_OF_LINE + s1 + s2 +s3;
    }

/*
    public void checkDegrees() {
        //HashSet<String> degrees = employee.getHashtags();
        for (Employee employee : listOfEmployees) {
            if (employee instanceof Manager) {
                if (((Manager) employee).getDEGREE_TYPES().equals("BSc")) {
                    int counter1 = degreeMap.get("BSc");
                    counter1 = counter1 + 1;
                    degreeMap.put("BSc", counter1);
                } else {
                    degreeMap.put("BSc", 1);
                }
                if (((Manager) employee).getDEGREE_TYPES().equals("MSc")) {
                    int counter2 = degreeMap.get("MSc");
                    counter2 = counter2 + 1;
                    degreeMap.put("MSc", counter2);
                } else {
                    degreeMap.put("MSc", 1);
                }
                if (((Manager) employee).getDEGREE_TYPES().equals("PhD")) {
                    int counter2 = degreeMap.get("PhD");
                    counter2 = counter2 + 1;
                    degreeMap.put("PhD", counter2);
                } else {
                    degreeMap.put("PhD", 1);
                }

            }
        }
    }

    public HashMap<String, Integer> mapEachDegree(){
        checkDegrees();
        HashMap<String, Integer> degreeMap = new HashMap<String, Integer>();
        return this.degreeMap;
    }*/


    /*
        public Map<String, Integer> mapEachDegree() throws Exception {
         counterBSc = 0;
         counterMSc = 0;
         counterPhD = 0;

         HashMap <String, Integer> degreeMap = new DegreeMap<String,Integer>;
         degreeMap.put((Manager) currentEmployee).getDEGREE_TYPES(), 0);
         degreeMap.put((Manager) currentEmployee).getDEGREE_TYPES(), 0);
         degreeMap.put((Manager) currentEmployee).getDEGREE_TYPES(), 0);

        if (listOfEmployees.isEmpty()) {
            throw new InvalidCompanyException("No employees registered yet.");
        }

        for (Employee currentEmployee : listOfEmployees) {
            if (currentEmployee instanceof Manager) {
                    if(((Manager) currentEmployee).getDEGREE_TYPES().equals("BSc")){
                        counterBSc = counterBSc + 1;}
                        else if(((Manager) currentEmployee).getDEGREE_TYPES().equals("MSc")){
                        counterMSc = counterMSc + 1;
                        }
                    else if(((Manager) currentEmployee).getDEGREE_TYPES().equals("PhD")) {
                    counterPhD = counterPhD + 1;
                }

            }
        }
        if (counterBSc > 0) {
            degreeMap.put("BSc", counterBSc);
        }
        if (counterMSc > 0) {
            degreeMap.put("MSc", counterBSc);
        }
        if (counterPhD > 0) {
            degreeMap.put("PhD", counterBSc);
        }
       return degreeMap;
    }
     */
}
