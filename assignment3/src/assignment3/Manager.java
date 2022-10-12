package assignment3;

public class Manager extends Employee
{
    private String DEGREE_TYPES;

    public Manager (String id, String name, double grossSalary, String DEGREE_TYPES) throws Exception {
        super (id,name,grossSalary);

        //create a boolean that returns true if the string DEGREE_TYPES contains the string BSc, MSc or PhD
        boolean containsBSc = DEGREE_TYPES.equals("BSc");
        boolean containsMSc = DEGREE_TYPES.equals("MSc");
        boolean containsPhD = DEGREE_TYPES.equals("PhD");

        //if it contains, assign the value---else throw the exception
        if(containsBSc || containsMSc || containsPhD){
            this.DEGREE_TYPES = DEGREE_TYPES;
        } else {throw new InvalidEmployeeException("Degree must be one of the options: BSc, MSc or PhD.");}
    }

    public String getDEGREE_TYPES()
    {
        return DEGREE_TYPES;
    }

    public void setDEGREE_TYPES(String DEGREE_TYPES) { this.DEGREE_TYPES = DEGREE_TYPES;} //throw

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
