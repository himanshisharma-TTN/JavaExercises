package Multithreading2;

import java.util.Scanner;
import java.util.concurrent.*;

//3 Use a singleThreadExecutor, newCachedThreadPool() and newFixedThreadPool()
// to submit a list of tasks and wait for completion of all tasks.

class NewTask implements Callable<Object>{
    @Override
    public synchronized Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" Working on 1st task.");
        for (int i=0;i<5;i++){
//            Scanner sc = new Scanner(System.in);
//            int val = sc.nextInt();
            System.out.println(Thread.currentThread().getName()+" in sleeping state"+i);
            Thread.sleep(i*1000);
        }
        return 10;
    }
}

public class Ques3 {
    public static void main(String[] args) {

        // New Cached Thread
        ExecutorService ex = Executors.newCachedThreadPool();
        System.out.println("1st task Submitted");
        Future<Object> result = ex.submit(new NewTask());
        System.out.println("2nd task Submitted");
        ex.submit(()->{
//            Scanner sc = new Scanner(System.in);
//            int val = sc.nextInt();
            System.out.println(Thread.currentThread().getName()+" completed 2nd task");
        });
        System.out.println("3rd task Submitted");
        ex.submit(()->{
            System.out.println(Thread.currentThread().getName()+" completed 3rd task");
        });
        System.out.println("shutting dowm");
        ex.shutdownNow();
//        System.out.println();


        // singleThreadExecutor
        ex = Executors.newFixedThreadPool(4);
        System.out.println("1st task Submitted to fixed");
        result = ex.submit(new NewTask());
        System.out.println("2nd task Submitted");
        ex.submit(()->{
//            Scanner sc = new Scanner(System.in);
//            int val = sc.nextInt();
            System.out.println(Thread.currentThread().getName()+" completed 2nd task by fixed");
        });
        System.out.println("3rd task Submitted");
        ex.submit(()->{
            System.out.println(Thread.currentThread().getName()+" completed 3rd task by fixed");
        });
        System.out.println("shutting 2");
//        ex.shutdown();
        ex.shutdownNow();

        // singleThreaded
        ex = Executors.newFixedThreadPool(2);
        System.out.println("1st task Submitted to single");
        result = ex.submit(new NewTask());
        System.out.println("2nd task Submitted to single");
        ex.submit(()->{
//            Scanner sc = new Scanner(System.in);
//            int val = sc.nextInt();
            System.out.println(Thread.currentThread().getName()+" completed 2nd task by single");
        });
        System.out.println("3rd task Submitted");
        ex.submit(()->{
            System.out.println(Thread.currentThread().getName()+" completed 3rd task by single");
        });
        System.out.println("shutting 3");
//        ex.shutdown();
        System.out.println(ex.shutdownNow().toString());


    }
}
