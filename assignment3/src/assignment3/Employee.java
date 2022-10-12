package assignment3;

public class Employee {
    private final String EMPLOYEE_ID;
    private String name;
    private double grossSalary;
    double netSalary;
    double truncatedSalary;
    double newGrossSalary;

    Employee(String id, String name, double grossSalary) throws Exception {
        if (id.isEmpty()){
            throw new InvalidEmployeeException("ID cannot be blank.");
        } else {  this.EMPLOYEE_ID = id;}

        if (name.isEmpty()) {
            throw new InvalidEmployeeException("Name cannot be blank.");
        } else {this.name = name;}

        if (grossSalary <= 0){
            throw new InvalidEmployeeException("Salary must be greater than zero.");
        } else {this.grossSalary = grossSalary;}
    }

    public String getID() {
        return EMPLOYEE_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double newGrossSalary) {
        this.grossSalary = newGrossSalary;
        truncateSalary(this.grossSalary);
    }

    //netSalary = grossSalary - (grossSalary * 0.1)
    public double calculateNetSalary() {
        netSalary = truncateSalary(grossSalary) * 0.9;
        return this.truncateSalary(netSalary);
    }

    public double truncateSalary(double salary) {
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
        if (anotherObject instanceof Employee otherEmployee) {
            boolean sameID = this.EMPLOYEE_ID.equals(otherEmployee.getID());
            return sameID;
        } else {
            return false;
        }

    }
}







