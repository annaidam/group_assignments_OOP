import java.util.ArrayList;
public class Company
{
    private ArrayList<Employee> listOfEmployees;

    public Company ()
    {
        this.listOfEmployees= new ArrayList<>();
    }

    public void registerEmployee (String id, String name, double grossSalary)
    {
        Employee employee = new Employee (id, name, grossSalary);
        this.listOfEmployees.add(employee);
    }

    public Employee findEmployee (String id)
    {
        for (int i=0; i<listOfEmployees.size(); i++)
        {
            Employee currentEmployee = listOfEmployees.get(i);
            if(id.equals(currentEmployee.getID()))
            {
                return currentEmployee;
            }
        }
        return null;
    }
}

