 class BackgroundTask implements Runnable {
    private  boolean running = true; // now volatile

    public void run() {
        while (running) {
            System.out.println("Working...");
            try {
                Thread.sleep(1000); // simulate work
                System.out.println("TRy");
            } catch (InterruptedException e) {
                 System.out.println("catch");
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Stopped");
    }

    public void stop() {
        running = false;
    }
}

public class ThreadVolatileKeyword {
    public static void main(String[] args) throws InterruptedException {
        BackgroundTask task = new BackgroundTask();
        Thread t = new Thread(task);
        t.start();

        Thread.sleep(3000);  // Let it run for a while
         task.stop();         // Send stop signal
        t.join();
    }
}
