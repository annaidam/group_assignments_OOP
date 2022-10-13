package assignment3;

import java.util.*;

public class Company implements Comparable {
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
    }*/

    public Employee findEmployee(String id) throws Exception {
        Employee currentEmployee = null;
        for (int i = 0; i < listOfEmployees.size(); i++) {
            currentEmployee = listOfEmployees.get(i);
            if (currentEmployee.getID().equals(id)) {
                return currentEmployee;
            }
        }
        throw new Exception ("Employee " + id + "was not registered yet.");
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

    @Override
    public double compareTo(Employee emp) {
        double compare = emp.getGrossSalary();
        return emp.getGrossSalary() - compare;
    }

    public String printSortedEmployees() throws Exception {
        if (listOfEmployees.isEmpty()) {
            throw new InvalidCompanyException("No employee has been registered yet.");
        }

       // Collections.sort(listOfEmployees);

        String allEmployeesSorted = "";
        for (Employee employee : listOfEmployees) {
            allEmployeesSorted = allEmployeesSorted + employee.toString() + END_OF_LINE;
        }
        return "Employees sorted by gross salary (ascending order):" + END_OF_LINE + allEmployeesSorted;
    }

    //Sort again using stuff we will learn tomorrow
    /*public String printSortedEmployees() throws Exception {
        if (listOfEmployees.isEmpty()) {
            throw new InvalidCompanyException("No employee has been registered yet.");
        }
        for (int i = 0; i < listOfEmployees.size(); i++) {
            for (int j = listOfEmployees.size() - 1; j < i; j--) {
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
    }*/

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

    public Map mapEachDegree() throws Exception {
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
}