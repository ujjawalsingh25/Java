import java.util.Scanner;

class G_CallByValue_AndReferrence
{
    static class Referrence_Wrapper{
        int data;
        Referrence_Wrapper(int data){
            this.data = data;
        } 
    }

    public static void callByReferrence(Referrence_Wrapper x, Referrence_Wrapper y) {
            // int temp = &x;
            // &x = y;
            // &y = temp;
            int temp = x.data;
            x.data = y.data;
            y.data = temp;
            System.out.println("Inside Function (Call by Referrence");
            System.out.println("X: " + x.data);
            System.out.println("Y: " + y.data);
        }

    public static void callByValue(int a, int b){
        int temp = a;
        a = b;
        b = temp;
        System.out.println("Inside Function (Call by Value)");
        System.out.println("A: " + a);
        System.out.println("B: " + b);
    }
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter value of A: ");
        int a = scanner.nextInt();
        System.out.print("Enter Value of B: ");
        int b = scanner.nextInt();

        callByValue(a, b);
        System.out.println("From Main (Call by Value)");
        System.out.println("A: " + a);
        System.out.println("B: " + b);
        System.out.println("-------------------------");

        System.out.print("Enter value of X: ");
        Referrence_Wrapper x = new Referrence_Wrapper(scanner.nextInt());
        System.out.print("Enter Value of Y: ");
        Referrence_Wrapper y = new Referrence_Wrapper(scanner.nextInt());
        
        // callByReferrence(x, y);         // Because of Referrence_Wrapper (x, y) is not applicable as these are 'INT' datatype 
        callByReferrence(x, y);
        System.out.println("From Main (Call by Referrence");
        System.out.println("X: " + x.data);
        System.out.println("Y: " + y.data);

        scanner.close();
    }
}