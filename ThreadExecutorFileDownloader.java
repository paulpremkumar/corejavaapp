import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutorFileDownloader {

    // Task that downloads a single file
    static class DownloadTask implements Runnable {
        private final String fileURL;
        private final String fileName;

        public DownloadTask(String fileURL, String fileName) {
            this.fileURL = fileURL;
            this.fileName = fileName;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(fileURL);
                URLConnection connection = url.openConnection();
                try (InputStream in = connection.getInputStream();
                     BufferedInputStream bin = new BufferedInputStream(in);
                     FileOutputStream fout = new FileOutputStream(fileName)) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = bin.read(buffer)) != -1) {
                        fout.write(buffer, 0, bytesRead);
                    }

                    System.out.println(Thread.currentThread().getName() + " finished downloading: " + fileName);

                }
            } catch (IOException e) {
                System.err.println(Thread.currentThread().getName() + " failed to download " + fileName + ": " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // Replace these URLs with real downloadable files
        String[][] files = {
            {"https://example.com/file1.jpg", "file1.jpg"},
            {"https://example.com/file2.jpg", "file2.jpg"},
            {"https://example.com/file3.jpg", "file3.jpg"},
        };

        // Create a fixed thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit all download tasks
        for (String[] file : files) {
            executor.submit(new DownloadTask(file[0], file[1]));
        }

        // Shutdown the executor gracefully
        executor.shutdown();
    }
}
