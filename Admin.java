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

    private JTextField userField;
    private JTextField groupField;

    private JButton createUserButton;
    private JButton createUserGroupButton;
    private JButton viewUserButton;

/*  private JPanel upperPanelRegion;
    private JPanel lowerPanelRegion;
    private JPanel leftPanelRegion; */
    
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

        // Initialize Popup
        /*popUp = new JFrame("Pop Up");
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400); */

        // Initialize right side of UI
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Initialize Top Right Layout.
        initTopRightLayout();

        // Initialize Bottom Right Layout
        initBottomRightLayout();

        // Setup Right side of the UI
        rightPanel.add(topRightPanel);
        rightPanel.add(Box.createVerticalStrut(300));
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

    private void initTopRightLayout() {
        // Create 
        topRightLayout = new GridLayout(3, 0, 10, 10);
        topRightPanel = new JPanel(topRightLayout);

        userPanel = new JPanel(new FlowLayout());
        groupPanel = new JPanel(new FlowLayout());
        topRightPanel = new JPanel(topRightLayout);

        userField = new JTextField(20);
        groupField = new JTextField(20);

        // Initialize Buttons
        createUserButton = new JButton("Create User");
        createUserGroupButton = new JButton("Create Group");
        viewUserButton = new JButton("Show User View");

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
        
        
        
        /*(e -> {
            newUser = userField.getText();
    //        if(newUser.length() != 0 && newUser.matches(alphaNumeric)) {
                User aPerson = new User(newUser);
                root.add(new DefaultMutableTreeNode(aPerson));
                treeModel = (DefaultTreeModel) tree.getModel();
                treeModel.reload();
      //      }
        //    else {
          //      JOptionPane.showMessageDialog(popupPane, "Error: Invalid input. Please make user alphanumeric.");
            }
        //} 
        
        ); */

        viewUserButton.addActionListener(e -> {
            DefaultMutableTreeNode temp = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            User person = searchUser(temp.getUserObject().toString());
            UserDisplay showUserDisplay = new UserDisplay(person);

        }); //TODO
        // Button Functionality End

        userPanel.add(userField);
        userPanel.add(createUserButton);
        
        // Makes a Panel for group Fields
        groupPanel.add(groupField);
        groupPanel.add(createUserGroupButton);
        
        // Creates Panel with UserPanels and GroupPanels together.
        topRightPanel.add(userPanel);
        topRightPanel.add(groupPanel);
        topRightPanel.add(viewUserButton);

    }

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
            displayPositiveMessageRatioButton.setText(temp.getUserObject().toString());
        });
        //TODO
        

        // Button Functionality End

        bottomRightPanel.add(displayTotalUserButton);
        bottomRightPanel.add(displayTotalGroupButton);
        bottomRightPanel.add(displayTotalMessageButton);
        bottomRightPanel.add(displayPositiveMessageRatioButton);
    }


    public void addUser(String username) {
        User person = new User(username);

        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(person.getUID());
        DefaultMutableTreeNode userNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        userNode.add(newNode);
        //root.add(newNode);

        treeModel = (DefaultTreeModel) tree.getModel();
        treeModel.reload();

        userList.add(person);

    }

    private User searchUser(String uid) {
        int index = -1;
        for(int i = 0; i < userList.size(); i++) {
            if(userList.get(i).toString().contains(uid)) {
                index = i;
                break;
            }

        }
        return userList.get(index);
    }
}
