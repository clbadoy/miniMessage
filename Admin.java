import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;


import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;


import java.util.ArrayList;


public class Admin extends JFrame implements AdminVisitor {
    
    private static Admin instance = null;

    private GridBagConstraints c;
    // Panel for Right side of Admin Panel
    private JPanel rightPanel;

    // Top Right Panels, buttons, text heirarchy
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
    private JScrollPane pane;
    private JTree tree;
    private DefaultMutableTreeNode root;

    private ArrayList<User> userList = new ArrayList<User>();
    private ArrayList<UserGroup> groupList = new ArrayList<UserGroup>();


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
        root = new DefaultMutableTreeNode("Root");
        tree = new JTree(root);

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

        root = new DefaultMutableTreeNode();
        tree = new JTree(root);


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
        
        displayTotalUserButton = new JButton("SHow User Total");
        displayTotalGroupButton = new JButton("Show Group Total");
        displayTotalMessageButton = new JButton("Show Message Total");
        displayPositiveMessageRatioButton = new JButton("Show Positive Percentage");

        bottomRightPanel.add(displayTotalUserButton);
        bottomRightPanel.add(displayTotalGroupButton);
        bottomRightPanel.add(displayTotalMessageButton);
        bottomRightPanel.add(displayPositiveMessageRatioButton);
    }

    @Override
    public void visit(User user) {
        // TODO Auto-generated method stub
        
    }
}
