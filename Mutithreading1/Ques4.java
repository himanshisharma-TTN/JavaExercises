package Mutithreading1;

//4 Write a code to simulate a deadlock in java

class Resource{
    int id = 0;
    Resource(int id){
        this.id=id;
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

    public void computeOperation(){
        synchronized (r1){
            System.out.println(Thread.currentThread().getName()+" Operating on "+r1.id +" ID resource");
            System.out.println(Thread.currentThread().getName()+ " waiting for "+r2.id +" ID resource");
            synchronized (r2){
                r1.work(r2);
            }
            System.out.println("Completed Operation");
        }
    }

    @Override
    public void run() {
        computeOperation();
    }
}

public class Ques4 {
    public static void main(String[] args) {
        Resource r1 = new Resource(1);
        Resource r2 = new Resource(2);

        Thread t1 = new Thread(new NewClass(r1,r2));
        Thread t2 = new Thread(new NewClass(r2,r1));

        t1.start();
        t2.start();

    }
}
