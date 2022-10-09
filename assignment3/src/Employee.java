public class Employee
{
    private final String employeeID;
    private String name;
    private double grossSalary;
    double netSalary;
    double truncatedNetSalary;
    double truncatedGrossSalary;

    Employee(String id, String name, double grossSalary)
    {
        this.employeeID =id;
        this.name=name;
        this.grossSalary=grossSalary;
    }

    public String getID() { return employeeID; }

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
        return this.truncatedNetSalary;
    }

    public double truncateGrossSalary(double grossSalary){

        double temporary1 = this.grossSalary*100;
        double temporary2 = (int)temporary1;
        truncatedGrossSalary = temporary2/100.0;
        return truncatedGrossSalary;
    }

    public double truncateNetSalary(){

        double temporary1 = this.netSalary*100;
        double temporary2 = (int)temporary1;
        truncatedNetSalary = temporary2/100.0;
        return truncatedNetSalary;
    }

    public boolean equals (Object anotherObject) {
        if (anotherObject == this) {
            return true;
        }
        if (anotherObject == null) {
            return false;
        }
        if (anotherObject instanceof Employee otherEmployee) {
            boolean sameID = this.employeeID.equals(otherEmployee.getID());
            return sameID;
        }
        else { return false; }

    }

    //<name>’s gross salary is <gross_salary> SEK per month.
    public String toString() {
        return this.name+"s gross salary is "+this.grossSalary+" SEK per month.";
    }

}