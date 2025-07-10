public class Bottle{

    private Lable lable;
    private String sorte;
    public Object band;

    public String getSorte() {
        return sorte;
    }

    public void setSorte(String sorte) {
        this.sorte = sorte;
    }

    public void printLable(){
        System.out.println(lable.getType() + " mindestens haltbar bis " + lable.getBestBefore());
    }

    public void setLable(Lable lable){
        this.lable = lable;
    }
}