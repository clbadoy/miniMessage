import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

public class UserGroup extends DefaultMutableTreeNode {
    
    private String groupID;
    private ArrayList<User> userList;
    private ArrayList<UserGroup> groupRecursiveList;

    public UserGroup(String uid,User creator) {
        groupID = uid;
        userList = new ArrayList<User>();
        groupRecursiveList = new ArrayList<UserGroup>();
        userList.add(creator);
    }

    public String getGroupID() {
        return groupID;
    }
    public ArrayList<UserGroup> getGroupList() {
        return groupRecursiveList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
    }    

}
