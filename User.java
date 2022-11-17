import java.util.ArrayList;


public class User extends Observer implements Visitor, Subject {

    private String userID;
    private ArrayList<Observer> followerList;
    private ArrayList<User> followingList;

    private FeedList newsFeed;

    public User(String uid) {
        userID = uid;
        followerList = new ArrayList<Observer>();
        followingList = new ArrayList<User>();
        newsFeed = new FeedList(this);
    }

    public String getUID() {
        return userID;
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

    public String toString() {
        return getUID();
    }
    
    // Update Feed


    @Override
    public void accept(AdminVisitor adminVisitor) {
        adminVisitor.visit(this);
    }

    @Override
    public void attach(Observer observe) {
        getFollowerList().add((Observer) observe);
        
    }

    @Override
    public void notifyAllObservers() {
        for(Observer observe : followerList) {
            ((User) observe).getNewsFeed().update((Message) subject);//.(Message) subject);
        }
        //TODO
        
    }

    @Override
    public void update(Subject subject) {
        // TODO Auto-generated method stub
        
    }
    
    
}
