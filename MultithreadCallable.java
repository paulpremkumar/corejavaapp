import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultithreadCallable {
    public static void main(String[] args) {
        // Create a fixed thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Define a Callable task
        Callable<String> task = () -> {
            Thread.sleep(2000);  // Simulate work
            return "Task completed!";
        };

        // Submit the task to executor
        Future<String> future = executor.submit(task);

        try {
            // Get the result (blocking call)
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
