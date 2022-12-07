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
 *  The UserGroup class creates new groups and adds members onto the group.
 */

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

public class UserGroup extends DefaultMutableTreeNode {
    
    private String groupID;
    private ArrayList<User> userList;
    private ArrayList<UserGroup> groupRecursiveList;
    private long groupCreationTime;
    private Timestamp timeCreateGroup;

    public UserGroup(String uid,User creator) {
        groupID = uid;
        userList = new ArrayList<User>();
        groupRecursiveList = new ArrayList<UserGroup>();
        groupCreationTime = System.currentTimeMillis();
        timeCreateGroup = new Timestamp(groupCreationTime);
        userList.add(creator);
        System.out.println(printGroupCreationTime(creator));
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

    public String toString() {
        return getGroupID();
    }

    public String printGroupCreationTime(User person) {
        String temp = "User \"" + person.getUID() + "\" has created the group \"" + getGroupID() + "\" @ " + timeCreateGroup.toString();
        return temp;  
    }

}
