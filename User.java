import java.util.ArrayList;


public class User extends Observer implements Subject, Visitor {

    private String userID;
    private ArrayList<Observer> followerList;
    private ArrayList<User> followingList;

    private FeedList newsFeed;
    private String groupID;


    public User(String uid) {
        userID = uid;
        followerList = new ArrayList<Observer>();
        followingList = new ArrayList<User>();
        newsFeed = new FeedList(this);
        groupID = null;
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
        uName.getFollowingList().add(this);
        attach(uName);
    }

    public void post(String string) {
        Message newMessage = new Message(this, string);
        newsFeed.sendMessage(newMessage); 
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
        getFollowerList().add((Observer) observe);
        
    }

    @Override
    public void notifyAllObservers() {
        for(Observer observe : followerList) {
            ((User) observe).update(this);
        }
        //TODO
        
    }

    @Override
    public void update(Subject subject) {
        newsFeed.addToFeed((Message) subject);
        
    }
    
    
}
