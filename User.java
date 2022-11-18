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
 *  The User class contains metadata about the user, has his/her own feed to read, and post.
 */
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

public class User extends DefaultMutableTreeNode implements Subject, Observer, Visitor {

    private String userID;
    private ArrayList<Observer> followerList;
    private ArrayList<User> followingList;

    private FeedList newsFeed;
    private String groupID;
    private Message newMessage;


    public User(String uid) {
        userID = uid;
        followerList = new ArrayList<Observer>();
        followingList = new ArrayList<User>();
        newsFeed = new FeedList(this);
        groupID = null;
        newMessage = null;
    }

    public String getUID() {
        return userID;
    }

    public String getGroupID() {
        return groupID;
    }

    public ArrayList<Observer> getFollowerList() {
        return followerList;
    }

    public ArrayList<User> getFollowingList() {
        return followingList;
    }

    public FeedList getNewsFeed() {
        return newsFeed;
    } 

    /*
     * Upon a user pressing the follow button,
     * Adds typed username to the folowing list of the user.
     * Attach function adds the current user to the other User's follower list.
     * Uses Observer pattern,
     */
    public void followUser(User uName) {
        this.getFollowingList().add(uName);
        attach(uName);
    }

    /*
     * Post method that uses Observer pattern to update followers' feeds with
     * the user's recent message.
     */
    public void post(String string) {
        newMessage = new Message(this, string);
        newsFeed.sendMessage(newMessage); 
        notifyAllObservers();
    }

    public void setGroupName(String gid) {
        groupID = gid;
    }

    public String toString() {
        return getUID();
    }

    /*
     * Visitor pattern to allow variations types of Visitor classes.
     * Used for conducting calculations.
     */
    @Override
    public void accept(AdminVisitor adminVisitor) {
        adminVisitor.visit(this);
    } // TODO

    /*
     * Observer pattern used to attach users to follower lists of another user.
     */
    @Override
    public void attach(Observer observe) {
        ((User) observe).getFollowerList().add(this);
        
    }

    /*
     * Observer pattern to notify all of the users about the new post made.
     */
    @Override
    public void notifyAllObservers() {
        for(Observer observe : followerList) {
            observe.update(newMessage);
        }
        
    }

    /*
     * Observer pattern to update the feeds of other users.
     */
    @Override
    public void update(Message message) {
        newsFeed.addToFeed(message);
        
    }

   
    
}
