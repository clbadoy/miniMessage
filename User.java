import java.util.ArrayList;


public class User extends Observer implements Visitor {

    private String userID;
    private ArrayList<User> followerList;
    private ArrayList<User> followingList;

    private FeedList newsFeed;
    public User(String uid) {
        userID = uid;
        followerList = new ArrayList<User>();
        followingList = new ArrayList<User>();
    }

    public String getUID() {
        return userID;
    }

    public ArrayList<User> getFollowerList() {
        return followerList;
    }

    public ArrayList<User> getFollowingList() {
        return followingList;
    }

    public void followUser(User uName) {
        followingList.add(uName);
        uName.getFollowerList().add(this);
    }

    public String toString() {
        return getUID();
    }
    
    // Update Feed
    @Override
    public void update(Subject subject) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void accept(AdminVisitor adminVisitor) {
        adminVisitor.visit(this);
    }
    
    
}
