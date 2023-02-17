package threading.examples;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class LockExample {


    ReentrantLock lock = new ReentrantLock();
    int count = 0;

    public void increment() {
        lock.lock();
        try {
            count = count + 1;
            System.out.println(Thread.currentThread().getName() + " Count: "+count);
        } finally {
            lock.unlock();
        };
    }

    public void stop (ExecutorService executorService) {
        try {
            System.out.println("attempt to shutdown executor");
            executorService.shutdown();
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executorService.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executorService.shutdownNow();
            System.out.println("shutdown finished");
        }
    }

    public void run() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        IntStream.range(0, 10000)
                .forEach(i -> executor.submit(this::increment));
       stop(executor);
       System.out.println(count);
    }



    public static void main(String[] args) {
        LockExample le = new LockExample();
        le.run();

    }
}