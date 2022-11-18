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
 *  The Subject interface is used to implement a follow system for users and is used for
 *  live updating.
 */
public interface Subject {

    /*
     * Adds a user to the follower list of the main User.
     */
    public void attach(Observer observe);

    /*
     * Notifies all of the followers of a new post/message
     * to automatically update to Feed.
     */
    public void notifyAllObservers();

}
