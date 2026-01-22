import java.util.Scanner;
public class Main1 {
    public static void main(String[] args){
        System.out.println("Enter your age");
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter your age");
        int age=scan.nextInt();
        System.out.println("Age is "+age);
        scan.close();

    }
}
