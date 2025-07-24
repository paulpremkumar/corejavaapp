class TypeConversion{
    public static void main(String[] arg){
      /*
      two type conversion in java
     1.Primitive type conversion
       1.widening(implicit)
        Automatically converts a smaller type to a larger type — safe and no data loss
        no explicit cast needed
       2.Norrowing(explicit)
       Converts a larger type to a smaller type — may cause data loss, so must use casting.
       explicit cast, might lose data
     2.Reference type conversion
        Upcasting (Implicit):
          Casting subclass to superclass — safe and automatic.
        Downcasting (Explicit)
          Casting superclass to subclass — requires explicit cast, risky at runtime.
    3. Conversion Between Primitive and Wrapper Classes (Boxing & Unboxing)
            Boxing (Autoboxing):
        It is the automatic conversion of a primitive type (like int, double) to its corresponding wrapper class object (Integer, Double).

        Example: converting int → Integer.

        Unboxing:
        It is the automatic conversion of a wrapper class object back to its corresponding primitive type.

        Example: converting Integer → int.

        Why Boxing/Unboxing?
        Java collections like ArrayList can only store objects, not primitives.

        So primitives must be converted (boxed) into objects when used in such collections.

        Similarly, when you get the object out, it’s converted back (unboxed) to primitives when needed.


      */ 
     //byte to int,char,float,double,long (implicit)
    byte a=10;
    long b=a;
    int c=a;
    double d=a;
    float e=a;
    System.out.println("Byte "+a+" is converted into long b="+b);
    System.out.println("Byte "+a+" is converted into int c="+c);
    System.out.println("Byte "+a+" is converted into double d="+d);
    System.out.println("Byte "+a+" is converted into float e="+e);
    // double into  byte,long,int,double,float (explicit)
      double value=10.2345687453845559887766;
      int valueInt=(int) value;
      byte valueByte=(byte) value;
      float valueFloat=(float) value;
      long valueLong=(long) value;
    System.out.println("double "+value+" is converted into int valueInt="+valueInt);
    System.out.println("double "+value+" is converted into  valueByte="+valueByte);
    System.out.println("double "+value+" is converted into  valueFloat="+valueFloat);
    System.out.println("double "+value+" is converted into  valueLong="+valueLong);
    TestUpcasting objUpcasting = new TestUpcasting();
     // boxed int to integer
     int unbox=10;
     Integer boxed=unbox;
      System.out.println("Boxed Integer: " + boxed);
     // unboxed
     Integer boxedV2= Integer.valueOf(20);
     int unboxV2=boxedV2;
     System.out.println("UnBoxed Integer: " + unboxV2);

    }
      
}
 

// referance type converion - Upcasting (Implicit)
    class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
    }
class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
    void fetch() {
        System.out.println("Dog fetches the ball");
    }
}

 class TestUpcasting {
    public static void main(String[] args) {
       
    }
    public TestUpcasting(){
 Dog dog = new Dog();
System.out.println("call type casting");
        // Upcasting: Dog → Animal (implicit)
        Animal animal = dog;

        animal.sound(); // Dog barks (runtime polymorphism)

        // animal.fetch();  // Compile error! Animal doesn't have fetch()
    }
}