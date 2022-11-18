/*
 *  Christian Badoy
 *  CS3560
 *  Profsessor Sun
 *  17 November 2022
 * 
 *  The purpose of this project is to create a functioning mini Twitter GUI program using
 *  the design patterns of Singleton, Composite, Visitor, and Observer. It also makes
 *  us learn the basics of Java Swing.
 * 
 *  This is the Observer interface that requires classes to implement.
 */
public interface Observer {
    
    /*
     * Oberver interface to implement a functioning update message
     * every time a user posts for his/her followers to see.
     */
    public abstract void update(Message message);
}
