
class Sum
{
    int num = 5;                            // instance variable
    public int add(int n1, int n2){
        return n1 + n2;                     // local variable
    }
}

class D__Stack_Heap
{
    public static void main(String[] args){
        int data1 = 3;
        int data2 = 5;
        Sum obj1 = new Sum();           // Referrence Variable  *** NOT OBJECT *****
        Sum obj2 = new Sum();
        
        int result = obj1.add(data1, data2);
        System.out.println("Sum : " + result);

        
        System.out.println("Num variable from Sum using Obj1 & Obj2 : " + obj1.num + " -- " + obj2.num);
        
        obj1.num = 10;

        System.out.println("Num variable from Sum using Obj1 : " + obj1.num);
        System.out.println("Num variable from Sum using Obj2 : " + obj2.num);
    }
}