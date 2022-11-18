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
        // Initialize SideBySide Panel
        userFollowPanel = new JPanel(new FlowLayout());
        messageTweetPanel = new JPanel(new FlowLayout());
        displayPane = new JPanel();

        // Initilize the JFrame
        displayPane.setLayout(new BoxLayout(displayPane, BoxLayout.Y_AXIS));
        this.setSize(800, 600);
        //this.getContentPane().getInsets().set(50,50,50,50);
        this.setResizable(false);
        this.setVisible(true);

        // Initialize Text and Buttons
        userIDArea = new JTextPane();
        userIDArea.setText("User: " + user.getUID() + "   |||   Group: " + user.getGroupID());
        userIDArea.setBackground(getBackground());
        doc = userIDArea.getStyledDocument();
        center = new SimpleAttributeSet();
        //StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        //doc.setParagraphAttributes(0, doc.getLength(), center, false);

        followUserTextField = new JTextField();
        followUserTextField.setText("Input user ID to follow.");
        followButton = new JButton("Follow User");

        // Applies Name to TextArea
        userIDArea.setEditable(false);

        //Establishes JList to display Followers.
        followerViewer = new JList<>();
        followerViewer.setModel(followModel);

        followPane = new JScrollPane(followerViewer,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        followPane.setBounds(0, 0, 300, 0);
        
        followerViewer.setBackground(Color.WHITE);
        followPane.setBackground(Color.WHITE);

        crowdFollow();

        // FollowButton Function Start
        followButton.addActionListener(e -> {
            String userToSearch = followUserTextField.getText();
            User toFollow = search(userToSearch);
            user.followUser(toFollow);

            followModel.addElement(toFollow);
            
            //updateFollowList(toFollow);
        });

        messageInput = new JTextField(50);
        messageInput.setText("Input Message");
        //messageInput.setBounds(30, 30, 30, 30);
        
        crowdFeed();
        // Start 2nd Half of Frame
        postMessageButton = new JButton("Post Message");

        postMessageButton.addActionListener(e -> {
            String tweet = messageInput.getText();
            Message newText = new Message(user, tweet);
            user.getNewsFeed().sendMessage(newText);

            newsModel.addElement(newText.toString());

            updateFeed(newText);

        }); //TODO



        viewNewsFeed = new JList<>();
        viewNewsFeed.setModel(newsModel);

        newsPane = new JScrollPane(viewNewsFeed, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        newsPane.setBounds(0, 0, 300, 0);

        viewNewsFeed.setBackground(Color.WHITE);

        // Create first row

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
    private void updateFollowers() {
        for(int i = 0; i < user.getFollowerList().size(); i++) {
            ((UserDisplay) display).update((User)user.getFollowerList().get(i)).getNewsFeed().getLatestMessage();
        }
    }
    */

    private User search(String userToSearch) {
        User tempPerson = Admin.getInstance().searchUser(userToSearch);

        return tempPerson;
    }

    private void crowdFeed() {
        int temp = user.getNewsFeed().getFeedList().size();
        for(int i = 0; i < temp; i++)
            {
                newsModel.addElement(user.getNewsFeed().getFeedList().get(temp - i - 1).toString());
            }
    }
    private void crowdFollow() {
        int temp = user.getFollowingList().size();
        for(int i = 0; i < temp; i++)
            {
                followModel.addElement(user.getFollowingList().get(i));
            }

    }

    private void updateFollowList(User str) {
        for(int i = 0; i < Admin.getInstance().getOpenPanels().size(); i++){
            if(user.getFollowerList().contains(((UserDisplay)Admin.getInstance().getOpenPanels().get(i)).getUser())) {
                ((UserDisplay)Admin.getInstance().getOpenPanels().get(i)).getFollowModel().addElement(str);
            }
        }
    } 

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

    public DefaultListModel<String> getNewsModel() {
        return newsModel;
    }
    public DefaultListModel<User> getFollowModel() {
        return followModel;
    }
}
