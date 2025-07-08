package com;

public class Zeitansage{
    public static void main(String[] args){
        TimerListener listener = new TimerListener() {
            int count = 0;
            @Override
            public void signalPerformed(){
                System.out.println(count + " Sekunden seit Start");
                count++;
            }
        };
        Thread a = new Thread( new Timer(listener));
        a.start();
    }
}