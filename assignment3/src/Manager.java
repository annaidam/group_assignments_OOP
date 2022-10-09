public class Manager extends Employee
{
    private final String DEGREE_TYPES;
    double newGrossSalary;

    public Manager (String id, String name, double grossSalary, String DEGREE_TYPES, double newGrossSalary)
    {
        super (id,name,grossSalary);
        this.DEGREE_TYPES = DEGREE_TYPES;
        this.newGrossSalary = newGrossSalary;
    }

    @Override
    public double calculateNetSalary()
    {
        double bonus =0.0;
        if (DEGREE_TYPES.equals("Bsc"))
        {
            //double regularSalary = super.salary or super.calculateSalary();
            bonus= 0.1* super.getGrossSalary();
            newGrossSalary = bonus + this.getGrossSalary();
            return newGrossSalary;
        }
        else if (DEGREE_TYPES.equals("Msc"))
        {
            bonus = 0.2*super.getGrossSalary();
            newGrossSalary = bonus + this.getGrossSalary();
            return newGrossSalary;
        }

        else //if (DEGREE_TYPES.equals("PhD"))
        {
            bonus = 0.35*super.getGrossSalary();
            newGrossSalary = bonus + this.getGrossSalary();
            return newGrossSalary;
        }

        //<degree> <name>’s gross salary is <gross_salary> SEK per month.
        /*public String toString()
        {
            return this.DEGREE_TYPES + " "+ this.getName()+"'s gross salary is "+this.newGrossSalary+" SEK per month.";
        }

         */
    }
}
