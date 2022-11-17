import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

public interface Subject {
    
    //protected ArrayList<Observer> observer;

    public void attach(Observer observe);

    public void notifyAllObservers();

    /* public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observe) {
        observer.add(observe);
    }
    
    public void notifyAllObservers() {
        for(Observer observe : observer) {
            observe.update(this);
        }
    } */
}
