package com;

public class Timer implements Runnable{
    
    TimerListener listener = null;

    public Timer(TimerListener listener){
        this.listener = listener;
    }

    @Override
    public void run() {
        while(true){
            listener.signalPerformed();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}
