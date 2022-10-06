public class Director {

    private String department;

    public Director (String name, String ID, double gross_salary, String degree, String department) {
        super.name = name;
        super.ID = ID;
        super.gross_salary = gross_salary;
        super.degree = degree;
        this.department = department;
    }

    public String getDepartment() {return this.department;}

    public double calculateNetSalary() {
        final double ADDITIONAL_SALARY = 5000;
        double directorGrossSalary = super.getGrossSalary() + super.getDegreeBonus() + ADDITIONAL_SALARY;
        double netSalary;
        netSalary = 0.0;
        if (directorGrossSalary < 30000) {
            netSalary = directorGrossSalary - (directorGrossSalary * 0.1);
        } else if (directorGrossSalary >= 30000 && directorGrossSalary <= 50000) {
            netSalary = directorGrossSalary - (directorGrossSalary * 0.2);
        } else if (directorGrossSalary > 50000) {
            double salaryLeft = directorGrossSalary - 30000;
            netSalary = directorGrossSalary - ((salaryLeft * 0.4) + (30000 * 0.2));
        }
        return netSalary;
    }

    public String toString() {
        return (super.getDegree() + " " + super.getName() + "'s gross salary is " + super.getGrossSalary() + " SEK per month. Dept: " + this.getDepartment());
    }
}
