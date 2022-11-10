import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class Admin extends JFrame {
    
    private static Admin instance = null;

    private JPanel upperPanelRegion;
    private JPanel lowerPanelRegion;
    private JPanel leftPanelRegion;

    private JButton createUserButton;
    private JButton createUserGroupButton;
    private JButton showUserViewButton;
    private JButton displayTotalUserButton;
    private JButton displayTotalGroupButton;
    private JButton positiveMessageRatioButton;
    private JButton displayTotalMessageCount;

    private JTree treeStructure;
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

    private void initComponents() {
        upperPanelRegion = new JPanel();
        lowerPanelRegion = new JPanel();
        leftPanelRegion = new JPanel();

        createUserButton = new JButton();
        createUserGroupButton = new JButton();
        showUserViewButton = new JButton();
        
        displayTotalUserButton = new JButton();
        displayTotalGroupButton = new JButton();
        displayTotalMessageCount = new JButton();
        positiveMessageRatioButton = new JButton();

        root = new DefaultMutableTreeNode();
        treeStructure = new JTree(root);
    }
}
