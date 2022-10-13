package assignment3;

import java.util.Comparator;

public class SalaryComparator implements Comparator<Employee> {

    public int compare(Employee emp1, Employee emp2) {
        double mySalary = emp1.getGrossSalary();
        double otherSalary = emp2.getGrossSalary();
        if (mySalary > otherSalary) {
            return 1;
        } else if (mySalary == otherSalary) {
            return 0;
        } else {
            return -1;
        }
    }
}
