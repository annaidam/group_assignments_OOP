package assignment3;

public class Director extends Manager {

    private String department;

    public Director (String id, String name, double grossSalary, String DEGREE_TYPES, String department) {
        super (id, name, grossSalary, DEGREE_TYPES); //newGrossSalary?
        this.department = department;
    }

    public String getDepartment() {return this.department;}
    public String setDepartment(String newDept) {return newDept;}

    public double setDirectorGrossSalary() {
        final double ADDITIONAL_SALARY = 5000;
        double directorGrossSalary = super.getGrossSalary() + super.getDegreeBonus() + ADDITIONAL_SALARY;
        setGrossSalary(directorGrossSalary);
        return this.getGrossSalary();
    }

    public double calculateNetSalary() {
        double netSalary;
        netSalary = 0.0;
        if (setDirectorGrossSalary() < 30000) {
            netSalary = setDirectorGrossSalary() - (setDirectorGrossSalary() * 0.1);
        } else if (setDirectorGrossSalary() >= 30000 && setDirectorGrossSalary() <= 50000) {
            netSalary = setDirectorGrossSalary() - (setDirectorGrossSalary() * 0.2);
        } else if (setDirectorGrossSalary() > 50000) {
            double salaryLeft = setDirectorGrossSalary() - 30000;
            netSalary = setDirectorGrossSalary() - ((salaryLeft * 0.4) + (30000 * 0.2));
        }
        return netSalary;
    }

    public String toString() {
        return (super.getDEGREE_TYPES() + " " + super.getName() + "'s gross salary is " + super.getGrossSalary() + " SEK per month. Dept: " + this.getDepartment());
    }
}
