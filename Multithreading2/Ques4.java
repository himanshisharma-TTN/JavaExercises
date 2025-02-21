package Multithreading2;
//4 WAP to return a random integert value from a thread execution using Future.


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ques4 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        Future<Integer> res = ex.submit(()->{
            System.out.println("Random Integer generating : ");
            Thread.sleep(2000);
            return (int) (Math.random()*100);
        });
        System.out.println(res.get());
        ex.shutdownNow();
    }
}
