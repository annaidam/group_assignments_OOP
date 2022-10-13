package assignment3;

public class Director extends Manager {

    private String department;
    boolean containsHUMAN_RESOURCES;
    boolean containsBUSINESS;
    boolean containsTECHNICAL;


    public Director (String id, String name, double grossSalary, String DEGREE_TYPES, String department) throws Exception {
        super (id, name, grossSalary, DEGREE_TYPES);

        //Presenting the creation or assignment with invalid Directors data.
        containsHUMAN_RESOURCES = department.equals("Human Resources");
        containsBUSINESS = department.equals("Business");
        containsTECHNICAL = department.equals("Technical");
        if (containsBUSINESS || containsTECHNICAL || containsHUMAN_RESOURCES) {
            this.department = department;
        } else {
            throw new InvalidEmployeeException("Department must be one of the options: Business, Human Resources or Technical.");
        }
    }

    public String getDepartment() {return this.department;}

    public void setDepartment(String newDept) throws Exception {
        if (containsBUSINESS || containsTECHNICAL || containsHUMAN_RESOURCES) {
            this.department = department;
        } else {
            throw new InvalidEmployeeException("Department must be one of the options: Business, Human Resources or Technical.");
        }
    }

    public double directorGrossSalary() {
        final double ADDITIONAL_SALARY = 5000.00;
        double directorGrossSalary = this.truncateSalary(super.getGrossSalary()) + getDegreeBonus() + ADDITIONAL_SALARY;
        return directorGrossSalary;
    }

    public double calculateNetSalary() {
        double netSalary;
        netSalary = 0.0;
        if (directorGrossSalary() < 30000) {
            netSalary = directorGrossSalary() * 0.9;
        } else if (directorGrossSalary() >= 30000 && directorGrossSalary() <= 50000) {
            netSalary = directorGrossSalary() * 0.8;
        } else if (directorGrossSalary() > 50000) {
            double salaryLeft = directorGrossSalary() - 30000;
            netSalary = directorGrossSalary() - ((salaryLeft * 0.4) + (30000 * 0.2));
        }
        return netSalary;
    }

    public String toString() {
        return (super.getDEGREE_TYPES() + " " + super.getName() + "'s gross salary is " + String.format("%.2f", this.truncateSalary(directorGrossSalary())) + " SEK per month. Dept: " + this.getDepartment());
    }
}
