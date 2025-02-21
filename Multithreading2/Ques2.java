package Multithreading2;

//2 Improve the code written in Basics of Multi Threading Part 1 exercise question 4 to handle the deadlock using reentract lock.

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Resource{
    int id = 0;
    private final ReentrantLock lock = new ReentrantLock();
    Resource(int id){
        this.id=id;
    }

    boolean getLock() throws InterruptedException {
        return lock.tryLock(1000, TimeUnit.MILLISECONDS);
    }
    void unlock(){
        lock.unlock();
    }
    void work(Resource r1){
        System.out.println("Using Resource "+r1.id);
    }
}

class NewClass implements Runnable{
    Resource r1,r2;
    NewClass(Resource r1 , Resource r2){
        this.r1 = r1;
        this.r2 = r2;
    }

    public void computeOperation() {
        boolean workComplete = false;
        while(true){
            boolean r1Lock = false;
            boolean r2Lock = false;
            try{
                r1Lock = r1.getLock();
                r2Lock = r2.getLock();
            }catch (InterruptedException e){
                System.out.println(e);
            }
            System.out.println(Thread.currentThread().getName()+" trying");
            if(r1Lock && r2Lock) {
                System.out.println(Thread.currentThread().getName()+" Operating on "+r1.id+" and "+r2.id+" ID resources");
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    System.out.println();
                }
                r1.work(r2);
                System.out.println("Completed Operation");
                workComplete = true;
            }
            if(r1Lock) {
                System.out.println("Unlock resource R1");
                r1.unlock();
            }
            if(r2Lock) {
                System.out.println("Unlock resource R2");
                r2.unlock();
            }
            if(workComplete) {
                workComplete=false;
                System.out.println(Thread.currentThread().getName()+" Completed ");
                break;
            }
        }
    }

    @Override
    public void run() {
        computeOperation();
    }
}

public class Ques2 {
    public static void main(String[] args)  {
        Resource r1 = new Resource(100);
        Resource r2 = new Resource(200);
        Thread t1 = new Thread(new NewClass(r1,r2));
        Thread t2 = new Thread(new NewClass(r2,r1));
        Thread t3 = new Thread(new NewClass(r2,r1));
        t1.start();
        t2.start();
        t3.start();
    }
}
