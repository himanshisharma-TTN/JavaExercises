package Multithreading2;

//1 WAP to show usage of Callable and demonstrate how it is different from Runnable
import java.util.concurrent.*;

class Task implements Callable<Integer>{
    int fact = 0;
    Task(int fact) {
        this.fact = fact;
    }
    @Override
    public Integer call() throws InterruptedException{
        int newval = fact;
        for (int i=1;i<newval;i++){
            System.out.println("Fact loop "+i);
            fact*=i;
            Thread.sleep(1000);
        }
        return fact;
    }

}

public class Ques1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(3);
        Future<Integer> result = ex.submit(new Task(5));
        ex.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Printing Work");
            }
        });
        System.out.println("Now again Factorial of 6 " + ex.submit(new Task(6)).get());
        System.out.println("Factorial result = "+result.get());
       ex.submit(()->{
            System.out.println("Returning 10");
            return 10;
        });
        ex.shutdownNow();
    }
}
