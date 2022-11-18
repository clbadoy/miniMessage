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
 *  The AdminVisitor interface that ties multiple variations of Visitor classes.
 */
public interface AdminVisitor {
    
    public int visit(User user);

    // public void visit(Feed feed);
}
