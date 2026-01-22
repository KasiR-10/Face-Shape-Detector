import java.util.Scanner;
class Main2 {
    public static void main(String[] args){
        System.out.print("Enter the your age");
        Scanner scan=new Scanner(System.in);
        int age=scan.nextInt();
        String yes = age>18 ? "Adult" : "Minor";
        System.out.println(yes);
        scan.close();



    }
    
}
