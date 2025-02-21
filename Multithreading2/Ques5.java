package Multithreading2;
//5 WAP to showcase the difference between shutdown() and shutdownNow().

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ques5 {
    public static void main(String[] args) {
        ExecutorService ex = Executors.newCachedThreadPool();
        System.out.println("1st task Submitted");
        Future<Object> result ;
        System.out.println("2nd task Submitted");
        ex.submit(()->{
            System.out.println(Thread.currentThread().getName()+" completed 2nd task");
        });
        System.out.println("3rd task Submitted");
        ex.submit(()->{
            System.out.println(Thread.currentThread().getName()+" completed 3rd task");
        });
        System.out.println("shutting dowm single ");
        ex.shutdown();


        ex = Executors.newSingleThreadExecutor();
        System.out.println("1st task Submitted to single");
        result = ex.submit(new NewTask()); // Here the task 1 is not completed and terminated
        System.out.println("2nd task Submitted to single");
        ex.submit(()->{
            System.out.println(Thread.currentThread().getName()+" completed 2nd task by single");
        });
        System.out.println("3rd task Submitted");
        ex.submit(()->{
            System.out.println(Thread.currentThread().getName()+" completed 3rd task by single");
        });
        System.out.println("shutting Fixed threaded ");
        System.out.println(ex.shutdownNow().toString());

    }
}
