public class Intern extends Employee {
    private int gpa;

    public Intern(String name, String ID, double gross_salary, int gpa) throws Exception {
        super(name, ID, gross_salary);
        this.gpa = gpa; //how can i set gpa to be between 0-10? bc it is not recommended to have conditionals in the constructor
    }

    public void setGpa(int gpa) {  //newGpa?
        this.gpa = gpa;          //=newGpa?
    }

    @Override
    public double calculateNetSalary() {     //definition

        if (this.gpa <= 5) {
            netSalary = 0;
        } else if (gpa < 8) {
            netSalary = super.getGrossSalary();
        } else {
            netSalary = super.getGrossSalary() + 1000;
        }
        //netSalary=super.getGrossSalary;
        return netSalary;
    }

    @Override
    public String toString() {
        return this.getName() + "'s gross salary is " + this.calculateNetSalary() + "SEK per month. GPA: "+ this.gpa;
    }
}