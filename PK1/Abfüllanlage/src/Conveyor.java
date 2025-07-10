import java.util.LinkedList;

public class Conveyor{

    private LinkedList<Bottle> bottleQueue = new LinkedList<Bottle>();
    private boolean overloaded = false;

    public void load(Bottle b){
        if(bottleQueue.size() < 50){
            bottleQueue.add(b);
        }else
            overloaded = true;
    }
    public Bottle withdraw(){
        if(isOverloaded())
            overloaded = false;
        return bottleQueue.pop();
    }

    public boolean isEmpty(){
        return bottleQueue.isEmpty();
    }

    public boolean isOverloaded(){
        return overloaded;
    }
}