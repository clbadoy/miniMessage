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
 *  The Admin class that provides functionality to test most of the features of a minitwitter.
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.event.*;

import java.util.regex.*;
import java.util.ArrayList;


public class Admin extends JFrame {
    
    private static Admin instance = null;

    private GridBagConstraints c;
    // Panel for Right side of Admin Panel
    private JPanel rightPanel;

    // Top Right Panels, buttons, text heirarchy
    private JOptionPane popupPane;
    private JFrame popUp;
    private GridLayout topRightLayout;
    private JPanel topRightPanel;
    private JPanel userPanel;
    private JPanel groupPanel;
    private JPanel verifyLastPanel;

    private JTextField userField;
    private JTextField groupField;

    private JButton createUserButton;
    private JButton createUserGroupButton;
    private JButton viewUserButton;

    private JButton verifyUniqueButton;
    private JButton getLastUpdatedUserButton;

    // Bottom Right Panels and Buttons Heirarchy
    private GridLayout bottomRightLayout;
    private JPanel bottomRightPanel;
    private JButton displayTotalUserButton;
    private JButton displayTotalGroupButton;
    private JButton displayTotalMessageButton;
    private JButton displayPositiveMessageRatioButton;


    // Left Screen Panel Region
    private UserGroup rootUserGroup;
    private DefaultTreeModel treeModel;
    private JScrollPane pane;
    private JTree tree;
    private DefaultMutableTreeNode root;

    private ArrayList<User> userList = new ArrayList<User>();
    private ArrayList<UserGroup> groupList = new ArrayList<UserGroup>();
    private static ArrayList<JFrame> openPanels = new ArrayList<>();

    private String newUser;
    private String newGroup;

    

    private static String alphaNumeric = "^[a-zA-Z0-9_]+$";


    // Constructor that revents classes to create another object of the Admin Class.
    private Admin()
    {
        initComponents();
    }

    public static Admin getInstance() 
    {
        if(instance == null) 
        {
            instance = new Admin();
        }

        return instance;
    }

    /**
     * Creates UI for Admin Panel
     */
    private void initComponents() {
        // Initialize JFrame and GridBagConstraints
        this.setTitle("Admin Panel");
        c = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.getContentPane().getInsets().set(10,10,10,10);
        this.setResizable(false);

        // Initialize right side of UI
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Initialize Top Right Layout.
        initTopRightLayout();

        // Initialize Bottom Right Layout
        initBottomRightLayout();

        // Combine items to the right side of the UI
        rightPanel.add(topRightPanel);
        rightPanel.add(Box.createVerticalStrut(250));
        rightPanel.add(bottomRightPanel);

        // Add rightPanel to JFrame via GridBagLayout
        rightPanel.add(bottomRightPanel);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        c.ipady=20;
        this.getContentPane().add(rightPanel, c);

        // Initialize left Side of UI + initialize Tree
        rootUserGroup = new UserGroup("root", new User("Admin"));
        groupList.add(rootUserGroup);
        root = new DefaultMutableTreeNode(rootUserGroup.getGroupID());
        tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        pane = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setBounds(25, 25, 200, 700);

        // Add Tree to Left side of UI
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 300;
        c.weightx = 0.5;
        c.gridheight = 3;
        c.gridx = 0;
        c.gridy = 0;
        this.getContentPane().add(pane, c);

        // Finalize Initialization
        this.setVisible(true);
    }

    /*
     * Creates Top Right Layout and implements functionalities for the buttons.
     * Contains userField, groupField, createUserButton, createUserGroupButton, and viewUserButton.
     */
    private void initTopRightLayout() {
        // Create 
        topRightLayout = new GridLayout(4, 0, 10, 10);
        topRightPanel = new JPanel(topRightLayout);

        userPanel = new JPanel(new FlowLayout());
        groupPanel = new JPanel(new FlowLayout());
        verifyLastPanel = new JPanel(new FlowLayout());
        topRightPanel = new JPanel(topRightLayout);

        userField = new JTextField(20);
        groupField = new JTextField(20);

        // Initialize Buttons
        createUserButton = new JButton("Create User");
        createUserGroupButton = new JButton("Create Group");
        viewUserButton = new JButton("Show User View");
        verifyUniqueButton = new JButton("Verify Valid User & Group IDs");
        getLastUpdatedUserButton = new JButton("Get Last updated User");

        // Button Functionality Start
        createUserButton.addActionListener(e -> {
            newUser = userField.getText();
            if(newUser.length() !=0 && newUser.matches(alphaNumeric)) {
                addUser(newUser);
            }
            else {
                JOptionPane.showMessageDialog(popupPane, "Error: Invalid input. Please enter a valid alphanumeric name.");
            }
        });

        createUserGroupButton.addActionListener(e -> {
            DefaultMutableTreeNode temp = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            newGroup = groupField.getText();
            if(newGroup.length() !=0 && newGroup.matches(alphaNumeric)) {
                User person = searchUser(temp.getUserObject().toString());
                addGroup(newGroup, person);
            }
            else {
                JOptionPane.showMessageDialog(popupPane, "Error: Invalid input. Please enter a valid alphanumeric name.");
            }

            
        });
        
        viewUserButton.addActionListener(e -> {
            DefaultMutableTreeNode temp = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            User person = searchUser(temp.getUserObject().toString());
            UserDisplay showUserDisplay = new UserDisplay(person);
            if(!openPanels.contains(showUserDisplay))
                openPanels.add(showUserDisplay);

        });

        getLastUpdatedUserButton.addActionListener(e -> {
            User temp = null;
            long latest = 0;
            for(int i = 0; i < userList.size(); i++) {
                if(userList.get(i).getLastUpdateTime() > latest) {
                    temp = userList.get(i);
                    latest = userList.get(i).getLastUpdateTime();
                }
            }
            
            JOptionPane.showMessageDialog(popupPane, "User with who conducted the most recent update: " + temp.getUID());
        });
        // Button Functionality End

        userPanel.add(userField);
        userPanel.add(createUserButton);
        
        // Makes a Panel for group Fields
        groupPanel.add(groupField);
        groupPanel.add(createUserGroupButton);

        // Makes a Panel for Validation Button and Last Updated User
        verifyLastPanel.add(verifyUniqueButton);
        verifyLastPanel.add(getLastUpdatedUserButton);
        
        // Creates Panel with UserPanels and GroupPanels together.
        topRightPanel.add(userPanel);
        topRightPanel.add(groupPanel);
        topRightPanel.add(viewUserButton);
        topRightPanel.add(verifyLastPanel);

    }

    /*
     * Creates Bottom Right Layout and implements functionalities for the buttons.
     * Contains displayTotalUserButton, displayTotalGroupButton, displayTotalMessageButton, and displayPosRatioButton.
     */
    private void initBottomRightLayout() {
        bottomRightLayout = new GridLayout(2, 2, 30, 20);
        bottomRightPanel = new JPanel(bottomRightLayout);
        bottomRightPanel.setSize(290, 200);
        
        displayTotalUserButton = new JButton("Show User Total");
        displayTotalGroupButton = new JButton("Show Group Total");
        displayTotalMessageButton = new JButton("Show Message Total");
        displayPositiveMessageRatioButton = new JButton("Show Positive Percentage");

        // Button Functionality Start
        displayTotalUserButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(popupPane, "Total Users: " + userList.size());
        });

        displayTotalGroupButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(popupPane, "Total Groups: " + groupList.size());
        });

        displayTotalMessageButton.addActionListener(e -> {
            DefaultMutableTreeNode temp = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            User person = searchUser(temp.getUserObject().toString());
            
            TotalButtonCalculator visitor = new TotalButtonCalculator();

            person.accept(visitor);
            JOptionPane.showMessageDialog(popupPane, "Total Messages in " + person.getUID() + "'s Feed: " + visitor.visit(person));
        });

        displayPositiveMessageRatioButton.addActionListener(e -> {
            DefaultMutableTreeNode temp = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            User person = searchUser(temp.getUserObject().toString());

            TotalButtonCalculator visitButton = new TotalButtonCalculator();
            PositiveRatioCalculator visitPositive = new PositiveRatioCalculator();
            person.accept(visitButton);
            person.accept(visitPositive);

            double percentage = 100 * ((double)visitPositive.visit(person))/ ((double)visitButton.visit(person));
            JOptionPane.showMessageDialog(popupPane, "Percentatge of positive messages that " + person.getUID() +" sent: " + percentage + "%");
        });
        // Button Functionality End

        bottomRightPanel.add(displayTotalUserButton);
        bottomRightPanel.add(displayTotalGroupButton);
        bottomRightPanel.add(displayTotalMessageButton);
        bottomRightPanel.add(displayPositiveMessageRatioButton);
    }

    /*
     * Gets arrayList of opened User JFrames. Updates user frames in runtime.
     */
    public ArrayList<JFrame> getOpenPanels() {
        return openPanels;
    }

    public ArrayList<User> getUsers() {
        return userList;
    }

    /*
     * Adds user based on what group is currently selected.
     */
    private void addUser(String username) {
        DefaultMutableTreeNode userNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        String temp = userNode.getUserObject().toString();
        System.out.println(temp);
        UserGroup tempG = searchGroup(temp);

        if(groupList.contains(tempG)) { // TODO
            User person = new User(username);
            person.setGroupName(tempG.getGroupID());
            tempG.addUser(person);  //TODO

            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(person.getUID());
            userNode.add(newNode);

            treeModel = (DefaultTreeModel) tree.getModel();
            treeModel.reload();  
            userList.add(person);    
            }
        else {
            JOptionPane.showMessageDialog(popupPane, "Error: Not a valid group.");
        }

    }

    /*
     * Creates group and adds selected user into the group.
     */
    private void addGroup(String groupName, User person) {
        UserGroup group = new UserGroup(groupName, person);
        groupList.add(group);
        person.setGroupName(groupName);

        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(person.getUID());
        DefaultMutableTreeNode userNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        userNode.setUserObject(groupName);
        userNode.add(newNode);

        treeModel = (DefaultTreeModel) tree.getModel();
        treeModel.reload();

    }

    /*
     * Conducts search for a user.
     */
    public User searchUser(String uid) {
        int index = -1;
        for(int i = 0; i < userList.size(); i++) {
            if(userList.get(i).toString().contains(uid)) {
                index = i;
                break;
            }

        }
        return userList.get(index);
    }

    /*
     * Conducts searching for a userGroup.
     */
    private UserGroup searchGroup(String uid) {
        int index = 0;
        for(int i = 0; i < groupList.size(); i++) {
            if(groupList.get(i).toString().contains(uid)) {
                index = i;
                break;
            }

        }
        return groupList.get(index);
    }
}
