public class BottlingPlant implements Runnable{

    private Conveyor band;
    private String sorte;

    public BottlingPlant(Conveyor band, String sorte){
        this.band = band;
        this.sorte = sorte;
    }

    @Override
    public void run(){
        while(true){
            try {
                synchronized (band){
                    while(band.isOverloaded()){
                        System.out.println("Abfuellanlage: Warten, da Foerderband voll\n\n");
                        band.wait();
                    }
                    Bottle bottle = new Bottle();
                    bottle.setSorte(sorte);
                    System.out.println("Abfuellanlage: Neue Flasche abgefuellt");
                    band.load(bottle);
                    band.notifyAll();
                }
                Thread.sleep((int) ((Math.random() % 5.0) * 1000.0));
            } catch (InterruptedException e) {}
        }
   }
}