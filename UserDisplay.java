import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


import java.awt.Color;
import java.awt.FlowLayout;

public class UserDisplay extends JFrame {
    
    // Upper Panel
    private JPanel userFollowPanel;
    private JPanel displayPane;

    private JTextPane userIDArea;
    private StyledDocument doc;
    private SimpleAttributeSet center;

    private JButton followButton;

    private JList followingViewer;
    private JScrollPane followPane;

    // Lower Panel
    private JPanel messageTweetPanel;
    private JList viewNewsFeed;
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
        this.getContentPane().getInsets().set(50,50,50,50);
        this.setResizable(false);
        this.setVisible(true);

        // Initialize Text and Buttons
        userIDArea = new JTextPane();
        userIDArea.setText("User: " + user.getUID());
        userIDArea.setBackground(getBackground());
        doc = userIDArea.getStyledDocument();
        center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        followButton = new JButton("Follow User");

        // Applies Name to TextArea
        userIDArea.setEditable(false);

        followingViewer = new JList();
        followPane = new JScrollPane(followingViewer,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        followPane.setBounds(300, 300, 300, 200);
        
        followingViewer.setBackground(Color.WHITE);
        followPane.setBackground(Color.WHITE);

        messageInput = new JTextField(50);
        //messageInput.setBounds(30, 30, 30, 30);
        postMessageButton = new JButton("Post Message");

        viewNewsFeed = new JList();

        viewNewsFeed.setBackground(Color.WHITE);

        // Create first row

        userFollowPanel.add(userIDArea);
        userFollowPanel.add(followButton);

        displayPane.add(userFollowPanel);

        displayPane.add(followingViewer);
        // Create Next Row

        messageTweetPanel.add(messageInput);
        messageTweetPanel.add(postMessageButton);

        displayPane.add(messageTweetPanel);
        displayPane.add(viewNewsFeed);

        this.add(displayPane); 
    }

    
}
