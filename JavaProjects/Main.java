import java.util.Scanner;
class Main{
    public static void main(String[] args) {
        String name = "Shekar";
        System.out.println("Hi " + name);
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter name : ");
        int age=scanner.nextInt();
        System.out.println(age);
        scanner.close();
    }
}
