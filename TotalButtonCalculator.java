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
 *  The TotalButtonCalculator class implements the Visitor Pattern in practice. It tabulates
 *  the total number of messages a user has sent.
 */

public class TotalButtonCalculator implements AdminVisitor {

    /*
     * A visitor class that calculates the total message count
     * that a user sent.
     */

    @Override
    public int visit(User user) {
        return user.getNewsFeed().getMessageCount();
    }

    
}
