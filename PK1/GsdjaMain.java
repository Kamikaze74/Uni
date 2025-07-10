package fh.pk1.gui;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GsdjaMain {

    
    

public static void main(String[] args) {

    SimpleIntegerProperty one = new SimpleIntegerProperty();
    SimpleIntegerProperty two = new SimpleIntegerProperty();


    one.bind(two);
    //one.set(5);
    System.out.println(two.getValue());
}

}

