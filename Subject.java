import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

public abstract class Subject extends DefaultMutableTreeNode {
    
    private ArrayList<Observer> observer = new ArrayList<Observer>();
    private int state;

    public int getState() {
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
    }
}
