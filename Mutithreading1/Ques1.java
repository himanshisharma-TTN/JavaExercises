package Mutithreading1;

//1 Create and Run a Thread using Runnable Interface and Thread class and show usage of sleep

class RunnableUsed implements Runnable{
    @Override
    public void run() {
        try {
            for (int i=0;i<10;i++){
                System.out.println("RunnableUsed class Run method Called "+i);
                Thread.sleep(2000);
            }
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }
}

class ThreadUsed extends Thread{
    @Override
    public void run() {
        try {
            for (int i=0;i<10;i++){
                System.out.println("ThreadUsed class Run method Called "+i);
                Thread.sleep(2000);
            }
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }
}


public class Ques1 {
    public static void main(String[] args) {
        RunnableUsed obj = new RunnableUsed();
        Thread t1 = new Thread(obj);
        Thread t3 = new Thread(obj);
        ThreadUsed t2 = new ThreadUsed();
        try{
            t1.start();
            t1.join(); /// It will not move forward until t1 is executed
            t2.start();
            t3.start();
            t2.join(); /// It will not move forward until t2 is executed
        }catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println("Main-thread Finished ");
    }
}
