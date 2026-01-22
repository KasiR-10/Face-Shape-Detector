import java.util.Scanner;
class Student{
    String name;
    int age;
    int major;
    void display(){
        System.out.println("Name :"+name+"\n"+" Age : "+age+"\n"+"Major is : "+major);
    }
}
class Main7{
    public static void main(String[] args){
        Student st=new Student();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your name");
        st.name=sc.nextLine();
        System.out.println("Enter your age");
        st.age=sc.nextInt();
        System.out.println("Enter your major");
        st.major=sc.nextInt();
        st.display();
        sc.close();
    }
}

