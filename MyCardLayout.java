import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyCardLayout extends CardLayout {

    //@override
    public void addLayoutComponent(Component comp, Object constraints) {

       if  (constraints != null) {
         super.addLayoutComponent(comp, constraints.toString());
       } else {
         super.addLayoutComponent(comp, constraints);
       }
    }
    
    //override
    public void show(JPanel comp, MenuEnum constraints){
        if  (constraints != null) {
         super.show(comp, constraints.toString());
       }
    }
}