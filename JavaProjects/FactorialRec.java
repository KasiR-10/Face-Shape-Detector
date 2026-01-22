import java.util.Scanner;

class FactorialRec {
    
    static int fact(int n)
    {
        if ( n == 0 || n == 1)
        return 1;
        else 
        return n*fact(n-1);
    }

    public static void main(String[] args) {
    
        System.out.print("Enter the number to find the factorial : ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = fact(n);
        System.out.println("Factorial of "+n+" is "+x);
        sc.close();
    }
}