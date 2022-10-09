public class Intern extends Employee {
    private int gpa;
    /*
@Override Employee
+calculateNetSalary() : double
//3 ‘if statements’ if-else if-else
//this.grossSalary=super.grossSalary (of Employee) +1000SEK for the 3rd if statement
//netSalary=grossSalary

     */
    /*Intern: An intern is a student practising their skills in the company.
    Therefore, an intern has a GPA (Grade Point Average) that is an integer between 0 - 10.
    The GPA is defined when creating the Intern and can later be updated.
    In turn, the GPA will affect the student’s salary since the company encourages the student to still focus on his/her studies.

GPA less than or equal to 5: then he/she will not receive a gross salary (i.e., it will be zero).
GPA between 5 and 8: then he/she will receive his/her full gross salary.
GPA greater than or equal to 8: then he/she will receive the full gross salary,
plus a benefit of 1,000 SEK to reward them for academic excellence.

Similar to the Manager, be careful not to erase the student’s original salary,
since he or she may improve their GPA. Since a student does not pay taxes while being an intern,
the net salary returned by a student will be the same as his/her gross salary.
*/


    public Intern(String name, String ID, double gross_salary, int gpa) throws Exception {
        super(name, ID, gross_salary);
        this.gpa = gpa; //how can i set gpa to be between 0-10? bc it is not recommended to have conditionals in the constructor
    }

    public void setGpa(int gpa) {  //newGpa?
        this.gpa = gpa;          //=newGpa?
    }

    @Override
    public double calculateNetSalary(){     //definition
        if(this.gpa<=5){
            this.grossSalary=0;
        }
        else if(gpa<8){
            this.grossSalary = super.grossSalary;
        }
        else {
            this.grossSalary=super.grossSalary +1000;
        }
    }

    @Override
    public String toString() {
        return name + "'s gross salary is " + this.calculateNetSalary() + "SEK per month. GPA: "+ this.gpa;
    }
}