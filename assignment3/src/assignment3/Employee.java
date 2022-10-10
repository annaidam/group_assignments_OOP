package assignment3;

public class Employee
{
    private final String EMPLOYEE_ID;
    private String name;
    private double grossSalary;
    double netSalary;
    double truncatedSalary;

    Employee(String id, String name, double grossSalary)
    {
        this.EMPLOYEE_ID =id;
        this.name = name;
        this.grossSalary=grossSalary;
    }

    public String getID() { return EMPLOYEE_ID; }

    public String getName() { return name; }

    public double getGrossSalary() { return grossSalary; }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setGrossSalary (double newGrossSalary) {
        this.grossSalary = newGrossSalary;
    }

    //netSalary = grossSalary - (grossSalary * 0.1)
    public double calculateNetSalary(){
        netSalary = this.grossSalary - (this.grossSalary*0.1);
        return this.truncateSalary(netSalary);
    }

    public double truncateSalary(double salary)
    {
        double temporary1 = salary*100;
        double temporary2 = (int)temporary1;
        truncatedSalary = temporary2/100.0;
        return truncatedSalary;
    }

    public boolean equals (Object anotherObject) {
        if (anotherObject == this) {
            return true;
        }
        if (anotherObject == null) {
            return false;
        }
        if (anotherObject instanceof Employee otherEmployee) {
            boolean sameID = this.EMPLOYEE_ID.equals(otherEmployee.getID());
            return sameID;
        }
        else { return false; }

    }

    //<name>’s gross salary is <gross_salary> SEK per month.
    public String toString() {
        return this.name+"s gross salary is "+this.grossSalary+" SEK per month.";
    }

}