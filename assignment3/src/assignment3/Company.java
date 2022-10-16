package assignment3;

import java.util.*;

public class Company {
    private ArrayList<Employee> listOfEmployees;
    HashMap<String, Integer> degreeMap;
    final String END_OF_LINE = System.lineSeparator();
    private boolean foundEmployee = false;
    private int counterBSc;
    private int counterMSc;
    private int counterPhD;

    /*
       TODO check if we can use 'name.trim().isEmpty'
       TODO add comments
       TODO make the code 'pretty'
       TODO Try catch exception + extras
       TODO readme.txt
     */
    public Company() {
        this.listOfEmployees = new ArrayList<>();
        this.degreeMap = new HashMap<String, Integer>();
    }

    public String createEmployee(String id, String name, double grossSalary) throws Exception {
        Employee employee = new Employee(id, name, grossSalary);

        for (Employee currentEmployee : listOfEmployees) {
            if (id.equals(currentEmployee.getID())) {
                throw new InvalidCompanyException("Cannot register. ID " + id + " is already registered.");
            }
        }

        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }

    public String createEmployee(String id, String name, double grossSalary, String DEGREE_TYPES) throws Exception {
        Employee employee = new Manager(id, name, grossSalary, DEGREE_TYPES);

        for (Employee currentEmployee : listOfEmployees) {
            if (id.equals(currentEmployee.getID())) {
                throw new InvalidCompanyException("Cannot register. ID " + id + " is already registered.");
            }
        }

        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }

    public String createEmployee(String id, String name, double grossSalary, String DEGREE_TYPES, String department) throws Exception {
        Employee employee = new Director(id, name, grossSalary, DEGREE_TYPES, department);

        for (Employee currentEmployee : listOfEmployees) {
            if (id.equals(currentEmployee.getID())) {
                throw new InvalidCompanyException("Cannot register. ID " + id + " is already registered.");
            }
        }

        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }

    public String createEmployee(String id, String name, double grossSalary, int GPA) throws Exception {
        Employee employee = new Intern(id, name, grossSalary, GPA);

        for (Employee currentEmployee : listOfEmployees) {
            if (id.equals(currentEmployee.getID())) {
                throw new InvalidCompanyException("Cannot register. ID " + id + " is already registered.");
            }
        }
        this.listOfEmployees.add(employee);
        return "Employee " + employee.getID() + " was registered successfully.";
    }

    public Employee findEmployee(String id) throws Exception {
        for (int i=0; i<listOfEmployees.size();i++) {
            Employee currentEmployee = listOfEmployees.get(i);
            if (id.equals(currentEmployee.getID())) {
                foundEmployee = true;
                return currentEmployee;
            }
        }
        for (int i=0; i<listOfEmployees.size();i++) {
            Employee currentEmployee = listOfEmployees.get(i);
            if (!id.equals(currentEmployee.getID())) {
                throw new InvalidCompanyException("Employee " + id + " was not registered yet.");
            }
        }
        return null;
    }

    public String removeEmployee(String empID) throws Exception {
        //findEmployee(empID);
        int counterRemoveEmployee =0;
        for (int i=0; i<listOfEmployees.size();i++) {
            Employee currentEmployee = listOfEmployees.get(i);
            if (empID.equals(currentEmployee.getID())) {
                listOfEmployees.remove(findEmployee((empID)));
                counterRemoveEmployee = counterRemoveEmployee +1;
            }
        }
        if (counterRemoveEmployee != 0)
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
            throw new InvalidCompanyException("Employee " + employeeID + " was not registered yet.");
        }
    }

    public String printAllEmployees() throws Exception {
        String allEmployees = "";

        if (listOfEmployees.isEmpty()) {
            throw new InvalidCompanyException("No employees registered yet.");
        }

        for (Employee employee : listOfEmployees) {
            allEmployees = allEmployees + employee.toString() + END_OF_LINE;
        }
        return "All registered employees:" + END_OF_LINE + allEmployees;
    }

    public double getNetSalary(String employeeID) throws Exception {
        if (!listOfEmployees.isEmpty()) {
            return findEmployee(employeeID).calculateNetSalary();
        } else {
            throw new InvalidCompanyException("Employee " + employeeID + " was not registered yet.");
        }
    }

    public double getTotalNetSalary() throws Exception {
        if (listOfEmployees.isEmpty()) {
            throw new InvalidCompanyException("No employees registered yet.");
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
            throw new InvalidCompanyException("No employees registered yet.");
        }
        Collections.sort(listOfEmployees);

        String allEmployeesSorted = "";
        for (Employee employee : listOfEmployees) {
            allEmployeesSorted = allEmployeesSorted + employee.toString() + END_OF_LINE;
        }
        return "Employees sorted by gross salary (ascending order):" + END_OF_LINE + allEmployeesSorted;
    }

    public String updateEmployeeName(String id, String newName) throws Exception {
        if (newName.isEmpty()) {
            throw new InvalidEmployeeException("Name cannot be blank.");
        }
        findEmployee(id);
        if (foundEmployee) {
            findEmployee(id).setName(newName);
            return "Employee " + id + " was updated successfully";
        } else {
            throw new InvalidCompanyException("Employee " + id + " was not registered yet.");
        }
    }

    public String updateGrossSalary(String id, double newGrossSalary) throws Exception {
        if (newGrossSalary <= 0){
            throw new InvalidEmployeeException("Salary must be greater than zero.");
        }
        findEmployee(id);
        if (foundEmployee) {
            findEmployee(id).setGrossSalary(newGrossSalary);
            return "Employee " + id + " was updated successfully";
        } else {
            throw new InvalidCompanyException("Employee " + id + " was not registered yet.");
        }
    }

    public String updateManagerDegree(String id, String newDegree) throws Exception {
        findEmployee(id);
        if (foundEmployee) {
            if (newDegree.equals("BSc") || newDegree.equals("MSc") || newDegree.equals("PhD")) {
                ((Manager) findEmployee(id)).setDEGREE_TYPES(newDegree);
                return "Employee " + id + " was updated successfully";
            }else {
                throw new InvalidEmployeeException("Degree must be one of the options: BSc, MSc or PhD.");
            }
        } else {
            throw new InvalidCompanyException("Employee " + id + " was not registered yet.");
        }
    }

    public String updateDirectorDept(String id, String newDept) throws Exception {
        findEmployee(id);
        if (foundEmployee) {
            if(newDept.equals("Human Resources") || newDept.equals("Business") || newDept.equals("Technical")) {
                ((Director) findEmployee(id)).setDepartment(newDept);
                return "Employee " + id + " was updated successfully";
            } else {
                throw new InvalidEmployeeException("Department must be one of the options: Business, Human Resources or Technical.");
            }
        } else {
            throw new InvalidCompanyException("Employee " + id + " was not registered yet.");
        }
    }

    public String updateInternGPA(String id, int newGPA) throws Exception {
        findEmployee(id);
        if (foundEmployee) {
            if (newGPA < 0 || newGPA > 10) {
                throw new InvalidEmployeeException(newGPA + " outside range. Must be between 0-10.");
            } else {
                ((Intern) findEmployee(id)).setGpa(newGPA);
                return "Employee " + id + " was updated successfully";
            }
        } else {
            throw new InvalidCompanyException("Employee " + id + " was not registered yet.");
        }
    }

    public String promoteToManager(String id, String degree) throws Exception {
        if (!listOfEmployees.isEmpty()) {
            String originalName = findEmployee(id).getName();
            double originalSalary = findEmployee(id).getGrossSalary();
            Employee promotedEmployee = new Manager(id, originalName, originalSalary, degree);
            removeEmployee(id);
            listOfEmployees.add(promotedEmployee);
            return promotedEmployee.getID() + " promoted successfully to Manager.";
        } else {
            throw new InvalidCompanyException("Employee " + id + " was not registered yet.");
        }
    }

    public String promoteToDirector(String id, String degree, String department) throws Exception {
        if (!listOfEmployees.isEmpty()) {
            Employee currentEmployee = findEmployee(id);
            Employee promotedEmployee = new Director(currentEmployee.getID(), currentEmployee.getName(), currentEmployee.getRawGrossSalary(), degree, department);
            listOfEmployees.remove(currentEmployee);
            listOfEmployees.add(promotedEmployee);
            return promotedEmployee.getID() + " promoted successfully to Director.";
        } else {
            throw new InvalidCompanyException("Employee " + id + " was not registered yet.");
        }
    }

    public String promoteToIntern(String id, int GPA) throws Exception {
        if (!listOfEmployees.isEmpty()) {
            String originalName = findEmployee(id).getName();
            double originalSalary = findEmployee(id).getRawGrossSalary();
            Employee promotedEmployee = new Intern(id, originalName, originalSalary, GPA);
            removeEmployee(id);
            listOfEmployees.add(promotedEmployee);
            return promotedEmployee.getID() + " promoted successfully to Intern.";
        } else {
            throw new InvalidCompanyException("Employee " + id + " was not registered yet.");
        }
    }

    public Map<String, Integer> mapEachDegree() throws Exception {
         counterBSc = 0;
         counterMSc = 0;
         counterPhD = 0;
        if (listOfEmployees.isEmpty()) {
            throw new InvalidCompanyException("No employees registered yet.");
        }
        for (Employee currentEmployee : listOfEmployees) {
            if (currentEmployee instanceof Manager) {
                    if(((Manager) currentEmployee).getDEGREE_TYPES().equals("BSc")){
                        counterBSc = counterBSc + 1;
                    } else if(((Manager) currentEmployee).getDEGREE_TYPES().equals("MSc")){
                    counterMSc = counterMSc + 1;
                    } else if(((Manager) currentEmployee).getDEGREE_TYPES().equals("PhD")) {
                    counterPhD = counterPhD + 1;
                }
            }
        }
        HashMap<String, Integer> hashMap = new HashMap<>();
        if (counterBSc != 0) {
            hashMap.put("BSc", counterBSc);
        }
        if (counterMSc != 0) {
            hashMap.put("MSc", counterMSc);
        }
        if (counterPhD != 0) {
            hashMap.put("PhD", counterPhD);
        }
        return hashMap;
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
