class MultiThreed{
    public static void main(String[] arg){
       OrderBooking bookorder=new OrderBooking();
       Runnable notify=new NotifyOrderStatus();
       PaymentStatus paymentstatus=new PaymentStatus();
       bookorder.start();
       Thread notic=new Thread(notify);
       notic.start();
       paymentstatus.start();
       System.out.println("Order management started");
    }
}

/*
 What is Multithreading?
Multithreading is the ability of a program to run multiple parts (threads) concurrently. Each thread runs independently but shares the same memory space.

Java provides two main ways to create threads:

Extending the Thread class

Implementing the Runnable interface
*/ 

class OrderBooking extends Thread{
    public void run(){
        System.out.println("Order booking initiated");
    }
}
class NotifyOrderStatus implements  Runnable{
    public void run(){
        System.out.println("Notification send initiated"); 
    }
}
class PaymentStatus extends Thread{
    public void run(){
        System.out.println("PaymentStatus intiated");
    }
}