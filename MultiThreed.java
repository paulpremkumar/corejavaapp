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
Implementing the Callable interface
Core Concepts in Threads and Multithreading
1. Thread
A thread is a single path of execution within a program.

Every Java program has at least one thread (the main thread).

Threads allow concurrent execution of code.

2. Process vs Thread
Process: An independent program with its own memory space.

Thread: A lightweight subprocess running within a process, sharing memory with other threads.

3. Creating Threads
Extending Thread class

Implementing Runnable interface

Using Callable with FutureTask or ExecutorService

4. Thread Lifecycle States
New — Thread created but not started.

Runnable — Thread is ready to run and waiting for CPU.

Running — Thread is actively executing.

Blocked/Waiting — Thread is waiting for a resource or event.

Timed Waiting — Thread waits for a specified time.

Terminated — Thread has finished execution.

5. Thread Scheduling
Managed by JVM and OS.

Preemptive or time-sliced scheduling.

Priority-based scheduling.

6. Synchronization
Prevents race conditions when multiple threads access shared resources.

synchronized keyword (methods or blocks)

Locks (intrinsic locks, explicit Lock interface from java.util.concurrent.locks)

7. Race Condition
When two or more threads access shared data and try to change it at the same time, causing inconsistent or incorrect results.

8. Deadlock
A situation where two or more threads wait indefinitely for each other to release locks.

Must be carefully avoided.

9. Volatile Variables
volatile keyword ensures visibility of changes to variables across threads.

Used for variables shared between threads without locking.

10. Thread Communication
wait(), notify(), and notifyAll() for inter-thread communication.

Used inside synchronized blocks.

11. Executor Framework
Manages a pool of threads for executing tasks.

Interfaces and classes:

Executor

ExecutorService

ScheduledExecutorService

Thread pools (FixedThreadPool, CachedThreadPool, etc.)

12. Callable and Future
Callable allows tasks that return results and throw exceptions.

Future represents the result of an asynchronous computation.

13. ThreadLocal
Provides thread-local variables; each thread has its own independent copy.

14. Atomic Variables
Classes like AtomicInteger, AtomicBoolean, AtomicReference provide lock-free thread-safe programming.

15. Fork/Join Framework
Designed for parallelism, splitting tasks recursively to run in parallel and then combine results.

16. Interrupting Threads
Mechanism to signal a thread to stop its current work (thread.interrupt()).

Threads should handle interruptions properly.

17. Daemon Threads
Background threads that don’t prevent JVM from exiting.

Typically used for housekeeping tasks.

Bonus: Key Java Classes/Interfaces Related to Threads
Class/Interface	Description
Thread	Represents a thread of execution.
Runnable	Interface to define a task without result.
Callable<V>	Interface to define a task with result.
ExecutorService	Thread pool and task execution manager.
Future<V>	Result of async computation.
FutureTask<V>	Wrapper to run Callable in Thread.
Lock	Explicit locking mechanism.
ReentrantLock	Common Lock implementation.
CountDownLatch	Allows one or more threads to wait until other threads complete.
CyclicBarrier	Waits for a set number of threads to reach a point.
Semaphore	Controls access to a resource with permits.
ThreadLocal<T>	Per-thread variable storage.

Summary
Multithreading involves understanding how to create, control, synchronize, and coordinate multiple threads running concurrently in the same program.
*/ 

class OrderBooking extends Thread{
    public void run(){
        System.out.println("Order booking initiated");
    }
}
class NotifyOrderStatus implements  Runnable{
    public void run(){
        try{
            Thread.sleep(500); // paus the thread 5sec
        System.out.println("Notification send initiated"); 

        }catch(InterruptedException e){
          throw new RuntimeException(e.getMessage());
        }
        
    }
}
class PaymentStatus extends Thread{
    public void run(){
        System.out.println("PaymentStatus intiated");
    }
}

/*
✅ Why Use an Executor?
🔹 1. Thread Pooling (Reusability)
Executors use a pool of threads, which can be reused to run multiple tasks. This avoids the overhead of creating new threads for every task.

🔹 2. Simplified Thread Management
You don't have to manage thread lifecycle (start(), join(), etc.) manually. You just submit tasks.

🔹 3. Better Resource Control
You can limit how many threads run at the same time — this prevents overwhelming the system.

🔹 4. Flexible Scheduling
Executors allow you to:

Run tasks once

Run tasks after a delay

Run tasks periodically
Types of Executors:
Executor Type	Description
newSingleThreadExecutor()	One thread for all tasks (runs serially)
newFixedThreadPool(int n)	Fixed number of threads
newCachedThreadPool()	Creates new threads as needed, reuses idle
newScheduledThreadPool(int n)	Run tasks with delay or at intervals

*/