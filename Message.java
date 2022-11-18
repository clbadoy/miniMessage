/*
 *  Christian Badoy
 *  CS3560
 *  Profsessor Sun
 *  17 November 2022
 * 
 *  The purpose of this project is to create a functioning mini Twitter GUI program using
 *  the design patterns of Singleton, Composite, Visitor, and Observer. It also makes
 *  us learn the basics of Java Swing.
 *  The purpose of the Message class is to give metadata to a message that a user has sent.
 */
public class Message{
    
    private static int totalMessageCounter = 0;
    private String message;
    private User author;
    private int msgIndex;

    public Message(User user, String text) {
        message = text;
        author = user;
        msgIndex = totalMessageCounter;
        totalMessageCounter++;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return author;
    }

    public int getTotalMessages() {
        return totalMessageCounter;
    }

    public int getMessageIndex() {
        return msgIndex;
    }

    public String toString() {
        String temp = "@" + getUser() + ": " + getMessage();
        return temp;
    }
}
