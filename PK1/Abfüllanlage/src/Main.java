public class Main {

    public static void main(String[] args){
        Conveyor band = new Conveyor();

        Thread plant = new Thread(new BottlingPlant(band, "Cola"));
        Thread lable = new Thread(new LabelingMachine(band, 2030));

        plant.start();
        lable.start();


    }
    
}
