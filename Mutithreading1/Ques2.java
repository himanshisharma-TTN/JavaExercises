package Mutithreading1;

//2 Use Synchronize method and synchronize block to enable synchronization between multiple threads trying to access method at same time.

class TaskProcess implements Runnable{
    int a = 0;
    public void sharedMethod(){
        for (int i=0;i<100;i++){
            try {
                synchronized (this){
                    System.out.println(Thread.currentThread().getName() + " "+ (++a) + " "+i);
                }
                Thread.sleep(2);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public synchronized void newMethod(){
        for (int i=0;i<100;i++){
            try {
                System.out.println(Thread.currentThread().getName() + " "+ (++a) + " "+i);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    @Override
    public void run() {
        newMethod();
        System.out.println("-------------------------");
        sharedMethod();
    }
}

class TaskP extends Thread{
    int a = 0;
    public void sharedMethod(){
        for (int i=0;i<100;i++){
            try {
                synchronized (this){
                    System.out.println(Thread.currentThread().getName() + " "+ (++a) + " "+i);
                }
                Thread.sleep(2);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public synchronized void newMethod(){
        for (int i=0;i<100;i++){
            try {
                System.out.println(Thread.currentThread().getId() + " "+ (++a) + " "+i);
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    @Override
    public void run() {
        newMethod();
        System.out.println(Thread.currentThread().getName()+"-------------------------");
        sharedMethod();
    }
}

public class Ques2 {
    int val = 0;
    void newMethod() throws InterruptedException{
        for (int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+ " " + val++);
            Thread.sleep(100);
        }
    }

    public static void main(String[] args) {
        // Using Runnable interface
        TaskProcess taskProcess = new TaskProcess();
        Thread t1 = new Thread(taskProcess);
        Thread t2 = new Thread(taskProcess);
        t2.setName("T2");
        t1.setName("T1");
        t1.start();
        t2.start();

        // Using Thread Class
        t1 = new TaskP();
        t2 = new TaskP();
        t2.setName("T2");
        t1.setName("T1");
        t1.start();
        t2.start();

    }
}
