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

    public String toString() {
        return getGroupID();
    }

}
