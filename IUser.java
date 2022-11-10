import java.util.ArrayList;

public interface IUser {
    
    public String getUID();

    public ArrayList<User> getFollowerList();

    public ArrayList<User> getFollowingList();

    // Adds person followed to following list, and adds self to User's Followers list.
    public void followUser(User uName);

    // public void followGroup(String gid); ***Visitor?
}
