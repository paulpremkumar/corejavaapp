class StringOperation{
    public static void main(String[] arg){
     String name="Paul";    
     String Nickname=name;  
      name="Prem";
       System.out.println("my Name is: "+name);
       System.out.println("my nick Name is: "+Nickname);
       /*String pool
            String Pool is a memory-saving feature in Java.

            It stores only one copy of each string literal.

            Use intern() if you want to add strings manually to the pool.
       */
      String message="Hello"; // its store on string pool
      String message2="Hello"; // both message and message1 store in same litral so it store same string pool
// but
String message3 = new String("Hello");
// message three also same litral but it not store in string pool its store on new object heap memory
// if you wan manualy change it into string bool means you could change using intern() to store into same string bool
System.out.println("string is not in poool due to message3 store heep "+(message2==message3));
message3=message3.intern();
System.out.println("after manualy add it into string pool "+(message2==message3));
int noOfCharInString=message3.length();
System.out.println("noOfCharInString = "+noOfCharInString);
System.out.println(message3.toUpperCase());
System.out.println(message3.toLowerCase());
String message4 = "Hello ";
System.out.println(message3.trim());
System.out.println(message3.charAt(1));
System.out.println(message3.substring(1));
System.out.println(message3.substring(1,3));
System.out.println(message3.contains("ll"));
System.out.println(message3.equals("Hello"));
String message5 = "";
System.out.println(message5.isEmpty());
System.out.println(message5.isBlank());
StringBuffer sbuf = new StringBuffer("Hello");
sbuf.append(" World");  // Thread-safe append
System.out.println(sbuf);
StringBuilder sbld = new StringBuilder("Hello");
sbld.append(" World");  // Faster but not thread-safe
System.out.println(sbld);

// synchronized threed
StringBuffer sb = new StringBuffer();

Thread t1 = new Thread(() -> {
    for (int i = 0; i < 5; i++) {
        sb.append("A");
    }
});

Thread t2 = new Thread(() -> {
    for (int i = 0; i < 5; i++) {
        sb.append("B");
    }
});

t1.start();
t2.start();
// ✅ Wait for both threads to finish
try {
    t1.join();
    t2.join();
} catch (InterruptedException e) {
    e.printStackTrace();
}

System.out.println("Output: " + sb);  // Now this should work correctly
    }
}