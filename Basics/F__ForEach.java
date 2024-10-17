
class Student
{
    int roll;
    String name;
    int marks;
}


class F__ForEach
{
    public static void main(String[] args){
        int arr[] = new int[5];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;
        arr[4] = 5;
        
        for(int iter : arr){
            System.out.println(iter);       // iter directly gives the value rather the index position
        }
        System.out.println("--------------");

  
        Student s1 = new Student();
        s1.roll = 101;
        s1.name = "ABC";
        s1.marks = 83;
        Student s2 = new Student();
        s2.roll = 102;
        s2.name = "DEF";
        s2.marks = 94;
        Student s3 = new Student();
        s3.roll = 103;
        s3.name = "GHI";
        s3.marks = 97;

        Student students[] = new Student[3];     // Declaring the array of Objects and its size   
        students[0] = s1;                       // Assigning the values
        students[1] = s2;
        students[2] = s3;

        for(Student stIter : students){
            System.out.println(stIter.roll + " -- " + stIter.name + " -- " + stIter.marks);
        }
    }
}