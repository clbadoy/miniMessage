import java.util.ArrayList;


public class User implements Subject, Observer, Visitor {

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

    public void followUser(User uName) {
        this.getFollowingList().add(uName);
        attach(uName);
    }

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
    
    // Update Feed


    @Override
    public void accept(AdminVisitor adminVisitor) {
        adminVisitor.visit(this);
    } // TODO

    @Override
    public void attach(Observer observe) {
        ((User) observe).getFollowerList().add(this);
        
    }

    @Override
    public void notifyAllObservers() {
        for(Observer observe : followerList) {
            observe.update(newMessage);
        }
        
    }

    @Override
    public void update(Message message) {
        newsFeed.addToFeed(message);
        
    }

   
    
}
