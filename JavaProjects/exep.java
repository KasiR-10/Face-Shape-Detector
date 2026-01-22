import java.util.*;
class Myexp extends Exception
{

}
public class exep {
    public static void main(String[] args){
      Scanner sc=new Scanner(System.in);
      int age=sc.nextInt();
      if(age<18)
      {
        throw new IllegalArgumentException("Age cannot be less than 18")
      }
      System.out.println("Age should be above 18");
     }
    }
