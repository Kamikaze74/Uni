public class LabelingMachine implements Runnable{


    private Conveyor band = null;
    private int date;

    public LabelingMachine(Conveyor band, int date){
        this.band = band;
        this.date = date;
    }



    @Override
    public void run() {
        while(true){
            try {
                Bottle bottle = null;

                synchronized (band){
                    while(band.isEmpty()){
                        System.out.println("Etikettiermaschine: Warten, da Foerderband leer\n\n");
                        band.wait();
                    }
                    bottle = band.withdraw();
                    band.notifyAll();
                }
                Lable lable = new Lable(bottle.getSorte(), date);
                bottle.setLable(lable);
                System.out.print("Etikettiermaschine: ");
                bottle.printLable();
                Thread.sleep((int) ((Math.random() % 10.0) * 1000.0));
            } catch (InterruptedException e) {
                
            }
        }
    }
}