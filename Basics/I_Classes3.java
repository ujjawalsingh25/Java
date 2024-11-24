import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

// 1001
// Ashwa
// IT
// 5
// true
// 1002
// Preeti
// IT
// 4
// true
// 1003
// Uma
// Admin
// 3
// false
// 1004
// Akash
// Hardware
// 4.5
// false
// IT 
// 2
// 1003
// Uma

class Employee {
    int id;
    String name;
    String branch;
    Double rating;
    Boolean transport;

    public Employee(int id, String name, String branch, Double rating, Boolean transport) {
        this.id = id;
        this.name = name;
        this.branch = branch;
        this.rating = rating;
        this.transport = transport;
    }

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getBranch()
    {
        return branch;
    }
    public void setBranch(String branch)
    {
        this.branch = branch;
    }
    public double getRating()
    {
        return rating;
    }
    public void setRating(double rating)
    {
        this.rating = rating;
    }
    public boolean getTransport()
    {
        return transport;
    }
    public void setTransport(boolean transport)
    {
        this.transport= transport;
    }
}

class I_Classes3
{
    public static int findCountOfEmployeesUsingCompTransport(Employee[] emp, String branch) {
        int count = 0;
        for(Employee empIter : emp) {
            if(empIter.getBranch().equals(branch) && empIter.getTransport()) count++;
        }

        return count;
    } 

    public static Employee[] findEmployeeWithSecondHighestRating(Employee[] emp) {
        ArrayList<Employee> ans = new ArrayList<>();
        ArrayList<Employee> ans1 = new ArrayList<>();
        for(Employee empIter: emp) {
            if(empIter.getTransport() == false) ans.add(empIter);
        }
        ans.sort(Comparator.comparing(Employee:: getRating).reversed());
        ans1.add(ans.get(1));
        // return ans.get(1);       // if return type is Employee not Employee[]
        // return new Employee[] { ans.get(1) };
        return ans1.toArray(new Employee[0]);
    } 

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Employee emp[] = new Employee[4];
        for(int i=0;i<4;i++) {
            int id = scanner.nextInt();
            scanner.nextLine();
            String name = scanner.nextLine();
            String branch = scanner.nextLine();
            Double rating = scanner.nextDouble();
            scanner.nextLine();
            Boolean transport = scanner.nextBoolean();
            scanner.nextLine(); 

            emp[i] = new Employee(id, name, branch, rating, transport);
        }
        String branch = scanner.nextLine();

        int count = findCountOfEmployeesUsingCompTransport(emp, branch);
        if(count!=0)
        {
            System.out.println(count);
        }
        else{
            System.out.println("No such Employees");
        }

        Employee[] secondHighest = findEmployeeWithSecondHighestRating(emp);
        if (secondHighest != null) {
            System.out.println(secondHighest[0].getId());
            System.out.println(secondHighest[0].getName());
        } else {
            System.out.println("All Employees using company transport");
        }

        scanner.close();
    }

}