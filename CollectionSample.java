import java.util.*;
import java.util.stream.Collectors;
class CollectionSample{
    public static void main(String [] arg){
      /*
                    Why use the Java Collections Framework?
                🔹 1. Efficient Data Handling
                Instead of writing your own data structures (e.g. arrays, linked lists, hash tables), Java provides ready-to-use, optimized classes for storing and manipulating data.

                🔹 2. Code Reusability & Maintainability
                You avoid rewriting logic for sorting, searching, or managing collections — it's all built in.

                🔹 3. Standardization
                All collections follow common interfaces like List, Set, Map, which:

                Makes your code easier to read

                Supports loose coupling and polymorphism

                🔹 4. Powerful Features
                Sorting (Collections.sort)

                Searching (binarySearch)

                Synchronization (Collections.synchronizedList)

                Thread-safe collections (ConcurrentHashMap, etc.)

                ✅ How to Use the Collections Framework
                1. Choose the Right Interface
                Need	Use This Interface
                Ordered list with duplicates	List
                Unique elements	Set
                Key-value pairs	Map
                Queue-like behavior (FIFO)	Queue or Deque

                2. Pick an Implementation
                Interface	Common Implementations
                List	ArrayList, LinkedList
                Set	HashSet, TreeSet
                Map	HashMap, TreeMap, LinkedHashMap
                Queue	PriorityQueue, ArrayDeque

      */  

        List<String> employeeNames=new ArrayList<String>();
        System.out.println("employeeNames: "+employeeNames);
        // add element to the list
        employeeNames.add("Revan");
        employeeNames.add("Anbu");
        employeeNames.add("Kumar");
        employeeNames.add(1,"Robot");
        int indexPrem=employeeNames.indexOf("Prem");
        if(indexPrem!=-1){ // name based index get and its index after add one element
        employeeNames.add(indexPrem+1,"Pizha");  
        }
        System.out.println("After added employeeNames: "+employeeNames);
        // soring list
        Collections.sort(employeeNames); // sort ascending
         System.out.println("After sorted Ascending employeeNames: "+employeeNames);
        Collections.sort(employeeNames,Comparator.reverseOrder()); // sort descending
         System.out.println("After sorted Descending employeeNames: "+employeeNames);
          Collections.sort(employeeNames); // sort ascending
        // without collactio class descending order
       employeeNames.sort(Comparator.reverseOrder());       
         System.out.println("After sorted Descending employeeNames: "+employeeNames);
         // search & filter
          boolean exist=employeeNames.contains("Revan"); // match case sencitive string exist exactly matched
          System.out.println("ba is exist="+exist);
          // search and filterout get list means
          employeeNames=employeeNames.stream().filter((name)->name.startsWith("R")).collect(Collectors.toList());
        System.out.println("employeeNames: "+employeeNames);
        int indexRevan=employeeNames.indexOf("Revan");
        System.out.println("index of Revan: " +indexRevan);
        // before binarysearch inde you need to sort ascending . its faster then indexof method when list heavy
        Collections.sort(employeeNames);
        int binarySearchIndex=Collections.binarySearch(employeeNames,"Revan");
        System.out.println("Binary search index of Revan: " +binarySearchIndex);
        employeeNames.remove("Kumar");
        System.out.println("After remove kumar employeeNames: "+employeeNames);
        employeeNames.remove(1);
        System.out.println("After remove index 1 employeeNames: "+employeeNames);
        employeeNames.remove("Robo");
        System.out.println("After remove robo not avialable employeeNames: "+employeeNames);
        // employeeNames.remove(5); // Exception in thread "main" java.lang.IndexOutOfBoundsException: Index 5 out of bounds for length 1
        //  System.out.println("After remove not avialable index employeeNames: "+employeeNames);
        employeeNames.clear();
        System.out.println("After All EmployeeNames removed "+employeeNames);

        // linked list
        LinkedList<String> rolesList=new LinkedList<String>();
        rolesList.add("Manager");
        rolesList.addFirst("Director");
        rolesList.addLast("Junior Developer");
        rolesList.add(2,"Senior Developer");
        System.out.println("Roles list: "+rolesList);
        String firstRole=rolesList.getFirst();
        System.out.println("first role = "+firstRole);
        String lastRole=rolesList.getLast();
        System.out.println("Last role = "+lastRole);
         // get head element
         String headElement=rolesList.peek();
         System.out.println("Head element in linkedlist: "+headElement);
        // remove
        rolesList.removeFirst();
         System.out.println("Roles list: "+rolesList);
        rolesList.removeLast();
         System.out.println("Roles list: "+rolesList);
        rolesList.remove("Manager");
         System.out.println("Roles list: "+rolesList);       
       String removedElement= rolesList.poll();
         System.out.println("remove using poll : "+removedElement);
        //   rolesList.remove(5); // Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 5, Size: 0
        //  System.out.println("Remove not available index: "+rolesList);

        // vector
        Vector<Student> studentList=new Vector<Student>();
        studentList.add(new Student(1,"Paul"));
        System.out.println("studentList: "+studentList);
        studentList.add(0,new Student(1,"Prem"));
        System.out.println("studentList: "+studentList);
        Student elementbyIndex=studentList.get(0);
        System.out.println("elementbyIndex : "+elementbyIndex);
        Student s = studentList.elementAt(1);
        System.out.println("Student by elementat : "+s);
        for(int i=0;i<studentList.size();i++){
            System.out.println("each student loop: "+studentList.get(i));
        }
        for(Student i:studentList){
            System.out.println("Enhanced loop: "+i);
        }
        Iterator<Student> iter=studentList.iterator();
        while(iter.hasNext()){
            Student ss=iter.next();
            System.out.println("Student name: "+ss);
        }
        Student firstElement=studentList.firstElement();
        System.out.println("firstElement: "+firstElement);
         Student lastElement=studentList.lastElement();
         System.out.println("lastElement: "+lastElement);
         // remove vector
         studentList.remove(0);
         System.out.println("After remove 0 element vector: "+studentList);
         studentList.clear(); // also remove al element in vector
         studentList.removeAll(studentList); // this also remove all element in vector
         System.out.println("After remove all vector: "+studentList);

         // Queue -  it is a interface not class
         Queue<String> q=new LinkedList<>();
         q.add("Paul");
         q.offer("Prem");
         System.out.println("Queue : "+q);

    }
}
class Student{
    private int id;
    private String name;
    Student(int id,String name){
      this.id=id;
      this.name=name;
    }
    @Override
public String toString() {
    return "{id=" + id + ", name='" + name + "'}";
}
    int getId(){
        return id;
    }
    String getName(){
        return name;
    }
}