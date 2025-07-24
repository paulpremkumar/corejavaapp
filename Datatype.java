import java.util.ArrayList;
import java.util.Arrays;
class Datatype{
    public static void main(String[] args){
        // primitive data types
        byte a=10;
        short b=20;
        int c=30;
        long d=40;
        char e='K';
        float f=10.2f;
        double g=20.24;
        boolean h=false;
        // non-primitive datatype
        String name="Paul";
        String[] names = {"paul", "prem", "kumar"};
        class TypeConversion{

        }
        names[0]="rub";
         for (String nameP : names) {
            // nameP="rub";
            System.out.println(nameP);
        }
          ArrayList<String> namesList = new ArrayList<>(Arrays.asList("paul", "prem", "kumar"));
    // Print all names from list
        for (String nameA : namesList) {
            System.out.println(nameA);
        }
        //interface

    }
}