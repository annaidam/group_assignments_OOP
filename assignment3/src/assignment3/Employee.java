package assignment3;

public class Employee implements Comparable<Employee> {
    private final String EMPLOYEE_ID;
    private String name;
    private double grossSalary;


    Employee(String id, String name, double grossSalary) throws Exception {
        if (id.trim().isEmpty()){
            throw new InvalidEmployeeException("ID cannot be blank.");
        } else {  this.EMPLOYEE_ID = id;}

        if (name.trim().isEmpty()) {
            throw new InvalidEmployeeException("Name cannot be blank.");
        } else {this.name = name;}

        if (grossSalary < 0){
            throw new InvalidEmployeeException("Salary must be greater than zero.");
        } else {this.grossSalary = grossSalary;}
    }

    public String getID() {
        return EMPLOYEE_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName)  throws Exception {
        if (name.isEmpty()) {
            throw new InvalidEmployeeException("Name cannot be blank.");
        } else {
            this.name = newName;
        }
    }

    public double getRawGrossSalary() {
        return this.grossSalary;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double newGrossSalary) {
        this.grossSalary = newGrossSalary;
        truncateSalary(this.grossSalary);
    }

    //netSalary = grossSalary - (grossSalary * 0.1)
    public double calculateNetSalary() throws Exception{
        double netSalary;
        if (grossSalary < 0){
            throw new InvalidEmployeeException("Salary must be greater than zero.");
        } else {
            netSalary = truncateSalary(grossSalary) * 0.9;
            return this.truncateSalary(netSalary);}
    }

    public double truncateSalary(double salary) {
        double truncatedSalary;
        double temporary1 = salary * 100;
        double temporary2 = (int) temporary1;
        truncatedSalary = temporary2 / 100.0;
        return truncatedSalary;

    }

    //<name>’s gross salary is <gross_salary> SEK per month.
    public String toString() {
        return this.name + "'s gross salary is " + String.format("%.2f", this.truncateSalary(grossSalary)) + " SEK per month.";
    }

    public boolean equals(Object anotherObject) {
        if (anotherObject == this) {
            return true;
        }
        if (anotherObject == null) {
            return false;
        }
        if (anotherObject instanceof Employee) {
            Employee otherEmployee = (Employee) anotherObject;
            boolean sameID = this.EMPLOYEE_ID.equals(otherEmployee.getID());
            return sameID;
        } else {
            return false;
        }

    }

    public int compareTo(Employee otherEmployee) {
        double mySalary = this.getGrossSalary();
        double otherSalary = otherEmployee.getGrossSalary();
        if (mySalary > otherSalary) {
            return 1;
        } else if (mySalary == otherSalary) {
            return 0;
        } else {
            return -1;
        }
    }

}