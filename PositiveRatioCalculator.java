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
 *  The PositiveRatioCalculator class implements the Visitor Pattern in practice.
 *  It returns the data about how many messages contains positive messages from a user.
 */
public class PositiveRatioCalculator implements AdminVisitor {

    /*
     * Visitor class that tabulates the total amount of positive
     * messages that a user inputted.
     */
    @Override
    public int visit(User user) {
        return user.getNewsFeed().getPositiveCount();
    }
    
}
