package assignment3;

public class Intern extends Employee {
    private int GPA;

    public Intern(String name, String id, double grossSalary, int GPA) throws Exception{
        super(name, id, grossSalary);
        if (id.isEmpty()){
            throw new InvalidIDException("ID cannot be blank.");}
        if (name.isEmpty()) {
            throw new InvalidNameException("Name cannot be blank.");}
        if (grossSalary <= 0){
            throw new NegativeSalaryException("Salary must be greater than zero.");}

        this.GPA = GPA;
    }

    public void setGpa(int GPA) {
        if(GPA >=0 && GPA <=10){
        this.GPA = GPA;
        }
    }

    @Override
    public double calculateNetSalary() {

        double internGrossSalary;
        if (this.GPA <= 5) {
            internGrossSalary = 0;
        } else if (GPA < 8) {
            internGrossSalary = super.getGrossSalary();
        } else {
            internGrossSalary = super.getGrossSalary() + 1000;
        }
        netSalary=internGrossSalary;
        setGrossSalary(internGrossSalary);
        return internGrossSalary;
    }

    @Override
    public String toString() {
        return this.getName() + "'s gross salary is " + String.format("%.2f", this.truncateSalary(this.calculateNetSalary())) + " SEK per month. GPA: "+ this.GPA;
    }
}