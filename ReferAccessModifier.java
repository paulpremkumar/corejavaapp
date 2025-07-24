import java.io.*;
class ReferAccessModifier extends AccessModifier{
     public static void main(String[] args){
        ReferAccessModifier obj=new ReferAccessModifier();
       obj.getValues(); 
     }
   public  void getValues(){
      //  System.out.println(this.a,+'a');
    // System.out.println("a: " + a);
System.out.println("b: " + b);
System.out.println("c: " + c);
System.out.println("d: " + d);
// System.out.println("a: " + a);
    }
}