
class Student
{
    int roll;
    String name;
    int marks;
}


class E__ArrayOf_Objects
{
    public static void main(String[] args){
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

        Student students[] = new Student[3];    // Declare array of Object and its size
        students[0] = s1;               // Assigning the values 
        students[1] = s2;
        students[2] = s3;

        for(int i=0;i<students.length;i++){
            System.out.println(students[i].roll + " -- " + students[i].name + " -- " + students[i].marks);
        }
    }
}