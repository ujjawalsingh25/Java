import java.util.Scanner;

class Swap
{
    public void swap(ReferrenceWrapper x, ReferrenceWrapper y){
        int temp = x.data;
        x.data = y.data;
        y.data = temp;
        System.out.println("After Swap (Inside Swap Function)");
        System.out.println("X: "+  x.data);  
        System.out.println("Y: " + y.data);
    }
}

class ReferrenceWrapper
{
    int data;
    ReferrenceWrapper(int data){
        this.data = data;
    }
}

class H_Swap_ClassAccess
{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        // ReferrenceWrapper wrapper = new ReferrenceWrapper();         // not needed

        System.out.print("Value of X: ");
        int xVal = scanner.nextInt();
        ReferrenceWrapper x = new ReferrenceWrapper(xVal);
        System.out.print("Value of Y: ");
        int yVal = scanner.nextInt();
        ReferrenceWrapper y = new ReferrenceWrapper(yVal);

        Swap swap_ReferrenceVariable = new Swap();                     // Referrence Variable  *** NOT OBJECT *****
        swap_ReferrenceVariable.swap(x, y);
        System.out.println("After Swap (Inside Main)");
        System.out.println("X: "+  x.data);
        System.out.println("Y: " + y.data);

        scanner.close();
    }
}

