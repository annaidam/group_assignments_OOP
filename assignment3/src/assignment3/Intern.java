package assignment3;

public class Intern extends Employee {
    private int GPA;

    public Intern(String name, String id, double grossSalary, int GPA) throws Exception{
        super(name, id, grossSalary);

        if (GPA < 0 || GPA > 10) {
            throw new InvalidEmployeeException(GPA + " outside range. Must be between 0-10.");
        } else {
            this.GPA = GPA;
        }
    }

    public void setGpa(int newGPA) throws Exception {
      // if (GPA >= 0 && GPA <= 10)
        this.GPA = newGPA;
        }

    public double getGrossSalary() {
        return calculateNetSalary();
    }
    @Override
    public double calculateNetSalary()  {
        double internGrossSalary;
        if (this.GPA <= 5) {
            internGrossSalary = 0.0;

        } else if (GPA <= 8) {
            internGrossSalary = super.getGrossSalary();
        } else {
            internGrossSalary = super.getGrossSalary() + 1000.00;
        }
        //netSalary = internGrossSalary;
        //setGrossSalary(internGrossSalary);
        return internGrossSalary;
    }

    @Override
    public String toString(){
        return this.getName() + "'s gross salary is " + String.format("%.2f", this.truncateSalary(this.calculateNetSalary())) + " SEK per month. GPA: "+ this.GPA;
    }
}