import java.util.Scanner;

class C__SwitchCase
{
    public static void main(String[] args) {
        String operation;
        int result, a, b;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number 1 (a): ");
        a = scanner.nextInt();
        System.out.print("Enter number 2 (b): ");
        b = scanner.nextInt();
        System.out.print("Enter operation (add, subtract, multiply, divide): ");
        operation = scanner.next();

        // switch(operation) {
        //     case "add":
        //         result = a + b;
        //         System.out.println(result);
        //         break;
        //     case "subtract":
        //         result = a - b;
        //         System.out.println(result);
        //         break;
        //     case "multiply":
        //         result = a * b;
        //         System.out.println(result);
        //         break;
        //     case "divide":
        //         result = a / b;
        //         System.out.println(result);
        //         break;
        //     default:
        //         System.out.println("Give a valid operation");
        //         break; 
        // }

    // __________  Can also be written As  _______________________________________________________                                                                                          
        result = switch(operation) {                                                          //  |
            case "add"      ->  a + b;          // return a+b value to 'result'               //  |
            case "subtract" ->  a - b;                                                        //  |                                 
            case "multiply" ->  a * b;                                                        //  |                                 
            case "divide" ->  a * b;                                                          //  |                                 
            default -> -1;                                                                    //  |                     
        };                                                                                    //  |     
    //                                                                                        //  |  
        if(result != -1)    System.out.println("Result: " + result);                          //  |
        else    System.out.println("Enter a valid operation.");                             //  |
                                                                                              //  |    
        scanner.close();                                                                      //  |
    }                                                                                         //  |
    // ___________________________________________________________________________________________|
}