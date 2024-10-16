class A__TypeCasting
{
    public static void main(String args[]){
        byte b1 = 12;
        int a1 = b1;
        System.out.println("Byte to Int  : " + a1);
        
        // int a2 = 2;
        // byte b2 = a2;       --> *** ERROR ***  int takes 4byte of memory space so can't be typecast to byte implicitly
        
        int a3 = 517;
        byte b3 = (byte) a3;            // -->> 517 % 256 = (rem -> 5) (Since int takes more memory
        System.out.println("Int to Byte  : " + b3);       // so the remainder will be the output when typecasted)
        
        float f1 = 4.29f;
        int a4 = (int) f1;
        System.out.println("Float to Int : " + a4);

        // ______  Type Promotion  ______________________________________________________
        byte a = 10;                                                               //   |
        byte b = 40;                                                               //   |
        int result = a*b;       //  Java converts to INT -> as byte of 10*40 i.e 400 is out of bytes memory range of -127 to 127
        System.out.println(result);             // The conversion is Type Promotion     |
        // _____________________________________________________________________________|

    }
}