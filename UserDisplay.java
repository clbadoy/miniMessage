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
 *  This is the GUI that allows the user to interact with several functions of a mini Twitter.
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

public class UserDisplay extends JFrame {
    
    // Upper Panel
    private JPanel userFollowPanel;
    private JPanel displayPane;

    private JTextPane userIDArea;
    private JTextField followUserTextField;
    private StyledDocument doc;
    private SimpleAttributeSet center;

    private JButton followButton;

    private JList<User> followerViewer;
    private DefaultListModel<User> followModel = new DefaultListModel<>();
    private JScrollPane followPane;

    // Lower Panel
    private JPanel messageTweetPanel;
    private JList<String> viewNewsFeed;
    private DefaultListModel<String> newsModel = new DefaultListModel<>();
    private JScrollPane newsPane;

    private JTextField messageInput;
    private JButton postMessageButton;

    
    private User user;

    public UserDisplay(User person) {
        user = person;

        initComponents();

    }

    private void initComponents() {
        // Initialize Panels with items in the same row.
        userFollowPanel = new JPanel(new FlowLayout());
        messageTweetPanel = new JPanel(new FlowLayout());
        displayPane = new JPanel();

        // Initilize the JFrame
        displayPane.setLayout(new BoxLayout(displayPane, BoxLayout.Y_AXIS));
        this.setSize(800, 600);
        this.setResizable(false);
        this.setVisible(true);

        // Initialize Text and Buttons for the header.
        // Contains information about the user along with a text field to enter a user to follow.
        userIDArea = new JTextPane();
        userIDArea.setText("User: " + user.getUID() + "   |||   Group: " + user.getGroupID());
        userIDArea.setBackground(getBackground());
        userIDArea.setEditable(false);
        doc = userIDArea.getStyledDocument();
        center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        followUserTextField = new JTextField();
        followUserTextField.setText("Input user ID to follow.");
        followButton = new JButton("Follow User");

        // Establishes JList to display Followers on JFrame.
        followerViewer = new JList<>();
        followerViewer.setModel(followModel);

        followPane = new JScrollPane(followerViewer,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        followPane.setBounds(0, 0, 300, 0);
        
        followerViewer.setBackground(Color.WHITE);
        followPane.setBackground(Color.WHITE);

        crowdFollow();

        /* 
         * FollowButton Function Start:
         * Takes user String from textField and searches for user.
         * Then updates JList field to display who the current user is following.
         */ 
        followButton.addActionListener(e -> {
            String userToSearch = followUserTextField.getText();
            User toFollow = search(userToSearch);
            user.followUser(toFollow);

            followModel.addElement(toFollow);
        });
        
        // When opening the user feed, it loads the information on who the user is currently following.
        crowdFeed();

        // Start Lower Half of JFrame

        // Initializes Textfield for user to enter text to send a message.
        messageInput = new JTextField(50);
        messageInput.setText("Input Message");
        postMessageButton = new JButton("Post Message");

        /*
         * postMessageButton Functionality
         * 1. Creates new Message Object.
         * 2. Sends the message onto the user's feed.
         * 3. Updates followers' feed.
         */
        postMessageButton.addActionListener(e -> {
            String tweet = messageInput.getText();
            Message newText = new Message(user, tweet);
            user.getNewsFeed().sendMessage(newText);

            newsModel.addElement(newText.toString());

            updateFeed(newText);

        });


        // Creates JList to display the messages.
        viewNewsFeed = new JList<>();
        viewNewsFeed.setModel(newsModel);

        newsPane = new JScrollPane(viewNewsFeed, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        newsPane.setBounds(0, 0, 300, 0);

        viewNewsFeed.setBackground(Color.WHITE);

        // Create first row of items onto JFrame.

        userFollowPanel.add(userIDArea);
        userFollowPanel.add(followUserTextField);
        userFollowPanel.add(followButton);

        displayPane.add(userFollowPanel);

        displayPane.add(followPane);
        // Create Next Row

        messageTweetPanel.add(messageInput);
        messageTweetPanel.add(postMessageButton);

        displayPane.add(messageTweetPanel);
        displayPane.add(newsPane);

        this.add(displayPane); 
    }

    /*
     * Calls Admin instance to search for a specifc user given the JTextField
     * that the user inputted.
     */
    private User search(String userToSearch) {
        User tempPerson = Admin.getInstance().searchUser(userToSearch);

        return tempPerson;
    }

    /*
     * Generates a list of users that the selected user is following upon pressing
     * "View User" in Admin Frame.
     */
    private void crowdFeed() {
        int temp = user.getNewsFeed().getFeedList().size();
        for(int i = 0; i < temp; i++)
            {
                newsModel.addElement(user.getNewsFeed().getFeedList().get(temp - i - 1).toString());
            }
    }

    /*
     * Generates recent messages that the selected user received/sent upon pressing
     * "View User" in Admin Frame.
     */
    private void crowdFollow() {
        int temp = user.getFollowingList().size();
        for(int i = 0; i < temp; i++)
            {
                followModel.addElement(user.getFollowingList().get(i));
            }

    }
    /*
     * Method that conducts live updates to other users' followList IF a user follows the sender.
     * Unused.
     */
    private void updateFollowList(User str) {
        for(int i = 0; i < Admin.getInstance().getOpenPanels().size(); i++){
            if(user.getFollowerList().contains(((UserDisplay)Admin.getInstance().getOpenPanels().get(i)).getUser())) {
                ((UserDisplay)Admin.getInstance().getOpenPanels().get(i)).getFollowModel().addElement(str);
            }
        }
    } 

    /*
     * Method that conducts live updates to other users' feedList IF a user follows the sender.
     */
    private void updateFeed(Message text) {
        for(int i = 0; i < Admin.getInstance().getOpenPanels().size(); i++){
            if(user.getFollowerList().contains(((UserDisplay)Admin.getInstance().getOpenPanels().get(i)).getUser())) {
                ((UserDisplay)Admin.getInstance().getOpenPanels().get(i)).getNewsModel().addElement(text.toString());
            }
        }
    } 

    public User getUser() {
        return user;
    }

    /*
     * Method that gets newsModel/followModel. Used for updating other feeds in runtime.
     */
    public DefaultListModel<String> getNewsModel() {
        return newsModel;
    }
    public DefaultListModel<User> getFollowModel() {
        return followModel;
    }
}
