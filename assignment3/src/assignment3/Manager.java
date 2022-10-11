package assignment3;

import assignment3.Employee;

public class Manager extends Employee
{
    private String DEGREE_TYPES;
    double newGrossSalary;

    public Manager (String id, String name, double grossSalary, String DEGREE_TYPES)
    {
        super (id,name,grossSalary);
        this.DEGREE_TYPES = DEGREE_TYPES;
    }

    public String getDEGREE_TYPES()
    {
        return DEGREE_TYPES;
    }

    public void setDEGREE_TYPES(String DEGREE_TYPES) { this.DEGREE_TYPES = DEGREE_TYPES;}

    public double getDegreeBonus() {
        double bonus = 0.0;
        if (DEGREE_TYPES.equals("BSc")) {
            bonus = 0.1 * super.getGrossSalary();
        } else if (DEGREE_TYPES.equals("MSc")) {
            bonus = 0.2 * super.getGrossSalary();
        } else if (DEGREE_TYPES.equals("PhD")) {
            bonus = 0.35 * super.getGrossSalary();
        }
        return bonus;
    }

    public double managerGrossSalary() {
        double managerGrossSalary = truncateSalary(super.getGrossSalary()) + getDegreeBonus();
        return managerGrossSalary;
    }

    @Override
    public double calculateNetSalary() {
        netSalary = truncateSalary(managerGrossSalary()) * 0.9;
        return netSalary;
    }

    //<degree> <name>’s gross salary is <gross_salary> SEK per month.
    public String toString()
    {
        return this.DEGREE_TYPES + " " + this.getName() + "'s gross salary is " + String.format("%.2f", this.truncateSalary(managerGrossSalary())) + " SEK per month.";
    }

}
