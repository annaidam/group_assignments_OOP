package assignment3;

public class Director extends Manager {

    private String department;

    public Director (String id, String name, double grossSalary, String DEGREE_TYPES, String department) throws Exception {
        super (id, name, grossSalary, DEGREE_TYPES);

        if (id.isEmpty()){
            throw new InvalidIDException("ID cannot be blank.");}
        if (name.isEmpty()) {
            throw new InvalidNameException("Name cannot be blank.");}
        if (grossSalary <= 0){
            throw new NegativeSalaryException("Salary must be greater than zero.");}
        //create a boolean that returns true if the string DEGREE_TYPES contains the string BSc, MSc or PhD
        boolean containsBSc = DEGREE_TYPES.contains("BSc");
        boolean containsMSc = DEGREE_TYPES.contains("MSc");
        boolean containsPhD = DEGREE_TYPES.contains("PhD");
        //if it does not contain---throw the exception
        if(!containsBSc || !containsMSc || !containsPhD){
            throw new InvalidDegreeException("Degree must be one of the options: BSc, MSc or PhD.");}

        this.department = department;
    }

    public String getDepartment() {return this.department;}
    public void setDepartment(String newDept) {this.department = newDept;}

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
