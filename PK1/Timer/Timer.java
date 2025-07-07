public class Timer {

    public interface TimerListener {
        public void signalPerformed(int seconds);
}
    public static void main(String[] args){

        TimerListener listener = new TimerListener() {
            @Override
            public void signalPerformed(int seconds) {
                    System.out.println(seconds + " Sekunden seit Start");
            }
        };

        Thread thread1 = new Thread(() -> {
            try {
                int seconds = 0;
                System.out.println("Timer gestartet");
                while (true) {
                    Thread.sleep(1000);
                    listener.signalPerformed(seconds);
                    seconds++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Beide Threads sind beendet");


    }
}
