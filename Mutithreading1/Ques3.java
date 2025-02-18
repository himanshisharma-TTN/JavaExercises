package Mutithreading1;

//3 WAP to showcase the usage of volatile in java.

import java.util.Scanner;

class NewExample implements Runnable{

    volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println("Thread Started");
        while (flag){

        }
        System.out.println("Thread Terminated");
    }

    public void stop(){
        flag=false;
    }
}

public class Ques3 {
    public static void main(String[] args) {
        NewExample obj = new NewExample();
        Thread t = new Thread(obj);
        t.start();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Something : ");
        if(sc.nextInt()==4) obj.stop();
    }
}


