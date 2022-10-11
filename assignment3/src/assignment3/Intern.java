package assignment3;

import assignment3.Employee;

public class Intern extends Employee {
    private int GPA;

    public Intern(String name, String ID, double gross_salary, int GPA) //throws Exception
     {
        super(name, ID, gross_salary);
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
        //netSalary=super.getGrossSalary; the net salary returned by a student will be the same as his/her gross salary.
        return internGrossSalary;
    }

    @Override
    public String toString() {
        return this.getName() + "'s gross salary is " + this.calculateNetSalary() + "SEK per month. GPA: "+ this.GPA;
    }
}